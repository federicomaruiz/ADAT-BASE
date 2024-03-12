package com.utad.Probando.service;

import com.utad.Probando.model.User;
import com.utad.Probando.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Anotación para indicar que esta clase es un servicio de Spring
@Service
public class UserService {

    // Inyección de dependencias de UserRepository
    private final UserRepository userRepository;

    // Constructor para la inyección de dependencias de UserRepository
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para obtener todos los usuarios
    public List<User> getUsers() {
        return userRepository.findAll(); // Devuelve todos los usuarios
    }

    // Método para guardar un nuevo usuario
    public User save(User user) {
        return userRepository.save(user); // Guarda el nuevo usuario en la base de datos
    }

    // Método para eliminar un usuario por su ID
    public void delete(Long id) {
        userRepository.deleteById(id); // Elimina el usuario con el ID proporcionado
    }

    // Método para obtener un usuario por su ID
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null); // Devuelve el usuario por su ID, si existe
    }


}
