package com.example.backend.p2p.event;


import com.example.backend.p2p.state.Node;
import com.example.backend.p2p.state.P2PState;
import com.example.backend.simulator.Event;

import java.util.ArrayList;

/**
 * Creates a new NodeReceivedFile class
 * This class is used when a node has received a file
 * and simulates that the receiving node changes the mAP value
 * for the sending node.
 */
public class NodeReceivedFile extends Event {
	
	private Node source;
	private Node destination;
	private double nextExecuteTime;
	private double value;
	private int nrOfNodesSend;
	private int newDestination;

	/**
	 * Creates a new instance of NodeReceivedFile class
	 * @param state
	 * @param source
	 * @param destination
	 * @param time
	 * @param value
	 */
	public NodeReceivedFile(P2PState state, Node source, Node destination, double time, double value) {
		super(state);
		super.eventDescription = "Node received file";
		this.source = source;
		this.destination = destination;
		super.eventUserDescription = destination.toString();
		super.nodeDestination = source.toString();
		super.executeTime = time;
		this.value = value;
	}
    @Override
	/**
	 * Executes NodeReceivedFile event and creates a new
	 * NodeSendFile event for the source node.
	 *
	 */
    public void runEvent() {
		P2PState s = (P2PState) state;
		source.addToMap(value);
		s.updateState(this);
		nextExecuteTime = s.getElapsedTime() + s.getNextTime();
		nrOfNodesSend = s.getNextNrOfNodes();
		newDestination = s.getNextNrOfNodes();
		while (newDestination == source.toInt()) {
			newDestination = s.getNextNrOfNodes();
		}
		nextExecuteTime = s.getElapsedTime()+ 0.1;
		source.addToEvent();
		eventQueue.addEvent(new NodeSendFile(s, source, s.nodesList[newDestination], nextExecuteTime));
	}
}





		/*
		for (int i = 0; i < 6; i++){

			nextExecuteTime = s.getElapsedTime() + s.getNextTime();;
    		eventQueue.addEvent(new NodeSendFile(s, s.getNode(0), s.getNode(2), nextExecuteTime));
		}
		*/
    	//eventQueue.addEvent(new NodeSendFile(s, node, 0.01));


