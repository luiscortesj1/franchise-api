# API de Gestión de Franquicias

Este proyecto proporciona una API para gestionar franquicias, sucursales y productos. Está desarrollado con **Spring Boot** y **Docker** para facilitar la gestión y despliegue de la aplicación.

## Requisitos

- Docker
- Docker Compose
- MySQL

## Ejecución local con Docker

1. Asegúrate de tener Docker y Docker Compose instalados.
2. Clona este repositorio en tu máquina local.
3. si se corre local crea un env. con la variables de entorno
4. En el directorio del proyecto, ejecuta el siguiente comando para iniciar la aplicación con Docker:

```bash
docker-compose up --build
Accede a la API en http://localhost:8081.

Detener y Eliminar los Contenedores
Cuando termines de trabajar, puedes detener y eliminar los contenedores con: docker-compose down

Despliegue en AWS con ECS y ECR
Pre-requisitos
Cuenta de AWS
Repositorio ECR configurado
Infraestructura desplegada con Terraform (redes, subnets, RDS, ECS, etc.)
Pasos
Construir la imagen Docker localmente:docker build -t franchise-api .

Iniciar sesión en el ECR:
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin <AWS_ACCOUNT_ID>.dkr.ecr.us-east-1.amazonaws.com

Etiquetar la imagen:

docker tag franchise-api:latest <AWS_ACCOUNT_ID>.dkr.ecr.us-east-1.amazonaws.com/franchise-api:latest
Subir la imagen al ECR:

bash

docker push <AWS_ACCOUNT_ID>.dkr.ecr.us-east-1.amazonaws.com/franchise-api:latest

------------------------------------------
Probar los endpoint 
__________________________________
Endpoints de la API

Obtener todas las franquicias
Método: GET
URL: /franchises
Descripción: Obtiene la lista de todas las franquicias con sus sucursales y productos.

1. Añadir una nueva franquicia
Método: POST
URL: /franchises
Descripción: Crea una nueva franquicia con su lista de sucursales.

Body de la solicitud:
{
  "name": "Franquicia Ejemplo 2",
  "branches": null
}

2. Actualizar una franquicia existente
Método: PUT
URL: /franchises/{id}
Descripción: Actualiza la información de una franquicia existente.
Body de la solicitud:
{
  "name": "Franquicia Ejemplo 2.0",
  "branches": null
}

3. Obtener todas las franquicias
Método: GET
URL: /franchises
Descripción: Obtiene la lista de todas las franquicias con sus sucursales y productos.


4. Eliminar una franquicia
Método: DELETE
URL: /franchises/{franchiseId}
Descripción: Elimina una franquicia existente.

5. Añadir una sucursal a una franquicia
Método: POST
URL: /branches
Descripción: Añade una nueva sucursal a una franquicia existente.
Body de la solicitud:
{
  "name": "Medellin",
  "franchise": {
    "id": 5
  }
}

6. Actualizar un sucursal existente
Método: PUT
URL: /branches/{id}
Descripción: Actualiza la información de una sucursal existente.
Body de la solicitud:
{
    "name": "Barranquilla",
    "franchise": {
        "id": 5
    }
}

7.Eliminar una sucursal
Método: DELETE
URL: /branches/{id}
Descripción: Elimina una sucursal existente.

8. Obtener todas las sucursales
Método: GET
URL: /branches
Descripción: lista las sucursales existente.


9. Añadir una producto a una sucursal
Método: POST
URL: /products/
Descripción: Añade un producto a una sucursal existente.
Body de la solicitud:
{
    "name": "zapatos",
    "stock":1000,
    "branch":{
        "id":14
    }
}

10. Editar un producto 
Método: PUT
URL: /products/{id}
Descripción: Edita un producto existente.
Body de la solicitud:
{
    "name": "zapatos",
    "stock":1000,
    "branch":{
        "id":14
    }
}

11. Eliminar un producto 
Método: DELETE
URL: /products/{id}
Descripción: Elimina un producto existente.
  

12. Obtiene los productos con mayor stock por sucursal
Método: GET
URL: /branches/franchise/{id_sucursal}/top-stock-product
Descripción: Elimina un producto existente.