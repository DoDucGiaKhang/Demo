package com.vvt.icommerce.userservice.service;

import com.vvt.icommerce.userservice.model.Authority;
import com.vvt.icommerce.userservice.model.Role;
import com.vvt.icommerce.userservice.model.User;
import com.vvt.icommerce.userservice.repository.AuthorityRepository;
import com.vvt.icommerce.userservice.repository.RoleRepository;
import com.vvt.icommerce.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Iterable<User> getAllUsers(String keyword) {
        if (keyword != null) {
            return userRepository.search(keyword);
        }
        return userRepository.findAll();
    }

    @Override
    public User getUser(long id) throws UsernameNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user"));
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return user;
    }

    public void setRoles(User user, List<String> roleNames) {
        List<Role> roles = new ArrayList<>();
        for (String roleName: roleNames) {
            roles.add(roleRepository.findByName(roleName));
        }
        user.setRoles(roles);
    }

    @Transactional
    public Authority createAuthorityIfNotFound(String name) {
        Authority authority = authorityRepository.findByName(name);
        if (authority == null) {
            authority = new Authority(name);
            authorityRepository.save(authority);
        }
        return authority;
    }

    public void setAuthorities(Role role, List<Authority> authorities) {
        role.setAuthorities(authorities);
    }

}
