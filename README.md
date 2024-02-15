# Práctica de Proyecto Final de Programacion Web

Web de Prototipo

Pagina Web de un Carrito de Compras

## Componentes

Angular 16.2.11

Java JDK 17, JPA, Servicios Rest

Servidor WildFly 29.0.0

Base de Datos PostgreSQL 16.1

## Descripción

### FRONTEND

Repositorio principal: https://github.com/dfao2003/WebCompras

Este proyecto se basó en el diseño realizado en figma <url> para su implementación. Se creo un frontend en Angular para el funcionamiento y el diseño, para el backend se creó un proyecto en eclipse con Maven y arquitectura de jakarta para apliaciones web, para el despliege de la apliación en Java se utilizó un servidor WildFly configurado para tener conexión con la base de datos de PostgreSQL.

En cuanto a la parte del Angular se realizó con la ayuda de flexbox en css para crear un diseño responsivo, con la ayuda del mediascreen nos va a permitir diseñar el modelo de forma que se altere según el tamaño de la pantalla y que se pueda desplegar en los distintos dispositivos que existen.

Para la creación del frontend se separaron en tres partes: páginas, header, footer. Esto nos permite poder utilizar un header y un mismo footer para todos los componente sin necesidad de repetirlos para cada componente.

Se comunica con el servidor de WildFly mediante servicios REST, invocando los servicios y enviando datos mediante parámetros en la URL o mediante objetos JSON que luego se mapean a clases creadas en la parte de Java.

### BACKEND

En la parte de backend se realizaron todas las clases que representan los dominios en la parte de frontend, y para cada uno se crearon clases de DAO y de Gestión, así como servicios básicos de POST, PUT, GET Y DELETE para ser consumidos y probar su funcionalidad. Se utilizó la librería de jakarta para las anotaciones de persistencia y de los servicios REST.

Se utilizó un servidor de WildFly para el despliege de la apliación web, y se configuró al servidor para tener una conexión con la base de PostgreSQL y que se eliga esta para la persistencia de los datos. Esto se realizó en el archivo standalone.xml dentro de la carpeta standalone/configuration del servidor de WildFly, además se agregaron drivers para poder realizar la conexión con una base de PostgreSQL.

Para habilitar la conexión con Postgres se configuró al proyecto para que tenga acceso directo a una base ya creada y se dieron las credenciales para poder acceder a la base.

En cuanto a código, cada parte de la aplicación se separaron en paquetes para tenerlos organizados: el paquete de modelos que contiene todas las entidades y su configuración de relaciones y campos para la persistencia en la base de datos, el paquete dao que contiene todos los daos de los modelos, estos realizan la persistencia de los datos y hacen consultas directas con la base de datos para obtener valores y eliminar o actualizar algunos valores, el paquete de servicios que contiene configuración para habilitar la comunicación mediante servicios REST y la definición de los servicios para que sean consumidos en la parte del frontend, y el paquete de gestión que permite la interacción entre las clases del dao y los servicios REST que se consumen.

En la clase CORSFilter se realizan la configuración de la aplicación para habilitar los servicios REST.

En la clase GestionDatos existen datos que se crean al inicio de la base si es que se requiere realizar pruebas de funcionamiento.

En la clase ServicioTransacciones está realizada la mayoría de la lógica para la comunicación mediante servicios REST, aquí es en donde la mayor parte de servicios importantes están definidos, estos servicios no reciben objetos JSON, reciben solo parámetros y realizan transacciones entre los valores ya guardados en la base, y dan respuestas en formato JSON para conveniencia de la parte de frontend.
