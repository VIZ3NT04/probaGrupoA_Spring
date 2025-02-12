package org.example.probagrupoa.service;

import org.example.probagrupoa.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario insertUser(Usuario usuarioDto);
    Usuario modificarUser(Usuario usuario);
    void deleteUser(Integer id);
    Usuario loginUsuario(String email,  String password);
    List<Usuario> listaUsuarios();
}
