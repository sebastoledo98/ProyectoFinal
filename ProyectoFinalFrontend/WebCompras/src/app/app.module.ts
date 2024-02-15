import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InicioComponent } from './pages/inicio/inicio.component';
import { MenuComponent } from './pages/menu/menu.component';
import { CategoriasComponent } from './pages/categorias/categorias.component';
import { CarritoComponent } from './pages/carrito/carrito.component';
import { AcercadeComponent } from './pages/acercade/acercade.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { CategoriasProductosComponent } from './pages/categorias-productos/categorias-productos.component';
import { IniciarSesionComponent } from './pages/iniciar-sesion/iniciar-sesion.component';
import { RegistrarseComponent } from './pages/registrarse/registrarse.component';
import { ContactoComponent } from './pages/contacto/contacto.component';
import { EditarUsuarioComponent } from './pages/editar-usuario/editar-usuario.component'
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { ProductoComponent } from './pages/producto/producto.component';
import { BuscadorComponent } from './pages/buscador/buscador.component';
import { ServiceWorkerModule } from '@angular/service-worker';

@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    MenuComponent,
    CategoriasComponent,
    CarritoComponent,
    AcercadeComponent,
    FooterComponent,
    HeaderComponent,
    CategoriasProductosComponent,
    IniciarSesionComponent,
    RegistrarseComponent,
    ContactoComponent,
    EditarUsuarioComponent,
    ProductoComponent,
    BuscadorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: !isDevMode(),
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
