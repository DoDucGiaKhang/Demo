package com.vvt.icommerce.userservice.repository;

import com.vvt.icommerce.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String role);
}
