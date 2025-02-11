package org.example.probagrupoa.controller;

import jakarta.validation.Valid;
import org.example.probagrupoa.entity.Usuario;
import org.example.probagrupoa.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return new ResponseEntity<>(service.listaUsuarios(), HttpStatus.OK);
    }

    /*=== LOGIN ===*/
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@Valid @RequestParam("email") String email, @Valid @RequestParam("password") String password) {
        System.out.println("Estan probant a loggearse");
        Usuario user = service.loginUsuario(email,password);
        System.out.println(user.getName() + " " + user.getPassword() + " " + user.getEmail());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }


    /*=== POST ===*/

    // PORROOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    @PostMapping
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario usuario) {

        Usuario user = service.insertUser(usuario);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }

    }

    /*=== PUT ===*/
    @PutMapping
    public ResponseEntity<Usuario> modificar(@Valid @RequestBody Usuario usuario) {
        Usuario user = service.modificarUser(usuario);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    /*=== DELETE ===*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id){
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
