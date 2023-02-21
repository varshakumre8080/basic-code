package com.blogApp.Controller;

import com.blogApp.Payload.UserDTO;
import com.blogApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO createUserDto = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }
}
