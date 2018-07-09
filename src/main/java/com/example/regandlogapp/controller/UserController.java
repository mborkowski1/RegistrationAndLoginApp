package com.example.regandlogapp.controller;

import com.example.regandlogapp.model.User;
import com.example.regandlogapp.model.UserCredential;
import com.example.regandlogapp.service.UserService;
import com.example.regandlogapp.validator.UserCredentialValidator;
import com.example.regandlogapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserCredentialValidator userCredentialValidator;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomePage() {
        return "welcome";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registrationPage(Model model) {
        model.addAttribute("userRegistrationForm", new User());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registrationProcess(@ModelAttribute("userRegistrationForm") User userRegistrationForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        userValidator.validate(userRegistrationForm, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
            return modelAndView;
        }

        userService.save(userRegistrationForm);
        modelAndView.setViewName("welcome");
        modelAndView.addObject("user", userRegistrationForm);

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("userLoginForm", new UserCredential());

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginProcess(@ModelAttribute("userLoginForm") UserCredential userLoginForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        userCredentialValidator.validate(userLoginForm, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login");
            return modelAndView;
        }

        User user = userService.findByUsername(userLoginForm.getUsername());
        modelAndView.setViewName("welcome");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

}
