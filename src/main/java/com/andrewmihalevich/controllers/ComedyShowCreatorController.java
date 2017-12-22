package com.andrewmihalevich.controllers;

import com.andrewmihalevich.models.ComedyShow;
import com.andrewmihalevich.models.User;
import com.andrewmihalevich.models.data.ComedyShowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by AndrewM on 8/7/2017.
 */
@Controller
public class ComedyShowCreatorController extends AbstractController{

    @Autowired
    private ComedyShowDao comedyShowDao;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String showManager(Model model, HttpServletRequest request) {

        User loggedInUser = getUserFromSession(request.getSession());


        //model.addAttribute("comedyShows", comedyShowDao.findAll());
        model.addAttribute("comedyShows", loggedInUser.getComedyShows());
        model.addAttribute(new ComedyShow());

        return "showCreator/show-creator";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String index(@ModelAttribute @Valid ComedyShow newComedyShow,
                        Errors errors,
                        Model model,
                        HttpServletRequest request) {

        User loggedInUser = getUserFromSession(request.getSession());

        if (errors.hasErrors()) {
            return "showCreator/show-creator";
        }

        loggedInUser.addComedyShow(newComedyShow);
        comedyShowDao.save(newComedyShow);
        userDao.save(loggedInUser);
        return "redirect:/create";

    }


}
