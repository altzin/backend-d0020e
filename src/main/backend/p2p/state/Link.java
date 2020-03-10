package com.example.backend.p2p.state;
import com.example.backend.p2p.time.UniformRandomStream;

import java.util.Random;

public class Link {
    private double weight;
    private Node source;
    private Node destination;
    private Random normDist;

    /**
     *  Weight is currently not in use but is planned to be used
     *
     * @param source
     * @param destination
     * @param normDist
     */
    public Link(Node source, Node destination){
        UniformRandomStream rand = new UniformRandomStream(-0.2,0.2,1234567);
        this.weight = rand.next();
        this.source = source;
        this.destination = destination;
        this.normDist = new Random();
    }

    public double getWeight(){return weight;}

    /**
     *
     * @return next value that is added to a Nodes mAP value
     */
    public double getNext(){
        return weight + normDist.nextGaussian();
    }
}
