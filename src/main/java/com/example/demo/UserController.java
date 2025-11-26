package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // Fake Database (liste en mémoire)
    private List<User> users = new ArrayList<>();

    // POST - CREATE
    @PostMapping
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    // GET ALL
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // GET ONE
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return users.stream()
                .filter(u -> u.id == id)
                .findFirst()
                .orElse(null);
    }

    // PUT (Update ALL fields)
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updated) {
        for (User u : users) {
            if (u.id == id) {
                u.name = updated.name;
                u.email = updated.email;
                return u;
            }
        }
        return null;
    }

    // PATCH (Update ONE field)
    @PatchMapping("/{id}")
    public User patchUser(@PathVariable int id, @RequestBody User updated) {
        for (User u : users) {
            if (u.id == id) {
                if (updated.name != null) u.name = updated.name;
                if (updated.email != null) u.email = updated.email;
                return u;
            }
        }
        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        boolean removed = users.removeIf(u -> u.id == id);
        return removed ? "User deleted" : "User not found";
    }
}
