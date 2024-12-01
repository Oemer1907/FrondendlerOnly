package com.bestfit.demo.Models;

public class Plan {
    private String category;
    private String machine;
    private int sets;
    private int reps;

    public Plan(String category, String machine, int sets, int reps) {
        this.category = category;
        this.machine = machine;
        this.sets = sets;
        this.reps = reps;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
