package com.sec.mspringsec.config;

import com.sec.mspringsec.model.SecUser;
import com.sec.mspringsec.repository.SecUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class eazyBankUserNameAndPwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    SecUserRepository secUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<SecUser> secUserList = secUserRepository.findByEmail(authentication.getName());
        //find first customer or throw exception
        SecUser secUser = secUserList.stream().findFirst().orElseThrow(() -> new BadCredentialsException(authentication.getName()));
        if (passwordEncoder.matches(authentication.getCredentials().toString(), secUser.getPwd())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(secUser.getRole()));
            return new UsernamePasswordAuthenticationToken(secUser.getEmail(), authentication.getCredentials().toString(), authorities);
        } else {
            throw new BadCredentialsException(secUser.getEmail());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
