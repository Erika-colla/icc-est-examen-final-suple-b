package test;

import controllers.ProductoController;
import models.Producto;
import validaciones.ValidProductoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class ProductoControllerTest {

    private ProductoController controller;
    private List<Producto> productos;

    @BeforeEach
    public void setup() {
        controller = new ProductoController();
        productos = List.of(
                new Producto("Cámara Digital", "001", Arrays.asList(99.0, 101.0, 100.0)),
                new Producto("Cámara Ddafadsigital", "001", Arrays.asList(99.0, 101.0, 101.0, 100.0)),
                new Producto("Mouse Inalámbrico", "002", Arrays.asList(45.0, 50.0, 55.0, 55.0)),
                new Producto("Pantalla OLED", "003", Arrays.asList(200.0, 190.0, 195.0, 195.0, 190.0)),
                new Producto("Teclado Mecánico", "004", Arrays.asList(80.0, 82.0, 78.0))); // Repetido por código

    }

    @Test
    public void testMetodoA() {
        Set<Producto> resultado = controller.ordenarProducto(productos);
        ValidProductoTest.validarMetodoA(resultado, productos);
    }

    @Test
    public void testMetodoB() {
        Set<Producto> ordenados = controller.ordenarProducto(productos);
        Map<String, List<Producto>> clasificados = controller.clasificarPorUnicidad(ordenados);
        ValidProductoTest.validarMetodoB(clasificados);
    }

    @Test
    public void testMetodoC() {
        List<Producto> destacados = controller.obtenerDestacados(productos);
        ValidProductoTest.validarMetodoC(destacados, productos);
    }

    @Test
    public void testMetodoD() {
        List<Producto> destacados = controller.obtenerDestacados(productos);
        Producto encontrado = controller.buscarPorNombre(destacados, "Pantalla OLED");
        ValidProductoTest.validarMetodoD(encontrado, destacados);
    }

    @Test
    public void testGetCantidadPreciosRepetidos() {
        Producto p = new Producto("Monitor LG", "M001", Arrays.asList(100.0, 105.0, 100.0, 110.0));
        int repetidos = p.getCatidadPreciosRepetidos(); // 2 veces 100.0 => 1 repetido
        assertEquals(1, repetidos, "La cantidad de precios repetidos debería ser 1");
    }

    @Test
    public void testCalcularPorcentajeCaracteresUnicos() {
        Producto p = new Producto("Teclado Logitech", "T001", Arrays.asList(45.0, 46.0));
        int porcentaje = p.getPorcentajeCaracteresUnicos();
        assertEquals(66, porcentaje, "El porcentaje de caracteres únicos debería ser 66");
    }
}
