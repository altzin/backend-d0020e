package com.example.backend.p2p.state;
import com.example.backend.p2p.time.UniformRandomStream;

import java.util.Random;

public class Link {
    private double weight;
    private UniformRandomStream rand;
    //private Random rand;
    private Node source;
    private Node destination;

    /*
    public Link(double weight, Node source, Node destination){
        this.weight = weight;
        this.source = source;
        this.destination = destination;
    }
     */

    /**
     * Weight is currently not in use but is planned to be implemented
     *
     * @param source
     * @param destination
     */
    public Link(Node source, Node destination){
        this.rand = new UniformRandomStream(-0.1,0.1);
        this.source = source;
        this.destination = destination;
    }

    public double getWeight(){return weight;}

    /**
     *
     * @return next value that is added to a Nodes mAP value
     */
    public double getNext(){
        return rand.next();
        //return rand.nextGaussian() *0.2;
    }
}
