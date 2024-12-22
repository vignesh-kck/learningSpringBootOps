package com.annztech.rightcareApi.user.service;


import com.annztech.rightcareApi.user.dto.RegisterUserDto;
import com.annztech.rightcareApi.user.entity.User;
import com.annztech.rightcareApi.user.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> registerUser( RegisterUserDto registerUserDto) {


        User user = new User();
        user.setFirstName(registerUserDto.getFirstName());
        user.setLastName(registerUserDto.getLastName());
        user.setMobile(registerUserDto.getMobile());
        user.setEmail(registerUserDto.getEmail());
        user.setNationalId(registerUserDto.getNationalId());
        user.setGender(registerUserDto.getGender());


        userRepository.save(user);

        return ResponseEntity.ok("Success");


    }

    public ResponseEntity<?> getUser(String uuid){
        
        Optional<User> userOptional = userRepository.findByUserUuid(uuid);
        
        User user = userOptional.get();
  
        RegisterUserDto userDTO = new RegisterUserDto(
            user.getFirstName(),
            user.getLastName(),
            user.getMobile(),
            user.getEmail(),
            user.getNationalId(),
            user.getGender()
        );

        return ResponseEntity.ok(userDTO);
    }

    @Transactional
    public ResponseEntity<?> deleteUser(String uuid){

        userRepository.deleteByUserUuid((uuid));

        return ResponseEntity.ok("user data deleted successfully");
    }

    public ResponseEntity<?> updateUserDetails(String uuid , RegisterUserDto registerUserDto ){

        Optional<User> existingUser = userRepository.findByUserUuid(uuid);

        User user = existingUser.get();
        user.setFirstName(registerUserDto.getFirstName());
        user.setLastName(registerUserDto.getLastName());
        user.setMobile(registerUserDto.getMobile());
        user.setEmail(registerUserDto.getEmail());
        user.setNationalId(registerUserDto.getNationalId());
        user.setGender(registerUserDto.getGender());

        userRepository.save(user);

        return ResponseEntity.ok("user data updated successfully");
    }


    public ResponseEntity<?> fetchAllUsers(String page, String size, String gender, String search){

         Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size));

         String firstName = null;
         String lastName = null;
         String mobile = null;
         String email = null;
 
         if (search != null && !search.isEmpty()) {
 
             firstName = search;
             lastName = search;
             mobile = search;
             email = search;
         }

         Integer genderInt = null;
         if (gender != null && !gender.isEmpty()) {
                 genderInt = Integer.parseInt(gender); 
           
         }

        Page<User> users;

     
        if (genderInt != null && search != null) {
            users = userRepository.findByGenderAndFirstNameContainingOrLastNameContainingOrMobileContainingOrEmailContaining(
                genderInt, firstName, lastName, mobile, email, pageable);
        } else if (genderInt != null) {
    
            users = userRepository.findByGender(genderInt, pageable);
        } else if (search != null) {
         
            users = userRepository.findByFirstNameContainingOrLastNameContainingOrMobileContainingOrEmailContaining(
                    firstName, lastName, mobile, email, pageable);
        } else {
            
            users = userRepository.findAll(pageable);
        }

        return ResponseEntity.ok(users);
    }
}
