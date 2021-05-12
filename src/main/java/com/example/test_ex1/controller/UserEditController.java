package com.example.test_ex1.controller;

import com.example.test_ex1.domain.Role;
import com.example.test_ex1.domain.User;
import com.example.test_ex1.repos.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserEditController {
    private final UserRepo userRepo;

    public UserEditController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String userList(Map<String, Object> modelUsers){
        List<User> users = userRepo.findAll();
        modelUsers.put("users", users);
        return "userList";
    }
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Map<String, Object> modelUsers){
        modelUsers.put("user", user);
        modelUsers.put("roles", Role.values());
        return "userEdit";
    }
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map <String, String> form,
            @RequestParam("userId") User user){
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
        return "redirect:/user";
    }
}
