package com.ncedu.eventx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateServlet {

    @RequestMapping("/helloWorld")
    public String helloworld(){
        return "template";
    }



}
