package org.example.probagrupoa.entity;

import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private String antiquity;

    //private Label etiqueta;

    //private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String image;

    private float price;

    // Te que ser un Double
    private String maps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAntiquity() {
        return antiquity;
    }

    public void setAntiquity(String antiquity) {
        this.antiquity = antiquity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMaps() {
        return maps;
    }

    public void setMaps(String maps) {
        this.maps = maps;
    }

    public Producto() {}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto(Integer id, String name, String description, String antiquity,
                    Usuario usuario, String image, float price, String maps) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.antiquity = antiquity;
        this.usuario = usuario;
        this.image = image;
        this.price = price;
        this.maps = maps;
    }
}
