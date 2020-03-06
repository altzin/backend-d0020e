package com.example.backend.p2p.state;

public class Node{

    private int id;
    private double map;
    private int nrOfEvents;
    
    /**
     *  TODO
     *  add so Map is generated random
     *
     * @param id
     * @param map
     */

    public Node(int id, double map){
        this.id = id;
        this.map = map;
        this.nrOfEvents = 0;
    }
    /**
     * @return Node id String
     */
    public String toString(){
        return Integer.toString(id);
    }
    public int toInt(){return id; }

    public double getMap(){return map;}
    /*
    Todo
     check so map stays in range between 0-1
     */
    public void addToMap(double value){
        if (this.map + value/10 >= 1){
            this.map = 1;
        }
        else if (this.map + value/10 <= 0){
            this.map = 0;
        }
        else {
            this.map = this.map + value/10;
        }
    }
    public void addToEvent(){ this.nrOfEvents++;}
    public int getEventCounter(){
        return nrOfEvents;
    }
}