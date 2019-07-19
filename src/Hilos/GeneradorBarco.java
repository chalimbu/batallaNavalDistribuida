/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import Beans.CampoBatalla;
import Beans.Poscision;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sebastian
 */
public class GeneradorBarco extends Thread{
    public ArrayList<Poscision> puntosBarco = new ArrayList<Poscision>();
    CampoBatalla cb;
    static volatile int numerosBarcos=0;
    int numeroBarco;
    public GeneradorBarco(CampoBatalla cb){
        this.cb=cb;
        numerosBarcos=numerosBarcos+1;
        numeroBarco=numerosBarcos;
    }
    @Override
    public void run() {
           CrearBarco();
    }
    
    private void CrearBarco(){
        while(!CrearPuntosBarco()){//repita mientras no puede crearlos
            devolverCambios();
        }        
    }
    /**
     * se debe garantizar que exista un punto antes, en arrayList, genera los puntos 
     * restante del barco si es posible
     * @return 
     */
    public boolean CrearPuntosBarco(){
        puntosBarco = new ArrayList<>();
        Random r=new Random();
        boolean direccion=r.nextBoolean();
        boolean fuePosibleCrearBarco=true;
        Poscision primero=new Poscision(cb);
        puntosBarco.add(primero);//la primera parte del barco
        puntosBarco.add(new Poscision(direccion, primero,1));
        puntosBarco.add(new Poscision(direccion,primero,2));
        for(int i=0;i<puntosBarco.size();i++){
            Poscision pos=puntosBarco.get(i);
            if(cb.AsignarPoscision(pos, numeroBarco)){//todo correcto
            }else{
                puntosBarco.set(i, new Poscision(pos.getX(), pos.getY(),false));
                return false;
            }
        }
        //System.out.println(fuePosibleCrearBarco+" "+numeroBarco);
        return fuePosibleCrearBarco;
    }
    
    public void devolverCambios(){
        for(int i=0;i<puntosBarco.size();i++){
            Poscision pos=puntosBarco.get(i);
            cb.DevolverPoscisionPoscision(pos, this.numeroBarco);
            
        }
        //cb.imprimirCampo();
        puntosBarco = new ArrayList<Poscision>();
        
    }
}
