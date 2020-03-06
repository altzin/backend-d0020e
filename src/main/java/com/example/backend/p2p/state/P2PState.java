package com.example.backend.p2p.state;

import com.example.backend.p2p.time.P2PTime;
import com.example.backend.simulator.Event;
import com.example.backend.simulator.EventQueue;
import com.example.backend.simulator.SimState;


public class P2PState extends SimState {
	
    //Constants
    private final int nrOfNodes;
	private final double TIME_SIM_STOP;
	//private final double ARRIVAL_SPEED;
	private final long TIME_SEED;

    public Node[] nodesList;
    public Link[][] linksList;
    private CreateNode nodeSpawn;
    private CreateLink linkSpawn;

	// Event descriptions
	private String eventDescription;
	private String nodeWhoPerformedEvent;
 	private String nodeDestination;


	private Node source;
	private Node destination;
	private double weight;

	private P2PTime p2pTime;

	/**
	 * Construct an instance of P2PTime
	 * 
	 * @param ARRIVAL_SPEED
	 * @param TIME_SEED
	 * @param nrOfNodes
	 * @param eventQueue
	 * @param TIME_SIM_STOP
	 */
    public P2PState (double ARRIVAL_SPEED, long TIME_SEED, int nrOfNodes, EventQueue eventQueue, double TIME_SIM_STOP){
    	super(eventQueue);
    	//this.ARRIVAL_SPEED = ARRIVAL_SPEED;
        this.TIME_SEED = TIME_SEED;
        this.nodesList = new Node[nrOfNodes];
        this.linksList = new Link[nrOfNodes][nrOfNodes];
        this.TIME_SIM_STOP = TIME_SIM_STOP;
        //this.nodesList = new Node[nrOfNodes];
        this.nodeSpawn = new CreateNode();
        this.linkSpawn = new CreateLink(weight, source, destination);
        this.nrOfNodes = nodesList.length;
		this.p2pTime = new P2PTime(ARRIVAL_SPEED,TIME_SEED, nrOfNodes);
    }

    /**
     * 
     * @return New node with incremented id
     */
    public Node createNewNode(){
        return nodeSpawn.newNode();
    }

	/**
	 * Spawns a new link
	 *
	 * @param weight
	 * @param source
	 * @param destination
	 * @return
	 */
	public Link createNewLink(double weight, Node source, Node destination){return linkSpawn.newLink(source, destination);}

    @Override
    public void updateState(Event event) {

		// DESCRIPTION OF EVENT

		// Updates event that occured
		eventDescription = event.getEventDescription();

		// Updates which node who performed the event.
		nodeWhoPerformedEvent = event.getEventUserDescription();

		//Updates destination
		nodeDestination = event.getNodeDestination();
		// Sets time to be the time that the event was executed.
		elapsedTime = event.getExTime();

		setChanged();
		notifyObservers();
    }
    
    @Override
    public void runSim() {
    	startSimulator();
    }
    
	/**
	 * 
	 * @return type of event
	 */
	public String getEventDescription() {
		return eventDescription;
	}
	
	/**
	 * 
	 * @return the customer who did an event
	 */
	public String getNodeWhoPerformedEvent() {
		return nodeWhoPerformedEvent;
	}
	public String getNodeDestination(){
		return nodeDestination;
	}
	
	public Node getNode(int id) {
		return nodesList[id];
	}

	public double getNodeMap(int id){return nodesList[id].getMap();}
	/**
	 * @return timestamp for events
	 */
	public double getElapsedTime() {
		return elapsedTime;
	}

	public int getNextNrOfNodes(){return p2pTime.nrSendToNodes();}

	public double getNextMap(){return p2pTime.nextMap();}

	public double getNextTime(){return p2pTime.timeSendNextFile();}

	public int getNrOfNodes() {
		return nrOfNodes;
	}
}