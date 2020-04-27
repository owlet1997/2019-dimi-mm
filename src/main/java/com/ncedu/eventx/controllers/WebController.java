package com.ncedu.eventx.controllers;


import com.ncedu.eventx.converters.UsersMapper;
import com.ncedu.eventx.models.DTO.*;
import com.ncedu.eventx.services.EventItemService;
import com.ncedu.eventx.services.UserEventItemService;
import com.ncedu.eventx.services.UserEventService;
import com.ncedu.eventx.services.UsersService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

@Controller
public class WebController {

    final UsersService usersService;
    final UserEventService userEventService;
    final UserEventItemService userEventItemService;
    final EventItemService eventItemService;

    final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebController(UsersService usersService,
                         UserEventService userEventService,
                         UserEventItemService userEventItemService,
                         EventItemService eventItemService,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersService = usersService;
        this.userEventService = userEventService;
        this.userEventItemService = userEventItemService;
        this.eventItemService = eventItemService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);

    @GetMapping("/user")

    public String userPage(@RequestParam("id") int id) {
        return "userProfile";
    }

    @GetMapping("/user-items")
    public String userItemsPage(@RequestParam("id") int id) {
        return "items";
    }


    @PutMapping("/user/{id}/change-passwd")
    @ResponseBody
    public UserForUpdateDTO updatePassword(@RequestBody PasswordChangeDTO user) {
        return usersService.updatePassword(user);
    }

    @Transactional
    @PutMapping(value = "/user/{id}/add-picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> updatePicture(@RequestParam MultipartFile file) throws IOException, URISyntaxException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        usersService.savePicture(file, username);
        return ResponseEntity.created(new URI("http://localhost:8080/blobs/" + file.getOriginalFilename())).build();
    }


    @PutMapping("/user/{id}/update")
    @ResponseBody
    public UserForUpdateDTO updateUser(@RequestBody UserForUpdateDTO user) {
        usersService.updateUser(user);
        return usersMapper.toUserForUpdateDTO(usersService.getUserById(user.getId()));
    }


    @GetMapping(value = "/add-event")
    public String addEvent() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        return "submit";
    }

    @GetMapping(value = "/")
    public String map() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        return "eventMap";
    }

    @GetMapping(value = "/event")
    public String test(@RequestParam("id") int id) {
        return "listItem";
    }

    @GetMapping(value = "/update-event")
    public String updateEvent(@RequestParam("id") int id) {
        return "updateEvent";
    }

    @GetMapping(value = "/login")
    public String loginBootstrap(Model model) {
        return "sign-in";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public UserForUpdateDTO loginPost(@RequestParam String username, @RequestParam String password) {
        UserDTO userFromDb = usersService.getUserByUsername(username);

        if (bCryptPasswordEncoder.matches(password, userFromDb.getPassword())) {

            return usersMapper.toUserForUpdateDTO(userFromDb);

        }
        System.out.println("Error!");
        return null;
    }

    @GetMapping(value = "/register")
    public String registerBootstrap() {
        return "register";
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user) {
        if (user.getPassword().equals(user.getPasswordConfirm())) {
            usersService.createRegisteredUser(user);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/add-event")
    @ResponseBody
    public EventWithUsersDTO createEvent(@RequestBody EventForCreateDTO event) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        EventWithUsersDTO eventWithUsersDTO = userEventService.createEvent(event, username);
        System.out.println(eventWithUsersDTO);
        return eventWithUsersDTO;
    }

    @PostMapping("/add-event-item")
    @ResponseBody
    public EventItemDTO createEventElements(@RequestBody EventItemForCreateDTO event) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        EventItemDTO eventItemDTO = userEventItemService.createEventItem(event, username);
        System.out.println(eventItemDTO);
        return eventItemDTO;
    }


    @GetMapping(value = "/check-auth",
            produces = {MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public UserDTO isAutentificated() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserDTO> userDTO = Optional.ofNullable(usersService.getUserByUsername(username));
        System.out.println("В системе пользователь " + userDTO);
        return userDTO.orElse(null);
    }

    @PostMapping("/upload")
    @ResponseBody
    public void uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        usersService.savePicture(file, username);

    }

    @GetMapping(value = "/download/{id}", //
            produces = {MediaType.APPLICATION_JSON_VALUE
            })
    @ResponseBody
    public String downloadImage(@PathVariable("id") int id) {

        String avatar = usersService.getUserById(id).getAvatarImg();
        if (avatar == null) {
            return null;
        } else
            return (char) 34 + "data:image/png;base64," + avatar + (char) 34;
    }

}

