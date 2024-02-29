package com.sec.mspringsec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CardController {
    @GetMapping("/card")
    public String getCard(){
        return "cards here";
    }
}
