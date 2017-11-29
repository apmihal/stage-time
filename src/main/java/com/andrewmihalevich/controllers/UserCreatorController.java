package com.andrewmihalevich.controllers;

import com.andrewmihalevich.models.ComedyShow;
import com.andrewmihalevich.models.User;
import com.andrewmihalevich.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;

@Controller
public class UserCreatorController {

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "create-user", method = RequestMethod.GET)
    public String createUser(Model model, HttpServletRequest request) {

        HttpSession httpSession = request.getSession();

        model.addAttribute("users", userDao.findAll());
        model.addAttribute(new User());



        model.addAttribute("loggedInUser", httpSession.getAttribute("loggedIn"));



        return "userCreator/user-creator";
    }

    @RequestMapping(value = "create-user", method = RequestMethod.POST)
    public String createUser(@ModelAttribute @Valid User user,
                             Model model,
                             Errors errors,
                             HttpServletRequest request) {

        if(errors.hasErrors()) {
            return "userCreator/user-creator";
        }

        HttpSession httpSession = request.getSession();


        httpSession.setAttribute("loggedIn", user);

        userDao.save(user);

        return "redirect:/create-user";
    }
}
