package com.court.auth.service;

import com.court.auth.entity.User;
import com.court.auth.model.UserDto;
import com.court.auth.repository.UserRepository;
import com.court.exception.CourtGlobalException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class UserService {
    private final static Logger LOG= LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository =userRepository;
        this.passwordEncoder =passwordEncoder;
    }
    @Transactional
    public void saveUser(UserDto userDto){
        try {
            userRepository.save(user(userDto,true));
        }catch (Exception e){
            LOG.error("signup operation has failed",e);
            throw new CourtGlobalException(e.getMessage());

        }
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    private User user(UserDto userDto, boolean isPWDEncode){
        return User
                .builder()
                .mobileNo(userDto.getMobileNo())
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .password(isPWDEncode? passwordEncoder.encode(userDto.getPassword()) : userDto.getPassword())
                .location(userDto.getLocation())
                .roles(userDto.getRoles())
                .build();
    }
}
