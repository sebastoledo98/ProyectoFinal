import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class TransaccionesService {

  constructor(private http: HttpClient) { }

  getDetalles(codigo: number){
    let url = environment.PATH_WS+"/compras/detalle?carro="+codigo;
    console.log(url);
    return this.http.get<any>(url);
  }

  putDetalle(producto: number, usuario: number, cantidad: number){
    let url = environment.PATH_WS+"/compras/detalle?"+producto+"&usuario="+usuario+"&cantidad="+cantidad;
    console.log(url);
    return this.http.put<any>(url,"");
  }

  getCarroUsuario(usuario: number){
    let url = environment.PATH_WS+"/compras/carroUser?usuario="+usuario;
    return this.http.get<any>(url);
  }

  deleteDetalleUsuario(usuario: number, carro: number, producto: number){
    let url = environment.PATH_WS+"/compras/detalle?usuario="+usuario+"&carro="+carro+"&producto="+producto;
    return this.http.delete<any>(url);
  }

  confirmarCarro(carro: number, usuario:number){
    let url = environment.PATH_WS+"/compras/confirmar?carro="+carro+"&usuario="+usuario;
    return this.http.put<any>(url,"");
  }

  borrarCarro(carro: number, usuario:number){
    let url = environment.PATH_WS+"/compras/carro?carro="+carro+"&usuario="+usuario;
    return this.http.delete<any>(url);
  }

  getProdutosBusqueda(nom: string){
    let url = environment.PATH_WS+"/compras/prods?"+nom;
    console.log(url);
    return this.http.get<any>(url);
  }

}
