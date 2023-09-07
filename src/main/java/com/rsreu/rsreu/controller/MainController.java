package com.rsreu.rsreu.controller;

import com.rsreu.rsreu.entity.School;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping(path = {"/", "addSchool"})
    public ModelAndView addSchoolForm() {
        ModelAndView view = new ModelAndView("add_school");
        view.addObject("school", new School());
        return view;
    }

    @PostMapping("/saveSchool")
    public String saveSchool(@ModelAttribute @Valid School school, BindingResult result, Model model) {
        return "add_school";
    }
}
