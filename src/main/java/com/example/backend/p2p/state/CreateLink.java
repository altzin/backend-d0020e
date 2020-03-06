package com.example.backend.p2p.state;

public class CreateLink {
    /**
     * This class creates new links
     */

    private double weight;
    private Node source;
    private Node destination;

    /**
     * Creates the first link
     */
    public CreateLink(double weight, Node source, Node destination){
        this.weight = weight;
        this.source = source;
        this.destination = destination;
    }
    public CreateLink(Node source, Node destination){
        this.weight = 1;
        this.source = source;
        this.destination = destination;
    }

    /**
     *
     * @param source
     * @param destination
     * @return Link
     */
    public Link newLink(Node source, Node destination){ return new Link(source, destination);
    }
}
