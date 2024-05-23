package com.vvt.icommerce.userservice.controller;

import com.vvt.icommerce.userservice.dto.PhotoDTO;
import com.vvt.icommerce.userservice.dto.UserDto;
import com.vvt.icommerce.userservice.dto.UserResponse;
import com.vvt.icommerce.userservice.jwtutils.TokenManager;
import com.vvt.icommerce.userservice.jwtutils.Token;
import com.vvt.icommerce.userservice.model.User;
import com.vvt.icommerce.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = { "", "/" })
    public @NotNull
    Iterable<User> getUsers(@RequestParam(required = false) String keyword) {
        return userService.getAllUsers(keyword);
    }

    @GetMapping(value = {"/{id}"})
    public User getUser(@PathVariable Long id) throws UsernameNotFoundException {
        return userService.getUser(id);
    }

    @GetMapping(value = {"/{username}"})
    public UserDetails loadUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }

    private byte[] getImageData(String tokenHeader, String photoId) {
        ResponseEntity<PhotoDTO>  restExchange = null;
        try {
            MultiValueMap<String, String> headers = new
                    LinkedMultiValueMap<String, String>();
            headers.add("Authorization", tokenHeader);
            headers.add("Content-Type", "application/json");
            HttpEntity request = new HttpEntity(null, headers);
            restExchange =
                    restTemplate.exchange(
                            "http://localhost:4000/api/photos/{photoId}",
                            HttpMethod.GET,
                            request, PhotoDTO.class, photoId);
        } catch (Exception ex) {
            return null;
        }
        PhotoDTO photoDto = restExchange.getBody();
        return photoDto.getImage().getData();
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserDto userDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (Exception e) {
            throw e;
        }
        final User user = userService.loadUserByUsername(userDto.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(user);
        UserResponse response = new UserResponse(user);
        response.setToken(jwtToken);
        String header = "Bearer " + jwtToken;
        response.setAvatarData(getImageData(header,user.getPictureId()));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto) throws UsernameNotFoundException {
        User user = userService.getUser(userDto.getId());
        userService.update(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping(path="/register")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .firstName(userDto.getFirstName()).lastName(userDto.getLastName())
                .enabled(true)
                .build();
        userService.setRoles(user, Arrays.asList("USER"));
        userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


}
