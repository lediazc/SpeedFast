# Cómo configurar SPEEDFAST

## 1) Crear BBDD
- Instalar MySQL.
- Instalar el gestor de BBDD a elección.
- Ejecutar el archivo 'speedfast_db.sql', esto creará las entidades y atributos necesarios.
- Verificar los datos de conexión con el documento 'src/main/java/controlador/ConexionBD.java'

### Se sugieren los siguientes datos para montar la BBDD:
- Base de datos: `speedfast`
- Host: `localhost`
- Puerto: `3307`

## 2) Ejecución del aplicativo
- Precondición: Realizar paso "1) Crear BBDD"
- Abrir proyecto
- Descargar las dependencias de 'pom.xml'
- Ejecutar el aplicativo desde 'src/main/java/main/Main.java'