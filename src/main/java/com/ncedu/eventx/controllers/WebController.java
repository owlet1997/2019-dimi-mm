package com.ncedu.eventx.controllers;

import com.ncedu.eventx.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class WebController {

    @RequestMapping(value = "/")
    public String startPage() {
        return "StartPage";
    }

    // Личный кабинет
    @RequestMapping(value = "/logIn", method = RequestMethod.GET)
    public String logIn(Model model) {
        model.addAttribute(new User()); // Добавить проверку на пользователя
        return "LogIn";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public  String logIn(@ModelAttribute User user,Model model) {
        model.addAttribute(user);
        if(user.getLogin().equals("123") && user.getPassword().equals("123")) {
            return "AuthorizedPage";
        }
        return "RegistrationPage";
    }

    @RequestMapping(value = "/primaryCabinet", method = RequestMethod.GET)
    public String primaryCabinet(@ModelAttribute User user, Model model) {
        model.addAttribute(user);
        return "PrimaryCabinet";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser() {
        return "Delete";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String editUser(Model model) {
        model.addAttribute(new User());
        return "EditUser";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(Model model) {
        model.addAttribute(new User());
        return "ChangePassword";
    }

}
