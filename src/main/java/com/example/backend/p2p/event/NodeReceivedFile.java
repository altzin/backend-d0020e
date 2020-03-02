package com.example.backend.p2p.event;


import com.example.backend.p2p.state.Node;
import com.example.backend.p2p.state.P2PState;
import com.example.backend.simulator.Event;

public class NodeReceivedFile extends Event {
	
	private Node source;
	private Node destination;
	private double nextExecuteTime;
	
	public NodeReceivedFile(P2PState state, Node source, Node destination, double time) {
		super(state);
		super.eventDescription = "Node received file";
		this.source = source;
		this.destination = destination;
		super.eventUserDescription = destination.toString();
		super.executeTime = time;
	}
    @Override
    public void runEvent() {
    	P2PState s = (P2PState) state;
    	//source.addToMap(0.1);
    	s.updateState(this);
    	int nextNrOfNodes = s.getNextNrOfNodes();
		/*
		for (int i = 0; i < 6; i++){

			nextExecuteTime = s.getElapsedTime() + s.getNextTime();;
    		eventQueue.addEvent(new NodeSendFile(s, s.getNode(0), s.getNode(2), nextExecuteTime));
		}
		*/
    	//eventQueue.addEvent(new NodeSendFile(s, node, 0.01));
    }
}
