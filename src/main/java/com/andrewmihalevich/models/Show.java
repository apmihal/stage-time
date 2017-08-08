package com.andrewmihalevich.models;

import com.andrewmihalevich.models.Performance;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AndrewM on 8/4/2017.
 */
@Entity
public class Show {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String venue;

    private Date dateAndTime;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private List<Performance> performances = new ArrayList<>();

    public Show(String name, String venue) {
        this.name = name;
        this.venue = venue;
    }

    public Show() {}


}
