package org.example.probagrupoa.controller;

import org.example.probagrupoa.entity.Categoria;
import org.example.probagrupoa.entity.Producto;
import org.example.probagrupoa.service.ICategoriaService;
import org.example.probagrupoa.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private ICategoriaService service;

    /*=== GET ===*/

    @GetMapping
    public ResponseEntity<List<Categoria>> listar(){
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria>insertar(@RequestBody Categoria categoria){
        return new ResponseEntity<>(service.insertar(categoria),HttpStatus.OK);
    }
}
