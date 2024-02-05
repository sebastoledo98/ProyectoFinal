package com.web.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Categorias")
public class Categoria {

    @Id
    //@GeneratedValue
    @Column(name="idCategoria")
    private int id;

    private String nombre;
    private String descripcion;

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

    public String toString(){
        return "Categoria [codigo = " + id + ", nombre = " + nombre + ", descripcion = " + descripcion + "]";
    }
}
