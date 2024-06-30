# Carmen Sandiego Uruguay

Carmen Sandiego Uruguay es un juego inspirado en la famosa serie de videojuegos educativos "Carmen Sandiego", pero localizado en Uruguay y desarrollado en Java. El juego se enfoca en los puntos de interés geográficos, históricos, leyendas, turismo y gastronomía de Uruguay.

## Descripción del juego

En este juego, el jugador asume el papel de un detective de la organización ACME que debe atrapar a una banda de criminales liderada por la infame Carmen Sandiego. El detective deberá seguir pistas y recorrer diferentes localidades de Uruguay para capturar a los secuaces de Carmen Sandiego y, finalmente, a la propia Carmen.

El juego cuenta con diferentes rangos de detective que el jugador puede alcanzar a medida que captura a los secuaces. Cada vez que el detective asciende de rango, se vuelve más fácil atrapar a los criminales.

## Accesibilidad

El juego está diseñado para ser accesible a personas con discapacidad visual severa. Al iniciar el juego, se le preguntará al usuario si necesita ayuda visual. Si el usuario responde afirmativamente, se activará un sistema de texto a voz (TTS) que leerá todos los textos del juego.

## Tecnologías utilizadas

- Java
- SQLite (base de datos)
- Java Swing (interfaz gráfica)

## Estructura del proyecto

El proyecto sigue la siguiente estructura de carpetas y archivos:

CarmenSandiegoUruguay/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/ejemplo/carmenuy/
│   │   │   │   ├── model/
│   │   │   │   │   ├── Detective.java
│   │   │   │   │   ├── CSD.java
│   │   │   │   │   ├── Secuaz.java
│   │   │   │   │   ├── Pista.java
│   │   │   │   │   ├── PistaGeografia.java
│   │   │   │   │   ├── PistaHistoria.java
│   │   │   │   │   ├── PistaLeyenda.java
│   │   │   │   │   ├── PistaGastronomia.java
│   │   │   │   │   ├── PistaTurismo.java
│   │   │   │   │   ├── Grafo.java
│   │   │   │   │   └── Rango.java
│   │   │   │   ├── dao/
│   │   │   │   │   ├── UsuarioDAO.java
│   │   │   │   │   ├── PistaDAO.java
│   │   │   │   │   └── LocalidadDAO.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── JuegoService.java
│   │   │   │   │   ├── UsuarioService.java
│   │   │   │   │   ├── PistaService.java
│   │   │   │   │   └── LocalidadService.java
│   │   │   │   ├── ui/
│   │   │   │   │   ├── VentanaLogin.java
│   │   │   │   │   ├── VentanaRegistro.java
│   │   │   │   │   ├── VentanaAyudaVisual.java
│   │   │   │   │   ├── VentanaJuego.java
│   │   │   │   │   ├── VentanaCaptura.java
│   │   │   │   │   └── VentanaFinal.java
│   │   │   │   └── Main.java
│   │   └── resources/
│   │       ├── imagenes/
│   │       │   ├── secuaces/
│   │       │   └── ...
│   │       ├── sonidos/
│   │       │   ├── efectos/
│   │       │   ├── musica/
│   │       │   └── voz/
│   │       └── database.db
│   └── test/
│       └── java/
│           ├── com/ejemplo/carmenuy/
│           │   ├── model/
│           │   │   └── (clases de prueba de modelo)
│           │   ├── dao/
│           │   │   └── (clases de prueba de DAO)
│           │   ├── service/
│           │   │   └── (clases de prueba de servicios)
│           │   └── ui/
│           │       └── (clases de prueba de interfaz de usuario)
│           └── (otras clases de prueba)
├── lib/
│   └── sqlite-jdbc-3.36.0.3.jar
├── .gitignore
├── pom.xml
└── README.md

## Instrucciones de compilación y ejecución

1. Asegúrate de tener Java Development Kit (JDK) instalado en tu sistema.
2. Clona este repositorio en tu máquina local.
3. Abre una terminal y navega hasta la carpeta raíz del proyecto.
4. Ejecuta el siguiente comando para compilar el proyecto:
   ```sh
   mvn clean compile
Una vez compilado, ejecuta el siguiente comando para iniciar el juego:
mvn exec:java -Dexec.mainClass="com.ejemplo.carmenuy.Main"
Contribución
Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:
Haz un fork de este repositorio.
Crea una nueva rama con un nombre descriptivo para tu contribución.
Realiza los cambios y mejoras en tu rama.
Envía un pull request describiendo tus cambios.
Licencia
Este proyecto está bajo la licencia [Commons Creative]. Consulta el archivo LICENSE para más detalles.
Contacto
Si tienes alguna pregunta o sugerencia sobre este proyecto, puedes contactarnos a través de [pastronomia@gmail.com] o visitar nuestro sitio web en [URL web].