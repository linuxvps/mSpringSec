package com.sec.mspringsec.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sec.mspringsec.Dto.SecUserDto;
import com.sec.mspringsec.model.SecUser;
import com.sec.mspringsec.repository.SecUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SecUserService {

    @Autowired
    SecUserRepository secUserRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    PasswordEncoder passwordEncoder;


    public SecUserDto findByEmail(String email) {
        List<SecUser> secUserList = secUserRepository.findByEmail(email);
        //find first customer or throw exception
        SecUser secUser = secUserList.stream().findFirst().orElseThrow(() -> new UsernameNotFoundException(email));
        SecUserDto secUserDto = objectMapper.convertValue(secUser, SecUserDto.class);
        return secUserDto;

    }

    public SecUserDto findById(Integer id) {
        Optional<SecUser> byId = secUserRepository.findById(id);
        //find first customer or throw exception
        SecUserDto secUserDto = objectMapper.convertValue(byId, SecUserDto.class);
        return secUserDto;

    }

    public List<SecUserDto> getAllUsers() {
        List<SecUser> secUserList = secUserRepository.findAll();
        List<SecUserDto> secUserDtoList = new ArrayList<>();
        for (SecUser user : secUserList) {
            SecUserDto secUserDto = objectMapper.convertValue(user, SecUserDto.class);
            secUserDtoList.add(secUserDto);
        } return secUserDtoList;
    }


    public SecUser saveUser(SecUserDto secUserDto) {
        SecUser user = objectMapper.convertValue(secUserDto, SecUser.class);
        String encodePass = passwordEncoder.encode(user.getPwd());
        user.setPwd(encodePass);
        return secUserRepository.save(user);
    }


    public void deleteUser(int userId) {
        secUserRepository.deleteById(userId);
    }

}
