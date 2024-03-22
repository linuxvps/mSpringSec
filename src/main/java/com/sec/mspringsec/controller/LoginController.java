package com.sec.mspringsec.controller;

import com.sec.mspringsec.Dto.SecUserDto;
import com.sec.mspringsec.model.SecUser;
import com.sec.mspringsec.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {


    @Autowired
    SecUserService secUserService;


    @PostMapping("/login/register")
    public ResponseEntity<String> registerUser(@RequestBody SecUserDto secUserDto) {
        ResponseEntity<String> response = null;
        try {
            SecUser user = secUserService.saveUser(secUserDto);
            if (user.getId() > 0) {
                response = ResponseEntity.status(HttpStatus.CREATED).body("the user successfully register");
            }
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("an exception occurred due to " + e.getMessage());
        }
        return response;
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

    @GetMapping("/login/finduser")
    public SecUserDto findUserByEmail(@PathVariable String email) {
        SecUserDto secUserDto = secUserService.findByEmail(email);
        return secUserDto;
    }


}
