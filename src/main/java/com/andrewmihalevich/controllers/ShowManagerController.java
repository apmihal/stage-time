package com.andrewmihalevich.controllers;

import com.andrewmihalevich.HibernateUtil;
import com.andrewmihalevich.models.Performance;
import com.andrewmihalevich.models.Performance;
import com.andrewmihalevich.models.data.PerformanceDao;
import com.andrewmihalevich.models.data.PerformanceDao;
import org.apache.catalina.Session;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by AndrewM on 6/20/2017.
 */
@Controller
public class ShowManagerController {

    @Autowired
    private PerformanceDao performanceDao;

    // @Autowired
    // private ShowDao showDao;

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String showManager(Model model) {


        model.addAttribute("performances", performanceDao.findAllByOrderByPositionAsc());
        model.addAttribute("nextPosition", performanceDao.count() + 1);
        model.addAttribute(new Performance());

        return "showManager/showManager";
    }

    @RequestMapping(value = "show", method = RequestMethod.POST)
    public String index(@ModelAttribute @Valid Performance newPerformance,
                        Errors errors,
                        Model model) {

        if (errors.hasErrors()) {
            return "showManager/showManager";
        }

        Performance performance = performanceDao.findOne(newPerformance.getPosition());

        if (performance != null) {
            // Get list of performances with position greater than or equal to newPerformance position
            // Updates positions of performances below new performance
            Iterable<Performance> performances = performanceDao.findByPositionGreaterThan(newPerformance.getPosition() - 1);
            ArrayList<Performance> updatedPerformances = new ArrayList<>();
            for (Performance p : performances) {
                p.setPosition(p.getPosition() + 1);
                updatedPerformances.add(p);

            }

            for (Performance p : updatedPerformances) {
                performanceDao.save(p);
            }
        }

        performanceDao.save(newPerformance);
        return "redirect:/show";
    }

    @RequestMapping(value = "show/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("message", id);
        model.addAttribute("performance", performanceDao.findById(id));

        return "showManager/edit";
    }

    @RequestMapping(value = "show/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable int id,
                       @ModelAttribute @Valid Performance newPerformance,
                       Errors errors,
                       Model model) {

        if (errors.hasErrors()) {
            return "showManager/edit/" + id;
        }

        Performance performanceToUpdate = performanceDao.findById(id);

        performanceToUpdate.setName(newPerformance.getName());
        performanceToUpdate.setTimeAllotted(newPerformance.getTimeAllotted());

        if (performanceToUpdate.getPosition() != newPerformance.getPosition()) {

            if (performanceToUpdate.getPosition() > newPerformance.getPosition()) {
                // If the updated position is higher up the list (a lower number)
                Iterable<Performance> performances = performanceDao.findAllByPositionBetween(newPerformance.getPosition(), performanceToUpdate.getPosition() - 1);
                ArrayList<Performance> updatedPerformances = new ArrayList<>();
                for (Performance p : performances) {
                    p.setPosition(p.getPosition() + 1);
                    updatedPerformances.add(p);
                }

                for (Performance p : updatedPerformances) {
                    performanceDao.save(p);
                }

                performanceToUpdate.setPosition(newPerformance.getPosition());

            } else {
                // If the updated position is lower on the list (a higher number)
                Iterable<Performance> performances = performanceDao.findAllByPositionBetween(performanceToUpdate.getPosition() + 1, newPerformance.getPosition());
                ArrayList<Performance> updatedPerformances = new ArrayList<>();
                for (Performance p : performances) {
                    p.setPosition(p.getPosition() - 1);
                    updatedPerformances.add(p);
                }

                for (Performance p : updatedPerformances) {
                    performanceDao.save(p);
                }

                performanceToUpdate.setPosition(newPerformance.getPosition());
            }
        }

        performanceDao.save(performanceToUpdate);

        return "redirect:..";
    }

    @RequestMapping(value = "show/test")
    public String test(Model model) {
        model.addAttribute("performances", performanceDao.findAllByPositionBetween(5, 1));
        return "test";
    }
}


