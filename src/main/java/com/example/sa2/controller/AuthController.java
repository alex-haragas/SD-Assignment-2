package com.example.sa2.controller;

import com.example.sa2.Service.AdminService;
import com.example.sa2.Service.ClientService;
import com.example.sa2.model.Admin;
import com.example.sa2.model.Client;
import com.example.sa2.model.auth.JwtClientResponse;
import com.example.sa2.model.User;
import com.example.sa2.repository.UserRepository;
import com.example.sa2.security.pwt.AuthTokenFilter;
import com.example.sa2.security.pwt.JwtUtils;
import com.example.sa2.security.services.UserDetailsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authcontroller")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ClientService clientService;
    @Autowired
    AdminService adminService;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    private static final Logger logger = LogManager.getLogger(AuthTokenFilter.class);

    @PostMapping("/logIn")
    @ResponseBody
    public JwtClientResponse authenticateUser(@RequestBody User loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        JwtClientResponse jwtClientResponse = new JwtClientResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getRole()
        );
        return jwtClientResponse;
    }


    @PostMapping("/signupClient")
    public ResponseEntity<?> registerClient(@RequestBody Client client) {
        try {
            clientService.addClient(client);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Name taken");
            return ResponseEntity.ok().body("Internal Faillure");
        }
        logger.info("Everything is fine");
        return ResponseEntity.ok().body("Done");
    }

    @PostMapping("/signupAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        try {
            adminService.addAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Name taken");
            return ResponseEntity.ok().body("Internal Faillure");
        }
        logger.error("Everything is fine");
        return ResponseEntity.ok().body("Done");
    }
}