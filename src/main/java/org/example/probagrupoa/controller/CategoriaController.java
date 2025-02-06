package org.example.probagrupoa.controller;

import org.example.probagrupoa.entity.Categoria;
import org.example.probagrupoa.entity.Producto;
import org.example.probagrupoa.service.ICategoriaService;
import org.example.probagrupoa.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private ICategoriaService service;

    /*=== GET ===*/

    @GetMapping
    public List<Categoria> listar(){
        return service.listar();
    }
}
