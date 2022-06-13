package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select * from role where id in (select role_id from users_and_roles where user_id in (select id from user where email = (:email)))", nativeQuery = true)
    List<Role> findRolesByEmail(String email);
}
