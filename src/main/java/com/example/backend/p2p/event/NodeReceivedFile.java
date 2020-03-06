package com.example.backend.p2p.event;


import com.example.backend.p2p.state.Node;
import com.example.backend.p2p.state.P2PState;
import com.example.backend.simulator.Event;

public class NodeReceivedFile extends Event {
	
	private Node source;
	private Node destination;
	private double nextExecuteTime;
	private double value;
	
	public NodeReceivedFile(P2PState state, Node source, Node destination, double time, double value) {
		super(state);
		super.eventDescription = "Node received file";
		this.source = source;
		this.destination = destination;
		super.eventUserDescription = destination.toString();
		super.nodeDestination = destination.toString();
		super.executeTime = time;
		this.value = value;
	}
    @Override
    public void runEvent() {
    	P2PState s = (P2PState) state;
    	source.addToMap(value);
    	System.out.println(value);
    	s.updateState(this);
    	//int nextNrOfNodes = s.getNextNrOfNodes();
		nextExecuteTime = s.getElapsedTime() + s.getNextTime();
		eventQueue.addEvent(new NodeSendFile(s,source,destination,nextExecuteTime));
		/*
		for (int i = 0; i < 6; i++){

			nextExecuteTime = s.getElapsedTime() + s.getNextTime();;
    		eventQueue.addEvent(new NodeSendFile(s, s.getNode(0), s.getNode(2), nextExecuteTime));
		}
		*/
    	//eventQueue.addEvent(new NodeSendFile(s, node, 0.01));
    }
}
