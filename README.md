# Alquilao Gestión de Alquileres

## Requisitos

- Windows 10
- Apache Tomcat v9.0.71
- MySQL 8.0.31
- Java 11.0.19

## Instalación

1. Descarga e instala Apache Tomcat v9.0.71 desde https://tomcat.apache.org/download-90.cgi
2. Asegúrate de tener MySQL 8.0.31 instalado y en funcionamiento en tu sistema.
3. Ejecuta el archivo de script adjunto `BBDD` en MySQL para crear la base de datos.
4. La base de datos incluye un usuario con datos de muestra. (usuario:'pepe', contraseña:'pepe').

## Configuración de Apache Tomcat

1. Abre una terminal o línea de comandos.
2. Navega hasta la carpeta bin de instalación de Apache Tomcat.
3. Ejecuta el comando `startup` para iniciar el servidor Tomcat.

## Despliegue de la Aplicación

1. Abre la carpeta que contiene el proyecto.
2. Localiza el archivo tfg_DAM-1.0-SNAPSHOT.WAR en la carpeta target.
3. Localiza la carpeta `webapps` en tu instalación de Apache Tomcat.
4. Copia el archivo WAR generado en el paso anterior dentro de la carpeta `webapps`.
5. Reinicia Apache Tomcat para que la aplicación se despliegue automáticamente.

¡Tu aplicación ahora debería estar lista para funcionar en Apache Tomcat!

## Ejecución de la Aplicación

Una vez que hayas desplegado la aplicación en Apache Tomcat, puedes ejecutarla desde localhost siguiendo estos pasos:

1. Abre tu navegador web preferido.
2. En la barra de direcciones, ingresa la siguiente URL: `http://localhost:8080/tfg_DAM-1.0-SNAPSHOT` 
3. Presiona Enter para cargar la página.
4. La aplicación debería aparecer en tu navegador y estar lista para usar.

Asegúrate de que Apache Tomcat esté en funcionamiento mientras ejecutas la aplicación desde localhost. Si el servidor no está activo, no podrás acceder a la aplicación en el navegador.

Si has realizado cambios en el puerto predeterminado de Apache Tomcat (8080), ajusta la URL de acuerdo a la configuración de tu servidor.




Este proyecto está bajo la Licencia Sergio Granjo Tostado.
