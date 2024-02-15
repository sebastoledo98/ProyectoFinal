import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { ProductosService } from 'src/app/services/productos.service';
import { TransaccionesService } from 'src/app/services/transacciones.service';

@Component({
  selector: 'app-buscador',
  templateUrl: './buscador.component.html',
  styleUrls: ['./buscador.component.scss']
})
export class BuscadorComponent implements OnInit{
  titulo: string = 'PRODUCTOS DESTACADO';

  cod?: number;

  Listaproductos: any;

  constructor(private router:Router, private productosService: ProductosService, private route: ActivatedRoute, private servicioTransaccion: TransaccionesService){

  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      console.log("Parametros: " + params)
      if(params['nombre']){
        console.log(params['nombre']);
        this.loadProducto(params['nombre']);
      }
    });
    
  }

  loadProducto(producto: string){
    this.Listaproductos = this.servicioTransaccion.getProdutosBusqueda(producto);
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
