package com.rkworld.HelloWorld.Controller;

import com.rkworld.HelloWorld.Entity.User;
import com.rkworld.HelloWorld.Repository.UserRepository;
import com.rkworld.HelloWorld.Service.UserService;
import com.rkworld.HelloWorld.Utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> user) {
        String email = user.get("email");
        String password = user.get("password");

        if (userRepository.findByEmail(email).isPresent()) {
            return new ResponseEntity<>("Email Already Exists", HttpStatus.CONFLICT);
        }

        userService.createUser(User.builder().email(email).password(password).build());

        return new ResponseEntity<>("Successfully Registered", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> user) {
        String email = user.get("email");
        String password = user.get("password");

        var userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User Not Registered", HttpStatus.UNAUTHORIZED);
        }

        User userEntity = userOptional.get();

        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }
}