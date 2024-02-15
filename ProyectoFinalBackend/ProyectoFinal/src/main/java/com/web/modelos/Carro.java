package com.web.modelos;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity //se especifica que sera una entidad para JPA
@Table(name="carros") //se especifica el nombre de la tabla para la persistencia en la base de datos
public class Carro{

    @Id//se especifica que el campo sera el id para la persistencia
    @Column(name="idCarro")//se especifica el nombre de la columna para la tabla de la base de datos
    @GeneratedValue(strategy = GenerationType.SEQUENCE)// se especifica que se autogeneren los valores de forma secuencial
    private int id;//campo del id del carro

    @OneToOne(fetch = FetchType.EAGER)// se aplica una relacion de 1 a 1, se aplica una forma de recuperacion inmediata cuando se inica la aplicacion
    //se especifica la llave foranea y las propiedades de la columna (puede ser nula, no es unica, se puede actualizar)
    @JoinColumn(name="idUsuario", nullable = true, unique = false, updatable = true)
    private Usuario usuario;//usuario al que pertenece el carro

    private String numero;// el numero del carro, se utiliza para generar la factura
    private double descuento;//el descuento que se aplica en la compra total
    private double total;//valor total de la compra aplicando el descuento
    private LocalDateTime fecha;//fecha en la que se crea el carro
    //estado del carrito
    //true si esta abierto (se pueden agregar productos), false si esta cerrad (no es posible agregar productos, solo es para informacion)
    private boolean estado;

    //obtener el id
    public int getId(){
        return this.id;
    }

    //asignar el id
    public void setId(int id){
        this.id = id;
    }

    //obtener el usuario del carro
    public Usuario getUsuario(){
        return this.usuario;
    }

    //asignar el usuario al carro
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    //obtener el numero de la factura
    public String getNumero(){
        return this.numero;
    }

    //asignar el numero de la factura
    public void setNumero(String numero){
        this.numero = numero;
    }

    //obtener el total del carro
    public double getTotal(){
        return this.total;
    }

    //asignar el total del carro
    public void setTotal(double total){
        this.total = total;
    }

    //obtener el descuento que se aplica al carro
    public double getDescuento(){
        return this.descuento;
    }

    //asignar el descuento a aplicar al carro
    public void setDescuento(double descuento){
        this.descuento = descuento;
    }

    //obtener la fecha en la que se creo el carro
    public LocalDateTime getFecha(){
        return this.fecha;
    }

    //asignar la fecha en la que se creo el carro
    public void setFecha(LocalDateTime fecha){
        this.fecha = fecha;
    }

    //obtener el estado del carro
    public boolean getEstado(){
        return this.estado;
    }

    //asignar el estado del carro
    public void setEstado(boolean estado){
        this.estado = estado;
    }

    //funcion toString para imprimir la clase
    @Override
    public String toString(){
        return "Carro [codigo =" + id + ", usuario = " + usuario.getId() + ", carro = " + numero + ", total = " + total + ", fecha = " + fecha + ", estado = " + estado + "]";
    }
}
