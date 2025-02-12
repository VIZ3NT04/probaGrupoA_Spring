package org.example.probagrupoa.entity;

import jakarta.persistence.*;

@Entity
public class Foto {
    @Id
    private Integer id;

    private String url;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Relación con Usuario (una foto por usuario)

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @OneToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
