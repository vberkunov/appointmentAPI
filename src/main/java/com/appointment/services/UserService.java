package com.appointment.services;

import com.appointment.entity.User;

public interface UserService {
    void save(User user);
    User findByEmail(String email);
}
