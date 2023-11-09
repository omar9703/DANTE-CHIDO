/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;
import Interfaz.Panel;
import Interfaz.PanelMixer;
 import Interfaz.PanelAjustes;
import Interfaz.LoginPanel;

import Datos.XmlRead;
import Datos.Configuracion;
import Datos.Logs;
import Interfaz.Networks;
import Interfaz.PanelAjustes;
import Interfaz.PanelLogs;
import Interfaz.newPanelMixer;
import sun.security.util.PendingException;
/**
 *
 * @author leone
 */
public class ClienteDante {

    /**
     * @param args the command line arguments
     */
    static Panel P;
   static ThreadStart start;
   static Networks N;
   static PanelLogs PA;
   static LoginPanel lp ;
  
    public static void main(String[] args) {
        // TODO code application logic here
        
        try{
           Logs.Write("Iniciando aplicacion clente");
           lp = new LoginPanel();
       //P=new Panel();
  
       //PA=new PanelLogs();
       //start=new ThreadStart(P);
       //start.start();
   lp.setVisible(true);
       //P.setVisible(true);
        }
        catch(Exception e){
          Logs.Write("error en archivo de configuracion");
       }
      
       
        
    }
    
}