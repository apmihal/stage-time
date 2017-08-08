package com.andrewmihalevich.models;

import com.andrewmihalevich.models.Performance;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private int showId;

    @Size(min=3, max=25)
    @NotNull
    private String showName;

    @Size(min=3, max=25)
    @NotNull
    private String showVenue;

    // private Date dateAndTime;

    // @ManyToOne
    // @JoinColumn(name = "show_id")
    // private List<Performance> performances = new ArrayList<>();

    public Show() {}

    public int getShowId() {
        return showId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowVenue() {
        return showVenue;
    }

    public void setShowVenue(String showVenue) {
        this.showVenue = showVenue;
    }
}
