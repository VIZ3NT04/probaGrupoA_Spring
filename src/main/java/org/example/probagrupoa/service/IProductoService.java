package org.example.probagrupoa.service;

import org.example.probagrupoa.entity.Producto;

import java.util.List;

public interface IProductoService {
    List<Producto> listar();
    List<Producto> filtrarPorName(String name);
    void detele(Integer id);
    List<Producto> filtrarPorPrice(Float price);
    Producto registrar(Producto producto);
    Producto modificar(Producto producto);
}
