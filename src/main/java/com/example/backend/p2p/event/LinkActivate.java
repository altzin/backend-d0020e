package com.example.backend.p2p.event;

import com.example.backend.p2p.state.Link;
import com.example.backend.p2p.state.Node;
import com.example.backend.p2p.state.P2PState;
import com.example.backend.simulator.Event;
import java.util.*;

/**
 * This class is used when a node sends a file
 *
 */
public class LinkActivate extends Event {
    private Link link;
    private Node source;
    private Node destination;
    private double value;

    /**
     * Creates an instance of LinkActivate event
     * @param state
     * @param link
     * @param source
     * @param destination
     * @param time
     */
    public LinkActivate(P2PState state,Link link, Node source, Node destination, double time){
        super(state);
        this.link = link;
        super.eventDescription = "Link Activate";
        this.source = source;
        this.destination = destination;
        super.nodeDestination = destination.toString();
        super.eventUserDescription = source.toString();
        super.executeTime = time;
    }

    @Override
    public void runEvent() {
        P2PState s = (P2PState) state;
        s.updateState(this);
        value = link.getNext();
        eventQueue.addEvent(new NodeReceivedFile(s, source, destination, s.getElapsedTime()+0.01, value));
    }
}
