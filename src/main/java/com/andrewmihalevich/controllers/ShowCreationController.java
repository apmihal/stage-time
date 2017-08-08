package com.andrewmihalevich.controllers;

import com.andrewmihalevich.models.data.ShowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by AndrewM on 8/7/2017.
 */
@Controller
public class ShowCreationController {

    @Autowired
    private ShowDao showDao;

    @RequestMapping(value = " ", method = RequestMethod.GET)
    private String showCreation(Model model) {

    }

}
