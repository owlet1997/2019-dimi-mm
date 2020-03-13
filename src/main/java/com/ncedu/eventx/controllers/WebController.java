package com.ncedu.eventx.controllers;


import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.models.DTO.UserDTO;
import com.ncedu.eventx.models.DTO.UserForCreateDTO;
import com.ncedu.eventx.services.UsersService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    final UsersService usersService;

    public WebController(UsersService usersService) {
        this.usersService = usersService;
    }
    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);


    @GetMapping(value = "/user/{id}")
    public ModelAndView userPage(@PathVariable("id") int userId) {
        UserDTO user = usersService.getUserById(userId);

        ModelAndView modelAndView = new ModelAndView("registrationPages/UserPage");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @GetMapping("/user/{id}/update")
    public ModelAndView updateUser(){
        return new ModelAndView("registrationPages/UpdateUser");
    }

    @PutMapping("/user/{id}/update")
    @ResponseBody
    public UserForCreateDTO updateUser(@RequestBody UserForCreateDTO user){

        usersService.updateUser(user);
        return usersMapper.toUserForCreateDTO(usersService.getUserById(user.getId()));
    }

    @GetMapping(value = "/list")
    public String list(Model model) {

        return "EventList";
    }

    @GetMapping(value = "/test")
    public String test(Model model) {

        return "test";
    }


//    // Личный кабинет
//    @GetMapping(value = "/login")
//    public String logIn(Model model) {
//
//        model.addAttribute(new UserEntity()); // Добавить проверку на пользователя
//        return "primaryCabinetPages/LogIn";
//    }
//
//    @PostMapping(value = "/login")
//    public  String logIn(@ModelAttribute UserEntity user, Model model) {
//
//        model.addAttribute(user);
//        if(user.getLogin().equals("123") && user.getPassword().equals("123")) {
//            return "primaryCabinetPages/AuthorizedPage";
//        }
//        return "registrationPages/RegistrationPage";
//    }
//
//    @RequestMapping(value = "/primaryCabinet", method = RequestMethod.GET)
//    public String primaryCabinet(@ModelAttribute UserEntity user, Model model) {
//        model.addAttribute(user);
//        return "primaryCabinetPages/PrimaryCabinet";
//    }

//    @RequestMapping(value = "/delete", method = RequestMethod.GET)
//    public String deleteUser() {
//        return "primaryCabinetPages/Delete";
//    }
//
//    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
//    public String editUser(Model model) {
//        model.addAttribute(new UserEntity());
//        return "primaryCabinetPages/EditUser";
//    }
//
//    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
//    public String changePassword(Model model) {
//
//        model.addAttribute(new UserEntity());
//        return "primaryCabinetPages/ChangePassword";
//    }

}

