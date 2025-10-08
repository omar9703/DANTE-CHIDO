package Interfaz;

import Datos.Configuracion;
import Datos.GlobalConfig;
import Datos.XmlRead;
import Negocio.ThreadAudio;
import Negocio.ThreadSocket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class MixerDynamic extends JPanel {
     public ArrayList<Integer> ganancias;
    public ArrayList<String> canales;
    public int Frecuencia;
    public int Muestra;
    public int[] alternar = new int[128];
    public Boolean[] varNoexcept = new Boolean[130];          
    public Boolean[] var = new Boolean[260];
    public ThreadAudio[] audio = new ThreadAudio[128];
    public  ThreadAudio[] audioSolo = new ThreadAudio[128];
    public ThreadSocket socket; 
    private int contadorPaneles = 0;
    public Configuracion Conf;
    private final int ANCHO_CELDA = 148;
    private final int ALTO_CELDA = 240;
    private XmlRead X;
    public Panel P;
    public MixerDynamic(Panel P) {
        X=new XmlRead();
        this.P = P;
         Conf= X.Read("config.xml");
        GlobalConfig.servidor = Conf.GetServidor();       
        GlobalConfig.ListaAlias = Conf.GetAlias();
        GlobalConfig.Frecuencia = Conf.GetFrecuencia();
        GlobalConfig.ListaPuertos = Conf.GetLista();
        GlobalConfig.ListaImages = Conf.GetListaImages();
        GlobalConfig.Muestra = Conf.GetMuestra();
        GlobalConfig.multicast = Conf.GetMultiCast();
        GlobalConfig.PortUDP = Conf.GetPortUDP();
        GlobalConfig.puerto = Conf.GetPuerto();
        GlobalConfig.Network = Conf.GetNet();
         canales=new ArrayList<String>();
        ganancias=new ArrayList<Integer>();
         for(int x=0;x<260;x++)
            var[x]=false;
        
        for(int i=0;i<128;i++){
            canales.add("OFF");
            ganancias.add(1);
            
           }
                inicializarPanel();
    }
    
    private void inicializarPanel() {
        setLayout(new GridLayout(0, 8, 8, 70));
        setBorder(BorderFactory.createEmptyBorder(5, 2, 8, 8));
        setBackground( Color.black);
        
        agregarPaneles(128);
    }
    
    public void agregarPanel() {
       CeldaIndividual celda = new CeldaIndividual(contadorPaneles,Conf,this);
        add(celda);
        contadorPaneles++;
        revalidate();
        repaint();
    }
    
    public void agregarPaneles(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            agregarPanel();
        }
    }
    
    public void limpiarPaneles() {
        removeAll();
        contadorPaneles = 0;
        revalidate();
        repaint();
    }
    
    public int getCantidadPaneles() {
        return contadorPaneles;
    }
    
    public CeldaIndividual getCelda(int indice) {
        if (indice >= 0 && indice < getComponentCount()) {
            Component comp = getComponent(indice);
            if (comp instanceof CeldaIndividual) {
                return (CeldaIndividual) comp;
            }
        }
        return null;
    }
    
    // Método para obtener todas las celdas
    public java.util.List<CeldaIndividual> CeldaIndividual() {
        java.util.List<CeldaIndividual> celdas = new java.util.ArrayList<>();
        for (Component comp : getComponents()) {
            if (comp instanceof CeldaIndividual) {
                celdas.add((CeldaIndividual) comp);
            }
        }
        return celdas;
    }

    public void DisableVolumeExcept(JSlider j, JToggleButton r,int y, int hilo){
        
        for (CeldaIndividual component : CeldaIndividual())
          {
            
                if(component.slider.equals(j)){
                    component.slider.setValue(10);
                }
                if (!component.slider.equals(j)){
                    component.slider.setValue(0);
                    component.slider.disable();
                    
               }
            

                if(component.toggleButton.equals(r)){
                    r.setSelected(true);
                    component.toggleButton.setBackground(new Color(255, 156, 0));
                    component.toggleButton.setIcon(new ImageIcon(getClass().getResource("auriculares.png")));                    
                    
                }
                
                else
                {
                    component.toggleButton.setSelected(false);
                    component.toggleButton.setBackground(new Color(69, 93, 220));
                    component.toggleButton.setIcon(new ImageIcon(getClass().getResource("microfono.png")));
                    
                }
            
          }
        for (int x=0;x<260;x++)
        {
            if(!(x==y))
            {
                var[x]=false;
            }
        }
        for (int x=0;x<128;x++)
        {
            if(!(hilo==x))
            {
                try{
                if(!audioSolo[x].equals(null)){
                    audioSolo[x].detener();
                    audioSolo[x].stop();
                }
                    
                }
                catch(Exception e)
                {
                    System.out.println("error cerrando ");
                }
            }
        }
        
    }
  
     public void EnableVolume(){
        
        for (CeldaIndividual component : CeldaIndividual())
          {
                component.slider.setValue(10);
                component.slider.enable();
            
          }
    }
     
     public void SetAlias(ArrayList<String> Nombres){
         int contador = 0;
        for (CeldaIndividual component : CeldaIndividual())
        {
            component.botonPrincipal.setText(Nombres.get(contador));
      
            contador++;
        }
        
     }

     public void SetImages(ArrayList<String> Images){
         int contador = 0;
        for (CeldaIndividual component : CeldaIndividual())
        {
            if(!Images.get(contador).equals("0")){
                try{
                    component.labelCentral.setIcon(new javax.swing.ImageIcon(Images.get(contador)));
                    this.repaint();
                }
                    catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
            }
            else
            {
               try{
                    component.labelCentral.setIcon(new javax.swing.ImageIcon("no_image.png"));
                    this.repaint();
                    }
                    catch(Exception ex){
                        System.out.println("ERROR DE ESCRITURA");
                    }

            }
            contador++;
        }
     }
     public void ClearAll(){
        
        for (CeldaIndividual component : CeldaIndividual())
        {
                               
                component.slider.setValue(10);
                //((JSlider) component).disable();
                

                component.toggleButton.setSelected(false);
                component.toggleButton.setBackground(Color.white);

                component.botonPrincipal.setBackground(Color.BLUE);
            
            
        }
        for (int x=0;x<260;x++)
        {           
                var[x]=false;
            
        }
        for (int x=0;x<128;x++)
        {
            alternar[x]=0;
            
            try{
            if(!audio[x].equals(null))
                audio[x].detener();
                audio[x].stop();
            }
            catch(Exception e)
            {
                System.out.println("error cerrando ");
            }
            
            try{
                
                if(varNoexcept[x+1]){
                    audioSolo[x].detener();
                    audioSolo[x].stop();
                }
                
            }                             
            catch(Exception ex)
            {
            }
            
        }
        
        for(int x=0;x<varNoexcept.length;x++){
            varNoexcept[x]=false;
        }
    }
}