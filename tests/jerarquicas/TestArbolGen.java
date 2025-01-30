/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests.jerarquicas;

import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

/**
 *
 * @author matia
 */
public class TestArbolGen {
    
    
    public static void main(String[] args) {
        ArbolGen a = new ArbolGen();
        
        a.insertar("A", "Z");
        
        a.insertar("B", "A");
        
        a.insertar("C", "A");
        
        a.insertar("D", "A");
        
        a.insertar("H", "D");
        
        a.insertar("J", "D");
        
        a.insertar("E", "C");
        
        a.insertar("E", "C");
        
        a.insertar("J", "E");
        
        a.insertar("F", "E");
        System.out.println(a.toString());
        
        System.out.println("Pertenece F al arbol? " + a.pertenece("F"));
        System.out.println("Pertenece K al arbol? " + a.pertenece("K"));
        
        Lista ancestros = a.ancestros("F");
        
        System.out.println("Los ancestros de F son : "+ancestros.toString());
        
        System.out.println("La altura del arbol es: "+a.altura());
        
        System.out.println("F esta en el nivel: "+a.nivel("F"));
        
        System.out.println("El padre de F es: " + a.padre("F"));
        
        Lista preorden = a.listarPreorden();
        
        System.out.println("Preorden: "+preorden.toString());
        
        Lista posorden = a.listarPosorden();
        
        System.out.println("Posorden: "+posorden.toString());
        
        Lista porNivel = a.listarNiveles();
        
        System.out.println("Por niveles: "+porNivel.toString());
    
        ArbolGen clon = a.clone();
        
        System.out.println("el clon del arbol es: " +clon.toString());
        
        Lista camino = new Lista();
        camino.insertar(camino.longitud()+1, "A");
        camino.insertar(camino.longitud()+1, "C");
        camino.insertar(camino.longitud()+1, "E");
        camino.insertar(camino.longitud()+1, "F");
        
        System.out.println("El camino "+camino.toString()+" se encuentra? "+a.verificarCamino(camino));
        
        System.out.println("El patron "+camino.toString()+" es patron? "+a.verificarPatron(camino));
        
        a.insertarPorPosicion("X", 3);
        
        System.out.println(a.toString());
    }
}
