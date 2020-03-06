package com.example.backend.p2p.event;


import com.example.backend.p2p.state.P2PState;
import com.example.backend.simulator.StartEvent;

public class P2PStartEvent extends StartEvent {
    public P2PStartEvent(P2PState state){
        super(state);
        super.eventDescription = "Network Open";
		super.eventUserDescription = "-";
		super.executeTime = 0.0;
    }
    @Override
	public void runEvent() {
        P2PState s = (P2PState) state;
        s.updateState(this);
        /**
         * Creates the Nodes in the network
         * TODO
         * Maybe put this in the constructor of P2PState instead
         * Fix so all nodes sends file, the last does not send anything now
         * Fix so links are created
         */
        double nextExecuteTime = s.getElapsedTime();
        for(int i=0; i<s.getNrOfNodes(); i++){
            s.nodesList[i] = s.createNewNode();
        //    nextExecuteTime = s.getElapsedTime() + 0.01;
        }
        for(int i=0; i < s.getNrOfNodes(); i++){
            for (int j=0; j < s.getNrOfNodes(); j++) {
                //s.createNewLink(1, s.nodesList[i], s.nodesList[j]);
               // s.createNewLink(1, s.getNode(i), s.getNode(j));
                s.linksList[i][j] = s.createNewLink(1, s.getNode(i), s.getNode(j));
            }

        }
        /*
         * TODO
         * Fix nextExecuteTime
         */
        nextExecuteTime = s.getElapsedTime() + s.getNextTime();
        eventQueue.addEvent(new NodeSendFile(s, s.nodesList[0],s.nodesList[1], nextExecuteTime));
        nextExecuteTime = s.getElapsedTime() + s.getNextTime();
        eventQueue.addEvent(new NodeSendFile(s, s.nodesList[1],s.nodesList[2], nextExecuteTime));
        nextExecuteTime = s.getElapsedTime() + s.getNextTime();
        eventQueue.addEvent(new NodeSendFile(s, s.nodesList[2],s.nodesList[3], nextExecuteTime));
        nextExecuteTime = s.getElapsedTime() + s.getNextTime();
        eventQueue.addEvent(new NodeSendFile(s, s.nodesList[3],s.nodesList[4], nextExecuteTime));
        nextExecuteTime = s.getElapsedTime() + s.getNextTime();
        eventQueue.addEvent(new NodeSendFile(s, s.nodesList[4],s.nodesList[0], nextExecuteTime));
    }
}