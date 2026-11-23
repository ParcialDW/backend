# bibliotecas-backend

API REST hecha con Spring Boot para registrar bibliotecas y consultarlas junto con sus libros.

## Requisitos

- Java 17 o superior
- Maven

## Ejecución

```bash
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

Al iniciar se cargan datos de prueba: 3 bibliotecas y 8 libros.

## Endpoints

| Método | Ruta           | Acción                                     |
|--------|----------------|--------------------------------------------|
| POST   | `/bibliotecas` | Registrar biblioteca                       |
| GET    | `/bibliotecas` | Consultar bibliotecas con sus libros       |

Ejemplo de cuerpo para `POST /bibliotecas`:

```json
{
  "nombre": "Biblioteca Sur",
  "direccion": "Avenida 68 # 20-10",
  "ciudad": "Bogotá"
}
```
