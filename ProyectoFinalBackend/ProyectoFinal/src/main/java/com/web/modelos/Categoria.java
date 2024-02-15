package com.web.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity//se especifica que sera una entidad para JPA
@Table(name="categorias")//se especifica el nombre de la tabla para la persistencia en la base de datos
public class Categoria {

    @Id//se especifica que el campo sera el id para la persistencia
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="idCategoria")//se especifica que el campo sera el id para la persistencia
    private int id;//campo del id del carro

    private String nombre;//nombre de la categoria
    private String descripcion;//descripcion detallada de la categoria
    private String imagen;//imagen que representa a la categoria

    //obtener el id
    public int getId(){
        return this.id;
    }

    //asignar el id
    public void setId(int id){
        this.id = id;
    }

    //obtener el nombre de la categoria
    public String getNombre(){
        return this.nombre;
    }

    //asginar el nombre de la categoria
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    //obtener la descripcion de la categoria
    public String getDescripcion(){
        return this.descripcion;
    }

    //asignar el nombre de la categoria
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    //obtener el nombre de la imagen de la categoria
    public String getImagen(){
        return this.imagen;
    }

    //asignar el nombre de la imagen de la categoria
    public void setImagen(String imagen){
        this.imagen = imagen;
    }

    //funcion toString para imprimir la clase
    @Override
    public String toString(){
        return "Categoria [codigo = " + id + ", nombre = " + nombre + ", descripcion = " + descripcion + ", imagen = " + imagen + "]";
    }
}
