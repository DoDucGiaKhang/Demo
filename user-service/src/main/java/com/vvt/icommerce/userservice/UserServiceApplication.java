package com.vvt.icommerce.userservice;

import com.vvt.icommerce.userservice.model.Authority;
import com.vvt.icommerce.userservice.model.Role;
import com.vvt.icommerce.userservice.model.User;
import com.vvt.icommerce.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	@Autowired
	PasswordEncoder passwordEncoder;

//	@Bean
//	CommandLineRunner runner(UserService userService) {
//		return args -> {
//			Role role1 = Role.builder().id(1L).name("ADMIN").build();
//			Role role2 = Role.builder().id(2L).name("USER").build();
//			userService.update(role1);
//			userService.update(role2);
//			Authority authority1 = userService.createAuthorityIfNotFound("ROLE_ADMIN");
//			Authority authority2 = userService.createAuthorityIfNotFound("ROLE_USER");
//			role1.addAuthority(authority1);
//			role2.addAuthority(authority2);
//			userService.update(role1);
//			userService.update(role2);
//			User user1 = User.builder()
//					.id(1L).username("user")
//					.password(passwordEncoder.encode("password"))
//					.firstName("david").lastName("beckham")
//					.enabled(true).build();
//			User user2 = User.builder()
//					.id(2L).username("thanh.vo")
//					.password(passwordEncoder.encode("password"))
//					.firstName("Thanh").lastName("Vo")
//					.enabled(true).build();
//			user1.addRole(role1);
//			user2.addRole(role2);
//			userService.update(user1);
//			userService.update(user2);
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
