package com.web.modelos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Detalles")
public class Detalle {

    @Id
    @Column(name="idDetalle")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idCarro", nullable = true, unique = false, updatable = true)
    private Carro carro;

    private double subtotal;
    private int cantidad;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idProducto", nullable = true, unique = false, updatable = true)
    private Producto producto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto(){
        return this.producto;
    }

    public void setProducto(Producto producto){
        this.producto = producto;
    }

    public Carro getCarro(){
        return this.carro;
    }

    public void setCarro(Carro carro){
        this.carro = carro;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal() {
        subtotal = cantidad * producto.getPrecio();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /*
    public void addProducto(Producto producto){
        if(productos == null)
            productos = new ArrayList<Producto>();
        productos.add(producto);
    }

    public List<Producto> getProductos(){
        return this.productos;
    }
    */

    @Override
    public String toString() {
        return "Detalle [id=" + id + ", carro = " + carro + ", producto = " + producto + ", subtotal=" + subtotal + ", cantidad=" + cantidad + "]";
    }

}
