# Carmen Sandiego Uruguay

Carmen Sandiego Uruguay es un juego educativo inspirado en la famosa serie "Carmen Sandiego", localizado en Uruguay y desarrollado en Java. El juego se enfoca en los puntos de interés geográficos, históricos, leyendas, turismo y gastronomía de Uruguay.

## Descripción del juego

En este juego, el jugador asume el papel de un detective de la organización ACME que debe atrapar a una banda de criminales liderada por la infame Carmen Sandiego. El detective deberá seguir pistas y recorrer diferentes localidades de Uruguay para capturar a los secuaces de Carmen Sandiego y, finalmente, a la propia Carmen.

## Características principales

- **Educativo**: Aprende sobre la geografía, historia y cultura de Uruguay mientras juegas.
- **Accesible**: Diseñado para ser accesible a personas con discapacidad visual severa.
- **Sistema de rangos**: Progresa en tu carrera de detective a medida que resuelves casos.
- **Base de datos local**: Almacena información de usuarios, localidades, pistas y progreso del juego.
- **Generación automática de pistas**: Sistema para generar pistas relevantes para cada localidad.
- **Interfaz gráfica**: Ventanas de juego implementadas para una experiencia visual mejorada.

## Tecnologías utilizadas

- Java 11 (Requerido para algunas funcionalidades de Swing)
- SQLite JDBC (3.36.0.3)
- Maven (gestión de dependencias y construcción)
- JUnit 5 y Mockito (para pruebas unitarias)

## Requisitos del sistema

- Java Development Kit (JDK) 11 o superior
- Maven 3.6.0 o superior

## Instrucciones de instalación y ejecución

1. Clona este repositorio:
git clone <URL_DEL_REPOSITORIO>
2. Navega hasta la carpeta raíz del proyecto:
cd CarmenSandiegoUruguay
3. Compila el proyecto:
mvn clean compile
4. Ejecuta el juego:
mvn exec:java -Dexec.mainClass="com.ejemplo.carmenuy.Main"

## Estructura del Proyecto

[Mantén la estructura del proyecto que ya tenías en el README original]

## Características técnicas destacadas

- **DAOs implementados**: LocalidadDAO y PistaDAO para interactuar con la base de datos SQLite.
- **Generación automática de localidades y distancias**: El sistema genera automáticamente localidades y calcula distancias entre ellas.
- **Sistema de pistas**: Implementación de un sistema para generar y almacenar pistas específicas para cada localidad.
- **Interfaz gráfica**: Ventanas de juego implementadas utilizando Java Swing con características que requieren Java 11.

## Accesibilidad

El juego está diseñado para ser accesible a personas con discapacidad visual severa. Al iniciar, se pregunta al usuario si necesita ayuda visual. Si la respuesta es afirmativa, se activa un sistema de texto a voz (TTS) que lee todos los textos del juego.

## Contribución

[Mantén la sección de contribución que ya tenías en el README original]

## Licencia

Este proyecto está bajo la licencia [Commons Creative]. Consulta el archivo [LICENSE](LICENSE) para más detalles.

## Contacto

Si tienes alguna pregunta o sugerencia sobre este proyecto, puedes contactarnos a través de [pastronomia@gmail.com] o visitar nuestro sitio web en [URL web].

## Agradecimientos

Queremos agradecer a todos los contribuidores y a la comunidad de desarrolladores que han hecho posible este proyecto.

```plaintext
├── CarmenSandiegoUruguay
│   ├── pom.xml
│   ├── README.md
│   ├── scripts
│   │   └── list_structure.sh
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.ejemplo.carmenuy
│   │   │   │       ├── dao
│   │   │   │       │   ├── LocalidadDAO.java
│   │   │   │       │   ├── MisionDAO.java
│   │   │   │       │   ├── PistaDAO.java
│   │   │   │       │   └── UsuarioDAO.java
│   │   │   │       ├── exception
│   │   │   │       │   └── PistaException.java
│   │   │   │       ├── Main.java
│   │   │   │       ├── model
│   │   │   │       │   ├── Constants.java
│   │   │   │       │   ├── CSD.java
│   │   │   │       │   ├── Detective.java
│   │   │   │       │   ├── Grafo.java
│   │   │   │       │   ├── Jugador.java
│   │   │   │       │   ├── Mision.java
│   │   │   │       │   ├── Partida.java
│   │   │   │       │   ├── PistaGastronomia.java
│   │   │   │       │   ├── PistaGeografia.java
│   │   │   │       │   ├── PistaHistoria.java
│   │   │   │       │   ├── Pista.java
│   │   │   │       │   ├── PistaLeyenda.java
│   │   │   │       │   ├── PistaTurismo.java
│   │   │   │       │   ├── Rango.java
│   │   │   │       │   └── Secuaz.java
│   │   │   │       ├── service
│   │   │   │       │   ├── AccesibilidadManager.java
│   │   │   │       │   ├── AudioManager.java
│   │   │   │       │   ├── ConfigService.java
│   │   │   │       │   ├── DatabaseInitializationService.java
│   │   │   │       │   ├── GrafoService.java
│   │   │   │       │   ├── JuegoService.java
│   │   │   │       │   ├── LocalidadService.java
│   │   │   │       │   ├── MisionManager.java
│   │   │   │       │   ├── NarrativaManager.java
│   │   │   │       │   ├── PistaService.java
│   │   │   │       │   └── UsuarioService.java
│   │   │   │       ├── tts
│   │   │   │       │   └── TTSManager.java
│   │   │   │       └── ui
│   │   │   │           ├── InputManager.java
│   │   │   │           ├── VentanaAyudaVisual.java
│   │   │   │           ├── VentanaCaptura.java
│   │   │   │           ├── VentanaFinal.java
│   │   │   │           ├── VentanaJuego.java
│   │   │   │           ├── VentanaLogin.java
│   │   │   │           └── VentanaRegistro.java
│   │   │   └── resources
│   │   │       ├── config.properties
│   │   │       ├── imagenes
│   │   │       │   └── secuaces
│   │   │       ├── init_database.sql
│   │   │       ├── localidades.csv
│   │   │       ├── messages.properties
│   │   │       ├── pistas.csv
│   │   │       ├── secuaces.csv
│   │   │       └── sonidos
│   │   │           ├── efectos
│   │   │           ├── musica
│   │   │           └── voz
│   │   │               ├── login_exitoso.mp3
│   │   │               └── login_fallido.mp3
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── ejemplo
│   │                   └── carmenuy
│   │                       ├── dao
│   │                       │   └── UsuarioDAOTest.java
│   │                       ├── model
│   │                       ├── service
│   │                       │   └── PistaServiceTest.java
│   │                       └── ui
│   │                           └── VentanaAyudaVisualTest.java
│   └── target
│       ├── classes
│       ├── dependency
│       └── test-classes
│           └── com
│               └── ejemplo
│                   └── carmenuy
│                       ├── dao
│                       ├── model
│                       ├── service
│                       └── ui
└end
