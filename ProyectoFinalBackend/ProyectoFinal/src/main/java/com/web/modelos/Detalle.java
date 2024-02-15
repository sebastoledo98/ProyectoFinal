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
@Table(name="detalles")//se especifica el nombre de la tabla para la persistencia en la base de datos
public class Detalle {

    @Id//se especifica que el campo sera el id para la persistencia
    @Column(name="idDetalle")//se especifica que el campo sera el id para la persistencia
    @GeneratedValue(strategy = GenerationType.SEQUENCE)// se especifica que se autogeneren los valores de forma secuencial
    private int id;//campo del id del detalle

    @ManyToOne(fetch=FetchType.EAGER)// se aplica una relacion de N a 1, se aplica una forma de recuperacion inmediata cuando se inica la aplicacion
    //se especifica la llave foranea y las propiedades de la columna (puede ser nula, no es unica, se puede actualizar)
    @JoinColumn(name="idCarro", nullable = true, unique = false, updatable = true)
    private Carro carro;//carro al que pertenece el detalle

    private double subtotal;//valor subtotal de la compra, es el precio del producto por la cantidad
    private int cantidad;//cantidad del producto que se compra

    @ManyToOne(fetch=FetchType.EAGER)// se aplica una relacion de N a 1, se aplica una forma de recuperacion inmediata cuando se inica la aplicacion
    //se especifica la llave foranea y las propiedades de la columna (puede ser nula, no es unica, se puede actualizar)
    @JoinColumn(name="idProducto", nullable = true, unique = false, updatable = true)
    private Producto producto;//producto que se agrega en el detalle

    //obtener el id del detalle
    public int getId() {
        return id;
    }

    //asignar el id del detalle
    public void setId(int id) {
        this.id = id;
    }

    //obtener el producto que se compra en el detalle
    public Producto getProducto(){
        return this.producto;
    }

    //asignar el producto que se compra en el detalle
    public void setProducto(Producto producto){
        this.producto = producto;
    }

    //obtener el carro al que pertenece el detalle
    public Carro getCarro(){
        return this.carro;
    }

    //asignar el carro al que pertenece el detalle
    public void setCarro(Carro carro){
        this.carro = carro;
    }

    //obtener la cantidad del producto
    public int getCantidad() {
        return cantidad;
    }

    //asignar la cantidad del producto
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //obtener el subtotal del detalle
    public double getSubtotal() {
        return subtotal;
    }

    //calcular el subtotal del detalle, se debe llamar luego de que se asigne el producto y la cantidad
    public void setSubtotal() {
        subtotal = cantidad * producto.getPrecio();
    }

    //funcion toString para imprimir la clase
    @Override
    public String toString() {
        return "Detalle [id=" + id + ", carro = " + carro + ", producto = " + producto + ", cantidad=" + cantidad + ", subtotal=" + subtotal + "]";
    }

}
