package org.example.probagrupoa.service;

import org.example.probagrupoa.entity.Categoria;
import org.example.probagrupoa.entity.Producto;
import org.example.probagrupoa.entity.dto.ProductoRequestDto;

import java.util.List;

public interface IProductoService {
    List<Producto> listar();
    //Producto buscarPorId(Integer id);
    List<Producto> filtrarPorCategoria(String nomCategoria);
    List<Producto> filtrarPorName(String name);
    void detele(Integer id);
    List<Producto> filtrarPorPrice(Float price);
    Producto registrar(ProductoRequestDto producto);
    Producto modificar(Producto producto);
}
