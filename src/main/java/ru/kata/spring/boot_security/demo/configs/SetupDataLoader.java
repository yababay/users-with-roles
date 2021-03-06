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

        Role adminRole = new Role (ROLE_ADMIN, "ADMIN", "Administrator");
        Role userRole = new Role (ROLE_USER, "USER","Simple user");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        User user1 = new User("User", "Userson", "userson@server.com", 22, "user1");
        user1.setRoles(userRoles);
        userRepository.save(user1);

        User user2 = new User("User", "Userman", "userman@server.com", 23, "user2");
        user2.setRoles(userRoles);
        userRepository.save(user2);

        User admin1 = new User("Admin", "Adminson", "adminson@server.com", 24, "admin1");
        admin1.setRoles(adminRoles);
        userRepository.save(admin1);

        User admin2 = new User("Admin", "Adminman", "adminman@server.com", 25, "admin2");
        admin2.setRoles(adminRoles);
        userRepository.save(admin2);

    }

}
