package com.raze.draftDiff.controller;

import com.raze.draftDiff.model.Role;
import com.raze.draftDiff.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{roleName}")
    public List<Role> findByName(@PathVariable("roleName") String roleName) {
        return roleService.findByName(roleName);
    }
}
