package com.dailycodebuffer.springsecurity.event.listener;

import com.dailycodebuffer.springsecurity.entity.User;
import com.dailycodebuffer.springsecurity.event.RegistrationCompleteEvent;
import com.dailycodebuffer.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Slf4j
public class RegistrationCompleteEventsListener implements
        ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create the verification token for th User with Link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(user, token);
        //Send Mail to user
        String url = event.getApplicationUrl()+
                "/verifyRegistration?token="+
                token;
        //send verification token
        log.info("Click the link to verify your account :{}" ,url);
    }
}
