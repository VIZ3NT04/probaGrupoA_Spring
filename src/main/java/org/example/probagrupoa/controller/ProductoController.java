package org.example.probagrupoa.controller;

import org.example.probagrupoa.entity.Producto;
import org.example.probagrupoa.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService service;

    /*=== GET ===*/

    @GetMapping
    public List<Producto> listar(){
        return service.listar();
    }

    @GetMapping("filtrarPorNombre/{name}")
    public List<Producto> listarPorName(@PathVariable("name") String name){
        return service.filtrarPorName(name);
    }

    @GetMapping("filtrarPorPrecio/{price}")
    public List<Producto> listarPorPrice(@PathVariable("price") float price){
        return service.filtrarPorPrice(price);
    }

    /*=== POST ===*/
    @PostMapping
    public Producto registrar(@RequestBody Producto producto) {
        return service.registrar(producto);
    }

    /*=== PUT ===*/
    @PutMapping
    public Producto modificar(@RequestBody Producto producto) {
        return service.modificar(producto);
    }

    /*=== DELETE ===*/
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        service.detele(id);
    }




}
