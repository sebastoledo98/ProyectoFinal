import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  constructor(private http: HttpClient) { }

  getProductos(){
    let url = environment.PATH_WS+"/productos/list";
    return this.http.get<any>(url);
  }

  getProductoCodigo(codigo: number){
    console.log("Servicio codigo: " + codigo);
    let url = environment.PATH_WS+"/productos?"+codigo;
    return this.http.get<any>(url);
  }

  getProductosCategorias(codigo: string){
    console.log("Servicios Categorias codigo: " + codigo)
    let url = environment.PATH_WS+"/productos/categ?"+codigo;
    return this.http.get<any>(url);
  }
}
