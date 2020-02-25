package com.ncedu.eventx.controllers;


import com.ncedu.eventx.model.domain.UserDAO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @RequestMapping(value = "/")
    public String startPage() {
        return "StartPage";
    }

    @RequestMapping(value = "/user")
    public String userPage(Model model) {

        model.addAttribute(new UserDAO());
        return "registrationPages/UserPage";
    }

    // Личный кабинет
    @RequestMapping(value = "/logIn", method = RequestMethod.GET)
    public String logIn(Model model) {

        model.addAttribute(new UserDAO()); // Добавить проверку на пользователя
        return "primaryCabinetPages/LogIn";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)

    public  String logIn(@ModelAttribute UserDAO user,Model model) {

        model.addAttribute(user);
        if(user.getLogin().equals("123") && user.getPassword().equals("123")) {
            return "primaryCabinetPages/AuthorizedPage";
        }
        return "registrationPages/RegistrationPage";
    }

    @RequestMapping(value = "/primaryCabinet", method = RequestMethod.GET)
    public String primaryCabinet(@ModelAttribute UserDAO user, Model model) {
        model.addAttribute(user);
        return "primaryCabinetPages/PrimaryCabinet";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser() {
        return "primaryCabinetPages/Delete";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String editUser(Model model) {
        model.addAttribute(new UserDAO());
        return "primaryCabinetPages/EditUser";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(Model model) {

        model.addAttribute(new UserDAO());
        return "primaryCabinetPages/ChangePassword";
    }

}

