package com.vvt.icommerce.userservice.repository;

import com.vvt.icommerce.userservice.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    public Authority findByName(String name);
}
