/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilossockets;

import Beans.CampoBatalla;
import Hilos.GeneradorBarco;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author sebastian
 */
public class HilosSockets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
    //objetos para el server tcp
    ObjectInputStream entrada;
    
    ServerSocket s=new ServerSocket(12345, 30000);
    Socket s1;
    
    
        // TODO code application logic here
        CampoBatalla cb=new CampoBatalla();
        GeneradorBarco gb[]=new GeneradorBarco[4];
        for(int i=0;i<gb.length;i++){
            gb[i]=new GeneradorBarco(cb);
            gb[i].start();
        }
        for(int i=0;i<gb.length;i++){//se hacen separados para hacer el paralelismo
            gb[i].join();
        }
        cb.imprimirCampo();
        
        while(true){
            System.out.println("Esperando cliente ..");
            //crear metodos con ayuda
           s1=esperarConexion(s);
           obtenerFlujos(s1,cb);
    }
            //
     
    }
    
    private static Socket esperarConexion(ServerSocket s) throws IOException {
        //3. codigo
        Socket s1 = s.accept();
        System.out.println("Se ha conectado un cliente desde" + s1.getInetAddress());
        return s1;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static void obtenerFlujos(Socket s1,CampoBatalla cb) throws IOException, ClassNotFoundException {
        //4.codigo
        while (true) {
            //flujo entrada para leer socket
            ObjectInputStream entrada = new ObjectInputStream(s1.getInputStream());
            String cadena = (String) entrada.readObject();
            System.out.println("Dato Recibido" + cadena);
            
            
            //DEVOLVERLE LOS DATOS AL CLIENTE
            EnviaRetroalimentacionAl(cadena,s1,cb);
            //flujo salida
            
            /*enviar datos
            salida = new ObjectOutputStream(s1.getOutputStream());
            salida.writeObject(cadena);
            salida.flush();//vaciar bufer
            System.out.println("Dato enviado\n");*/
            //if (cadena.equals("exit")) {
              //  s1.close();
              //  System.out.println("\n Cierre desde el cliente");
              //  break;
            //}

        }

        //thrownew UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     private static void EnviaRetroalimentacionAl(String mensaje,Socket s1,CampoBatalla cb) throws IOException{
        ObjectOutputStream salida = new ObjectOutputStream(s1.getOutputStream());
        salida.writeObject(cb.intentarAtaque(mensaje));
        salida.flush();//vaciar bufer
        System.out.println("Dato enviado\n");

    }

}
