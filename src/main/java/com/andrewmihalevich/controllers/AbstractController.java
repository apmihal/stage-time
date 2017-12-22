package com.andrewmihalevich.controllers;

import com.andrewmihalevich.models.User;
import com.andrewmihalevich.models.data.ComedyShowDao;
import com.andrewmihalevich.models.data.PerformanceDao;
import com.andrewmihalevich.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController {

    @Autowired
    protected UserDao userDao;

    /*
     * Other DAOs can be autowired here and they'll be available
     * to all classes extending AbstractController
     * */

    @Autowired
    protected ComedyShowDao comedyShowDao;

    @Autowired
    protected PerformanceDao performanceDao;


    public static final String userSessionKey = "user_id";

    protected User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        return userId == null ? null : userDao.findOne(userId);
    }

    protected void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getUid());
    }

    @ModelAttribute("user")
    public User getUserForModel(HttpServletRequest request) {
        return getUserFromSession(request.getSession());
    }

}
