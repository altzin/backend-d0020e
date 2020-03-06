package com.example.backend.p2p.state;

public class Node{

    private int id;
    private double map;
    /**
     * 
     * @param id
     * @param map
     */
    public Node(int id, double map){
        this.id = id;
        this.map = map;
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
        this.map = this.map + value;
    }
}