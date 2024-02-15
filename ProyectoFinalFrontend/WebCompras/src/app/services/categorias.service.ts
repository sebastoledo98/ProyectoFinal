import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class CategoriasService {

  constructor(private http: HttpClient) { }

  getCategorias(){
    let url = environment.PATH_WS+"/categorias/list";
    return this.http.get<any>(url);
  }

  getCategoriaPr(codigo: number){
    console.log("Servicio categoria: " + codigo);
    let url = environment.PATH_WS+"/categorias?"+codigo;
    return this.http.get<any>(url);
  }

}
