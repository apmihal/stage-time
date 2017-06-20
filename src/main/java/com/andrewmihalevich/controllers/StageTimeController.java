package com.andrewmihalevich.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AndrewM on 6/20/2017.
 */
@Controller
public class StageTimeController {

    @RequestMapping(value = "")
    public String index(Model model) {
        return "index";
    }
}
