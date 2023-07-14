package com.court.controller;
import com.court.auth.entity.User;
import com.court.auth.model.UserDto;
import com.court.auth.service.UserService;
import com.court.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class CourtController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;
    @GetMapping("/health")
    public String welcome() {
        System.out.println("Test******health**************");

        return Messages.getMessage(messageSource, "SUCCESS.SIGNUP", new Object[] { 123,12345});
    }

    @GetMapping
    public List<User> addNewUser() {
        System.out.println("Test********************");
        return userService.getUsers();
    }


}
