package com.andrewmihalevich.models.data;

import com.andrewmihalevich.models.Performer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AndrewM on 6/20/2017.
 */
public interface PerformerDao extends CrudRepository<Performer, Integer> {
}
