package com.andrewmihalevich.controllers;

import com.andrewmihalevich.HibernateUtil;
import com.andrewmihalevich.models.Performer;
import com.andrewmihalevich.models.data.PerformerDao;
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
public class StageTimeController {

    @Autowired
    private PerformerDao performerDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("performers", performerDao.findAllByOrderByPositionAsc());
        model.addAttribute("nextPosition", performerDao.count() + 1);
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

//        Session session = (Session) HibernateUtil.getSessionFactory().openSession();
//
//        Query query = session.createQuery("from Performer where position = :code ");
//        query.setParameter("code", newPerformer.getPosition());



        Performer performer = performerDao.findOne(newPerformer.getPosition());

        if (performer != null) {
            //model.addAttribute("testError", "someone is already at this position");

            // Get list of performers with position greater than or equal to newPerformer position
            // Updates positions of performers below new performer
            Iterable<Performer> performers = performerDao.findByPositionGreaterThan(newPerformer.getPosition() - 1);
            ArrayList<Performer> updatedPerformers = new ArrayList<>();
            for (Performer p : performers) {
                p.setPosition(p.getPosition() + 1);
                // performerDao.save(p);
                updatedPerformers.add(p);


                // Add 1 to their positions
            }

            for (Performer p : updatedPerformers) {
                performerDao.save(p);
            }
        }



        performerDao.save(newPerformer);
        return "redirect:";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("message", id);
        model.addAttribute("performer", performerDao.findById(id));

        return "edit";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable int id,
                       @ModelAttribute @Valid Performer newPerformer,
                       Errors errors,
                       Model model) {

        return "redirect:..";
    }
}


