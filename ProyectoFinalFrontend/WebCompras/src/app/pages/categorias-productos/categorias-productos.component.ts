import { Component } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { Categoria } from 'src/app/domain/Categoria';
import { Producto } from 'src/app/domain/Producto';
import { CategoriasService } from 'src/app/services/categorias.service';
import { ProductosService } from 'src/app/services/productos.service';

@Component({
  selector: 'app-categorias-productos',
  templateUrl: './categorias-productos.component.html',
  styleUrls: ['./categorias-productos.component.scss']
})
export class CategoriasProductosComponent {

  titulo: string = 'Productos de ';

  categoria: Categoria = new Categoria();  

  listaProductos?: any;

  cod?: number;

  codigo?: number;

  producto: Producto = new Producto();

  constructor(private router: Router, private route: ActivatedRoute, private serviciosCategoria: CategoriasService, private serviciosProducto: ProductosService){
    this.route.params.subscribe(params => {
      console.log("Parametros: " + params)
      if(params['codigo']){
        this.loadCategoria(params['codigo']);
        this.listaProductos = this.serviciosProducto.getProductosCategorias(params['codigo']);
        console.log("Verificacion: " + this.listaProductos);
      }
    })
  }

  loadCategoria(codigo: number){
    this.serviciosCategoria.getCategoriaPr(codigo).subscribe(data => {
      console.log("loadCategoria: " + data);
      this.categoria = <any>data;
    });
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
