package com.example.backend.p2p.state;

public class Node{

    private int id;
    private double map;
    private double mean_map;
    private double sum_map;
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
        this.sum_map = map;
        this.mean_map = map;
        this.nrOfEvents = 1;
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
     check so sum_map stays in range between 0-1, value is more than what is added
     */
    public void addToMap(double value){
        if (this.map + value >= 1){
            this.sum_map += (1 - map);
            this.map = 1;

        }
        else if (this.map + value <= 0){
            this.sum_map -= map;
            this.map = 0;

        }
        else {
            this.sum_map += value;
            this.map = this.map + value;

        }
    }
    public void addToEvent(){ nrOfEvents++;}
    public int getEventCounter(){
        return nrOfEvents;
    }
    public double getMeanMap(){return sum_map/nrOfEvents; }
}