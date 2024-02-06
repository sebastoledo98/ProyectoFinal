package com.web.modelos;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@Table(name="carros")
public class Carro{

    @Id
    @Column(name="idFactura")
    //@GeneratedValue
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idUsuario", nullable = true, unique = false, updatable = true)
    private Usuario usuario;

    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name="idCarro")
    //private Carro carro;

    private String numero;
    private double descuento;
    private double total;
    private LocalDateTime fecha;
    //estado del carrito
    //true si esta abierto (se pueden agregar productos), false si esta cerrad (no es posible agregar productos, solo es para informacion)
    private boolean estado;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    /*
    public Carro getCarro(){
        return this.carro;
    }

    public void setCarro(Carro carro){
        this.carro = carro;
    }
    */

    public String getNumero(){
        return this.numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    public double getTotal(){
        return this.total;
    }

    public void setTotal(double total){
        this.total = total;
    }

    public double getDescuento(){
        return this.descuento;
    }

    public void setDescuento(double descuento){
        this.descuento = descuento;
    }

    public LocalDateTime getFecha(){
        return this.fecha;
    }

    public void setFecha(LocalDateTime fecha){
        this.fecha = fecha;
    }

    public boolean getEstado(){
        return this.estado;
    }

    public void setEstado(boolean estado){
        this.estado = estado;
    }

    @Override
    public String toString(){
        //return "Factura [codigo =" + id + ", usuario = " + usuario.getId() + ", carro = " + carro.getId() + ", total = " + total + ", fecha = " + fecha + ", estado = " + estado + "]";
        return "Carro [codigo =" + id + ", usuario = " + usuario.getId() + ", carro = " + numero + ", total = " + total + ", fecha = " + fecha + ", estado = " + estado + "]";
    }
}
