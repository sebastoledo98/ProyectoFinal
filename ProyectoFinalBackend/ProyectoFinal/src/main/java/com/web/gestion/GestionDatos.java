package com.web.gestion;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.util.List;
import java.time.LocalDateTime;

import com.web.modelos.*;

@Singleton
@Startup
public class GestionDatos {

    @Inject
    private GestionUsuarios gUsuarios;
    @Inject
    private GestionDetalles gDetalles;
    @Inject
    private GestionCategorias gCategorias;
    @Inject
    private GestionCarros gCarros;
    @Inject
    private GestionProductos gProductos;

    @PostConstruct
    public void init(){
        System.out.println("iniciando");
        
        /*
        //usuario 1
        Usuario usuario = new Usuario();
        System.out.println(usuario);
        usuario.setId(1);
        usuario.setUsuario("pepito");
        usuario.setPassword("pepito123");
        usuario.setNombres("Pepe");
        usuario.setApellidos("Perez");
        usuario.setEmail("pepe@ejemplo.com");
        usuario.setUbicacion("Cuenca");
        usuario.setDireccion("Av. Don Bosco");
        usuario.setTelefono("0986532147");
        usuario.setSaldo(120.00);
        System.out.println(usuario);
        gUsuarios.guardarUsuario(usuario);

        //usuario 2
        usuario = new Usuario();
        usuario.setId(2);
        usuario.setUsuario("diego");
        usuario.setPassword("diego123");
        usuario.setNombres("Diego");
        usuario.setApellidos("Gomez");
        usuario.setEmail("diego@ejemplo.com");
        usuario.setUbicacion("Quito");
        usuario.setDireccion("Av. Don Bosco");
        usuario.setTelefono("0986532147");
        usuario.setSaldo(325.00);
        gUsuarios.guardarUsuario(usuario);

        //Categorias
        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Belleza");
        categoria.setDescripcion("Productos para cuidado personal y cosmeticos");
        gCategorias.guardarCategoria(categoria);

        categoria = new Categoria();
        categoria.setId(2);
        categoria.setNombre("Computadoras");
        categoria.setDescripcion("Computadoras personales y portatiles");
        gCategorias.guardarCategoria(categoria);

        categoria = new Categoria();
        categoria.setId(3);
        categoria.setNombre("Deportes");
        categoria.setDescripcion("Implementos para ejercicio y deportes");
        gCategorias.guardarCategoria(categoria);

        categoria = new Categoria();
        categoria.setId(4);
        categoria.setNombre("Electronicos");
        categoria.setDescripcion("Productos electronicos de uso diario");
        gCategorias.guardarCategoria(categoria);

        categoria = new Categoria();
        categoria.setId(5);
        categoria.setNombre("Hogar");
        categoria.setDescripcion("Decorativos para interiores y exteriores");
        gCategorias.guardarCategoria(categoria);

        categoria = new Categoria();
        categoria.setId(6);
        categoria.setNombre("Jugueteria");
        categoria.setDescripcion("Juguetes para niños");
        gCategorias.guardarCategoria(categoria);

        categoria = new Categoria();
        categoria.setId(7);
        categoria.setNombre("Libros");
        categoria.setDescripcion("Libros e implementos para la lectura");
        gCategorias.guardarCategoria(categoria);

        categoria = new Categoria();
        categoria.setId(8);
        categoria.setNombre("Ropa");
        categoria.setDescripcion("Ropa casual y formal para mujeres y hombres");
        gCategorias.guardarCategoria(categoria);

        categoria = new Categoria();
        categoria.setId(9);
        categoria.setNombre("Salud");
        categoria.setDescripcion("Productos para el cuidado de la salud");
        gCategorias.guardarCategoria(categoria);

        //Productos
        //Belleza
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("L'Oreal, Paris Magic, Crema BB para embellecimiento");
        producto.setDescripcion("BB Cream es un artículo imprescindible en tu régimen de belleza y debe ser el paso final antes de aplicar tu maquillaje. Una vez que hayas eliminado las células muertas de la piel y hayas hecho tu diligencia de limpieza.");
        producto.setPrecio(14.99);
        producto.setStock(60);
        producto.setImagen("bell1");
        producto.setCategoria(gCategorias.buscarCategoriaId(1));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(2);
        producto.setNombre("essence - Máscara de pestañas postizas");
        producto.setDescripcion("essence - Máscara de pestañas postizas de la princesa Lash, sin gluten y no testado en animales, color negro");
        producto.setPrecio(9.73);
        producto.setStock(38);
        producto.setImagen("bell2");
        producto.setCategoria(gCategorias.buscarCategoriaId(1));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(3);
        producto.setNombre("Neutrogena Toallita de limpieza facial individual");
        producto.setDescripcion("Neutrogena Toallita de limpieza facial individual, toallitas faciales diarias para eliminar la suciedad, aceite, maquillaje y máscara impermeable, suave, envueltas individualmente, 100% fibras a base de plantas, 20 unidades");
        producto.setPrecio(9.19);
        producto.setStock(53);
        producto.setImagen("bell3");
        producto.setCategoria(gCategorias.buscarCategoriaId(1));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(4);
        producto.setNombre("Peripera Lip Tint Ink the Velvet");
        producto.setDescripcion("Peripera Lip Tint Ink the Velvet | color de alto pigmento, duradero, ligero, no probado en animales, sin gluten, sin parabenos | 0.14 onzas líquidas (paquete de 1, 017 ROSY NUDE)");
        producto.setPrecio(11.0);
        producto.setStock(36);
        producto.setImagen("bell4");
        producto.setCategoria(gCategorias.buscarCategoriaId(1));
        gProductos.guardarProducto(producto);
    
        //Computadoras
        producto = new Producto();
        producto.setId(5);
        producto.setNombre("SAMSUNG Galaxy Tab S9 FE Wi-Fi 10.9");
        producto.setDescripcion("Tablet Android, IP68 resistente al agua y al polvo, batería de larga duración, potente procesador, S Pen, cámara de 8 MP, diseño ligero, versión estadounidense, 2023, gris");
        producto.setPrecio(399.0);
        producto.setStock(25);
        producto.setImagen("comp1");
        producto.setCategoria(gCategorias.buscarCategoriaId(2));
        gProductos.guardarProducto(producto);


        producto = new Producto();
        producto.setId(6);
        producto.setNombre("MSI Escritorio para juegos Aegis ZS");
        producto.setDescripcion("Aegis ZS, AMD Ryzen 7 7700, GeForce RTX 4060, 16 GB de RAM, SSD de 1 TB, refrigeración por ventilador RGB, Wi-Fi 6E, teclado y mouse incluidos, compatible con bricolaje, Windows 11 Home-Adv: 7NUC-607US");
        producto.setPrecio(1099.99);
        producto.setStock(18);
        producto.setImagen("comp2");
        producto.setCategoria(gCategorias.buscarCategoriaId(2));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(7);
        producto.setNombre("ASUS Vivobook Laptop L210");
        producto.setDescripcion("Laptop ultradelgada de 11.6 pulgadas, procesador Intel Celeron N4020, 4 GB de RAM, 128 GB de almacenamiento eMMC, Windows 11 Home en modo S con un año de Office 365 Personal, L210MA-DS04, negro estrella");
        producto.setPrecio(249.99);
        producto.setStock(22);
        producto.setImagen("comp3");
        producto.setCategoria(gCategorias.buscarCategoriaId(2));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(8);
        producto.setNombre("SAMSUNG Computadora portátil empresarial Galaxy Book3");
        producto.setDescripcion("15.6 pulgadas/Windows 11 PRO/16 GB - Procesador Intel® Core i7-1355U de 512 GB/ 13ª generación, modelo 2023, NP754XFG-KB1US, plata");
        producto.setPrecio(1099.99);
        producto.setStock(24);
        producto.setImagen("comp4");
        producto.setCategoria(gCategorias.buscarCategoriaId(2));
        gProductos.guardarProducto(producto);

        //Deportes
        producto = new Producto();
        producto.setId(9);
        producto.setNombre("Tobillera de neopreno, transpirable y ajustable.");
        producto.setDescripcion("Protege el tobillo y minimiza el riesgo de lesiones. Perfecta para síntomas de lesiones crónicas y agudas en el tobillo. Ayuda a aliviar el dolor y proporciona comodidad relacionada con la fascitis plantar.");
        producto.setPrecio(19.99);
        producto.setStock(73);
        producto.setImagen("dep1");
        producto.setCategoria(gCategorias.buscarCategoriaId(3));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(10);
        producto.setNombre("Amazfit Band 5");
        producto.setDescripcion("Monitor de actividad física con Alexa incorporado, duración de batería de 15 días, oxígeno en sangre, frecuencia cardíaca, monitoreo del sueño, seguimiento de la salud de las mujeres, control de música, resistente al agua, negro (modelo: S2005OV1N)");
        producto.setPrecio(39.99);
        producto.setStock(52);
        producto.setImagen("dep2");
        producto.setCategoria(gCategorias.buscarCategoriaId(3));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(11);
        producto.setNombre("WILSON NBA Forge Series Indoor/Outdoor Basketballs");
        producto.setDescripcion("Producto oficial de la NBA: Wilson es la pelota de baloncesto oficial de la NBA. Esta pelota cuenta con la marca oficial de la NBA junto con la icónica escritura Wilson.");
        producto.setPrecio(78.92);
        producto.setStock(39);
        producto.setImagen("dep3");
        producto.setCategoria(gCategorias.buscarCategoriaId(3));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(12);
        producto.setNombre("Modvel Knee Braces for Knee Pain Women & Men");
        producto.setDescripcion("Evita el dolor, la inflamación y las lesiones deportivas con nuestras rodilleras para el dolor de rodilla. Ofrece un apoyo óptimo para personas con desgarro de menisco, ACL, artritis e hinchazón poscirugía");
        producto.setPrecio(29.99);
        producto.setStock(34);
        producto.setImagen("dep4");
        producto.setCategoria(gCategorias.buscarCategoriaId(3));
        gProductos.guardarProducto(producto);

        //Electronicos
        producto = new Producto();
        producto.setId(13);
        producto.setNombre("WD Disco duro externo de escritorio");
        producto.setDescripcion("WD Disco duro externo de escritorio Elements de 22 TB, disco duro externo USB 3.0 para almacenamiento plug-and-play - WDBWLG0220HBK-NESN");
        producto.setPrecio(549.99);
        producto.setStock(38);
        producto.setImagen("elec1");
        producto.setCategoria(gCategorias.buscarCategoriaId(4));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(14);
        producto.setNombre("JBL Tune Buds");
        producto.setDescripcion("JBL Sonido de graves puro: Los controladores de 0.394 in de diseño inteligente mejorados por el factor de forma de los cogollos ofrecen el sonido de graves puro de JBL para que sientas cada latido pulsante");
        producto.setPrecio(99.95);
        producto.setStock(34);
        producto.setImagen("elec2");
        producto.setCategoria(gCategorias.buscarCategoriaId(4));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(15);
        producto.setNombre("JBL CHARGE 5 - Altavoz Bluetooth portátil");
        producto.setDescripcion("JBL Sonido profesional con un controlador de excursión larga optimizado, tweeter separado y radiadores de graves dobles JBL Batería de larga duración que ofrece hasta 20 horas de reproducción");
        producto.setPrecio(179.95);
        producto.setStock(27);
        producto.setImagen("elec3");
        producto.setCategoria(gCategorias.buscarCategoriaId(4));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(16);
        producto.setNombre("Bose QuietComfort 45 - Auriculares inalámbricos");
        producto.setDescripcion("Auriculares inalámbricos con cancelación de ruido: el equilibrio perfecto entre silencio, comodidad y sonido. Bose Utiliza pequeños micrófonos para medir, comparar y reaccionar al ruido exterior, cancelándolo con señales opuestas");
        producto.setPrecio(349.00);
        producto.setStock(55);
        producto.setImagen("elec4");
        producto.setCategoria(gCategorias.buscarCategoriaId(4));
        gProductos.guardarProducto(producto);

        //Hogar
        producto = new Producto();
        producto.setId(17);
        producto.setNombre("Estatua de jardín solar con suculentas y 7 luces led");
        producto.setDescripcion("Nacome - Estatua de jardín solar con suculentas y 7 luces led, estatua de tortuga para patio, balcón, patio, adorno de césped");
        producto.setPrecio(39.99);
        producto.setStock(15);
        producto.setImagen("hog1");
        producto.setCategoria(gCategorias.buscarCategoriaId(5));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(18);
        producto.setNombre("Lámpara de mesa con forma de luna");
        producto.setDescripcion("Lámpara de mesa, de Mydethun, luz nocturna, se carga por USB, control táctil de brillo, dos tonos uno cálido y otro blanco frío");
        producto.setPrecio(19.99);
        producto.setStock(38);
        producto.setImagen("hog2");
        producto.setCategoria(gCategorias.buscarCategoriaId(5));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(19);
        producto.setNombre("Jarrones decorativos de cerámica blanca");
        producto.setDescripcion("MNAS Products - Jarrones decorativos, juego de 2, diseño nórdico, moderno, minimalista, para decoración del hogar");
        producto.setPrecio(32.99);
        producto.setStock(59);
        producto.setImagen("hog3");
        producto.setCategoria(gCategorias.buscarCategoriaId(5));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(20);
        producto.setNombre("Juego de jarrón de cerámica de 3 piezas");
        producto.setDescripcion("CwlwGO - Juego de 3 piezas, pequeño jarrón rústico para decoración del hogar de campo");
        producto.setPrecio(22.99);
        producto.setStock(63);
        producto.setImagen("hog4");
        producto.setCategoria(gCategorias.buscarCategoriaId(5));
        gProductos.guardarProducto(producto);

        //Jugueteria
        producto = new Producto();
        producto.setId(21);
        producto.setNombre("Juguetes para bebés cangrejo gateante");
        producto.setDescripcion("Juguete de bebé cangrejo gateante: se mueve rápido, se desliza hacia los lados como un cangrejo real, reproduce sonidos y música divertidos y se ilumina. Tiene sensores que invierten su dirección cuando se acerca a un obstáculo.");
        producto.setPrecio(35.99);
        producto.setStock(25);
        producto.setImagen("jug1");
        producto.setCategoria(gCategorias.buscarCategoriaId(6));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(22);
        producto.setNombre("MINGKIDS Juguetes Montessori para niños de 1 año");
        producto.setDescripcion("MINGKIDS Juguetes Montessori para niños de 1 año, juguete clasificador de bebé, cubo colorido y 6 piezas de forma multisensorial, juguetes de aprendizaje para niños pequeños, regalos de cumpleaños, juguetes para bebés de 6-12-18 meses");
        producto.setPrecio(19.99);
        producto.setStock(22);
        producto.setImagen("jug2");
        producto.setCategoria(gCategorias.buscarCategoriaId(6));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(23);
        producto.setNombre("Munchkin Float and Play Bubbles - Juguete de baño");
        producto.setDescripcion("Incluye 2 divertidos personajes y 2 juguetes giratorios que giran y sirven como sonajeros. Cada burbuja ayuda a estimular el sentido de la vista, la audición y el tacto del bebé. Los anillos texturizados se mueven libremente alrededor de las burbujas");
        producto.setPrecio(9.39);
        producto.setStock(36);
        producto.setImagen("jug3");
        producto.setCategoria(gCategorias.buscarCategoriaId(6));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(24);
        producto.setNombre("Nuby Llaves de mordedor de gel de hielo");
        producto.setDescripcion("Nuby Ice Gel Teether Keys Nuby Ice Gel Teether Keys contiene gel purIce no tóxico que permite que se mantenga fresco durante mucho tiempo. Su superficie texturizada fresca alivia y estimula las encías doloridas.");
        producto.setPrecio(5.99);
        producto.setStock(47);
        producto.setImagen("jug4");
        producto.setCategoria(gCategorias.buscarCategoriaId(6));
        gProductos.guardarProducto(producto);

        //Libros
        producto = new Producto();
        producto.setId(25);
        producto.setNombre("El Alquimista: Una Fabula Para Seguir Tus Suenos");
        producto.setDescripcion("El Alquimista, de Paulo Coelho, sigue cambiando la vida de sus lectores para siempre. Con más de dos millones de copias vendidas alrededor del mundo, El Alquimista se ha establecido como un clásico moderno, admirado universalmente");
        producto.setPrecio(9.88);
        producto.setStock(29);
        producto.setImagen("libro1");
        producto.setCategoria(gCategorias.buscarCategoriaId(7));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(26);
        producto.setNombre("Mi Viaje Sin Ti: Lo Que Queríamos Ser Y No Fuimos");
        producto.setDescripcion("Muchas veces tenemos que irnos a pesar del miedo, de las dudas, y de la falta de confianza. En esta ocasión, conocerás a Vespertine y viajarás de su mano luchando contra el temor a estar solo, el falso apego y la aceptación a la muerte.");
        producto.setPrecio(16.03);
        producto.setStock(19);
        producto.setImagen("libro2");
        producto.setCategoria(gCategorias.buscarCategoriaId(7));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(27);
        producto.setNombre("Aprender inglés para adultos principiantes");
        producto.setDescripcion("¿Estás buscando aprender inglés? ¿Quieres poder viajar y comunicarte en el idioma universal? ¿Necesitas saber inglés para poder crecer en tu trabajo? ¿Quieres poder entender lo que dicen los personajes de tus series y películas favoritas?");
        producto.setPrecio(24.97);
        producto.setStock(30);
        producto.setImagen("libro3");
        producto.setCategoria(gCategorias.buscarCategoriaId(7));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(28);
        producto.setNombre("EL REY DE AMARILLO");
        producto.setDescripcion("El libro lleva el nombre de una obra de teatro ficticia de mismo título que aparece mencionada repetidamente en algunas de las historias, una obra prohibida que induce desesperación o locura en quien lo lee");
        producto.setPrecio(40.91);
        producto.setStock(34);
        producto.setImagen("libro4");
        producto.setCategoria(gCategorias.buscarCategoriaId(7));
        gProductos.guardarProducto(producto);

        //Ropa
        producto = new Producto();
        producto.setId(29);
        producto.setNombre("Legendary Whitetails Men's Buck Camp Flannel");
        producto.setDescripcion("Legendary Whitetails Men's Buck Camp Flannel, Long Sleeve Plaid Button Down Casual Shirt, Corduroy Cuffs");
        producto.setPrecio(31.87);
        producto.setStock(15);
        producto.setImagen("ropa1");
        producto.setCategoria(gCategorias.buscarCategoriaId(8));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(30);
        producto.setNombre("Pantalones deportivos para hombre");
        producto.setDescripcion("Tejido suave y anti-desgarros, ligero y extremadamente duradero. Materiales resistentes al viento y estructura que te protege del mal clima. Forro de malla que ofrece una transpirabilidad y comodidad superiores.");
        producto.setPrecio(24.00);
        producto.setStock(38);
        producto.setImagen("ropa2");
        producto.setCategoria(gCategorias.buscarCategoriaId(8));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(31);
        producto.setNombre("Brooks - Tenis de correr Adrenaline GTS 22 con soporte");
        producto.setDescripcion("Estos tenis para mujer están indicados para corredoras de intensidad media o baja que quieren un apoyo de confianza, GuideRails, que les permita concentrarse en la diversión de la carrera.");
        producto.setPrecio(128.04);
        producto.setStock(12);
        producto.setImagen("ropa3");
        producto.setCategoria(gCategorias.buscarCategoriaId(8));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(32);
        producto.setNombre("Jeasona Calcetines de gato para mujer");
        producto.setDescripcion("Jeasona Calcetines de gato para mujer, regalos de gato, lindos calcetines de animales, regalos de búho de perro para mujeres");
        producto.setPrecio(30.00);
        producto.setStock(33);
        producto.setImagen("ropa4");
        producto.setCategoria(gCategorias.buscarCategoriaId(8));
        gProductos.guardarProducto(producto);

        //Salud
        producto = new Producto();
        producto.setId(33);
        producto.setNombre("Curad CUR45585RB Compresas preparadas con alcohol");
        producto.setDescripcion("Curad CUR45585RB Compresas preparadas con alcohol, paquete de 400. Las compresas preparadas con alcohol de Cuard son gruesas para proporcionar una excelente acción de limpieza.");
        producto.setPrecio(9.99);
        producto.setStock(50);
        producto.setImagen("salud1");
        producto.setCategoria(gCategorias.buscarCategoriaId(9));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(34);
        producto.setNombre("Band-Aid - Curitas adhesivas con tela flexible");
        producto.setDescripcion("Band-Aid - Curitas adhesivas con tela flexible para el cuidado de heridas y primeros auxilios, varios tamaños, 100 piezas, color beige");
        producto.setPrecio(11.78);
        producto.setStock(48);
        producto.setImagen("salud2");
        producto.setCategoria(gCategorias.buscarCategoriaId(9));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(35);
        producto.setNombre("Toallitas antibacterianas de mano de Wet Ones");
        producto.setDescripcion("Toallitas antibacterianas de mano de Wet Ones, 20 unidades (paquete de 10), 04819, 0, 1, 10");
        producto.setPrecio(22.99);
        producto.setStock(62);
        producto.setImagen("salud3");
        producto.setCategoria(gCategorias.buscarCategoriaId(9));
        gProductos.guardarProducto(producto);

        producto = new Producto();
        producto.setId(36);
        producto.setNombre("Rollos de vendaje de gasa - 36 rollos");
        producto.setDescripcion("Suministros de primeros auxilios de primera calidad para aventuras seguras - Rollos de gasa flexibles, elásticos y transpirables - Envoltura de vendaje de 3 pulgadas x 4.1 yardas para vendaje de heridas");
        producto.setPrecio(11.99);
        producto.setStock(50);
        producto.setImagen("salud4");
        producto.setCategoria(gCategorias.buscarCategoriaId(9));
        gProductos.guardarProducto(producto);

        //Carro 1
        Carro carro = new Carro();
        //carro.setId(1);
        carro.setUsuario(gUsuarios.leerUsuario(1));
        carro.setNumero("001-001-1234");
        carro.setFecha(LocalDateTime.now());

        //Detalle 1
        Detalle detalle = new Detalle();
        //detalle.setId(1);
        detalle.setCarro(carro);
        detalle.setProducto(producto);
        detalle.setCantidad(5);
        detalle.setSubtotal();

        carro.setTotal(detalle.getSubtotal());
        carro.setDescuento(0.00);
        gCarros.guardarCarro(carro);
        gDetalles.guardarDetalle(detalle);

        //Carro 2
        carro = new Carro();
        //carro.setId(2);
        carro.setUsuario(gUsuarios.leerUsuario(2));
        carro.setNumero("002-002-5678");
        carro.setFecha(LocalDateTime.now());
        carro.setDescuento(1.00);
        carro.setTotal(0.00);
        gCarros.guardarCarro(carro);

        //Detalle 2
        detalle = new Detalle();
        //detalle.setId(2);
        detalle.setCarro(carro);
        detalle.setProducto(gProductos.leerProducto(1));
        detalle.setCantidad(5);
        detalle.setSubtotal();
        gDetalles.guardarDetalle(detalle);
        double tot = detalle.getSubtotal();

        //Detalle 3
        detalle = new Detalle();
        //detalle.setId(3);
        detalle.setCarro(carro);
        detalle.setProducto(gProductos.leerProducto(2));
        detalle.setCantidad(2);
        detalle.setSubtotal();
        gDetalles.guardarDetalle(detalle);
        tot = detalle.getSubtotal() + tot;

        carro.setTotal(tot);
        gCarros.guardarCarro(carro);
        */

        //Listar Clientes
        List<Usuario> lista = gUsuarios.getUsuarios();
        for(Usuario u : lista){
            System.out.println(u);
        }

        //Listar categorias
        List<Categoria> categorias = gCategorias.getCategorias();
        for(Categoria c : categorias){
            System.out.println(c);
        }

        //Listar productos
        List<Producto> productos = gProductos.getProductos();
        for(Producto p : productos){
            System.out.println(p);
        }

        //Listar Detalles
        List<Detalle> detalles = gDetalles.getDetalles();
        for(Detalle c : detalles){
            System.out.println(c);
        }

        //Listar Carros
        System.out.println("--------------Carros--------------");
        List<Carro> carros = gCarros.getCarros();
        for(Carro f : carros){
            System.out.println(f);
        }
    }
}
