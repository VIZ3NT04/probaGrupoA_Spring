package org.example.probagrupoa.controller;

import org.example.probagrupoa.entity.Producto;
import org.example.probagrupoa.entity.Usuario;
import org.example.probagrupoa.service.IProductoService;
import org.example.probagrupoa.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService service;

    /*=== POST ===*/
    @PostMapping
    public Usuario registrar(@RequestBody Usuario usuario) {
        return service.insertUser(usuario);
    }

    /*=== PUT ===*/
    @PutMapping
    public Usuario modificar(@RequestBody Usuario usuario) {
        return service.modificarUser(usuario);
    }

    /*=== DELETE ===*/
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        service.deleteUser(id);
    }


}
