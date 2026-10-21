# Gestor de Incidencias

**Gestor de Incidencias** es una aplicación de consola desarrollada en **Java** para consultar el listado de incidencias de soporte de un centro educativo.

Su finalidad es permitir al personal del centro revisar rápidamente los avisos de soporte técnico: ver todas las incidencias, filtrar las que siguen abiertas, buscar por texto y consultar el detalle de una incidencia concreta.

## Versión de Java

El proyecto utiliza **Java 17**.

## Funcionalidades

La aplicación permite:

- Mostrar **todas** las incidencias registradas.
- Mostrar solo las incidencias **abiertas**.
- **Buscar** incidencias por identificador o título, sin distinguir mayúsculas y minúsculas.
- Consultar el **detalle** de una incidencia a partir de su identificador (por ejemplo, `INC-001`).
- Usarse mediante un **menú interactivo** o mediante **argumentos** en la línea de comandos.

## Compilación y ejecución

Con Maven instalado en el equipo:

```bash
mvn clean compile exec:java
```

Con el wrapper de Maven incluido en el proyecto (Windows):

```bash
.\mvnw.cmd clean compile exec:java
```

Sin argumentos se abre el menú interactivo. También se pueden indicar operaciones directamente:

| Operación | Descripción |
|---|---|
| `ayuda` | Muestra la ayuda |
| `listar` | Muestra todas las incidencias |
| `abiertas` | Muestra las incidencias abiertas |
| `buscar <texto>` | Busca por identificador o título |
| `detalle <id>` | Muestra una incidencia concreta |

Ejemplo:

```bash
.\mvnw.cmd exec:java -Dexec.args="detalle INC-001"
```

## Generación y consulta de Javadoc

Para generar la documentación técnica:

```bash
.\mvnw.cmd javadoc:javadoc
```

La documentación se genera en la carpeta `docs/`. Para consultarla en local, abre el archivo `docs/index.html` en el navegador.

## Clases principales

| Clase | Responsabilidad |
|---|---|
| `App` | Punto de entrada de la aplicación. Gestiona el menú interactivo y los argumentos de consola. |
| `GestorIncidencias` | Almacena las incidencias y permite listarlas, filtrarlas y buscarlas. |
| `Incidencia` | Representa una incidencia con su identificador, título, prioridad y estado. |
| `IncidenciaNoEncontradaException` | Excepción que se lanza cuando no existe una incidencia con el identificador buscado. |

## Estructura de archivos

```
documentacion-proyecto-ud1-julio-lopez/
├── .mvn/wrapper/                  # Configuración del wrapper de Maven
├── docs/                          # Documentación Javadoc generada (GitHub Pages)
├── src/main/java/es/centro/incidencias/
│   ├── App.java
│   ├── GestorIncidencias.java
│   ├── Incidencia.java
│   └── IncidenciaNoEncontradaException.java
├── .gitignore
├── ENUNCIADO_ACTIVIDAD.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Documentación publicada

La documentación Javadoc está publicada en GitHub Pages:

[https://juliolpzsu.github.io/documentacion-proyecto-ud1-julio-lopez/](https://juliolpzsu.github.io/documentacion-proyecto-ud1-julio-lopez/)