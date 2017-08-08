package com.andrewmihalevich.models.data;

import com.andrewmihalevich.models.ComedyShow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by AndrewM on 8/4/2017.
 */
public interface ComedyShowDao extends CrudRepository<ComedyShow, Integer> {
    public ComedyShow findById(int id);

}
