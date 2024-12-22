package com.annztech.rightcareApi.user.dto;


import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private String nationalId;

    private Integer gender;

}
