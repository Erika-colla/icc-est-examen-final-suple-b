package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.DocFlavor.STRING;

public class Producto implements Comparable<Producto> {

  private String nombre;
  private String codigo;
  private List<Double> precio;


  public Producto(String nombre, String codigo, List<Double> precio) {
    this.nombre = nombre;
    this.codigo = codigo;
    this.precio = precio;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public void setPrecio(List<Double> precio) {
    this.precio = precio;
  }


  public List<Double> getPrecio() {
    return precio;
  }

  public int getCatidadPreciosRepetidos() {
    int repetidos = 0;
    
    for (int i = 0; i < precio.size(); i++) {
      for (int j = i + 1; j < precio.size(); j++) {
        if(precio.get(i).equals(precio.get(j))) {
          repetidos++;
          break;
        }
      }
    }
    return repetidos;
  }

  public int getPorcentajeCaracteresUnicos() {
    String texto = nombre.replace(" ", "").toLowerCase();

    Set<Character> unicos = new HashSet<>();
    for (int i = 0; i < texto.length(); i++) {
      unicos.add(texto.charAt(i));
    }
    int total = texto.length();
    int cantidadUnicos = unicos.size();

    if (total == 0 ) {
      return 0;
    }

    return(cantidadUnicos*100) / total;
  }

  
  @Override
  public boolean equals(Object obj) {
    if(this == obj) {
      return true;
    }
    if(!(obj instanceof Producto)) 
    return false;
    Producto p = (Producto) obj;
    return this.codigo.equals(p.codigo);
  }

  @Override
  public int hashCode() {
    return codigo.hashCode();
  }

  @Override
  public int compareTo(Producto o) {
    int comparacionNombre = this.nombre.compareTo(o.nombre);
    if (comparacionNombre != 0) {
      return comparacionNombre;
    }
    return this.codigo.compareTo(o.codigo);
  }

  @Override
  public String toString() {
    return "Producto [nombre=" + nombre + ", codigo=" + codigo + ", precio=" + precio
        + ", Precios Repetidos: " + getCatidadPreciosRepetidos() + ", Porcentaje Caracteres Unicos: "
        + getPorcentajeCaracteresUnicos() + "]";
  }

}
