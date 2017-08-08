package com.andrewmihalevich.controllers;

import com.andrewmihalevich.models.Test;
import com.andrewmihalevich.models.data.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by AndrewM on 8/8/2017.
 */
@Controller
public class TestController {

    @Autowired
    private TestDao testDao;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(Model model) {

        model.addAttribute("tests" , testDao.findAll());
        model.addAttribute(new Test());

        return "test/test";
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public String test(@ModelAttribute @Valid Test newTest,
                       Errors errors,
                       Model model) {
        if (errors.hasErrors()) {
            return "showManager/showManager";
        }

        testDao.save(newTest);
        return "redirect:/test";
    }
}
