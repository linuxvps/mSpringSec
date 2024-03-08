package com.sec.mspringsec.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sec.mspringsec.Dto.SecUserDto;
import com.sec.mspringsec.model.SecUser;
import com.sec.mspringsec.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {


    @Autowired
    SecUserService secUserService;


    @PostMapping("/login/register")
    public int registerUser(@RequestBody SecUserDto secUserDto) {
        SecUser user = secUserService.saveUser(secUserDto);
        return user.getId();
    }

    @GetMapping("/login/getAllUser")
    public List<SecUserDto> getAllUser() {
            List<SecUserDto> allUsers = secUserService.getAllUsers();
        return allUsers;
    }

    @GetMapping("/login/getUserById/{id}")
    public SecUserDto getUserById(@PathVariable Integer id) {
        SecUserDto byId = secUserService.findById(id);
        return byId;
    }


    @GetMapping("/login/deleteuser/{id}")
    public String deleteUserById(@PathVariable Integer id) {
        secUserService.deleteUser(id);
        return "ok";
    }
}
