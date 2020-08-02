package com.appointment.services;

import com.appointment.entity.Role;
import com.appointment.entity.User;
import com.appointment.repositories.RoleRepository;
import com.appointment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.getOne(1L));
        user.setRoles(roles);
        userRepo.save(user);


    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
