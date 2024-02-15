package com.web.modelos;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity//se especifica que sera una entidad para JPA
@Table(name="usuarios")//se especifica el nombre de la tabla para la persistencia en la base de datos
public class Usuario {

    @Id//se especifica que el campo sera el id para la persistencia
    @Column(name="idUsuario")//se especifica que el campo sera el id para la persistencia
    @GeneratedValue(strategy = GenerationType.SEQUENCE)// se especifica que se autogeneren los valores de forma secuencial
    private int id;//campo del id del usuario

    private String usuario;//nombre de usuario para el login
    private String password;//contrasena del usuario para el login
    private String nombres;//nombres completos del usuario
    private String apellidos;//apellidos completos del usuario
    private String email;//email del usuario
    private String ubicacion;//ubicacion del usuario, ciudad
    private String direccion;//direccion domicialiario del usuario
    private String telefono;//numero de telefono personal del usuario
    private double saldo = 500.00;//saldo de la cuenta del usuario, se asigna 500 por defecto

    //obtener el id del usuario
    public int getId() {
        return id;
    }

    //asignar el id del usuario
    public void setId(int id) {
        this.id = id;
    }

    //obtener la direccion domicialiaria del usuario
    public String getDireccion() {
        return direccion;
    }

    //asignar al direccion domicialiaria del usuario
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //obtener los nombres completos del usuario
    public String getNombres() {
        return nombres;
    }

    //asignar los nombres completos del usuario
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    //obtener el usuario para el login
    public String getUsuario() {
        return usuario;
    }

    //asignar el usuario para el login
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    //obtener la constrasena para el login
    public String getPassword() {
        return password;
    }

    //asignar la contrasena para el login
    public void setPassword(String password) {
        this.password = password;
    }

    //obtener los apellidso completos del usuario
    public String getApellidos() {
        return apellidos;
    }

    //asignar los apellidos completos del usuario
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    //obtener el email del usuario
    public String getEmail() {
        return email;
    }

    //asignar el email del usuario
    public void setEmail(String email) {
        this.email = email;
    }

    //obtener la ubicacion del usuario
    public String getUbicacion() {
        return ubicacion;
    }

    //asignar la ubicacion dl usuario
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    //obtener el numero de telefono personal del usuario
    public String getTelefono() {
        return telefono;
    }

    //asignar el numero de telefono personal del usuario
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //obtener el saldo de la cuenta del usuario
    public double getSaldo(){
        return this.saldo;
    }

    //asignar el saldo de la cuenta del usuario
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    //funcion toString para imprimir la clase
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", usuario=" + usuario + ", password=" + password + ", nombres=" + nombres
                    + ", apellidos=" + apellidos + ", email=" + email + ", ubicacion=" + ubicacion + ", direccion="
                    + direccion + ", telefono=" + telefono + "]";
    }

}
