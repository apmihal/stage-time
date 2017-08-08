package com.andrewmihalevich.models;



// import sun.misc.Perf;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by AndrewM on 6/20/2017.
 */
@Entity
public class Performance {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=25)
    private String name;

    @NotNull
    // @Column(unique = true)
    private int position;

    @NotNull
    private int timeAllotted;

    @OneToMany
    private Show show;

    public Performance(String name, int position, int timeAllotted) {
        this.name = name;
        this.position = position;
        this.timeAllotted = timeAllotted;
    }

    public Performance() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTimeAllotted() {
        return timeAllotted;
    }

    public void setTimeAllotted(int timeAllotted) {
        this.timeAllotted = timeAllotted;
    }

    public int compareTo(Performance comparePerformance) {
        int compareQuantity = comparePerformance.getPosition();

        return this.position - compareQuantity;


    }
}
