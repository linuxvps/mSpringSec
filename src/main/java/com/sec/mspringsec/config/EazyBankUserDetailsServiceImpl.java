package com.sec.mspringsec.config;

import com.sec.mspringsec.model.Customer;
import com.sec.mspringsec.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EazyBankUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //find User in custom table
        List<Customer> customerList = customerRepository.findByEmail(username);
        //find first customer or throw exception
        Customer customer = customerList.stream().findFirst().orElseThrow(() -> new UsernameNotFoundException(username));
        //grant the customer
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customer.getRole()));
        //make new user
        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }
}
