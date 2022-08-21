package com.dailycodebuffer.springsecurity.service;

import com.dailycodebuffer.springsecurity.entity.User;
import com.dailycodebuffer.springsecurity.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(User user, String token);
}
