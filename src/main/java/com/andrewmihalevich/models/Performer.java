package com.andrewmihalevich.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by AndrewM on 6/20/2017.
 */
@Entity
public class Performer {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=25)
    private String name;

    @NotNull
    private int position;

    @NotNull
    private int timeAllotted;

    public Performer(String name, int position, int timeAllotted) {
        this.name = name;
        this.position = position;
        this.timeAllotted = timeAllotted;
    }

    public Performer() { }

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
}
