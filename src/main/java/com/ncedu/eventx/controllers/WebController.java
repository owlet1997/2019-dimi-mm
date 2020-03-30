package com.ncedu.eventx.controllers;


import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.models.DTO.EventForCreateDTO;
import com.ncedu.eventx.models.DTO.UserForUpdateDTO;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.entities.UserEntity;
import com.ncedu.eventx.services.UserEventService;
import com.ncedu.eventx.services.UsersService;
import org.apache.tomcat.jni.User;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class WebController {

    final UsersService usersService;
    final UserEventService userEventService;

    public WebController(UsersService usersService, UserEventService userEventService) {
        this.usersService = usersService;
        this.userEventService = userEventService;
    }
    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);



    @GetMapping("/user")
    public String userPage(@RequestParam("id") int id) {
        return "userProfile";
    }

//    @GetMapping(value = "/user/{id}")
//    public ModelAndView userPage(@PathVariable("id") int userId) {
//        UserDTO user = usersService.getUserById(userId);
//
//        ModelAndView modelAndView = new ModelAndView("registrationPages/UserPage");
//        modelAndView.addObject("user",user);
//        return modelAndView;
//    }


    @PutMapping("/user/{id}/update")
    @ResponseBody
    public UserForUpdateDTO updateUser(@RequestBody UserForUpdateDTO user){
//    public UserDTO updateUser(@RequestBody UserDTO user){
        usersService.updateUser(user);
        return usersMapper.toUserForUpdateDTO(usersService.getUserById(user.getId()));
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
        return "EventList";
    }

    @GetMapping(value = "/add-event")
    public String addEvent(Model model) {

        return "submit";
    }

    @GetMapping(value = "/")
    public String map(Model model) {
        return "eventMap";
    }

    @GetMapping(value = "/event")
    public String test(@RequestParam("id") int id) {

        return "listItem";
    }

    @PostMapping(value = "/login")
    public String authorize(Model model) {
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }

   /* @GetMapping(value = "/registration")
    public String registrationGet() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registrationPost(@RequestBody UserDTO user) {
        usersService.createRegisteredUser(user);
        return "redirect:/";
    } */

    @GetMapping(value = "/log-in")
    public String loginBootstrap(Model model) {
        return "sign-in";
    }

    @GetMapping(value = "/register")
    public String registerBootstrap() {
        return "register";
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user)
    {
        usersService.createRegisteredUser(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/add-event")
    @ResponseBody
    public EventForCreateDTO createEvent(@RequestBody EventForCreateDTO event){
        userEventService.createEvent(event);
        return event;
    }



}

