import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { CategoriasService } from 'src/app/services/categorias.service';

@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  styleUrls: ['./categorias.component.scss']
})
export class CategoriasComponent implements OnInit{

  Listacategorias: any;

  cod?: number;

  constructor(private router: Router, private serviciosCategorias: CategoriasService){

  }

  ngOnInit(): void {
    this.Listacategorias=this.serviciosCategorias.getCategorias();
  }

  IrProductos(categoria: any){
    console.log("Categoria:", categoria);
    this.cod = categoria.id;
    console.log("Codigo de la categoria: " + this.cod);
    let params: NavigationExtras = {
      queryParams:{
        categoria: categoria
      }
    }

    this.router.navigate(['paginas/cat_productos/codigo='+this.cod], params);
  }
}
