package com.appointment.services.implementation;

import com.appointment.entity.Role;
import com.appointment.repositories.RoleRepository;
import com.appointment.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role findByName(String name) {
        Role result = roleRepository.findByName(name);
        return result;
    }
}
