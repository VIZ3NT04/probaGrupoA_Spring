package org.example.probagrupoa.service;

import org.example.probagrupoa.entity.Categoria;
import org.example.probagrupoa.entity.Producto;
import org.example.probagrupoa.repository.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

    @Autowired
    private ICategoriaRepository repo;


    @Override
    public List<Categoria> listar() {
            return repo.findAll();
    }
}
