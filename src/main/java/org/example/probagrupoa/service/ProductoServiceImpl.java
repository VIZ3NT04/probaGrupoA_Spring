package org.example.probagrupoa.service;

import org.example.probagrupoa.entity.Producto;
import org.example.probagrupoa.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService{
    @Autowired
    private IProductoRepository repo;


    @Override
    public List<Producto> listar() {
        return repo.findAll();
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Producto> filtrarPorName(String name) {
        List<Producto> productos = repo.filtrarPorName(name);
        return productos;
    }

    @Override
    public void detele(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Producto> filtrarPorPrice(Float price) {
        List<Producto> productos = repo.filtrarPorPrice(price);
        return productos;
    }

    @Override
    public Producto registrar(Producto producto) {
        return repo.save(producto);
    }

    @Override
    public Producto modificar(Producto producto) {
        return repo.save(producto);
    }
}
