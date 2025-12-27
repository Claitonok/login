package com.loginpage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginpage.model.UsuarioLogin;
import com.loginpage.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;

    /**
     * 
     * @return RETORNA TODOS OS DADOS DO BANCO DE DADOS
     */
    @Operation(summary = "Nesse endpoint retorna todos os dados", description = "Retorna todos os dados do banco de dados")
    @GetMapping
    public ResponseEntity<List<UsuarioLogin>> obterUsuarios(){
        
        try {
            return new ResponseEntity<>(usuarioService.ObterTodos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    /**
     * ESSE METODO ESPERA PASSAR O {id}:
     * @param id 
     * @return RETORNA OS DADOS DO BANCO DE DADOS PELO {id}
     */
    @Operation(summary = "Nesse endpoint retorna todos os dados pelo Id do usuario!", description = "Retorna os dados do banco de dados pelo - {id}")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioLogin>> obterPorId(@PathVariable Integer id){
        
        try {
            
            Optional<UsuarioLogin> usuarioId = usuarioService.ObterPorId(id);

             if (usuarioId.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuarioId, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    /**
     * ESSE METODO SERVE PARA CADASTRAR POR ISSO DO @PostMapping
     * LEMBRAR DE PASSAR POR PARAMETRO NO METODO O @RequestBody NOME_DA_CLASSE | APELIDO DA CLASSE!!
     * @param usuario
     * @return
     */
    @PostMapping
    public ResponseEntity<UsuarioLogin> adicionaUsers(@RequestBody UsuarioLogin usuario) {
        return new ResponseEntity<>(usuarioService.Adicionar(usuario), HttpStatus.CREATED);
    }   

    /**
     * ESSE METODO ELE SERVE PARA EXCLUIR DO BANCO DE DADOS PELO id
     * @param id ESPERA PASSAR O {id} PELA URI
     * @return RETORNA O STATUS DE EXCLUSÃO
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UsuarioLogin> deletaUsers(@PathVariable Integer id){

        try {
              Optional<UsuarioLogin> usuarioId = usuarioService.ObterPorId(id);

             if (usuarioId.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            return new ResponseEntity<>(usuarioService.deletar(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    

    /**
     * ESSE METOOO ELE ATUALIZA PELO {id} PASSADO PELA URI
     * @param usuario
     * @param id
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioLogin> atualizar(@RequestBody UsuarioLogin usuario, @PathVariable Integer id){

        try {
              Optional<UsuarioLogin> usuarioId = usuarioService.ObterPorId(id);

             if (usuarioId.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(usuarioService.atualizaUsuario(id, usuario), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    /**
     * ESSE METODO SERVE PARA CADASTRAR POR ISSO DO @PostMapping
     * LEMBRAR DE PASSAR POR PARAMETRO NO METODO O @RequestBody NOME_DA_CLASSE | APELIDO DA CLASSE!!
     * @param usuario
     * @return
     */
    @Operation(summary = "Nesse endpoint faz o login do usuario", description = "Faz o login do usuario passando email e senha")
    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<UsuarioLogin> login(@PathVariable String email, @PathVariable String password) {

        try {
          
            System.out.println("Email: " + email + " Senha: " + password);

            UsuarioLogin usuario = new UsuarioLogin();
            usuario.setEmail(email);
            usuario.setPassword(password);

            UsuarioLogin usuarioLogado = usuarioService.loginUsers(usuario);
            if (usuarioLogado == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuarioLogado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }   

}
