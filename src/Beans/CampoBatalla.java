/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Utils.Validador;

/**
 *
 * @author sebastian
 */
public class CampoBatalla {
    private volatile int campoBatalla[][];
    private boolean encontrados[][];
    private int largo;
    private int ancho;
    private int intentos=0;
    
    /**
     * crea el campo de batalla con 0, y asigna los encontrados a falso
     * @param x largo del campo
     * @param y ancho del campo
     */
    public CampoBatalla(){
        this.largo=10;
        this.ancho=10;
        campoBatalla=new int[this.largo][this.ancho];
        for(int i=0;i<campoBatalla.length;i++){
            for(int j=0;j<campoBatalla[0].length;j++){
                campoBatalla[i][j]=0;
            }
        }
        encontrados=new boolean[this.largo][this.ancho];
        for(int i=0;i<encontrados.length;i++){
            for(int j=0;j<encontrados[0].length;j++){
                encontrados[i][j]=false;
            }
        }
    }
    
    public int getLargo(){
        return largo;
    }
    
    public int getAncho(){
        return ancho;
    }
    
    /**
     * asigna la poscision al barco si es posible sino devuelve falso
     * @param pos la poscision que se intenta asignar
     * @param barco el numero del barco
     * @return si la poscision pudo ser asignad true, sino por fuera de limite o 
     * que ya exisitia false.
     */
    public boolean AsignarPoscision(Poscision pos,int barco){
        if(pos.getX()<largo&&pos.getY()<ancho){
            if(campoBatalla[pos.getX()][pos.getY()]==0){
                campoBatalla[pos.getX()][pos.getY()]=barco;
                return true;
            }else{
                return false;
            }
        }else{
        return false;
        }
    }
    
    public void DevolverPoscisionPoscision(Poscision pos,int barco){
        if(pos.getX()<largo&&pos.getY()<ancho){
            if(campoBatalla[pos.getX()][pos.getY()]==barco){
                campoBatalla[pos.getX()][pos.getY()]=0;
            }else{
                    System.out.println("no era barco"+pos.getX()+" "+pos.getY());
            }
        }else{
            System.out.println("no devuelta"+pos.getX()+" "+pos.getY());
        }
    }
    
    public void imprimirCampo(){
        for(int i=0;i<campoBatalla.length;i++){
            System.out.print(i+"-");
            for(int j=0;j<campoBatalla[0].length;j++){
                System.out.print(campoBatalla[i][j]+".");
            }
            System.out.println("");
        }
    }
    
    public String intentarAtaque(String cadena){
        this.intentos++;
        if(this.intentos<16){//por que se empieza en 1
        String[] parts = cadena.split(",");
        if(parts.length<2){
            return "se requieren 2 numeros de cordenadas separados por coma";
        }else if(Validador.esNumeroPositivo(parts[0])&&Validador.esNumeroPositivo(parts[1])){
            int numero1=Integer.parseInt(parts[0]);
            int numero2=Integer.parseInt(parts[1]);
            return intentarAtaque(numero1,numero2);
        }else{
            return "ambos valores deben ser numeros positivos de cordenadas entre 0 y 9";
        }
        //return "";
        }else{
            return "has perdido, con "+ aciertosLogrados();
        }
    }
    
    private String intentarAtaque(int x,int y){
        if(x<encontrados.length&&y<encontrados.length){
            encontrados[x][y]=true;
            if(campoBatalla[x][y]!=0){
                int restantes=PosicionesRestante(campoBatalla[x][y]);
                if(restantes==-1){
                    return "!!!!!!!!!!!!!!!!!!!!has acertado todas!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
                }else{
                return "has acertado quedan "+restantes;
                }
            }else{
                return "has fallado";
            }
        }else{
            return "cordenadas fuera de limite";
        }
    }
    
    /**
     * verifica los puestos que quedan del barco, si ya estan todos los barcos del juevos devuelve -1
     * @param numeroBarco
     * @return 
     */
    private int PosicionesRestante(int numeroBarco){
        int partesBarco=3;
        boolean acertadoTodas=true;
        for(int i=0;i<campoBatalla.length;i++){
            for(int j=0;j<campoBatalla[0].length;j++){
                if(campoBatalla[i][j]==numeroBarco&&encontrados[i][j]){
                    partesBarco--;
                }
                if(campoBatalla[i][j]!=0&&!encontrados[i][j]){
                    acertadoTodas=false;
                }
            }
        }
        if(acertadoTodas){
            return -1;
        }else{
        return partesBarco;
        }
    }
    
    
    private int aciertosLogrados(){
        int aciertos=0;
        for(int i=0;i<campoBatalla.length;i++){
            for(int j=0;j<campoBatalla[0].length;j++){
                if(campoBatalla[i][j]!=0&&encontrados[i][j]){
                    aciertos++;
                }
            }
        }
        return aciertos;
    }
   
}
