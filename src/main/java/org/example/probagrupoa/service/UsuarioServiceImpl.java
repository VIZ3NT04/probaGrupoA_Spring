package org.example.probagrupoa.service;

import org.example.probagrupoa.entity.Usuario;
import org.example.probagrupoa.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
    @Autowired
    private IUsuarioRepository repo;

    @Override
    public Usuario insertUser(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public Usuario modificarUser(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public void deleteUser(Integer id) {
        repo.deleteById(id);
    }
}
