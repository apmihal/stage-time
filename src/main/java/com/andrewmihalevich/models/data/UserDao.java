package com.andrewmihalevich.models.data;

import com.andrewmihalevich.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Integer> {

    public List<User> findAll();

}
