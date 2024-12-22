package com.annztech.rightcareApi.user.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userData")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userUuid" , nullable = false , unique = true , updatable = false , length=36)
    private String userUuid;

    @Column(name="firstName", nullable = true , length = 255 )
    private String firstName;

    @Column(name="lastName" , nullable = true , length = 255)
    private String lastName;

    @Column(name="mobile", nullable = true , unique = true , length=10)
    private String mobile;

    @Column(name = "email" , nullable = true , unique = true)
    private String email;

//    @Column(name = "date_of_birth", nullable = true)
//    @JsonFormat(pattern = "dd/MM/yyyy")
//    private LocalDate dateOfBirth;

    @Column(name = "nationalId",nullable = true, unique = true , length = 10)
    private String nationalId;

    @Column(name = "gender", nullable = true , length = 1)
    private Integer gender;



    @PrePersist
    public void generateUuid(){
        if(this.userUuid== null){
            this.userUuid= UUID.randomUUID().toString();
        }
    }

}
