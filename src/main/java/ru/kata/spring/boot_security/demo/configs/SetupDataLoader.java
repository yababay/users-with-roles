package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final static long ROLE_ADMIN = 1;
    private final static long ROLE_USER = 2;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public SetupDataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Role adminRole = new Role (ROLE_ADMIN, "Administrator");
        Role userRole = new Role (ROLE_USER, "Simple user");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        User user1 = new User("user1", "User", "Userson", "userson@server.com", "user1");
        user1.setRoles(userRoles);
        userRepository.save(user1);

        User user2 = new User("user2", "User", "Userman", "userman@server.com", "user2");
        user2.setRoles(userRoles);
        userRepository.save(user2);

        User admin1 = new User("admin1", "Admin", "Adminson", "adminson@server.com", "admin1");
        admin1.setRoles(adminRoles);
        userRepository.save(admin1);

        User admin2 = new User("admin2", "Admin", "Adminman", "adminman@server.com", "admin2");
        admin2.setRoles(adminRoles);
        userRepository.save(admin2);

    }

}
