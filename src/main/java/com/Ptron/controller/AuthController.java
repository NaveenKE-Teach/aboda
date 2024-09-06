package com.Ptron.controller;

import com.Ptron.entity.User;
import com.Ptron.payload.LoginDto;
import com.Ptron.payload.Signup;
import com.Ptron.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity<String> createUser(@RequestBody Signup signup) {
        if (userRepository.existsByEmail(signup.getEmail())) {
            return new ResponseEntity<>("Email ID is already registered!", HttpStatus.CONFLICT);
        }
        if (userRepository.existsByUsername(signup.getUsername())) {
            return new ResponseEntity<>("Username is already registered!", HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setName(signup.getName());
        user.setUsername(signup.getUsername());
        user.setEmail(signup.getEmail());
        user.setPassword(passwordEncoder.encode(signup.getPassword()));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/sing-in")
    public ResponseEntity<String> signIn(@RequestBody LoginDto loginDto) {

        //1.Supply loginDto.getUsername()-username to loadByUser method in CustomUserDetail  class
        //2.it will compare
        // Expected Actual  credentials with expected credentials  load by user ,ethod
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //Similar to session variable
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return new ResponseEntity<>("sign-in successfully ", HttpStatus.OK);

    }

}
