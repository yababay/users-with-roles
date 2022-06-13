package ru.kata.spring.boot_security.demo.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.NamedNativeQuery;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User where email = (:email)")
    User findByEmail(String email);
}
