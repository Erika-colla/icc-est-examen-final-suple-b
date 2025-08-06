

# Examen Práctico – Evaluación de Productos

---

## 📄 Descripción General

En un entorno comercial, se requiere gestionar una colección de productos mediante una serie de operaciones que permiten:

* Eliminar duplicados por código y ordenar por nombre y código.
* Clasificar los productos según la unicidad de caracteres en su nombre.
* Identificar productos con precios repetidos.
* Buscar productos destacados por nombre eficientemente.

Estas funciones simulan un sistema de análisis y evaluación de productos en inventario.

---

## 🧾 Modelo: `Producto`

Cada objeto `Producto` tiene:

* `nombre`: Nombre del producto.
* `codigo`: Identificador único del producto.
* `precios`: Lista de precios históricos (mínimo 3, valores `double`).

### Campos Calculados:

* `porcentajeCaracteresUnicos`: Porcentaje de caracteres únicos en el nombre, ignorando espacios y mayúsculas.
* `catidadPreciosRepetidos`: Número de precios que se repiten en la lista de precios.

Ejemplo:

```java
Nombre: "Teclado Logitech"
Sin espacios: "tecladologitech"
Caracteres únicos: {t, e, c, l, a, d, o, g, i, h} = 10
Total caracteres: 15 → Porcentaje: (10 * 100) / 15 = 66%

Precios: [50.0, 55.0, 50.0, 60.0] → Precios repetidos = 1
```

---

## 🔧 Clase a implementar: `ProductoController`

### Método A – `ordenarProducto(List<Producto>)`

* Retorna un `TreeSet<Producto>`.
* Elimina duplicados por `codigo`.
* Ordena por:

  1. Nombre (ascendente)
  2. Código (ascendente)

> La clase `Producto` debe implementar `Comparable` respetando esta lógica.

---

### Método B – `clasificarPorUnicidad(Set<Producto>)`

* Retorna un `Map<String, List<Producto>>` agrupando productos por el porcentaje de unicidad de caracteres en el nombre.

| Rango Porcentaje | Categoría |
| ---------------- | --------- |
| 90–100           | A         |
| 70–89            | B         |
| 50–69            | C         |
| 30–49            | D         |
| 0–29             | E         |

> Usa `LinkedHashMap` y `LinkedList` para conservar el orden de inserción.

---

### Método C – `obtenerDestacados(List<Producto>)`

* Filtra los productos con más de un precio repetido.
* Retorna la lista ordenada por nombre ascendente.

---

### Método D – `buscarPorNombre(List<Producto> lista, String nombre)`

* Aplica búsqueda binaria sobre la lista ordenada por nombre.
* Retorna el producto correspondiente o `null`.

---

## 📊 Ejemplo de ejecución esperada

```java
List<Producto> productos = Arrays.asList(
    new Producto("Monitor Samsung", "P001", Arrays.asList(150.0, 150.0, 160.0)),
    new Producto("Teclado Logitech", "P002", Arrays.asList(50.0, 55.0, 50.0, 60.0)),
    new Producto("Mouse Logitech", "P003", Arrays.asList(30.0, 30.0, 30.0)),
    new Producto("Monitor Samsung", "P001", Arrays.asList(150.0, 155.0)), // código repetido
    new Producto("Cámara Web", "P004", Arrays.asList(100.0, 100.0, 120.0)),
    new Producto("Auriculares", "P005", Arrays.asList(80.0, 90.0, 100.0))
);
```

### Resultado consola:

```java
Productos ordenados por nombre y código (sin duplicados):
1. Auriculares (P005)
2. Cámara Web (P004)
3. Monitor Samsung (P001)
4. Mouse Logitech (P003)
5. Teclado Logitech (P002)

Clasificación por porcentaje de caracteres únicos:
A: []
B: [Teclado Logitech]
C: [Auriculares, Mouse Logitech]
D: [Monitor Samsung]
E: [Cámara Web]

Productos destacados (precios repetidos > 1):
- Cámara Web
- Mouse Logitech
- Teclado Logitech

Producto buscado por nombre "Teclado Logitech":
→ Teclado Logitech (P002)
```

---

## 📆 RÚBRICA DE EVALUACIÓN (30 puntos)

| **Componente Evaluado**                    | **Descripción**                                                                                     | **Puntos** |
| ------------------------------------------ | --------------------------------------------------------------------------------------------------- | ---------- |
| ✅ `getCatidadPreciosRepetidos()`           | Calcula correctamente la cantidad de precios repetidos.                                             | 3.0        |
| ✅ `getPorcentajeCaracteresUnicos()`        | Calcula correctamente el porcentaje de caracteres únicos del nombre (sin espacios, sin mayúsculas). | 3.0        |
| 🔷 Método A – `ordenarProducto(...)`       | Elimina duplicados por código y ordena correctamente por nombre y código.                           | 6.0        |
| 🔷 Método B – `clasificarPorUnicidad(...)` | Agrupa correctamente en `Map<String, List<Producto>>` según rangos. Respeta el orden de entrada.    | 6.0        |
| 🔷 Método C – `obtenerDestacados(...)`     | Filtra y ordena correctamente productos con más de un precio repetido.                              | 5.0        |
| 🔷 Método D – `buscarPorNombre(...)`       | Implementa búsqueda binaria correctamente sobre lista ordenada por nombre.                          | 4.0        |
| 🖨️ Salidas esperadas                      | Se imprime de forma ordenada y clara cada bloque de ejecución en consola.                           | 3.0        |
| 💯 **Total**                               |                                                                                                     | **30.0**   |

---

## 🧪 Validación Automática

Las pruebas unitarias validarán:

* Cálculos correctos de unicidad y repetición.
* Lógica de ordenamiento y clasificación.
* Precisión en la búsqueda binaria.
* Uso correcto de `TreeSet`, `Map`, `List`, y `Set`.

