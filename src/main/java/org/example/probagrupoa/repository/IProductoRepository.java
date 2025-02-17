package org.example.probagrupoa.repository;

import org.example.probagrupoa.entity.Categoria;
import org.example.probagrupoa.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductoRepository extends JpaRepository<Producto,Integer> {
    @Query("FROM Producto p WHERE p.name LIKE %:name%")
    List<Producto> filtrarPorName(@Param("name") String name);

    @Query("FROM Producto p WHERE p.price <= :price")
    List<Producto> filtrarPorPrice(@Param("price") Float price);

    @Query("FROM Producto p WHERE p.categoria.name = :nomCategoria")
    List<Producto> filtrarPorCategoria(@Param("nomCategoria") String nomCategoria);
}
