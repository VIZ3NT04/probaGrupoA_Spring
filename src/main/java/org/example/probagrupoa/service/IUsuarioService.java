package org.example.probagrupoa.service;

import org.example.probagrupoa.entity.Usuario;
import org.example.probagrupoa.entity.dto.RequestUserDto;

public interface IUsuarioService {
    Usuario insertUser(Usuario usuario);
    Usuario modificarUser(Usuario usuario);
    void deleteUser(Integer id);
    Usuario loginUsuario(String email,  String password);
}
