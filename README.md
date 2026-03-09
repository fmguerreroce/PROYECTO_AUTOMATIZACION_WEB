# Automatización de pruebas - Polymer Shop

## Descripción

Proyecto de automatización de pruebas desarrollado utilizando **Serenity BDD** y **Cucumber** con el patrón **Screenplay**.

El objetivo del proyecto es validar el flujo de selección de productos y proceso de compra en la tienda **Polymer Shop**, incluyendo la selección de chaquetas y tallas, utilizando datos parametrizados desde un archivo CSV.

---

## Tecnologías utilizadas

* Java
* Serenity BDD
* Gherkin
* Cucumber
* Maven
* Screenplay Pattern
* Jenkins

---

## Escenarios automatizados

1. Selección de chaquetas y tallas.
2. Validación del proceso completo de compra.
3. Escenario parametrizado consumiendo datos desde CSV.

---

## Estructura del proyecto

El proyecto está organizado siguiendo el patrón **Screenplay**, separando responsabilidades para mejorar la mantenibilidad y reutilización del código.

```
├── pom.xml
├── README.md
├── serenity.properties
└── src
     └── test
          ├── java
          │    └── com.testing
          │         ├── hooks
          │         ├── interactions
          │         ├── model
          │         ├── questions
          │         ├── runners
          │         ├── stepdefinitions
          │         ├── task
          │         ├── userinterfaces
          │         └── utils
          │
          └── resources
               ├── data
               ├── features
               ├── serenity.conf
               └── Jenkinsfile
```

---

## Archivos principales

| Archivo             | Descripción                                                    |
| ------------------- | -------------------------------------------------------------- |
| pom.xml             | Configuración del proyecto y dependencias manejadas con Maven  |
| README.md           | Documentación del proyecto                                     |
| serenity.properties | Configuración del framework Serenity                           |
| Jenkinsfile         | Configuración del pipeline de integración continua con Jenkins |

---

## Carpetas principales

| Carpeta         | Descripción                                     |
| --------------- | ----------------------------------------------- |
| hooks           | Configuración inicial y final de los escenarios |
| interactions    | Acciones reutilizables del usuario              |
| model           | Modelos de datos utilizados en las pruebas      |
| questions       | Validaciones realizadas por el actor            |
| runners         | Clases que ejecutan los escenarios              |
| stepdefinitions | Implementación de pasos de Cucumber             |
| task            | Flujo de acciones del actor                     |
| userinterfaces  | Localizadores de elementos de la interfaz       |
| utils           | Clases utilitarias                              |
| data            | Archivos de datos de prueba (CSV)               |
| features        | Escenarios escritos en Gherkin                  |

---

## Ejecución del proyecto

Para ejecutar las pruebas utilizar el siguiente comando:

```
mvn clean verify
```

---

## Reporte de ejecución

Después de ejecutar las pruebas, Serenity genera reportes automáticos en:

```
target/site/serenity/index.html
```

Estos reportes incluyen:

* Resultados de ejecución
* Evidencia de pasos
* Capturas de pantalla
* Detalles de los escenarios

---

## Integración continua

El proyecto incluye un **Jenkinsfile** para ejecutar las pruebas automáticamente en un pipeline de **Jenkins**, permitiendo integrar la ejecución de pruebas dentro de un flujo de **CI/CD**.

---

