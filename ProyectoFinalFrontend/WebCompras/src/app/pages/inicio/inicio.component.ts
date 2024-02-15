import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { ProductosService } from 'src/app/services/productos.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})

export class InicioComponent implements OnInit{

  titulo: string = 'PRODUCTOS DESTACADO';

  cod?: number;

  Listaproductos: any;

  constructor(private router:Router, private productosService: ProductosService){

  }

  ngOnInit(): void {
    this.Listaproductos = this.productosService.getProductos();
  }

  IrProd(producto: any){
    console.log("Producto:", producto);
    this.cod = producto.id;
    console.log("Codigo del producto: " + this.cod);
    let params: NavigationExtras = {
      queryParams:{
        producto: producto
      }
    }

    this.router.navigate(['paginas/producto/codigo='+this.cod], params);
  }

}

