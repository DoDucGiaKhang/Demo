package com.vvt.icommerce.userservice.controller;

import com.vvt.icommerce.userservice.jwtutils.TokenManager;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenManager tokenManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping(value = {"/{token}"})
    public ResponseEntity<UserDetails> authenticate(@PathVariable("token") String tokenHeader) throws Exception {
        String username = null;
        String token = null;
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            token = tokenHeader.substring(7);
            try {
                username = tokenManager.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                logger.debug("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.debug("JWT Token has expired");
            }
        } else {
            logger.debug("Bearer String not found in token");
        }
        if (null != username) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (tokenManager.validateJwtToken(token, userDetails)) {
                return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
            }
        }
        return new ResponseEntity<UserDetails>(HttpStatus.BAD_REQUEST);
    }
}
