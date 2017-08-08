package com.andrewmihalevich.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndrewM on 8/4/2017.
 */
@Entity
public class ComedyShow {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=25)
    private String name;

    @NotNull
    @Size(min=3, max=25)
    private String Venue;

    @OneToMany
    @JoinColumn(name = "show_id")
    private List<Performance> performances = new ArrayList<>();

    public ComedyShow() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }
}
