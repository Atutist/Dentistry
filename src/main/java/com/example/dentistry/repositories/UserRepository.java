package com.example.dentistry.repositories;

import com.example.dentistry.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer id);
    Optional<User> findByUsernameIgnoreCase (String username);
    User findByUsername(String username);

}
