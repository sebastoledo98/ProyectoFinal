import { Component } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {
  paginas = [
    {path: 'paginas/inicio', title:'Inicio'},
    {path: 'paginas/acercade', title: 'Acerca de'},
    {path: 'paginas/categorias', title: 'Categorias'}
  ]
}
