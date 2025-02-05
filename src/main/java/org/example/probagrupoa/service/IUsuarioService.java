package org.example.probagrupoa.service;

import org.example.probagrupoa.entity.Usuario;

public interface IUsuarioService {
    Usuario insertUser(Usuario usuario);
    Usuario modificarUser(Usuario usuario);
    void deleteUser(Integer id);
}
