package com.andrewmihalevich.models.data;

import com.andrewmihalevich.models.ComedyShow;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AndrewM on 8/4/2017.
 */
public interface ComedyShowDao extends CrudRepository<ComedyShow, Integer> {

}
