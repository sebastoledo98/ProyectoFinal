package com.web.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Carros")
public class Carro {

    @Id
    @Column(name="idCarro")
    //@GeneratedValue
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idFactura", nullable = true, unique = false, updatable = true)
    private Factura factura;

    private double subtotal;
    private double cantidad;

    @ManyToOne(fetch=FetchType.LAZY)
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

    public Factura getFactura(){
        return this.factura;
    }

    public void setFactura(Factura factura){
        this.factura = factura;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal() {
        subtotal = cantidad * producto.getPrecio();
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
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
        return "Carro [id=" + id + ", factura = " + factura + ", producto = " + producto + ", subtotal=" + subtotal + ", cantidad=" + cantidad + "]";
    }

}
