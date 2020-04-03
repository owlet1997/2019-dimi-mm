package com.ncedu.eventx.controllers;


import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.models.DTO.EventForCreateDTO;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.DTO.UserForUpdateDTO;
import com.ncedu.eventx.services.UserEventService;
import com.ncedu.eventx.services.UsersService;
import com.ncedu.eventx.services.impl.UsersServiceImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    final UsersService usersService;
    final UserEventService userEventService;

    final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebController(UsersService usersService, UserEventService userEventService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersService = usersService;
        this.userEventService = userEventService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);

    @GetMapping("/user")
    public String user() {
//        System.out.println("Secure = " + SecurityContextHolder.getContext().getAuthentication().getName());

        UserDTO user = usersService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        System.out.println("user = " + user);

        return ("redirect:/userInfo?id=" + user.getId());
    }

    @GetMapping("/userInfo")
    public String userPage(@RequestParam("id") int id) {

        return "userProfile";
    }


    @GetMapping("/user-items")
    public String userItemsPage(@RequestParam("id") int id) {
        return "items";
    }


    @PutMapping("/user/{id}/update")
    @ResponseBody
    public UserForUpdateDTO updateUser(@RequestBody UserForUpdateDTO user){
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

    @GetMapping(value = "/log-in")
    public String loginBootstrap(Model model) {
        return "sign-in";
    }

    @PostMapping(value = "/log-in")
    public String loginPost(@RequestParam String username,@RequestParam String password) {
        UserDTO userFromDb = usersService.getUserByUsername(username);

        if(bCryptPasswordEncoder.matches(password,userFromDb.getPassword())){
            return "redirect:/user?id=" + userFromDb.getId();
        }
        return "redirect:/register";
    }

    @GetMapping(value = "/register")
    public String registerBootstrap() {
        return "register";
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user)
    {
        if (user.getPassword().equals(user.getPasswordConfirm())) {
            usersService.createRegisteredUser(user);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/add-event")
    @ResponseBody
    public EventForCreateDTO createEvent(@RequestBody EventForCreateDTO event){
        userEventService.createEvent(event);
        return event;
    }



}

