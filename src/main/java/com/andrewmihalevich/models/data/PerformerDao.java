package com.andrewmihalevich.models.data;

import com.andrewmihalevich.models.Performer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by AndrewM on 6/20/2017.
 */
public interface PerformerDao extends CrudRepository<Performer, Integer> {
    public Performer findByPosition(int position);
    public List<Performer> findByPositionGreaterThan(int position);
    public List<Performer> findAllByOrderByPositionAsc();
    public Performer findById(int id);

}
