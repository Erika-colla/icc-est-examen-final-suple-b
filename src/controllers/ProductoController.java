
package controllers;

import java.util.*;
import java.util.stream.Collectors;

import models.Producto;

public class ProductoController {

    // Ordena los productos por nombre y leugo por codigo y perimro valida si el
    // cdogio es repetido si es petido lo elimano
    // Implementaido con interefas comparable en el modeli
    public Set<Producto> ordenarProducto(List<Producto> productos) {
        TreeSet<Producto> set = new TreeSet<>();
        set.addAll(productos);
        return set;
    }

    public Map<String, List<Producto>> clasificarPorUnicidad(Set<Producto> productos) {
        Map<String, List<Producto>> mapa = new LinkedHashMap<>();
        for (Producto p : productos) {
            String categoria;
            int porcentaje = p.getPorcentajeCaracteresUnicos();
            if (porcentaje >= 90)
                categoria = "A";
            else if (porcentaje >= 70)
                categoria = "B";
            else if (porcentaje >= 50)
                categoria = "C";
            else if (porcentaje >= 30)
                categoria = "D";
            else
                categoria = "E";

            mapa.putIfAbsent(categoria, new LinkedList<>());
            mapa.get(categoria).add(p);
        }
        return mapa;
    }

    public List<Producto> obtenerDestacados(List<Producto> lista) {
        return lista.stream()
                .filter(p -> p.getCatidadPreciosRepetidos() > 1)
                .sorted(Comparator.comparing(Producto::getNombre))
                .collect(Collectors.toList());
    }

    public Producto buscarPorNombre(List<Producto> lista, String nombre) {
        int izq = 0, der = lista.size() - 1;
        while (izq <= der) {
            int mid = izq + (der - izq) / 2;
            Producto actual = lista.get(mid);
            int cmp = actual.getNombre().compareTo(nombre);
            if (cmp == 0)
                return actual;
            else if (cmp < 0)
                izq = mid + 1;
            else
                der = mid - 1;
        }
        return null;
    }
}
