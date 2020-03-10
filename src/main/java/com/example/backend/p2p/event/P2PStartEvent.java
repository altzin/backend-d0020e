package com.example.backend.p2p.event;


import com.example.backend.p2p.state.P2PState;
import com.example.backend.simulator.StartEvent;

/**
 * This class opens the network and creates the nodes and links.
 */
public class P2PStartEvent extends StartEvent {
    private double nextExecuteTime;
    private int destination;
    /**
     * Creates a new start event
     * @param state
     */
    public P2PStartEvent(P2PState state){
        super(state);
        super.eventDescription = "Network Open";
		super.eventUserDescription = "-";
		super.executeTime = 0.0;
    }

    /**
     * Executes start event.
     *  1.Creates Nodes
     *  2.Creates Links
     *  3.Creates a new Send event for every node
     */
    @Override
	public void runEvent() {
        P2PState s = (P2PState) state;
        s.updateState(this);
        //Creates nodes
        for(int i=0; i<s.getNrOfNodes(); i++){
            s.nodesList[i] = s.createNewNode();
        }
        //Creates Links
        for(int i=0; i < s.getNrOfNodes(); i++){
            for (int j=0; j < s.getNrOfNodes(); j++) {
                s.linksList[i][j] = s.createNewLink(1, s.getNode(i), s.getNode(j));
            }
        }
        //Creates NodeSendFile event for every node
        for(int i=0; i<s.getNrOfNodes(); i++){
            destination = s.getNextNrOfNodes();
            while( i == destination){
                destination = s.getNextNrOfNodes();
            }
            nextExecuteTime = s.getElapsedTime() + s.getNextTime();
            eventQueue.addEvent(new NodeSendFile(s, s.nodesList[i],s.nodesList[destination], nextExecuteTime));
        }
    }
}