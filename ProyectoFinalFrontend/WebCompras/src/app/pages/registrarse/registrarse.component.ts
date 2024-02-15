import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/domain/Usuario';
import { UsuariosService } from 'src/app/services/usuarios.service';

@Component({
  selector: 'app-registrarse',
  templateUrl: './registrarse.component.html',
  styleUrls: ['./registrarse.component.scss']
})
export class RegistrarseComponent {

  usuarios: any;

  usuario: Usuario = new Usuario();

  cont?: string;
  contVer?: string;
  mensajeAviso?: string;

  constructor(private usuarioServicios: UsuariosService, private router: Router){
    
  }

  guardarUsuario(){
    if(this.cont != this.contVer){
      console.log("Contraseña incorrecta");
      this.mensajeAviso = "Las dos contraseñas no son iguales";
    }else{
      console.log("Contraseña correcta");
      this.mensajeAviso = "";
      this.usuarioServicios.registrarUsuario(this.usuario).subscribe(data=>{
        console.log(data);
      });
      this.router.navigate(['paginas/inicio']);
    }
  }

  botonCancelar(){
    this.router.navigate(['paginas/inicio']);
  }
}
