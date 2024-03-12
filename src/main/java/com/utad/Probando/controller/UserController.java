package com.utad.Probando.controller;

import com.utad.Probando.model.User;
import com.utad.Probando.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/usuarios")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> list = userService.getUsers();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(userService.getById(id));
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> actualizarUser(@PathVariable Long id, @RequestBody User userActualizado) {
        Optional<User> userOptional = Optional.ofNullable(userService.getById(id));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userActualizado.getName() != null) {
                user.setName(userActualizado.getName());
            }
            if (userActualizado.getAge() != null) {
                user.setAge(userActualizado.getAge());
            }
            if (userActualizado.getRanking() != null) {
                user.setRanking(userActualizado.getRanking());
            }
            User userActualizadaEnBD = userService.save(user);
            return ResponseEntity.ok(userActualizadaEnBD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
