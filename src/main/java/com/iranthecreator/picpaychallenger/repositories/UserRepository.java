package com.iranthecreator.picpaychallenger.repositories;

import com.iranthecreator.picpaychallenger.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(Long id);

}
