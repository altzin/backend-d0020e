package com.example.backend.p2p.state;

public class CreateNode{
	/**
	 * This class creates new Nodes
	 */
    private int id;
    private double map;

    /**
     * Creates the first Node
     */
    public CreateNode(){
        id = 0;
        map = 0;
    }
    /**
     * 
     * @return new node with id++
     */
    public Node newNode(){
        return new Node(id++, 0);
    }

    public Node newNode(int i){
        return new Node(id++, i);
    }
}