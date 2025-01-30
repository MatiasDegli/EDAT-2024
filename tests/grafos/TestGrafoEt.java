/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests.grafos;

import grafos.dinamicos.etiquetados.GrafoEtiquetado;
import lineales.dinamicas.Lista;

/**
 *
 * @author matia
 */
public class TestGrafoEt {
    
    public static void main(String[] args) {
        
        GrafoEtiquetado g = new GrafoEtiquetado();
        
        g.insertarVertice("A");
        g.insertarVertice("B");
        g.insertarVertice("D");
        g.insertarVertice("C");        
        g.insertarVertice("E");        
        System.out.println(g.toString());     
        
        
        g.eliminarVertice("E");        
        System.out.println(g.toString());
        
        
        g.insertarArco("A", "B", "12");
        g.insertarArco("A", "C", "5");        
        g.insertarArco("C", "D", "3");
        //g.insertarArco("D", "B", "7");
        //g.insertarArco("D", "A", "10");
        g.insertarArco("B", "C", "1");        
        System.out.println(g.toString());
        
        
        g.eliminarArco("B", "C");
        System.out.println(g.toString());
        
        
        g.insertarArco("B", "C", "8");
        System.out.println(g.toString());                
        
        System.out.println("Existe un camino de A a D en el grafo? "+g.existeCamino("A", "D"));
        
        Lista corto = g.caminoMasCorto("A", "D");
        System.out.println("Camino mas corto de B a D: "+corto.toString());
        
        Lista largo = g.caminoMasLargo("A", "D");
        System.out.println("Camino mas largo de B a D: "+largo.toString());
        
        
        GrafoEtiquetado clon = g.clone();
        System.out.println("El clon del grafo es: "+clon.toString());
        
    }
    
}
