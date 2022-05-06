package com.alkemy.disney.auth.service;

import com.alkemy.disney.auth.dto.UserDTO;
import com.alkemy.disney.auth.entity.UserEntity;
import com.alkemy.disney.auth.repository.UserRepository;
import com.alkemy.disney.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null){
            throw new UsernameNotFoundException("user or password not found");
        }
        return new User(userEntity.getUsername(),userEntity.getPassword(), Collections.emptyList());//aca le deberia pasar los roles, pero como no los vamos a usar, le paso una lista vacia

    }

    public boolean save(UserDTO dto){
        UserEntity entity = new UserEntity();

        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity=userRepository.save(entity);
        if(entity != null){
            emailService.sendWelcomeEmailTo(entity.getUsername());
        }

        return entity!=null;
    }

}
