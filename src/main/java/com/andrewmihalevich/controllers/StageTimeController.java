package com.andrewmihalevich.controllers;

import com.andrewmihalevich.models.Performer;
import com.andrewmihalevich.models.data.PerformerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by AndrewM on 6/20/2017.
 */
@Controller
public class StageTimeController {

    @Autowired
    private PerformerDao performerDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("performers", performerDao.findAll());
        model.addAttribute(new Performer());

        return "index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String index(@ModelAttribute @Valid Performer newPerformer,
                        Errors errors,
                        Model model) {

        if (errors.hasErrors()) {
            return "index";
        }

        performerDao.save(newPerformer);
        return "redirect:";
    }
}
