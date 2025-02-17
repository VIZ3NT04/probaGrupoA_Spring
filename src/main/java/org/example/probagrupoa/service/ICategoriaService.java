package org.example.probagrupoa.service;

import org.example.probagrupoa.entity.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICategoriaService {
    List<Categoria> listar();
    Categoria insertar(Categoria categoria);
}
