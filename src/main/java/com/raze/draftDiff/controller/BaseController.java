package com.raze.draftDiff.controller;

import com.raze.draftDiff.model.Role;
import com.raze.draftDiff.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BaseController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/initApp")
    public void initApp() {
        Role top = new Role();
        top.setId(UUID.randomUUID().toString());
        top.setName("top");
        roleService.save(top);

        Role jungle = new Role();
        jungle.setId(UUID.randomUUID().toString());
        jungle.setName("jungle");
        roleService.save(jungle);

        Role mid = new Role();
        mid.setId(UUID.randomUUID().toString());
        mid.setName("middle");
        roleService.save(mid);

        Role bot = new Role();
        bot.setId(UUID.randomUUID().toString());
        bot.setName("bottom");
        roleService.save(bot);

        Role sup = new Role();
        sup.setId(UUID.randomUUID().toString());
        sup.setName("support");
        roleService.save(sup);
    }

}
