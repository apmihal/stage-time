package com.andrewmihalevich.controllers;

import com.andrewmihalevich.models.data.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by AndrewM on 8/8/2017.
 */
@Controller
public class TestController {

    @Autowired
    private TestDao testDao;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(Model model) {

        model.addAttribute("tests", testDao.findAll());

        return "test/test";
    }
}
