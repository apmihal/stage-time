package com.andrewmihalevich.controllers;

import com.andrewmihalevich.models.ComedyShow;
import com.andrewmihalevich.models.Performance;
import com.andrewmihalevich.models.data.ComedyShowDao;
import com.andrewmihalevich.models.data.PerformanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by AndrewM on 6/20/2017.
 */
@Controller
public class ShowManagerController extends AbstractController {

//    @Autowired
//    private PerformanceDao performanceDao;
//
//    @Autowired
//    private ComedyShowDao comedyShowDao;

    // @Autowired
    // private ShowDao showDao;

    @RequestMapping(value = "show/{show_id}", method = RequestMethod.GET)
    public String showManager(Model model,
                              @PathVariable int show_id) {

        ComedyShow show = comedyShowDao.findById(show_id);

        model.addAttribute("performances", performanceDao.findByComedyShowOrderByPositionAsc(show));
        model.addAttribute("show_id", show_id);
        model.addAttribute(new Performance());

        return "showManager/showManager";
    }

    @RequestMapping(value = "show/{show_id}", method = RequestMethod.POST)
    public String index(@ModelAttribute @Valid Performance newPerformance,
                        Errors errors,
                        Model model,
                        @PathVariable int show_id) {

        ComedyShow show = comedyShowDao.findById(show_id);

        if (errors.hasErrors()) {
            return "showManager/showManager";
        }

        Performance performance = performanceDao.findOne(newPerformance.getPosition());

        if (performance != null) {
            // Get list of performances with position greater than or equal to newPerformance position
            // Updates positions of performances below new performance
            Iterable<Performance> performances = performanceDao.findByPositionGreaterThanAndComedyShow(newPerformance.getPosition() - 1, show);
            ArrayList<Performance> updatedPerformances = new ArrayList<>();
            for (Performance p : performances) {
                p.setPosition(p.getPosition() + 1);
                updatedPerformances.add(p);

            }

            for (Performance p : updatedPerformances) {
                performanceDao.save(p);
            }
        }

        ComedyShow comedyShow = comedyShowDao.findOne(show_id);
        newPerformance.setComedyShow(comedyShow);
        performanceDao.save(newPerformance);
        return "redirect:/show/" + show_id;
    }

    @RequestMapping(value = "show/{show_id}/edit/{performance_id}", method = RequestMethod.GET)
    public String edit(Model model,
                       @PathVariable int performance_id,
                       @PathVariable int show_id) {

        ComedyShow show = comedyShowDao.findById(show_id);


        model.addAttribute("message", performance_id);
        model.addAttribute("performance", performanceDao.findById(performance_id));
        model.addAttribute("performances", performanceDao.findByComedyShowOrderByPositionAsc(show));


        return "showManager/edit";
    }

    @RequestMapping(value = "show/{show_id}/edit/{performance_id}", method = RequestMethod.POST)
    public String edit(@PathVariable int performance_id,
                       @PathVariable int show_id,
                       @ModelAttribute @Valid Performance newPerformance,
                       Errors errors,
                       Model model) {

        ComedyShow show = comedyShowDao.findById(show_id);


        if (errors.hasErrors()) {
            return "showManager/edit/" + performance_id;
        }

        Performance performanceToUpdate = performanceDao.findById(performance_id);

        performanceToUpdate.setName(newPerformance.getName());
        performanceToUpdate.setTimeAllotted(newPerformance.getTimeAllotted());

        if (performanceToUpdate.getPosition() != newPerformance.getPosition()) {

            if (performanceToUpdate.getPosition() > newPerformance.getPosition()) {
                // If the updated position is higher up the list (a lower number)
                Iterable<Performance> performances = performanceDao.findAllByPositionBetweenAndComedyShow(newPerformance.getPosition(), performanceToUpdate.getPosition() - 1, show);
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
                Iterable<Performance> performances = performanceDao.findAllByPositionBetweenAndComedyShow(performanceToUpdate.getPosition() + 1, newPerformance.getPosition(), show);
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

    @RequestMapping(value = "show/{show_id}/edit/{performance_id}/remove", method = RequestMethod.GET)
    public String remove(@PathVariable int performance_id,
                         Model model) {

        performanceDao.delete(performanceDao.findById(performance_id));

        return "redirect:../..";
    }


}


