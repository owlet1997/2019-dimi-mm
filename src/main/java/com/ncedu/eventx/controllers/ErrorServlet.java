package com.ncedu.eventx.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorServlet implements ErrorController
{
    private static final String ERROR_MAPPING = "/error";

    @RequestMapping(value = ERROR_MAPPING)
    public String error() {
        return "404";
    }

    @Override
    public String getErrorPath() {
        return ERROR_MAPPING;
    }

}
