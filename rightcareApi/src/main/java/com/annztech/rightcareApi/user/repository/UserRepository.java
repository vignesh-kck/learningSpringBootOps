package com.annztech.rightcareApi.user.repository;

import com.annztech.rightcareApi.user.entity.User;

import java.util.Optional;

// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserUuid(String userUuid);

    void deleteByUserUuid(String userUuid);

    Page<User> findByGenderAndFirstNameContainingOrLastNameContainingOrMobileContainingOrEmailContaining(
        int gender, String firstName, String lastName, String mobile, String email, Pageable pageable);


    Page<User> findByGender(int gender, Pageable pageable);


    Page<User> findByFirstNameContainingOrLastNameContainingOrMobileContainingOrEmailContaining(
        String firstName, String lastName, String mobile, String email, Pageable pageable);
}
