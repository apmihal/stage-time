package com.andrewmihalevich.models.data;

import com.andrewmihalevich.models.Performance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by AndrewM on 6/20/2017.
 */
public interface PerformanceDao extends CrudRepository<Performance, Integer> {
    public Performance findByPosition(int position);
    public List<Performance> findByPositionGreaterThan(int position);
    public List<Performance> findAllByOrderByPositionAsc();
    public Performance findById(int id);
    public List<Performance> findAllByPositionBetween(int start, int end);

}