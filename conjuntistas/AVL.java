/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conjuntistas;

import lineales.dinamicas.Lista;

/**
 *
 * @author matia
 */
public class AVL {
    
    
    private NodoAVL raiz;
    
    
    public boolean pertenece(Comparable elem){
        boolean exito=true;
        
        return exito;
    }
    
    
    public boolean insertar(Comparable newElem) {
        boolean exito=false;

        if (esVacio()) {
            raiz = new NodoAVL(newElem, null, null);
            exito=true;
        } 
        else {
            NodoAVL nodo = insertarAux(newElem, raiz);
            if(nodo!=null){                
                exito=true;
            }
        }

        return exito;
    }
    
    
    private NodoAVL insertarAux(Comparable elem, NodoAVL recorre) {
        
        if (recorre != null) {
            
            if (elem.compareTo(recorre.getElem()) < 0) {
                
                if (recorre.getIzquierdo() == null) {
                    recorre.setIzquierdo(new NodoAVL(elem, null, null));
                    recorre.recalcularAltura();
                    System.out.println("recorre es: "+recorre.getElem()+ " y su altura es: "+recorre.getAltura());
                    System.out.println("su hijo izq es: "+recorre.getIzquierdo().getElem());
                    recorre=balancear(recorre);
                    System.out.println("recorre ahora es: "+recorre.getElem());
                    recorre=balancearCompleto(recorre, raiz);
                    System.out.println("y ahora es: "+recorre.getElem());
                } 
                else {
                    insertarAux(elem, recorre.getIzquierdo());                    
                }
            } 
            else if (elem.compareTo(recorre.getElem()) > 0) {
                
                if (recorre.getDerecho() == null) {
                    recorre.setDerecho(new NodoAVL(elem, null, null));
                    recorre.recalcularAltura();
                    recorre=balancear(recorre);
                    recorre=balancearCompleto(recorre, raiz);                         
                } 
                else {
                    insertarAux(elem, recorre.getDerecho());
                }
            }
            else{
                recorre=null;
            }
        }
        
        return recorre;
    }
                      
    
    private NodoAVL balancear(NodoAVL recorre) {
        
        if (recorre != null) {
            int balance = obtenerBalance(recorre);
            int balanceHijo = 0;
            NodoAVL hijo = null;            
            
            if (Math.abs(balance) > 1) {
                NodoAVL HI = recorre.getIzquierdo();
                NodoAVL HD = recorre.getDerecho();

                if (HI != null && HD != null) {
                    
                    if (HI.getAltura() > HD.getAltura()) {
                        balanceHijo = obtenerBalance(HI);
                        hijo = HI;
                    } else {
                        balanceHijo = obtenerBalance(HD);
                        hijo = HD;
                    }
                } 
                else if (HI != null && HD == null) {
                    System.out.println("ENTRAMOS !!!");
                    balanceHijo = obtenerBalance(HI);
                    hijo = HI;
                    System.out.println("recorre es: "+recorre.getElem()+", hijo es: "+HI.getElem()+", hijo de hijo es: "+HI.getIzquierdo().getElem());
                } 
                else if (HD != null) {
                    balanceHijo = obtenerBalance(HD);
                    hijo = HD;
                }

                if (balance == 2) {
                    System.out.println("ENTRAMOS 2 !!! ");
                    if (balanceHijo == -1) {
                        recorre.setIzquierdo(rotacionIzquierda(hijo));
                        recorre = rotacionDerecha(recorre);
                    } 
                    else {
                        
                        System.out.println("ENTRAMOS 3 !!!");
                        
                        recorre = rotacionDerecha(recorre);
                    }
                } 
                else if (balance == -2) {
                    
                    if (balanceHijo == 1) {
                        recorre.setDerecho(rotacionDerecha(hijo));
                        recorre = rotacionIzquierda(recorre);
                    } 
                    else {
                        recorre = rotacionIzquierda(recorre);
                    }
                }
            }
        }

        return recorre;
    }
    
    
    private NodoAVL rotacionIzquierda(NodoAVL pivote){
        NodoAVL HD = pivote.getDerecho();
        pivote.setDerecho(HD.getIzquierdo());
        HD.setIzquierdo(pivote);
        pivote.recalcularAltura();
        HD.recalcularAltura();

        if (pivote == raiz){
            raiz = HD;
        } 
        else{
            NodoAVL padre = obtenerPadre(pivote.getElem(), raiz);
            if (padre.getIzquierdo() == pivote){
                padre.setIzquierdo(HD);
            } 
            else{
                padre.setDerecho(HD);
            }
        }

        return HD;
    }
    
    
    private NodoAVL rotacionDerecha(NodoAVL pivote){
        NodoAVL HI = pivote.getIzquierdo();
        pivote.setIzquierdo(HI.getDerecho());
        HI.setDerecho(pivote);
        pivote.recalcularAltura();
        HI.recalcularAltura();

        if (pivote == raiz){
            raiz = HI;
        } 
        else{
            NodoAVL padre = obtenerPadre(pivote.getElem(), raiz);
            if (padre.getIzquierdo() == pivote){
                padre.setIzquierdo(HI);
            } 
            else{
                padre.setDerecho(HI);
            }
        }
        
    return HI;
}
    
    
    private int obtenerBalance(NodoAVL nodo) {
        int balance = 0;
        NodoAVL HI = nodo.getIzquierdo();
        NodoAVL HD = nodo.getDerecho();

        if (HI != null && HD != null) {
            balance = (HI.getAltura() - HD.getAltura());
        } 
        else if (HI != null && HD == null) {
            balance = (HI.getAltura() + 1);
        } 
        else if (HD != null) {
            balance = (-1 - HD.getAltura());
        }
        
        if(Math.abs(balance)>1){
            System.out.println("el nodo: "+nodo.getElem()+" esta desbalanceado");
        }
        
        return balance;
    }
    
    
    public boolean eliminar(Comparable elem){
        boolean exito = false;
        
        NodoAVL nodo = eliminarAux(elem, raiz);
        
        if(nodo!=null){
            raiz=nodo;
            exito=true;
        }        
        
        return exito;
    }
    
    
    private NodoAVL eliminarAux(Comparable elem, NodoAVL recorre){
        
        if(recorre!=null){
            
            NodoAVL izq = recorre.getIzquierdo();
            NodoAVL der = recorre.getDerecho();
            
            if(recorre.getElem().compareTo(elem)==0){
                
                NodoAVL padre = obtenerPadre(elem, raiz);
                                
                if(izq==null && der==null){    
                    sinHijo(padre, recorre);
                }
                else if(izq!=null && der!=null){
                    dosHijos(recorre);
                }
                else{
                    unHijo(padre, recorre);                    
                }
                padre.recalcularAltura();
                padre=balancear(padre);
                recorre=balancearCompleto(padre, raiz);
            }
            else if(recorre.getElem().compareTo(elem)<0){
                recorre=eliminarAux(elem, recorre.getDerecho());
            }
            else{
                recorre=eliminarAux(elem, recorre.getIzquierdo());
            }
        }
        
        return recorre;
    }
    
    
    private NodoAVL balancearCompleto(NodoAVL hijo, NodoAVL recorre){
        
        if(recorre!=null){
            
            if(raiz==hijo){
                recorre.recalcularAltura();
                recorre=balancear(raiz);
            }
            else if(recorre.getIzquierdo()==hijo || recorre.getDerecho()==hijo){
                recorre.recalcularAltura();
                System.out.println("hijo es: "+hijo.getElem()+", recorre es: "+ recorre.getElem());
                recorre=balancear(recorre);
                recorre=balancearCompleto(recorre, raiz);
            }
            else if(recorre.getElem().compareTo(hijo.getElem())<0){
                balancearCompleto(hijo, recorre.getDerecho());
            }
            else{
                balancearCompleto(hijo, recorre.getIzquierdo());
            }
        }
        return recorre;
    }
    
    
    private void sinHijo(NodoAVL padre, NodoAVL actual){
        
        if(padre.getIzquierdo()==actual){
            padre.setIzquierdo(null);
        }
        else{
            padre.setDerecho(null);
        }
    }
    
    
    private void unHijo(NodoAVL padre, NodoAVL actual){
        
        NodoAVL izq = actual.getIzquierdo();
        NodoAVL der = actual.getDerecho();
        
        if(izq!=null && der==null){
            
            if(padre.getIzquierdo()==actual){
                padre.setIzquierdo(izq);
            }
            else{
                padre.setDerecho(izq);
            }
            padre.recalcularAltura();
            balancear(padre);                      
        }
        else if(izq==null && der!=null){
            
            if(padre.getIzquierdo()==actual){
                padre.setIzquierdo(der);
            }
            else{
                padre.setDerecho(der);
            }      
        }
    }
    
    
    private void dosHijos(NodoAVL actual){
        
        NodoAVL der = actual.getDerecho();
        
        while(der.getIzquierdo()!=null){
                der=der.getIzquierdo();
        }
        
        eliminarAux(der.getElem(), raiz);
        actual.setElem(der.getElem());
    }
    
    
    private NodoAVL obtenerPadre(Comparable elem, NodoAVL recorre){
        NodoAVL padre = null;
        
        if(recorre!=null){
            
            if(recorre.getIzquierdo()!=null && recorre.getIzquierdo().getElem().compareTo(elem)==0 || 
                recorre.getDerecho()!=null && recorre.getDerecho().getElem().compareTo(elem)==0){
                
                padre=recorre;
            }
            else if(recorre.getElem().compareTo(elem)<0){
                padre=obtenerPadre(elem, recorre.getDerecho());
            }
            else{
                padre=obtenerPadre(elem, recorre.getIzquierdo());
            }
        }
        
        return padre;
    }
    
    
    public Lista listar(){
        Lista lis = new Lista();
        
        listarAux(lis, raiz);
        
        return lis;
    }
    
    
    private void listarAux(Lista lis, NodoAVL recorre){
        
        if(recorre!=null){
            listarAux(lis, recorre.getIzquierdo());
            lis.insertar(lis.longitud()+1, recorre.getElem());
            listarAux(lis, recorre.getDerecho());
        }
        
    }
    
    
    public Lista listarRango(Comparable piso, Comparable techo){
        Lista lis = new Lista();
        
        listarRangoAux(lis, piso, techo, raiz);
        
        return lis;
    }
    
    
    private void listarRangoAux(Lista lis, Comparable piso, Comparable techo, NodoAVL recorre){
        
        if(recorre!=null && recorre.getElem().compareTo(techo)<=0){
            listarRangoAux(lis, piso, techo, recorre.getIzquierdo());
            
            if(recorre.getElem().compareTo(piso)>=0){
                lis.insertar(lis.longitud()+1, recorre.getElem());
            }
            
            listarRangoAux(lis, piso, techo, recorre.getDerecho());
        }
        
    }
    
    
    public Comparable minimoElem(){
        Comparable min;
        NodoAVL recorre=raiz;
        
        if(esVacio()){
            min=null;
        }
        else{
            if(recorre.getIzquierdo()!=null){
                while(recorre.getIzquierdo()!=null){
                    recorre=recorre.getIzquierdo();
                }
            }
            min=recorre.getElem();
        }
        
        return min;
    }
    
    
    public Comparable maximoElem(){
        Comparable max;
        NodoAVL recorre=raiz;
        
        if(esVacio()){
            max=null;
        }
        else{
            if(recorre.getDerecho()!=null){
                while(recorre.getDerecho()!=null){
                    recorre=recorre.getDerecho();
                }
            }
            max=recorre.getElem();
        }
        
        return max;
    }
    
    
    public boolean esVacio(){
        return raiz==null;
    }
    
    
    public String toString(){
        
        String msj=toStringAux("", raiz);
        
        return msj;
    }
    
    
    private String toStringAux(String msj, NodoAVL recorre){
        String cadena;
        
        if(recorre!=null){
        
            if(recorre.getIzquierdo()!=null){
                cadena=recorre.getElem() + " es padre de "
                    + recorre.getIzquierdo().getElem() +" (izq)";
                
                if(recorre.getDerecho()!=null){
                    cadena+= " y de " + recorre.getDerecho().getElem()+" (der)";
                }
            }
            else if(recorre.getDerecho()!=null){
                cadena=recorre.getElem() + " es padre de "
                    + recorre.getDerecho().getElem() +" (der)";
            }
            else{
                cadena = recorre.getElem() + " no tiene hijos";
            }
            
            cadena+="\n";
            
            msj+=cadena;
            
            msj=toStringAux(msj, recorre.getIzquierdo());
            msj=toStringAux(msj, recorre.getDerecho());
        }
        
        return msj;
    }
    
}
