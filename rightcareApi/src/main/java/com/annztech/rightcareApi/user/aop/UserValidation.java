package com.annztech.rightcareApi.user.aop;


import com.annztech.rightcareApi.ENUM.StatusCode;
import com.annztech.rightcareApi.shared.model.MessageResponse;
import com.annztech.rightcareApi.user.dto.RegisterUserDto;
import com.annztech.rightcareApi.user.entity.User;
import com.annztech.rightcareApi.user.repository.UserRepository;

import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserValidation {

        @Autowired
    private UserRepository userRepository;

    @Around("execution(* com.annztech.rightcareApi.user.service.UserService.registerUser(..))")
    public Object validateUserRegistration(ProceedingJoinPoint  joinPoint) throws Throwable {
        
        Object[] arguments = joinPoint.getArgs();
        RegisterUserDto registerUserDto = (RegisterUserDto) arguments[0];

        
        if (registerUserDto.getFirstName() == null || registerUserDto.getFirstName().isEmpty()) {

            StatusCode statusData = StatusCode.firstNameEmpty ;
            
            int code=  statusData.getCode();
            String message =statusData.getMessage();

             MessageResponse.Status status = new MessageResponse.Status(code, message);


            MessageResponse response = new MessageResponse(status);

              return  ResponseEntity.badRequest().body(response);
        }

        if(registerUserDto.getFirstName().matches("\\d+")){

            StatusCode statusData = StatusCode.firstNameCannotBeANumber ;
            
            int code=  statusData.getCode();
            String message =statusData.getMessage();

             MessageResponse.Status status = new MessageResponse.Status(code, message);


            MessageResponse response = new MessageResponse(status);

              return  ResponseEntity.badRequest().body(response);
        }

        if(!registerUserDto.getFirstName().matches("[a-zA-Z]+")){

            StatusCode statusData = StatusCode.firstNameonlyAlphabets ;
            
            int code=  statusData.getCode();
            String message =statusData.getMessage();

             MessageResponse.Status status = new MessageResponse.Status(code, message);


            MessageResponse response = new MessageResponse(status);

              return  ResponseEntity.badRequest().body(response);
        }


        return joinPoint.proceed();


    }



    @Around("execution(* com.annztech.rightcareApi.user.service.UserService.getUser(..))")
    public Object validateGetUserRequest(ProceedingJoinPoint joinPoint)throws Throwable{

        Object[] userUuid = joinPoint.getArgs();


        if(!((String) userUuid[0]).matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")){
            StatusCode statusData = StatusCode.invalidUserUuid ;
            
            int code=  statusData.getCode();
            String message =statusData.getMessage();

             MessageResponse.Status status = new MessageResponse.Status(code, message);


            MessageResponse response = new MessageResponse(status);

              return  ResponseEntity.badRequest().body(response);
        }
    
 
        return joinPoint.proceed();
    
    }


    @Around("execution(* com.annztech.rightcareApi.user.service.UserService.deleteUser(..))")
    public Object processDeleteRequest(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] userUuid =joinPoint.getArgs();

        if(!((String) userUuid[0]).matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")){
            StatusCode statusData = StatusCode.invalidUserUuid ;
            
            int code=  statusData.getCode();
            String message =statusData.getMessage();

             MessageResponse.Status status = new MessageResponse.Status(code, message);


            MessageResponse response = new MessageResponse(status);

              return  ResponseEntity.badRequest().body(response);
        }

        Optional<User> user = userRepository.findByUserUuid(((String) userUuid[0]));

        if(user.isEmpty()){
            StatusCode statusData = StatusCode.userNotFound ;
            
            int code=  statusData.getCode();
            String message =statusData.getMessage();

             MessageResponse.Status status = new MessageResponse.Status(code, message);


            MessageResponse response = new MessageResponse(status);

              return  ResponseEntity.badRequest().body(response);
        }


        return joinPoint.proceed();


    }



    @Around("execution(* com.annztech.rightcareApi.user.service.UserService.updateUserDetails(..))")
    public Object processDetailsUpdateRequest(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] arguments =joinPoint.getArgs();

        Optional<User> user = userRepository.findByUserUuid(((String) arguments[0]));

        if(user.isEmpty()){
            StatusCode statusData = StatusCode.userNotFound ;
            
            int code=  statusData.getCode();
            String message =statusData.getMessage();

             MessageResponse.Status status = new MessageResponse.Status(code, message);


            MessageResponse response = new MessageResponse(status);

              return  ResponseEntity.badRequest().body(response);
        }

        return joinPoint.proceed();
    }
}
