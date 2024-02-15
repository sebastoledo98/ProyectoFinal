import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  constructor(private router:Router){

  }

  busqueda: String = "";

  IrInic(){
    this.router.navigate(['paginas/inicio'])
  }

  IrCarr(){
    this.router.navigate(['paginas/carrito']);
  }

  IrIniSes(){
    this.router.navigate(['paginas/iniciarSesion']);
  }

  IrReg(){
    this.router.navigate(['paginas/registrarse']);
  }

  buscar(){
    console.log(this.busqueda);
    this.router.navigate(['paginas/buscador/nombre='+this.busqueda]);
  }
}
