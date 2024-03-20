package com.sec.mspringsec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactUsController {
    @GetMapping("/contactus")
    public String contactUs() {
        return "{\"message\": \"Welcome to our contact page!\"}";
    }
}
