package com.andrewmihalevich.models.data;

import com.andrewmihalevich.models.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by AndrewM on 8/8/2017.
 */
@Repository
@Transactional
public interface TestDao extends CrudRepository<Test, Integer> {
}
