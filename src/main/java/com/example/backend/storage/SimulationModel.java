package com.example.backend.storage;

public class SimulationModel {
    private String nodes;
    private String runtime;
    private String seed;
    private String arrivalSpeed;

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getArrivalSpeed() {
        return arrivalSpeed;
    }

    public void setArrivalSpeed(String arrivalSpeed) {
        this.arrivalSpeed = arrivalSpeed;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }
}
