package com.example.backend.p2p.state;

public class Link {
    private double weight;
    private Node source;
    private Node destination;

    public Link(double weight, Node source, Node destination){
        this.weight = weight;
        this.source = source;
        this.destination = destination;
    }
    public Link(Node source, Node destination){
        this.weight = 1;
        this.source = source;
        this.destination = destination;
    }

    public double getWeight(){return weight;}
}
