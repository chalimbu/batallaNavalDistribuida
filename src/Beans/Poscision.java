/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.Random;

/**
 *
 * @author sebastian
 */
public class Poscision {
    private int x;
    private int y;
    boolean disponible;
    public Poscision(int x,int y){
        this.x=x;
        this.y=y;
        this.disponible=true;
    }
    public Poscision(int x,int y,boolean disponible){
        this.x=x;
        this.y=y;
        this.disponible=disponible;
    }
    public Poscision(CampoBatalla cb){
        Random r=new Random();
        this.x=(int)(r.nextFloat()*cb.getLargo());
        this.y=(int)(r.nextFloat()*cb.getAncho());
        disponible=true;
    }
    public Poscision(boolean direccion, Poscision p,int aumento){
        int x=p.getX();
        int y=p.getY();
        if(direccion){
            x=x+aumento;
        }else{
            y=y+aumento;
        }
        this.x=x;
        this.y=y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public boolean getDisponible(){
        return this.disponible;
    }
}
