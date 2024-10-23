Ejecución de la Aplicación
Para ejecutar la aplicación, abre una terminal y ejecuta: docker-compose up --build

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