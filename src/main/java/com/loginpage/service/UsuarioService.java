package com.loginpage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loginpage.model.UsuarioLogin;
import com.loginpage.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Logar
     * @param usuario
     * @return
     */
    public UsuarioLogin loginUsers(UsuarioLogin usuario){

        Optional<UsuarioLogin> usuarioLogin = usuarioRepository.findByEmailAndPassword(usuario.getEmail(), usuario.getPassword());

        if (usuarioLogin.isEmpty()) {
            System.err.println("Usuário ou senha inválidos.");
        } else {
            return usuarioLogin.get();
        }
         return null;
    }
    
    /**
     * Metodo obterTodos
     * @return retorna todos os Usuarios
     */
    public List<UsuarioLogin> ObterTodos(){
        List<UsuarioLogin> usuario = usuarioRepository.findAll();
         if (usuario.isEmpty()) {
            System.err.println("Nenhum usuario cadastrado!!");
        }
        return usuario;
    }

    /**
     * Metodo que pega por ID
     * @param id Esse metodo pede que seja passado um id
     * @return retorna uma dados por id
     */
    public Optional<UsuarioLogin> ObterPorId(Integer id){
        Optional<UsuarioLogin> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            System.err.println("Não foi encontrado o id: " + id + " do usuario");
        }
        return usuario;
    }

    /**
     * Metodo que ADICIONAR UM USUARIO
     * @param usuario
     * @return retorna o usuario cadastrado
     */
    public UsuarioLogin Adicionar(UsuarioLogin usuario){

        UsuarioLogin usuario2 = new UsuarioLogin();
        usuario2 = usuarioRepository.save(usuario);

        return usuario2;
    }

    /** Metodo que deleta usuario
     * @param id 
     * @return 
     */
    public UsuarioLogin deletar(Integer id){

        Optional<UsuarioLogin> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            System.err.println("O id do usuario precisa ser passado: " + id + "Ou não encontramos seu id");
        }
        usuarioRepository.deleteById(id);
        return usuario.get();
    }

    
     /**
     * Metodo para atualizar o usuario.
     * @param produto que será para atualizar.
     * @param id do usuario para atualizar o sistema.
     * @return Retorna um valor atualizado.
     */
    public UsuarioLogin atualizaUsuario(Integer id, UsuarioLogin usuario){

        usuario.setId(id);
        UsuarioLogin resposta = new UsuarioLogin();
        resposta = usuarioRepository.save(usuario);

        return resposta;
    }
}
