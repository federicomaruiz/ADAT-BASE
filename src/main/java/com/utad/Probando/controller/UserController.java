package com.utad.Probando.controller;

import com.utad.Probando.model.User;
import com.utad.Probando.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Indica que esta clase es un controlador REST
@RestController
// Define la raíz de la URL para este controlador
@RequestMapping(path = "api/v1/usuarios")
public class UserController {
    // Inyección de dependencias de UserService
    private final UserService userService;

    // Anotación para la inyección de dependencias de UserService
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Método para manejar las solicitudes GET
    public ResponseEntity<List<User>> getAll() {
        // Obtiene la lista de usuarios del servicio
        List<User> list = userService.getUsers();
        // Devuelve la lista de usuarios con una respuesta HTTP 200 OK
        return ResponseEntity.ok(list);
    }

    // Método para manejar las solicitudes POST
    public ResponseEntity<User> save(@RequestBody User user) {
        // Guarda el nuevo usuario en la base de datos
        User newUser = userService.save(user);
        // Devuelve el nuevo usuario guardado con una respuesta HTTP 200 OK
        return ResponseEntity.ok(newUser);
    }

    // Método para manejar las solicitudes DELETE con un parámetro de ruta "id"
    public ResponseEntity<Optional<User>> delete(@PathVariable Long id) {
        // Elimina el usuario con el ID proporcionado
        userService.delete(id);
        // Devuelve una respuesta HTTP 200 OK sin contenido
        return ResponseEntity.ok().build();
    }

    // Método para manejar las solicitudes GET con un parámetro de ruta "id"
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id) {
        // Obtiene el usuario por su ID
        Optional<User> user = Optional.ofNullable(userService.getById(id));
        // Si el usuario está presente en la base de datos
        if (user.isPresent()) {
            // Devuelve el usuario con una respuesta HTTP 200 OK
            return ResponseEntity.ok(user);
        }
        // Si el usuario no está presente, devuelve una respuesta HTTP 404 Not Found
        return ResponseEntity.notFound().build();
    }

    // Método para manejar las solicitudes PUT con un parámetro de ruta "id"
    public ResponseEntity<User> actualizarUser(@PathVariable Long id, @RequestBody User userActualizado) {
        // Obtiene el usuario por su ID
        Optional<User> userOptional = Optional.ofNullable(userService.getById(id));
        // Si el usuario está presente en la base de datos
        if (userOptional.isPresent()) {
            // Obtiene el usuario de la base de datos
            User user = userOptional.get();
            // Actualiza los campos del usuario si se proporcionan en el cuerpo de la solicitud
            if (userActualizado.getName() != null) {
                user.setName(userActualizado.getName());
            }
            if (userActualizado.getAge() != null) {
                user.setAge(userActualizado.getAge());
            }
            if (userActualizado.getRanking() != null) {
                user.setRanking(userActualizado.getRanking());
            }
            // Guarda el usuario actualizado en la base de datos
            User userActualizadaEnBD = userService.save(user);
            // Devuelve el usuario actualizado con una respuesta HTTP 200 OK
            return ResponseEntity.ok(userActualizadaEnBD);
        } else { // Si el usuario no está presente
            // Devuelve una respuesta HTTP 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}
