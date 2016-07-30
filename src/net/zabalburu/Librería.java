
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zabalburu;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

/**
 *
 * @author Danny Party
 */
public class Librería {
    private static Libro[]libro=new Libro[50];
    private static int numLibros;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        inicializar();
        menu();
    }

    private static void inicializar() {
        libro[0]=new Libro("Aprenda C Ya","Programación",75.12,5);
        libro[1]=new Libro("Microsoft Office","Ofimática",58.6,12);
        libro[2]=new Libro("Windows 8","Sistemas Operativos",45,90);
        libro[3]=new Libro("C Avanzado","Programación",90,3);
        libro[4]=new Libro("Word Básico","Ofimática",64.6,10);
        libro[5]=new Libro("Windows 8 Servidor","Sistemas Operativos",52.3,7);
        libro[6]=new Libro("Access 2014","Ofimática",32.45,5);
        libro[7]=new Libro("Diseño de Algoritmos","Programación",90.15,10);
        libro[8]=new Libro("Excel 2014","Ofimática",52.58,4);
        
        numLibros=9;
    }

    private static void menu() {
        String respuesta;
        int opcion;
        
        do {
        respuesta=JOptionPane.showInputDialog("1. Vender Libro"+
                "\n2. Listar Libros Alfabéticamente"+
                "\n3. Mostar Libros de un Tema"+
                "\n4. Listar Libros por Temas"+
                "\n5. Salir"+
                "\n\nOpción [1-5]");
        opcion=Integer.parseInt(respuesta);
        
            switch (opcion) {
                case 1:
                    venderLibro();
                    break;
                case 2:
                    listarAlfabeticamente();
                    break;
                case 3:
                    mostrarLibrosTema();
                    break;
                case 4:
                    listarLibrosTemas();
                    break;
                case 5:    
            }
            
        } while (opcion!=5);
        
    }

    private static void venderLibro() {
        String titulo;
        int i;
        
        titulo=JOptionPane.showInputDialog("Introduzca título de libro a vender: ");
        
        for (i = 0; i < numLibros&&!titulo.equalsIgnoreCase(libro[i].getTitulo()); i++);
        
        if (i<numLibros) {
            String respuesta;
            int numero;
            
            respuesta=JOptionPane.showInputDialog("TEMA: "+libro[i].getTema()+
                    "\nPRECIO: "+libro[i].getPrecio()+
                    "\nUNIDADES DISPONIBLES: "+libro[i].getUnidades()+
                    "\n\n¿CUÁNTAS UNIDADES DESEA COMPRAR?");
            numero=Integer.parseInt(respuesta);
            
            if (numero>libro[i].getUnidades()) {
                JOptionPane.showMessageDialog(null,"NO HAY TANTAS UNIDADES","ERROR",JOptionPane.ERROR_MESSAGE);
            }else{
                libro[i].vender(numero);
                
                if (JOptionPane.showConfirmDialog(null,"¿DESEA VENDER MÁS LIBROS?","VENDER",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                    venderLibro();
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(null,"TÍTULO NO ENCONTRADO","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void listarAlfabeticamente() {
        int sumaUnidades;
        double sumaVentas,sumaImporte;
        
        sumaUnidades=0;
        sumaVentas=0;
        sumaImporte=0;
        
        for (int i = 0; i < numLibros-1; i++) {
            for (int j = i+1; j < numLibros; j++) {
                if (libro[i].getTitulo().compareToIgnoreCase(libro[j].getTitulo())>0) {
                   Libro temp=libro[i];
                   libro[i]=libro[j];
                   libro[j]=temp;
                }
            }
        }
        String listado="<html><h1>LISTADO DE LIBROS</h1>";
        listado+="<table border='1'><tr><th>TÍTULO</th>"+
                "<th>TEMA</th>"+
                "<th>PRECIO</th>"+
                "<th>UNIDADES</th>"+
                "<th>VENTAS</th>"+
                "<th>IMPORTE</th></tr>";
        for (int i = 0; i < numLibros; i++) {
            double importe=libro[i].getPrecio()*libro[i].getVentas();           
            sumaUnidades+=libro[i].getUnidades();
            sumaVentas+=libro[i].getVentas();
            sumaImporte+=importe;
                
            listado+="<tr><td>"+libro[i].getTitulo()+
                     "</td><td>"+libro[i].getTema()+
                     "</td><td>"+libro[i].getPrecio()+
                     "</td><td>"+libro[i].getUnidades()+
                     "</td><td>"+libro[i].getVentas()+                    
                     "</td><td>"+importe+"</td></tr>";
        }
        listado+="<tr><th>SUMA UNIDADES</th>"+sumaUnidades+
                 "<th>SUMA VENTAS</th>"+sumaVentas+
                 "<th>SUMA IMPORTE</th>"+sumaImporte;
        listado+="</table></html>";
        
        JTextPane txp = new JTextPane();
        txp.setContentType("text/html");
        txp.setText(listado);
        JOptionPane.showMessageDialog(null, txp,"LISTADO DE LIBROS",JOptionPane.PLAIN_MESSAGE);
    }

    private static void mostrarLibrosTema() {
        for (int i = 0; i < numLibros-1; i++) {
            for (int j = i+1; j < numLibros; j++) {
                if (libro[i].getTema().compareToIgnoreCase(libro[j].getTema())>0) {
                   Libro temp=libro[i];
                   libro[i]=libro[j];
                   libro[j]=temp;
                }
            }
        }
        int menor;
        String respuesta;
        for (int i = 0; i < numLibros-1; i++) {
            menor=i;
            for (int j = i+1; j < numLibros; j++) {
                if (libro[j].getTema().compareToIgnoreCase(libro[menor].getTema())<0) {
                    menor=j;
                }
            }
            Libro temp=libro[i];
            libro[i]=libro[menor];
            libro[menor]=temp;
        }
        
        respuesta=JOptionPane.showInputDialog("INTRODUZCA TEMA A LISTAR: ");
        int mayor,medio,menorB;
        
        menorB=0;
        mayor=numLibros-1;
        medio=(mayor+menorB)/2;
        
        while(menorB<=mayor&&!respuesta.equalsIgnoreCase(libro[medio].getTema())){
            
            if (respuesta.compareToIgnoreCase(libro[medio].getTema())>0) {
                menorB=medio+1;
            }else{
                mayor=medio-1;
            }
            medio=(mayor+menorB)/2;
        }
        
        if (menorB<=mayor&&respuesta.equalsIgnoreCase(libro[medio].getTema())) {
            String listado="<html><h1>LISTADO DE LIBROS DE UN TEMA</h1>";
            listado+="<h2>TEMA: "+respuesta+"</h2>";
            listado+="<table border ='1'><tr><th>TÍTULO</th>"+
                     "<th>PRECIO</th>"+
                     "<th>UNIDADES</th></tr>";
            
            for (; medio < numLibros-1&&respuesta.equalsIgnoreCase(libro[medio].getTema()); medio++) {
                listado+="<tr><td>"+libro[medio].getTitulo()+
                         "</td><td>"+libro[medio].getPrecio()+
                         "</td><td>"+libro[medio].getUnidades()+"</td></tr>";
            }
            listado+="</table></html>";
            
            JTextPane txp=new JTextPane();
            txp.setContentType("text/html");
            txp.setText(listado);
            JOptionPane.showMessageDialog(null, listado,"LISTADO DE LIBROS",JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,"TEMA NO ENCONTRADO","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void listarLibrosTemas() {
        for (int i = 0; i < numLibros-1; i++) {
            for (int j = numLibros-1; j > i; j--) {
                
                if (libro[j].getTema().compareToIgnoreCase(libro[j-1].getTema())<0) {
                    
                    Libro temp=libro[j];
                    libro[j]=libro[j-1];
                    libro[j-1]=temp;
                }
            }
        }
        
        int pos;
        String tema;
        
        for (pos=0;pos<numLibros;pos++) {
            
            tema=libro[pos].getTema();
            String listado="<html><h1>TEMA: "+tema+"</h1>";
            listado+="<table border='1'><tr><th>TÍTULO</th>"+
                    "<th>UNIDADES</th>"+
                    "<th>PRECIO</th></tr>";
            
            for (;pos<numLibros&&tema.equalsIgnoreCase(libro[pos].getTema()); pos++) {
                listado+="<tr><td>"+libro[pos].getTitulo()+
                         "</td><td>"+libro[pos].getUnidades()+
                         "</td><td>"+libro[pos].getPrecio()+"</td></tr>";
            }
            
            listado+="</table></html>";
            JTextPane txp=new JTextPane();
            txp.setContentType("text/html");
            txp.setText(listado);
            JOptionPane.showMessageDialog(null, txp,"LISTADO LIBROS POR TEMA",JOptionPane.PLAIN_MESSAGE);
        }
    }
    
}
