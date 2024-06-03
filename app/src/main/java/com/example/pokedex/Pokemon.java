package com.example.pokedex;
import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {

    private String numero,nombre,altura,peso,sexo,categoria,habilidad,evolucion,tipo1,tipo2,debilidad1,debilidad2,debilidad3,debilidad4;
    private int imagen,imagenEvo;

    public Pokemon(String numero, String nombre, String altura, String peso, String sexo, String categoria, String habilidad, String evolucion, String tipo1, String tipo2, String debilidad1, String debilidad2, String debilidad3, String debilidad4, int imagen, int imagenEvo) {
        this.numero = numero;
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.sexo = sexo;
        this.categoria = categoria;
        this.habilidad = habilidad;
        this.evolucion = evolucion;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.debilidad1 = debilidad1;
        this.debilidad2 = debilidad2;
        this.debilidad3 = debilidad3;
        this.debilidad4 = debilidad4;
        this.imagen = imagen;
        this.imagenEvo = imagenEvo;
    }

    public Pokemon(String numero, String nombre, String tipo1, String tipo2, int imagen) {
        this.numero = numero;
        this.nombre = nombre;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.imagen = imagen;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(String evolucion) {
        this.evolucion = evolucion;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    public String getDebilidad1() {
        return debilidad1;
    }

    public void setDebilidad1(String debilidad1) {
        this.debilidad1 = debilidad1;
    }

    public String getDebilidad2() {
        return debilidad2;
    }

    public void setDebilidad2(String debilidad2) {
        this.debilidad2 = debilidad2;
    }

    public String getDebilidad3() {
        return debilidad3;
    }

    public void setDebilidad3(String debilidad3) {
        this.debilidad3 = debilidad3;
    }

    public String getDebilidad4() {
        return debilidad4;
    }

    public void setDebilidad4(String debilidad4) {
        this.debilidad4 = debilidad4;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getImagenEvo() {
        return imagenEvo;
    }

    public void setImagenEvo(int imagenEvo) {
        this.imagenEvo = imagenEvo;
    }
}
