package models;

import java.util.*;

public class Producto implements Comparable<Producto> {
    private String nombre;
    private String codigo;
    private List<Double> precios;
    private int porcentajeCaracteresUnicos;

    public Producto(String nombre, String codigo, List<Double> precios) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precios = precios;
        this.porcentajeCaracteresUnicos = calcularPorcentajeCaracteresUnicos();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Double> getPrecios() {
        return precios;
    }

    /// ESTE metodo cacual cuantos precios son repetidos a lo largo del historial de
    /// precios de este producto
    public int getCatidadPreciosRepetidos() {

        Set<Double> preciosUnicos = new HashSet<>(precios);
        return precios.size() - preciosUnicos.size();
    }

    private int calcularPorcentajeCaracteresUnicos() {
        String sinEspacios = nombre.replace(" ", "").toLowerCase();
        Set<Character> unicos = new HashSet<>();
        for (char c : sinEspacios.toCharArray())
            unicos.add(c);
        if (sinEspacios.length() == 0)
            return 0;
        return (int) (unicos.size() * 100.0 / sinEspacios.length());
    }

    public int getPorcentajeCaracteresUnicos() {
        return porcentajeCaracteresUnicos;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Producto))
            return false;
        return this.codigo.equals(((Producto) o).codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ") - Precios Repetidos: " + getCatidadPreciosRepetidos() + ", Unicos: "
                + porcentajeCaracteresUnicos + "%";
    }

    @Override
    public int compareTo(Producto o) {
        if (this.getCodigo().equals(o.getCodigo()))
            return 0;

        // Si son iguales, comparar por nombre
        int cmp = this.nombre.compareTo(o.nombre);
        if (cmp != 0)
            return cmp;

        // Si también son iguales, comparar por código
        return this.codigo.compareTo(o.codigo);
    }
}
