import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TransaccionesService } from 'src/app/services/transacciones.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.scss']
})

export class CarritoComponent implements OnInit{
  titulo: string = 'CARRITO DE COMPRAS';

  ListaDetalles: any;

  usuario?: any;

  cod: number = 0;

  sizeCarrito: number = 0;

  precioTot: number = 0;
  precioRed: number = 0;

  constructor(private router: Router, private serviceTransacciones: TransaccionesService){
    this.obtenerDetalles();
  }

  ngOnInit(): void {
    this.obtenerDetalles();
  }

  obtenerDetalles(){
    this.usuario = JSON.parse(localStorage.getItem('usuario')!);
    console.log("Codigo del usuario: " + this.usuario);
    this.serviceTransacciones.getCarroUsuario(this.usuario.id).subscribe(data=>{
      console.log(data);
      this.precioTot = data.total;
      this.precioRed = Number(this.precioTot.toFixed(2));
      this.cod = data.id;
      console.log("codCarro: " + this.cod);
      this.ListaDetalles = this.serviceTransacciones.getDetalles(this.cod);
      this.serviceTransacciones.getDetalles(this.cod).subscribe(data => {
        console.log("detalles: " +Object.keys(data).length);
        this.sizeCarrito = Object.keys(data).length;
      })
      console.log("lista" + this.ListaDetalles);
    });
  }

  borrarProducto(codigoProd: number){
    console.log("codigoProducto: " + codigoProd)
    //usuario: number, carro: number, producto: number
    if(confirm("¿Desea borrar el producto?")){
      alert("Se borro con exito")
      this.serviceTransacciones.deleteDetalleUsuario(this.usuario.id,this.cod,codigoProd).subscribe(data=>{
        console.log(data);
      });
      this.ngOnInit();
    }else{
      this.ngOnInit();
    }
  }

  aceptarCompra(){
    //carro: number, usuario:number
    if(confirm("¿Desea confirmar la compra?")){
      alert("La compra se realizo con exito");
      this.serviceTransacciones.confirmarCarro(this.cod,this.usuario.id).subscribe(data=>{
        console.log(data);
        this.ngOnInit();
      })
    }else{
      this.ngOnInit();
    }
  }

  borrarCarrito(){
    if(confirm("¿Desea borrar el carrito?")){
      alert("El carrito se borro con exito");
      this.serviceTransacciones.borrarCarro(this.cod,this.usuario.id).subscribe(data=>{
        console.log(data);
      })
      this.router.navigate(['paginas/inicio']);
    }else{
      this.ngOnInit();
    }
  }
}
