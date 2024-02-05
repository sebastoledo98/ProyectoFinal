package com.web.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Productos")
public class Producto {

    @Id
    @Column(name="idProducto")
    //@GeneratedValue
    private int id;

    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    //Relaciones
    @ManyToOne
    @JoinColumn(name="idCategoria", nullable = true, unique = false, updatable = true)
    private Categoria categoria;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public double getPrecio(){
        return this.precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    public int getStock(){
        return this.stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public Categoria getCategoria(){
        return this.categoria;
    }

    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }

    public String toString(){
        return "Producto [codigo = " + id + ", descripcion = " + descripcion + ", precio = " + precio + ", stock = " + stock + ", categoria = " + categoria + "]";
    }
}
