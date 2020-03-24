package com.ncedu.eventx.controllers;

import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.DTO.UserForUpdateDTO;
import com.ncedu.eventx.services.UsersService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    final UsersService usersService;

    public RegistrationController(UsersService usersService) {
        this.usersService = usersService;
    }
    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);

    @GetMapping(value = "/registration")
    public String registrationForm(Model model) {

        model.addAttribute("user", new UserDTO());
        return "registrationPages/RegistrationPage";
    }

    @PostMapping(value = "/registration")
    @ResponseBody
    public UserForUpdateDTO registrationSubmit(@RequestBody UserForUpdateDTO user) {

        usersService.createRegisteredUser(user);
        return usersMapper.toUserForCreateDTO(usersService.getUserById(user.getId()));
    }
}