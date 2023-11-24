package com.rsreu.rsreu.controller;

import com.rsreu.rsreu.data.entity.School;
import com.rsreu.rsreu.service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SchoolService schoolService;

    @GetMapping("/school/addSchool")
    public ModelAndView addSchoolForm() {
        ModelAndView view = new ModelAndView("add_school");
        view.addObject("school", new School());
        return view;
    }

    @PostMapping("/school/saveSchool")
    public Object saveSchool(@ModelAttribute @Valid School school, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_school";
        } else {
            schoolService.saveSchool(school);
        }
        ModelAndView view = new ModelAndView("add_school");
        view.addObject("school", new School());
        return view;
    }

    @GetMapping("/listSchools")
    public ModelAndView listSchools(@ModelAttribute @Valid School school, BindingResult result, Model model) {
        ModelAndView view = new ModelAndView("list_school");
        view.addObject("schools", schoolService.getAll());
        return view;
    }
}
