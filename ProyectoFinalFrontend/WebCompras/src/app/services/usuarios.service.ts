import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../domain/Usuario';
import { environment } from 'src/enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor(private http: HttpClient) { }

  registrarUsuario(usuario: Usuario){
    let url = environment.PATH_WS+'/usuarios';
    return this.http.post<any>(url,usuario);
  }

  iniciarSesion(usuario: String, password: String){
    let url = environment.PATH_WS+'/usuarios/login?usr='+usuario+'&pass='+password;
    return this.http.get<any>(url)
  }
}
