package com.vvt.icommerce.userservice.service;

import com.vvt.icommerce.userservice.model.Authority;
import com.vvt.icommerce.userservice.model.Role;
import com.vvt.icommerce.userservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {

    Iterable<User> getAllUsers(String keyword);

    User getUser(long id) throws UsernameNotFoundException;

    User update(User user);

    Role update(Role role);

    public void setRoles(User user, List<String> roles);

    public Authority createAuthorityIfNotFound(String name);

    public void setAuthorities(Role role, List<Authority> authorities);

    public User loadUserByUsername(String username) throws UsernameNotFoundException;
}
