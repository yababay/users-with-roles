package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserController(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<User> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(user -> new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(), user.getRoles()))
            .collect(Collectors.toList());
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        Set<Role> roles = roleRepository.findRolesByIds(user.getRoleIds()).stream().collect(Collectors.toSet());
        user.setRoles(roles);
        user.setPassword(User.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        User reply = userRepository.findByEmail(user.getEmail());
        return new User(reply.getId(), reply.getFirstName(), reply.getLastName(), reply.getEmail(), reply.getAge(), reply.getRoles());
    }

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
}
