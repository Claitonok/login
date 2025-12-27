package com.loginpage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loginpage.model.UsuarioLogin;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioLogin, Integer> {


    Optional<UsuarioLogin> findByEmailAndPassword(String email, String password);
    
}
