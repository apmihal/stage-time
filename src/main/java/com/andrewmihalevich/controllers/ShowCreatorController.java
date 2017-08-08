package com.andrewmihalevich.controllers;

import com.andrewmihalevich.models.Show;
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
public class ShowCreatorController {

    @Autowired
    private ShowDao showDao;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String showManager(Model model) {


        model.addAttribute("shows", showDao.findAll());
        model.addAttribute(new Show());

        return "showManager/showManager";
    }


}
