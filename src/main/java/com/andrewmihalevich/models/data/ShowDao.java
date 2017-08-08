package com.andrewmihalevich.models.data;

import com.andrewmihalevich.models.Show;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AndrewM on 8/4/2017.
 */
public interface ShowDao extends CrudRepository<Show, Integer> {

}
