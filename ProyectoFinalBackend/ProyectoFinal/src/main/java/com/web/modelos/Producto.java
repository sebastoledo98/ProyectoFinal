package com.web.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity//se especifica que sera una entidad para JPA
@Table(name="Productos")//se especifica el nombre de la tabla para la persistencia en la base de datos
public class Producto {

    @Id//se especifica que el campo sera el id para la persistencia
    @Column(name="idProducto")//se especifica que el campo sera el id para la persistencia
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;//campo del id del producto

    private String nombre;//nombre del producto
    private String descripcion;//descripcion detallada del producto
    private double precio;//precio del producto
    private int stock;//cantidad disponible del producto
    private String imagen;//imagen que representa a la categoria

    //Relaciones
    @ManyToOne(fetch=FetchType.EAGER)// se aplica una relacion de N a 1, se aplica una forma de recuperacion inmediata cuando se inica la aplicacion
    //se especifica la llave foranea y las propiedades de la columna (puede ser nula, no es unica, se puede actualizar)
    @JoinColumn(name="idCategoria", nullable = true, unique = false, updatable = true)
    private Categoria categoria;//categoria a la que pertence el producto

    //obtener el id del producto
    public int getId(){
        return this.id;
    }

    //asignar el id del detalle
    public void setId(int id){
        this.id = id;
    }

    //obtener el nombre del producto
    public String getNombre(){
        return this.nombre;
    }

    //asginar el nombre del producto
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    //obtener la descripcion del producto
    public String getDescripcion(){
        return this.descripcion;
    }

    //asignar la descripcion del producto
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    //obtener el precio del producto
    public double getPrecio(){
        return this.precio;
    }

    //asignar el precio del producto
    public void setPrecio(double precio){
        this.precio = precio;
    }

    //obtener la cantidad disponible del producto
    public int getStock(){
        return this.stock;
    }

    //asignar la cantidad disponible del producto
    public void setStock(int stock){
        this.stock = stock;
    }

    //obtener el nombre del producto
    public String getImagen(){
        return this.imagen;
    }

    //asignar el nombre del producto
    public void setImagen(String imagen){
        this.imagen = imagen;
    }

    //obtener la categoria a la que pertenece el producto
    public Categoria getCategoria(){
        return this.categoria;
    }

    //asignar la categoria a la que pertenece el producto
    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }

    //funcion toString para imprimir la clase
    @Override
    public String toString(){
        return "Producto [codigo = " + id + ", descripcion = " + descripcion + ", precio = " + precio + ", stock = " + stock + ", imagen = " + imagen + ", categoria = " + categoria + "]";
    }
}
