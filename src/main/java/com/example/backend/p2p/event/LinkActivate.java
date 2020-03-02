package com.example.backend.p2p.event;

import com.example.backend.p2p.state.Link;
import com.example.backend.p2p.state.Node;
import com.example.backend.p2p.state.P2PState;
import com.example.backend.simulator.Event;

public class LinkActivate extends Event {
    private Link link;
    private Node source;
    private Node destination;
    private double value;

    public LinkActivate(P2PState state,Link link, Node source, Node destination, double time){
        super(state);
        this.link = link;
        super.eventDescription = "Link Activate";
        this.source = source;
        this.destination = destination;
        super.eventUserDescription = source.toString();
        super.executeTime = time;
    }

    @Override
    public void runEvent() {
        P2PState s = (P2PState) state;
        s.updateState(this);
        value = link.getWeight();
        value = value * s.getNextMap();
        source.addToMap(0.1);
        eventQueue.addEvent(new NodeReceivedFile(s, source, destination, 0.01));
    }
}
