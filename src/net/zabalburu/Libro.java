/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.zabalburu;

/**
 *
 * @author alumno
 */
public class Libro {
    private String titulo,tema;
    private double precio;
    private int unidades,ventas;

    public Libro(String titulo, String tema) {
        this.titulo = titulo;
        this.tema = tema;
    }

    public Libro(String titulo, String tema, double precio, int unidades) {
        this.titulo = titulo;
        this.tema = tema;
        this.precio = precio;
        this.unidades = unidades;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getVentas() {
        return ventas;
    }
    
    public double vender(int cantidad){
        
        double importeVenta=cantidad*this.getPrecio();       
        this.setUnidades(this.unidades-cantidad);
        this.ventas+=cantidad;
        
        if (this.unidades<cantidad) {
            return 0;
        }else{
            return importeVenta;
        }
    }
}
