# challenge-conversor-moneda_esp

Una aplicación de consola en Java que permite convertir entre distintas monedas usando datos en tiempo real
desde [ExchangeRate-API](https://www.exchangerate-api.com/).

---

## Cómo Ejecutar

### ️ Requisitos

- Java 11 o superior
- Clave de API de [ExchangeRate-API](https://www.exchangerate-api.com/)

### 🔧 Configuración

1. Clona o descarga este repositorio.
2. Reemplaza `"YOUR_API_KEY"` en `CurrencyConverterLogic.java` con tu propia clave de API:
   ```java
   private static final String API_KEY = "TU_CLAVE_API";
   ```

### Ejecutar

```bash
javac -d out src/*.java
java -cp out Main
```

---

## Funcionalidades

- Conversión entre:
    - USD ↔ ARS (Peso Argentino)
    - USD ↔ BRL (Real Brasileño)
    - USD ↔ COP (Peso Colombiano)
- Lógica separada en clases para mayor mantenibilidad.
- Uso de `HttpClient` y `Gson` para consumir una API REST.
- Menú interactivo en consola.

---

## Ejemplo

```
Sea bienvenido/a al Conversor de Moneda =]
1) Dólar =>> Peso argentino
2) Peso argentino =>> Dólar
...
Elija una opción válida:
```

---

## Créditos

- [ExchangeRate-API](https://www.exchangerate-api.com/)
- Desarrollado en Java 11 con Gson y HttpClient.

---
