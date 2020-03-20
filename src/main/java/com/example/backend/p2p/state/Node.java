package com.example.backend.p2p.state;

public class Node{

    private int id;
    private double map;
    private double mean_map;
    private double sum_map;
    private int nrOfSentFiles;

    /**
     *
     * @param id
     * @param map
     */

    public Node(int id, double map){
        this.id = id;
        this.map = map;
        this.sum_map = map;
        this.mean_map = map;
        this.nrOfSentFiles = 1;
    }
    /**
     * @return Node id String
     */
    public String toString(){
        return Integer.toString(id);
    }

    /**
     *
     * @return Node id int
     */
    public int toInt(){return id; }

    /**
     *
     * @return map value
     */
    public double getMap(){return map;}

    /**
     *  This method adds a value to the mAP and checks so it stays in
     *  the range 0-1.
     * @param value
     */
    public void addToMap(double value){
        if (this.map + value >= 1){
            this.map = 1;
            this.sum_map +=map;

        }
        else if (this.map + value <= 0){
            this.map = 0;
            this.sum_map +=map;

        }
        else {
            this.map = this.map + value;
            this.sum_map +=map;

        }
    }

    /**
     * Adds the counter for how many files a node has sent
     */
    public void addToEvent(){ nrOfSentFiles++;}

    public int getEventCounter(){
        return nrOfSentFiles;
    }
    public double getMeanMap(){return sum_map/nrOfSentFiles; }
}