import { Categoria } from "./Categoria";

export class Producto{
    codigo?: number;
    nombre?: String;
    descripcion?: String;
    precio?: number;
    stock?: number;
    imagen?: String;
    categoria?: Categoria;
}