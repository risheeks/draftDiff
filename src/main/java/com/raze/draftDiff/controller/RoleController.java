package com.raze.draftDiff.controller;

import com.raze.draftDiff.model.Role;
import com.raze.draftDiff.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
