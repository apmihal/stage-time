package com.andrewmihalevich.controllers;

import com.andrewmihalevich.models.Show;
import com.andrewmihalevich.models.data.ShowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by AndrewM on 8/7/2017.
 */
@Controller
public class ShowCreatorController {

    @Autowired
    private ShowDao showDao;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    private String showCreator(Model model) {

        model.addAttribute("shows", showDao.findAll());

        return "showCreator/show-creator";

    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    private String showCreator(@ModelAttribute @Valid Show newShow,
                               Errors errors,
                               Model model) {

        if (errors.hasErrors()) {
            return "showCreator/show-creator";
        }

        showDao.save(newShow);
        return "redirect:";

    }

}
