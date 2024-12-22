package com.annztech.rightcareApi.user.controller;

import org.springframework.http.ResponseEntity;
import com.annztech.rightcareApi.user.dto.RegisterUserDto;
import com.annztech.rightcareApi.user.entity.User;
import com.annztech.rightcareApi.user.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto registerUserDto){
        return userService.registerUser(registerUserDto);
    }

    @GetMapping("/getUser/{uuid}")
    public ResponseEntity<?> getMethodName(@PathVariable String uuid) {
        return userService.getUser(uuid);
    }

    @DeleteMapping("/removeUser/{uuid}")
    public ResponseEntity<?> deleteRequestedUser(@PathVariable String uuid){
        return userService.deleteUser(uuid);
    }

    @PatchMapping("updateUserDetails/{uuid}")
    public ResponseEntity<?> updateRequestedUser(@PathVariable String uuid , @RequestBody RegisterUserDto registerUserDto ){
        return userService.updateUserDetails(uuid , registerUserDto ) ;
    }

    @GetMapping("/fetchUsers")
    public ResponseEntity<?> fetchAllUsers(
        @RequestParam(defaultValue = "1") String page,
        @RequestParam(defaultValue = "10") String size,
        @RequestParam(required = false) String gender,
        @RequestParam(required = false) String search) {
        return userService.fetchAllUsers(page , size , gender , search);
    }
    
    
}
