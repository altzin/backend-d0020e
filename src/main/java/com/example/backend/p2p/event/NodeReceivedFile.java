package com.example.backend.p2p.event;


import com.example.backend.p2p.state.Node;
import com.example.backend.p2p.state.P2PState;
import com.example.backend.simulator.Event;

import java.util.ArrayList;

public class NodeReceivedFile extends Event {
	
	private Node source;
	private Node destination;
	private double nextExecuteTime;
	private double value;
	private int nrOfNodesSend;
	
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
    public void runEvent() {
		P2PState s = (P2PState) state;
		source.addToMap(value);
		System.out.println(value);
		s.updateState(this);
		//int nextNrOfNodes = s.getNextNrOfNodes();
		nextExecuteTime = s.getElapsedTime() + s.getNextTime();
		nrOfNodesSend = s.getNextNrOfNodes();
		ArrayList<Integer> destinationContainer = new ArrayList<>();
		int temp = s.getNextNrOfNodes();
		System.out.println("SLUMPAT: " + temp);
//		for (int i = 0; i < nrOfNodesSend; i++) {
//			while (destinationContainer.contains(temp)) {
//				temp = s.getNextNrOfNodes();
//			}
		while (temp == source.toInt()) {
			temp = s.getNextNrOfNodes();
		}
			System.out.println("slumpat:" + temp);
			destinationContainer.add(temp);
			nextExecuteTime = s.getElapsedTime()+ 0.1;
			eventQueue.addEvent(new NodeSendFile(s, source, s.nodesList[temp], nextExecuteTime));
		}
	}





		/*
		for (int i = 0; i < 6; i++){

			nextExecuteTime = s.getElapsedTime() + s.getNextTime();;
    		eventQueue.addEvent(new NodeSendFile(s, s.getNode(0), s.getNode(2), nextExecuteTime));
		}
		*/
    	//eventQueue.addEvent(new NodeSendFile(s, node, 0.01));


