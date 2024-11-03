package com.raze.draftDiff.service;

import com.raze.draftDiff.model.Role;
import com.raze.draftDiff.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
