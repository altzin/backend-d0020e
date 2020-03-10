package com.example.backend.p2p.event;

import com.example.backend.p2p.state.Link;
import com.example.backend.p2p.state.Node;
import com.example.backend.p2p.state.P2PState;
import com.example.backend.simulator.Event;

/**
 * This class creates a new event for a Node to send a file to some other node.
 */
public class NodeSendFile extends Event {
	private Node source;
	private Node destination;
	/**
	 * 
	 * @param state
	 * @param source
	 * @param destination
	 * @param time
	 */
    public NodeSendFile(P2PState state, Node source, Node destination, double time){
    	super(state);
        super.eventDescription = "Node send file";
        this.source = source;
        this.destination = destination;
        super.eventUserDescription = source.toString();
        super.nodeDestination = destination.toString();
        super.executeTime = time;
    }

	/**
	 * Executes a Send File event
	 */
	@Override
    public void runEvent() {
    	P2PState s = (P2PState) state;
    	s.updateState(this);
    	double nextExecuteTime = s.getElapsedTime() + 0.01;
    	eventQueue.addEvent(new LinkActivate(s, s.linksList[source.toInt()][destination.toInt()], source, destination, nextExecuteTime));
    }
}