package org.example.probagrupoa.repository;

import org.example.probagrupoa.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository  extends JpaRepository<Categoria,Integer> {

}
