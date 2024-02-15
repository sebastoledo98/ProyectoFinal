import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {
  paginas = [
    {path: 'paginas/inicio', title:'Inicio'},
    {path: 'paginas/acercade', title: 'Acerca de'},
    {path: 'paginas/categorias', title: 'Categorias'},
    {path: 'paginas/contacto', title: 'Contactenos'},
    {path: 'paginas/registrarse', title: 'Registrarse'},
    {path: 'paginas/iniciarSesion', title: 'Iniciar sesion'}
  ]

  constructor(private route: Router){

  }

  IrContac(){
    this.route.navigate(['paginas/contacto']);
  }

  IrInic(){
    this.route.navigate(['paginas/inicio']);
  }
}
