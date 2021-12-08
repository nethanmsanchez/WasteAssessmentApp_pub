package com.example.wasteassessment2;

public class Entry {
    private double count;
    private double weight;
    private double volume;

    public Entry(double count, double weight, double volume){
        this.count = count;
        this.weight = weight;
        this.volume = volume;
    }

    /* ===== Set Methods ===== */
    public void setCount(double count) {
        this.count = count;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    /* ===== Get Methods ===== */
    public double getCount() {
        return count;
    }

    public double getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }
}
