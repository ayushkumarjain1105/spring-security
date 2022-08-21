package com.dailycodebuffer.springsecurity.controller;

import com.dailycodebuffer.springsecurity.entity.User;
import com.dailycodebuffer.springsecurity.event.RegistrationCompleteEvent;
import com.dailycodebuffer.springsecurity.model.UserModel;
import com.dailycodebuffer.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){

        User user = userService.registerUser(userModel);

        publisher.publishEvent(new RegistrationCompleteEvent(user,
                applicationUrl(request)));
        return "Success";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+
                request.getContextPath();

    }
}
