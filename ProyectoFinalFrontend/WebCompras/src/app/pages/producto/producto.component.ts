import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Producto } from 'src/app/domain/Producto';
import { Usuario } from 'src/app/domain/Usuario';
import { ProductosService } from 'src/app/services/productos.service';
import { TransaccionesService } from 'src/app/services/transacciones.service';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.scss']
})
export class ProductoComponent{
  
  producto: Producto = new Producto();

  usuarioObj: Usuario = new Usuario();

  cantidad: number;

  cod: number = 0;

  usuario: any;

  codigoUser: number = 0;

  constructor(private router: Router, private route: ActivatedRoute, private servicioProductos: ProductosService, private servicioTransaccion: TransaccionesService){
    this.route.params.subscribe(params => {
      console.log("Parametros: " + params)
      if(params['codigo']){
        this.loadProducto(params['codigo']);
      }
    });
    this.cantidad = 1;
  }

  loadProducto(codigo: number){
    this.servicioProductos.getProductoCodigo(codigo).subscribe(data => {
      console.log("loadProducto: " + data);
      this.producto = <any>data;
      this.cod = codigo;
    });
  }

  AgregarDetalle(){
    console.log(localStorage.getItem('usuario'));
    this.usuario = JSON.parse(localStorage.getItem('usuario')!);
    console.log("Codigo del usuario: " + this.usuario);
    this.servicioTransaccion.putDetalle(this.cod,this.usuario.id,this.cantidad).subscribe(data => {
      console.log(data);
      if(data.codigo == 1){
        alert("Producto agregado")
      }else{
        alert('Error al insertar' + data.mensaje);
      }
    });
  }  

}
