package org.example.probagrupoa.service;

import jakarta.transaction.Transactional;
import org.example.probagrupoa.entity.Usuario;
import org.example.probagrupoa.repository.IUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
    @Autowired
    private IUsuarioRepository repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public Usuario insertUser(Usuario usuario) {
        try {
            System.out.println("CANCERRR ->>>>>>>>>>>>>>>>>><<"+usuario.getName());
            return repo.save(usuario);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException("Error de concurrencia al insertar el usuario", e);
        }
    }

    @Override
    public Usuario modificarUser(Usuario usuario) {
        System.out.println("Usuario encontrado: " + usuario.getName() + " " + usuario.getEmail());
        return repo.save(usuario);
    }

    @Override
    public void deleteUser(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Usuario loginUsuario(String email, String password) {

            System.out.println("==================================================================");
            Usuario user = repo.loginUsuario(email, password);
            if (user != null) {
                System.out.println("Usuario encontrado: " + user.getName());
            } else {
                System.out.println("Usuario no encontrado o credenciales incorrectas.");
            }
            return user;
        }

    @Override
    public List<Usuario> listaUsuarios() {
        return repo.findAll();
    }


}
