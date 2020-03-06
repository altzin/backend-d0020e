package com.example.backend.p2p.state;
import com.example.backend.p2p.time.UniformRandomStream;

import java.util.Random;

public class Link {
    private double weight;
    private Node source;
    private Node destination;

    //public Link(double weight, Node source, Node destination){
        //this.weight = weight;
       // this.source = source;
        //this.destination = destination;
  //  }
    public Link(Node source, Node destination){
        UniformRandomStream rand = new UniformRandomStream(-0.2,0.2,1234567);
        this.weight = rand.next();
        this.source = source;
        this.destination = destination;
    }

    public double getWeight(){return weight;}
    public double getNext(){
        Random normDist = new Random();
        return weight + normDist.nextGaussian();
    }
}
