package com.utad.Probando.repository;

import com.utad.Probando.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Anotación para indicar que esta interfaz es un repositorio de Spring
@Repository
// Extiende JpaRepository para operaciones CRUD básicas en la base de datos
public interface UserRepository extends JpaRepository<User, Long> {
}