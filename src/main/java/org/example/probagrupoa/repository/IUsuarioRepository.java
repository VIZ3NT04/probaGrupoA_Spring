package org.example.probagrupoa.repository;

import org.example.probagrupoa.entity.Producto;
import org.example.probagrupoa.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
}
