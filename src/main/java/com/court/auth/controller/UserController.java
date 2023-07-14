package com.court.auth.controller;

import com.court.auth.model.UserAuth;
import com.court.auth.model.UserDto;
import com.court.auth.service.JwtService;
import com.court.auth.service.UserService;
import com.court.exception.CourtEntityNotFoundException;
import com.court.utils.Messages;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * This class for authentication
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {
  private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private MessageSource messageSource;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public UserController(UserService userService, MessageSource messageSource){
        this.userService =userService;
        this.messageSource= messageSource;
    }

    /**
     * This used for signup
     * @param userDto request payload
     * @return a http status details
     */
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.ok(Messages.getMessage(messageSource, "SUCCESS.SIGNUP", new Object[] { userDto.getUserName() }));
    }

    /**
     * This method will do authentication
     * @param authRequest a request payload
     * @return a token
     */
    @PostMapping("/login")
    public ResponseEntity authenticateAndGetToken(@RequestBody UserAuth authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token =jwtService.generateToken(authRequest.getUsername());
            logger.info("User successfully login");

            return ResponseEntity.ok(Map.of("token", token));
        } else {
            throw new CourtEntityNotFoundException("invalid user request !","ERROR:LOGIN");
        }
    }
}
