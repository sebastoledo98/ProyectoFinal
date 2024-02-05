package com.web.modelos;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="Usuarios")
public class Usuario {

    @Id
    @Column(name="idUsuario")
    private int id;

    private String usuario;
    private String password;
    private String nombres;
    private String apellidos;
    private String email;
    private String ubicacion;
    private String direccion;
    private String telefono;

    //@OneToMany(mappedBy="idFacturas")
    //private List<Factura> facturas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /*
    public void addFactura(Factura factura){
        if(facturas == null)
            facturas = new ArrayList<Factura>();
        facturas.add(factura);
    }

    public List<Factura> getFacturas(){
        return this.facturas;
    }
    */

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", usuario=" + usuario + ", password=" + password + ", nombres=" + nombres
                    + ", apellidos=" + apellidos + ", email=" + email + ", ubicacion=" + ubicacion + ", direccion="
                    + direccion + ", telefono=" + telefono + "]";
    }

}
