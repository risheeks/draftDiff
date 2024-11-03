package com.raze.draftDiff.repository;

import com.raze.draftDiff.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    public List<Role> findByName(String name);
}
