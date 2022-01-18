/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import Negocio.ThreadAudio;
import Negocio.ThreadSocket;
import Datos.Configuracion;
import Datos.GlobalConfig;
import Datos.XmlRead;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
//import javafx.scene.control.RadioButton;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
/**
/**
 *
 * @author campos
 */
public class newPanelMixer extends javax.swing.JPanel {

  private ArrayList<Integer> ganancias;
    private ArrayList<String> canales;
    private int Frecuencia;
    private int Muestra;
    
    private int alternarSOLO = 0;
    
    private ThreadAudio[] audio = new ThreadAudio[64];
    private ThreadAudio[] audioSolo = new ThreadAudio[64];
    
    
    private int[] alternar = new int[64];
    
    private Boolean[] varNoexcept = new Boolean[65];
            
    public Boolean[] var = new Boolean[130];
   
           
    private Configuracion Conf;
    private XmlRead X;
    private Panel P;
    
    private ThreadSocket socket;
    /**
     * Creates new form NewJPanel
     */
    public newPanelMixer(Panel P) {
        this.P=P;
        initComponents();
        X=new XmlRead();
       
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
        for(int x=0;x<130;x++)
            var[x]=false;
        
        for(int i=0;i<64;i++){
            canales.add("OFF");
            ganancias.add(1);
            
           }
        
        ///init
        V1.setValue(10);
        V2.setValue(10);
        V3.setValue(10);
        V4.setValue(10);
        V5.setValue(10);
        V6.setValue(10);
        V7.setValue(10);
        V8.setValue(10);
        V9.setValue(10);
        V10.setValue(10);
        V11.setValue(10);
        V12.setValue(10);
        V13.setValue(10);
        V14.setValue(10);
        V15.setValue(10);
        V16.setValue(10);
        V17.setValue(10);
        V18.setValue(10);
        V19.setValue(10);
        V20.setValue(10);
        V21.setValue(10);
        V22.setValue(10);
        V23.setValue(10);
        V24.setValue(10);
        V25.setValue(10);
        V26.setValue(10);
        V27.setValue(10);
        V28.setValue(10);
        V29.setValue(10);
        V30.setValue(10);
        V31.setValue(10);
        V32.setValue(10);
        
        V33.setValue(10);
        V34.setValue(10);
        V35.setValue(10);
        V36.setValue(10);
        V37.setValue(10);
        V38.setValue(10);
        V39.setValue(10);
        V40.setValue(10);
        V41.setValue(10);
        V42.setValue(10);
        V43.setValue(10);
        V44.setValue(10);
        V45.setValue(10);
        V46.setValue(10);
        V47.setValue(10);
        V48.setValue(10);
        V49.setValue(10);
        V50.setValue(10);
        V51.setValue(10);
        V52.setValue(10);
        V53.setValue(10);
        V54.setValue(10);
        V55.setValue(10);
        V56.setValue(10);
        V57.setValue(10);
        V58.setValue(10);
        V59.setValue(10);
        V60.setValue(10);
        V61.setValue(10);
        V62.setValue(10);
        V63.setValue(10);
        V64.setValue(10);
        
        
        LoadImages(Conf);
        LoadAlias(Conf);
        
        
        Component[] components = this.getComponents();
        
        for (Component component : components)
          {
            if (component instanceof JSlider)
            {
                                              
                ((JSlider) component).setValue(5);
                
                
            }
            if(component instanceof JToggleButton)
            {
              
                ((JToggleButton)component).setSelected(false);
            }
            
            if(component instanceof JButton)
            {
              
                ((JButton)component).setBackground(new Color(25, 31, 49));
            }
            
          }
        components = null;
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public void ClearAll(){
        
        
        Component[] components = this.getComponents();
        
        for (Component component : components)
          {
            if (component instanceof JSlider)
            {
                                              
                ((JSlider) component).setValue(10);
                //((JSlider) component).disable();
                
            }
            if(component instanceof JToggleButton)
            {
              
                ((JToggleButton)component).setSelected(false);
                ((JToggleButton)component).setBackground(Color.white);
            }
            
            if(component instanceof JButton)
            {
              
                ((JButton)component).setBackground(Color.BLUE);
            }
            
          }
        for (int x=0;x<130;x++)
        {           
                var[x]=false;
            
        }
        for (int x=0;x<64;x++)
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
        components = null;
    }
    
    
    void mainfunctionAudioChannel(int index,JButton button,JSlider slider,int canal){
        var[(index*2)-1]=true;
        if(!var[index*2])
        {// TODO add your handling code here:     
            alternar[index-1]++;   
            if(alternar[index-1]==1){

                 try{
                                                   
                    audio[index-1]=new ThreadAudio(GlobalConfig.Network,GlobalConfig.ListaPuertos.get(0),GlobalConfig.multicast,P,GlobalConfig.Frecuencia,GlobalConfig.Muestra,canal,slider,button,socket);
                    audio[index-1].start();
                    button.setBackground(Color.GREEN);
                    varNoexcept[index] = false; 
                                     
                 }
                 catch(Exception e){
                    System.out.println("Error de hilo ");
                    button.setBackground(Color.RED);
                    slider.setEnabled(false);
                    //var[index-1]=false;
                 }


            }   

            if(alternar[index-1]==2){
                 audio[index-1].detener();
                 audio[index-1].stop();
                 alternar[index-1]=0;
                 button.setBackground(new Color(25, 31, 49));
                 //var[index-1]=false;
                    
                 var[(index*2)-1]=false;
            }
        }
    }
    
    void mainfunctionSOLO(int index,JButton button,JToggleButton togglebutton,JSlider slider,int canal){
        var[index*2]=true;
        if(togglebutton.isSelected()&&!var[(index*2)-1]){
            DisableVolumeExcept(slider,togglebutton,index*2,index-1);
            System.out.println("activado");
                        
         try{
             audioSolo[index-1]=new ThreadAudio(GlobalConfig.Network,GlobalConfig.ListaPuertos.get(0),GlobalConfig.multicast,P,GlobalConfig.Frecuencia,GlobalConfig.Muestra,canal,slider,button,socket);
             audioSolo[index-1].start();
             varNoexcept[index] = true; 
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               var[index*2]=false;
         }     
        }
        if(togglebutton.isSelected()&&var[(index*2)-1])
        {
            DisableVolumeExcept(slider,togglebutton,index*2,index-1);
        }
        if(!togglebutton.isSelected()){
            EnableVolume();
            var[index*2]=false;
            try{
                if(varNoexcept[index]){
                    audioSolo[index-1].detener();
                    audioSolo[index-1].stop();
                }
                
            }
            catch(Exception ex)
            {
            }
            
            togglebutton.setBackground(new Color(69, 93, 220));
            togglebutton.setIcon(new ImageIcon(getClass().getResource("microfono.png")));
        }
    }
    
    public void SetGanancias(ArrayList<Integer> ganancias){
       this.ganancias=ganancias;
    }
    
    public ArrayList<Integer> GetGanacias(){
      return ganancias;
    }
    
     public void SetCanales(ArrayList<String> canales){
       this.canales=canales;
    }
    
    public ArrayList<String> GetCanales(){
      return canales;
    }
 
    public void SetAlias(ArrayList<String> Nombres){
       
       this.C1.setText(Nombres.get(0));
       this.C2.setText(Nombres.get(1));
       this.C3.setText(Nombres.get(2));
       this.C4.setText(Nombres.get(3));
       this.C5.setText(Nombres.get(4));
       this.C6.setText(Nombres.get(28));
       this.C7.setText(Nombres.get(6));
       this.C8.setText(Nombres.get(7));
      
       this.C9.setText(Nombres.get(8));
       this.C10.setText(Nombres.get(9));
       this.C11.setText(Nombres.get(10));
       this.C12.setText(Nombres.get(11));
       this.C13.setText(Nombres.get(12));
       this.C14.setText(Nombres.get(13));
       this.C15.setText(Nombres.get(14));
       this.C16.setText(Nombres.get(15));
       
       this.C17.setText(Nombres.get(16));
       this.C18.setText(Nombres.get(17));
       this.C19.setText(Nombres.get(18));
       this.C20.setText(Nombres.get(19));
       this.C21.setText(Nombres.get(20));
       this.C22.setText(Nombres.get(21));
       this.C23.setText(Nombres.get(22));
       this.C24.setText(Nombres.get(23));
       
       this.C25.setText(Nombres.get(24));
       this.C26.setText(Nombres.get(25));
       this.C27.setText(Nombres.get(26));
       this.C28.setText(Nombres.get(27));
       this.C29.setText(Nombres.get(5));
       this.C30.setText(Nombres.get(29));
       this.C31.setText(Nombres.get(30));
       this.C32.setText(Nombres.get(31));
       
       
       //32 to 64
       
       
       this.C33.setText(Nombres.get(32));
       this.C34.setText(Nombres.get(33));
       this.C35.setText(Nombres.get(34));
       this.C36.setText(Nombres.get(35));
       this.C37.setText(Nombres.get(36));
       this.C38.setText(Nombres.get(37));
       this.C39.setText(Nombres.get(38));
      
       this.C40.setText(Nombres.get(39));
       this.C41.setText(Nombres.get(40));
       this.C42.setText(Nombres.get(41));
       this.C43.setText(Nombres.get(42));
       this.C44.setText(Nombres.get(43));
       this.C45.setText(Nombres.get(44));
       this.C46.setText(Nombres.get(45));
       this.C47.setText(Nombres.get(46));
       
       this.C48.setText(Nombres.get(47));
       this.C49.setText(Nombres.get(48));
       this.C50.setText(Nombres.get(49));
       this.C51.setText(Nombres.get(50));
       this.C52.setText(Nombres.get(51));
       this.C53.setText(Nombres.get(52));
       this.C54.setText(Nombres.get(53));
       this.C55.setText(Nombres.get(54));
       
       this.C56.setText(Nombres.get(55));
       this.C57.setText(Nombres.get(56));
       this.C58.setText(Nombres.get(57));
       this.C59.setText(Nombres.get(58));
       this.C60.setText(Nombres.get(59));
       this.C61.setText(Nombres.get(60));
       this.C62.setText(Nombres.get(61));
       this.C63.setText(Nombres.get(62));
       this.C64.setText(Nombres.get(63));
       this.repaint();
             
    }
    
    
    
    public void SetImages(ArrayList<String> Images){
             
       if(!Images.get(0).equals("0")){
            try{
                I1.setIcon(new javax.swing.ImageIcon(Images.get(0)));
                this.repaint();
            }
                catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
       else
       {
           try{
                I1.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       if(!Images.get(1).equals("0")){
                try{
                    I2.setIcon(new javax.swing.ImageIcon(Images.get(1)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I2.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       if(!Images.get(2).equals("0")){
                try{
                    I3.setIcon(new javax.swing.ImageIcon(Images.get(2)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I3.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       if(!Images.get(3).equals("0")){
                try{
                    I4.setIcon(new javax.swing.ImageIcon(Images.get(3)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I4.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       if(!Images.get(4).equals("0")){
                try{
                    I5.setIcon(new javax.swing.ImageIcon(Images.get(4)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I5.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       } 
       
       
       if(!Images.get(5).equals("0")){
                try{
                    I6.setIcon(new javax.swing.ImageIcon(Images.get(5)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I6.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
      if(!Images.get(6).equals("0")){
                try{
                    I7.setIcon(new javax.swing.ImageIcon(Images.get(6)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
      
       else{
           try{
                I7.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       if(!Images.get(7).equals("0")){
                try{
                    I8.setIcon(new javax.swing.ImageIcon(Images.get(7)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I8.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       if(!Images.get(8).equals("0")){
                try{
                    I9.setIcon(new javax.swing.ImageIcon(Images.get(8)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I9.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
      
       if(!Images.get(9).equals("0")){
                try{
                    I10.setIcon(new javax.swing.ImageIcon(Images.get(9)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I10.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       if(!Images.get(10).equals("0")){
                try{
                    I11.setIcon(new javax.swing.ImageIcon(Images.get(10)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I11.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       if(!Images.get(11).equals("0")){
                try{
                    I12.setIcon(new javax.swing.ImageIcon(Images.get(11)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I12.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       if(!Images.get(12).equals("0")){
                try{
                    I13.setIcon(new javax.swing.ImageIcon(Images.get(12)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I13.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       if(!Images.get(13).equals("0")){
                try{
                    I14.setIcon(new javax.swing.ImageIcon(Images.get(13)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I14.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       if(!Images.get(14).equals("0")){
            try{
                I15.setIcon(new javax.swing.ImageIcon(Images.get(14)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I15.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       if(!Images.get(15).equals("0")){
            try{
                I16.setIcon(new javax.swing.ImageIcon(Images.get(15)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I16.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
        if(!Images.get(16).equals("0")){
            try{
                I17.setIcon(new javax.swing.ImageIcon(Images.get(16)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I17.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!Images.get(17).equals("0")){
            try{
                I18.setIcon(new javax.swing.ImageIcon(Images.get(17)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I18.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!Images.get(18).equals("0")){
            try{
                I19.setIcon(new javax.swing.ImageIcon(Images.get(18)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I19.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!Images.get(19).equals("0")){
            try{
                I20.setIcon(new javax.swing.ImageIcon(Images.get(19)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I20.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!Images.get(20).equals("0")){
            try{
                I21.setIcon(new javax.swing.ImageIcon(Images.get(20)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I21.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!Images.get(21).equals("0")){
            try{
                I22.setIcon(new javax.swing.ImageIcon(Images.get(21)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I22.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        if(!Images.get(22).equals("0")){
            try{
                I23.setIcon(new javax.swing.ImageIcon(Images.get(22)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I23.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        if(!Images.get(23).equals("0")){
            try{
                I24.setIcon(new javax.swing.ImageIcon(Images.get(23)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I24.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!Images.get(24).equals("0")){
            try{
                I25.setIcon(new javax.swing.ImageIcon(Images.get(24)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I25.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!Images.get(25).equals("0")){
            try{
                I26.setIcon(new javax.swing.ImageIcon(Images.get(25)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I26.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!Images.get(26).equals("0")){
            try{
                I27.setIcon(new javax.swing.ImageIcon(Images.get(26)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I27.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!Images.get(27).equals("0")){
            try{
                I28.setIcon(new javax.swing.ImageIcon(Images.get(27)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I28.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!Images.get(28).equals("0")){
            try{
                I29.setIcon(new javax.swing.ImageIcon(Images.get(28)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I29.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!Images.get(29).equals("0")){
            try{
                I30.setIcon(new javax.swing.ImageIcon(Images.get(29)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I30.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        if(!Images.get(30).equals("0")){
            try{
                I31.setIcon(new javax.swing.ImageIcon(Images.get(30)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I31.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        if(!Images.get(31).equals("0")){
            try{
                I32.setIcon(new javax.swing.ImageIcon(Images.get(31)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I32.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        //32 to 64
        
        changeImage(Images, 32);
        changeImage(Images, 33);
        changeImage(Images, 34);
        changeImage(Images, 35);
        changeImage(Images, 36);
        changeImage(Images, 37);
        changeImage(Images, 38);
        changeImage(Images, 39);
        changeImage(Images, 40);
        changeImage(Images, 41);
        changeImage(Images, 42);
        changeImage(Images, 43);
        changeImage(Images, 44);
        changeImage(Images, 45);
        changeImage(Images, 46);
        changeImage(Images, 47);
        changeImage(Images, 48);
        changeImage(Images, 49);
        changeImage(Images, 50);
        changeImage(Images, 51);
        changeImage(Images, 52);
        changeImage(Images, 53);
        changeImage(Images, 54);
        changeImage(Images, 55);
        changeImage(Images, 56);
        changeImage(Images, 57);
        changeImage(Images, 58);
        changeImage(Images, 59);
        changeImage(Images, 60);
        changeImage(Images, 61);
        changeImage(Images, 62);
        changeImage(Images, 63);
        
       
    }
    
    void changeImage(ArrayList<String> Images,int index){
        if(!Images.get(index).equals("0")){
            try{
                
                switch(index){
                    case 0:
                        I1.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 1:
                        I2.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 2:
                        I3.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 3:
                        I4.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 4:
                        I5.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 5:
                        I6.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 6:
                        I7.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 7:
                        I8.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 8:
                        I9.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 9:
                        I10.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 10:
                        I11.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 11:
                        I12.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 12:
                        I13.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 13:
                        I14.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 14:
                        I15.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 15:
                        I16.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 16:
                        I17.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 17:
                        I18.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 18:
                        I19.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 19:
                        I20.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 20:
                        I21.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 21:
                        I22.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 22:
                        I23.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 23:
                        I24.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 24:
                        I25.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 25:
                        I26.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 26:
                        I27.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 27:
                        I28.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 28:
                        I29.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 29:
                        I30.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 30:
                        I31.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 31:
                        I32.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    
                    case 32:
                        I33.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 33:
                        I34.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 34:
                        I35.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 35:
                        I36.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 36:
                        I37.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 37:
                        I38.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 38:
                        I39.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 39:
                        I40.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 40:
                        I41.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 41:
                        I42.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 42:
                        I43.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 43:
                        I44.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 44:
                        I45.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 45:
                        I46.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 46:
                        I47.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 47:
                        I48.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 48:
                        I49.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 49:
                        I50.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 50:
                        I51.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 51:
                        I52.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 52:
                        I53.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 53:
                        I54.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 54:
                        I55.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 55:
                        I56.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 56:
                        I57.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 57:
                        I58.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 58:
                        I59.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 59:
                        I60.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 60:
                        I61.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 61:
                        I62.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 62:
                        I63.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                    case 63:
                        I64.setIcon(new javax.swing.ImageIcon(Images.get(index)));
                        break;
                }
                
                
                
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
               
                switch(index){
                    case 0:
                        I1.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 1:
                        I2.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 2:
                        I3.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 3:
                        I4.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 4:
                        I5.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 5:
                        I6.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 6:
                        I7.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 7:
                        I8.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 8:
                        I9.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 9:
                        I10.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 10:
                        I11.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 11:
                        I12.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 12:
                        I13.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 13:
                        I14.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 14:
                        I15.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 15:
                        I16.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 16:
                        I17.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 17:
                        I18.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 18:
                        I19.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 19:
                        I20.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 20:
                        I21.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 21:
                        I22.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 22:
                        I23.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 23:
                        I24.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 24:
                        I25.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 25:
                        I26.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 26:
                        I27.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 27:
                        I28.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 28:
                        I29.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 29:
                        I30.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 30:
                        I31.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 31:
                        I32.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    
                    case 32:
                        I33.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 33:
                        I34.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 34:
                        I35.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 35:
                        I36.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 36:
                        I37.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 37:
                        I38.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 38:
                        I39.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 39:
                        I40.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 40:
                        I41.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 41:
                        I42.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 42:
                        I43.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 43:
                        I44.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 44:
                        I45.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 45:
                        I46.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 46:
                        I47.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 47:
                        I48.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 48:
                        I49.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 49:
                        I50.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 50:
                        I51.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 51:
                        I52.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 52:
                        I53.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 53:
                        I54.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 54:
                        I55.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 55:
                        I56.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 56:
                        I57.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 57:
                        I58.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 58:
                        I59.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 59:
                        I60.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 60:
                        I61.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 61:
                        I62.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 62:
                        I63.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                    case 63:
                        I64.setIcon(new javax.swing.ImageIcon("no_image.png"));
                        break;
                }
               
      
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
    }
    
    public void LoadAlias(Configuracion C){
       this.C1.setText(C.GetAlias().get(0));
       this.C2.setText(C.GetAlias().get(1));
       this.C3.setText(C.GetAlias().get(2));
       this.C4.setText(C.GetAlias().get(3));
       this.C5.setText(C.GetAlias().get(4));
       this.C6.setText(C.GetAlias().get(28));
       this.C7.setText(C.GetAlias().get(6));
       this.C8.setText(C.GetAlias().get(7));
       this.C9.setText(C.GetAlias().get(8));
       this.C10.setText(C.GetAlias().get(9));
       this.C11.setText(C.GetAlias().get(10));
       this.C12.setText(C.GetAlias().get(11));
       this.C13.setText(C.GetAlias().get(12));
       this.C14.setText(C.GetAlias().get(13));
       this.C15.setText(C.GetAlias().get(14));
       this.C16.setText(C.GetAlias().get(15));
       this.C17.setText(C.GetAlias().get(16));
       this.C18.setText(C.GetAlias().get(17));
       this.C19.setText(C.GetAlias().get(18));
       this.C20.setText(C.GetAlias().get(19));
       this.C21.setText(C.GetAlias().get(20));
       this.C22.setText(C.GetAlias().get(21));
       this.C23.setText(C.GetAlias().get(22));
       this.C24.setText(C.GetAlias().get(23));
       this.C25.setText(C.GetAlias().get(24));
       this.C26.setText(C.GetAlias().get(25));
       this.C27.setText(C.GetAlias().get(26));
       this.C28.setText(C.GetAlias().get(27));
       this.C29.setText(C.GetAlias().get(5));
       this.C30.setText(C.GetAlias().get(29));
       this.C31.setText(C.GetAlias().get(30));
       this.C32.setText(C.GetAlias().get(31));
       
       this.C33.setText(C.GetAlias().get(32));
       this.C34.setText(C.GetAlias().get(33));
       this.C35.setText(C.GetAlias().get(34));
       this.C36.setText(C.GetAlias().get(35));
       this.C37.setText(C.GetAlias().get(36));
       this.C38.setText(C.GetAlias().get(37));
       this.C39.setText(C.GetAlias().get(38));
       this.C40.setText(C.GetAlias().get(39));
       this.C41.setText(C.GetAlias().get(40));
       this.C42.setText(C.GetAlias().get(41));
       this.C43.setText(C.GetAlias().get(42));
       this.C44.setText(C.GetAlias().get(43));
       this.C45.setText(C.GetAlias().get(44));
       this.C46.setText(C.GetAlias().get(45));
       this.C47.setText(C.GetAlias().get(46));
       this.C48.setText(C.GetAlias().get(47));
       this.C49.setText(C.GetAlias().get(48));
       this.C50.setText(C.GetAlias().get(49));
       this.C51.setText(C.GetAlias().get(50));
       this.C52.setText(C.GetAlias().get(51));
       this.C53.setText(C.GetAlias().get(52));
       this.C54.setText(C.GetAlias().get(53));
       this.C55.setText(C.GetAlias().get(54));
       this.C56.setText(C.GetAlias().get(55));
       this.C57.setText(C.GetAlias().get(56));
       this.C58.setText(C.GetAlias().get(57));
       this.C59.setText(C.GetAlias().get(58));
       this.C60.setText(C.GetAlias().get(59));
       this.C61.setText(C.GetAlias().get(60));
       this.C62.setText(C.GetAlias().get(61));
       this.C63.setText(C.GetAlias().get(62));
       this.C64.setText(C.GetAlias().get(63));
       
       this.repaint();
    } 
 
    private void LoadImages(Configuracion C){
             
       if(!C.GetListaImages().get(0).equals("0")){
                try{
                    I1.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(0)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I1.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       
       if(!C.GetListaImages().get(1).equals("0")){
                try{
                    I2.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(1)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I2.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       if(!C.GetListaImages().get(2).equals("0")){
                try{
                    I3.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(2)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I3.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       
       if(!C.GetListaImages().get(3).equals("0")){
                try{
                    I4.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(3)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I4.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       if(!C.GetListaImages().get(4).equals("0")){
                try{
                    I5.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(4)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I5.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       if(!C.GetListaImages().get(5).equals("0")){
                try{
                    I6.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(5)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I6.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
         if(!C.GetListaImages().get(6).equals("0")){
                try{
                    I7.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(6)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I7.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
         if(!C.GetListaImages().get(7).equals("0")){
                try{
                    I8.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(7)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I8.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       if(!C.GetListaImages().get(8).equals("0")){
                try{
                    I9.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(8)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I9.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       if(!C.GetListaImages().get(9).equals("0")){
                try{
                    I10.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(9)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I10.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       if(!C.GetListaImages().get(10).equals("0")){
                try{
                    I11.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(10)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I11.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       if(!C.GetListaImages().get(11).equals("0")){
                try{
                    I12.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(11)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I12.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       if(!C.GetListaImages().get(12).equals("0")){
                try{
                    I13.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(12)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I13.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       if(!C.GetListaImages().get(13).equals("0")){
                try{
                    I14.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(13)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I14.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
         if(!C.GetListaImages().get(14).equals("0")){
                try{
                    I15.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(14)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I15.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
         if(!C.GetListaImages().get(15).equals("0")){
                try{
                    I16.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(15)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I16.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
       
       
       
        if(!C.GetListaImages().get(16).equals("0")){
            try{
                I17.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(16)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I17.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!C.GetListaImages().get(17).equals("0")){
            try{
                I18.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(17)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I18.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!C.GetListaImages().get(18).equals("0")){
            try{
                I19.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(18)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I19.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!C.GetListaImages().get(19).equals("0")){
            try{
                I20.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(19)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I20.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!C.GetListaImages().get(20).equals("0")){
            try{
                I21.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(20)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I21.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!C.GetListaImages().get(21).equals("0")){
            try{
                I22.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(21)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I22.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
         if(!C.GetListaImages().get(22).equals("0")){
                try{
                    I23.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(22)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I23.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
          if(!C.GetListaImages().get(23).equals("0")){
                try{
                    I24.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(23)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I24.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
        
        if(!C.GetListaImages().get(24).equals("0")){
            try{
                I25.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(24)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I25.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        
        if(!C.GetListaImages().get(25).equals("0")){
            try{
                I26.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(25)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I26.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        if(!C.GetListaImages().get(26).equals("0")){
            try{
                I27.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(26)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I27.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        if(!C.GetListaImages().get(27).equals("0")){
            try{
                I28.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(27)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I28.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        if(!C.GetListaImages().get(28).equals("0")){
            try{
                I29.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(28)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I29.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
        if(!C.GetListaImages().get(29).equals("0")){
            try{
                I30.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(29)));
                this.repaint();
            }
            catch(Exception ex){
                System.out.println("ERROR DE ESCRITURA");
            }      
        }
        else{
           try{
                I30.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        
         if(!C.GetListaImages().get(30).equals("0")){
                try{
                    I31.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(30)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I31.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
       
         if(!C.GetListaImages().get(31).equals("0")){
                try{
                    I32.setIcon(new javax.swing.ImageIcon(C.GetListaImages().get(31)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                I32.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
      
       
        changeImage(C.GetListaImages(), 32);
        changeImage(C.GetListaImages(), 33);
        changeImage(C.GetListaImages(), 34);
        changeImage(C.GetListaImages(), 35);
        changeImage(C.GetListaImages(), 36);
        changeImage(C.GetListaImages(), 37);
        changeImage(C.GetListaImages(), 38);
        changeImage(C.GetListaImages(), 39);
        changeImage(C.GetListaImages(), 40);
        changeImage(C.GetListaImages(), 41);
        changeImage(C.GetListaImages(), 42);
        changeImage(C.GetListaImages(), 43);
        changeImage(C.GetListaImages(), 44);
        changeImage(C.GetListaImages(), 45);
        changeImage(C.GetListaImages(), 46);
        changeImage(C.GetListaImages(), 47);
        changeImage(C.GetListaImages(), 48);
        changeImage(C.GetListaImages(), 49);
        changeImage(C.GetListaImages(), 50);
        changeImage(C.GetListaImages(), 51);
        changeImage(C.GetListaImages(), 52);
        changeImage(C.GetListaImages(), 53);
        changeImage(C.GetListaImages(), 54);
        changeImage(C.GetListaImages(), 55);
        changeImage(C.GetListaImages(), 56);
        changeImage(C.GetListaImages(), 57);
        changeImage(C.GetListaImages(), 58);
        changeImage(C.GetListaImages(), 59);
        changeImage(C.GetListaImages(), 60);
        changeImage(C.GetListaImages(), 61);
        changeImage(C.GetListaImages(), 62);
        changeImage(C.GetListaImages(), 63);
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
                                                                                                                                               

    private void V9StateChanged(javax.swing.event.ChangeEvent evt) {                                
        // TODO add your handling code here:
        ganancias.set(8,V9.getValue());
    }                               

    private void V10StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(9,V10.getValue());
    }                                

    private void V11StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(10,V11.getValue());
    }                                

    private void V12StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(11,V12.getValue());
    }                                

    private void V13StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(12,V13.getValue());
    }                                

    private void V14StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(13,V14.getValue());
    }                                

    private void V17StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(16,V17.getValue());
    }                                

    private void V18StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(17,V18.getValue());
    }                                

    private void V19StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(18,V19.getValue());
    }                                

    private void V20StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(19,V20.getValue());
    }                                

    private void V21StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(20,V21.getValue());
    }                                

    private void V22StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(21,V22.getValue());
    }                                

    private void V30StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(29,V30.getValue());
    }                                

    private void V25StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(24,V25.getValue());
    }                                

    private void V26StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(25,V26.getValue());
    }                                

    private void V27StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(26,V27.getValue());
    }                                

    private void V28StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(27,V28.getValue());
    }                                

    private void V29StateChanged(javax.swing.event.ChangeEvent evt) {                                 
        // TODO add your handling code here:
        ganancias.set(28,V29.getValue());
    }                                              
                                                                                                                                                                                                                                                                                                                          
                                                                                              
    private void V1StateChanged(javax.swing.event.ChangeEvent evt) {                                
        // TODO add your handling code here:
         ganancias.set(0,V1.getValue());
    }                               

    private void V2StateChanged(javax.swing.event.ChangeEvent evt) {                                
        // TODO add your handling code here:
        ganancias.set(1,V2.getValue());
    }                               

    private void V3StateChanged(javax.swing.event.ChangeEvent evt) {                                
        // TODO add your handling code here:
        ganancias.set(2,V3.getValue());
    }                               

    private void V4StateChanged(javax.swing.event.ChangeEvent evt) {                                
        // TODO add your handling code here:
        ganancias.set(3,V4.getValue());
    }                               

    private void V5StateChanged(javax.swing.event.ChangeEvent evt) {                                
        // TODO add your handling code here:
        ganancias.set(4,V5.getValue());
    }                               

    private void V6StateChanged(javax.swing.event.ChangeEvent evt) {                                
        // TODO add your handling code here:
        ganancias.set(5,V6.getValue());
    }                               

    private boolean desactivateButton(JButton boton,JRadioButton radio,int banderaAlternar,ThreadAudio tempAudio,boolean lastActivate){
       
        try{
            if(!radio.isSelected() && tempAudio.IsRunning()){
            tempAudio.SetForcedDown(true);
            //tempAudio.detener();
            banderaAlternar=0;
            boton.setBackground(Color.blue);
            lastActivate = true;
            radio.setEnabled(false);
            return true;
        }
        }
        catch(Exception ex){
            return false;
            
        }
        
        
        return false;
    }
    
    private void activateLastButton(JButton boton,JRadioButton radio,int banderaAlternar,ThreadAudio tempAudio,boolean lastActivate,JSlider V){
        
        if(lastActivate==true){
            lastActivate=false;
            banderaAlternar=1;
            
            try{
            Conf=X.Read("config.xml"); 
            tempAudio=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(0),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),0,V,boton,socket);
            tempAudio.start();
            boton.setBackground(Color.GREEN);
            
            radio.setEnabled(true);
            radio.setSelected(false);
         }
         catch(Exception e){
            System.out.println("Error de hilo ");
            boton.setBackground(Color.RED);
            radio.setEnabled(false);
            
         }
            
        }
        
    }
    
    
    
    private void DisableVolumeExcept(JSlider j, JToggleButton r,int y, int hilo){
        
        Component[] components = this.getComponents();
        
        for (Component component : components)
          {
            if (component instanceof JSlider)
            {
                if(component.equals(j)){
                    ((JSlider)component).setValue(10);
                }
                if (!component.equals(j)){
                    ((JSlider) component).setValue(0);
                    ((JSlider) component).disable();
                    
                }
            }
            if(component instanceof JToggleButton)
            {
                if(component.equals(r)){
                    r.setSelected(true);
                    ((JToggleButton)component).setBackground(new Color(255, 156, 0));
                    ((JToggleButton)component).setIcon(new ImageIcon(getClass().getResource("auriculares.png")));                    
                    
                }
                
                else
                {
                    ((JToggleButton)component).setSelected(false);
                    ((JToggleButton)component).setBackground(new Color(69, 93, 220));
                    ((JToggleButton)component).setIcon(new ImageIcon(getClass().getResource("microfono.png")));
                    
                }
            }
          }
        for (int x=0;x<130;x++)
        {
            if(!(x==y))
            {
                var[x]=false;
            }
        }
        for (int x=0;x<64;x++)
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
    
     private void EnableVolume(){
        
        Component[] components = this.getComponents();
        
        for (Component component : components)
          {
            if (component instanceof JSlider)
            {
                ((JSlider) component).setValue(10);
                ((JSlider) component).enable();
            }
          }
    }
      ////
    
    void test(){
    }
    
                                                                                                                                                                               
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        I1 = new javax.swing.JLabel();
        I9 = new javax.swing.JLabel();
        V1 = new javax.swing.JSlider();
        C1 = new javax.swing.JButton();
        C9 = new javax.swing.JButton();
        V9 = new javax.swing.JSlider();
        S1 = new javax.swing.JToggleButton();
        I17 = new javax.swing.JLabel();
        C17 = new javax.swing.JButton();
        V17 = new javax.swing.JSlider();
        S17 = new javax.swing.JToggleButton();
        I25 = new javax.swing.JLabel();
        C25 = new javax.swing.JButton();
        V25 = new javax.swing.JSlider();
        S25 = new javax.swing.JToggleButton();
        S9 = new javax.swing.JToggleButton();
        I3 = new javax.swing.JLabel();
        C3 = new javax.swing.JButton();
        V3 = new javax.swing.JSlider();
        S3 = new javax.swing.JToggleButton();
        I11 = new javax.swing.JLabel();
        C11 = new javax.swing.JButton();
        V11 = new javax.swing.JSlider();
        S11 = new javax.swing.JToggleButton();
        I19 = new javax.swing.JLabel();
        C19 = new javax.swing.JButton();
        V19 = new javax.swing.JSlider();
        S19 = new javax.swing.JToggleButton();
        I27 = new javax.swing.JLabel();
        C27 = new javax.swing.JButton();
        V27 = new javax.swing.JSlider();
        S27 = new javax.swing.JToggleButton();
        S2 = new javax.swing.JToggleButton();
        V2 = new javax.swing.JSlider();
        C2 = new javax.swing.JButton();
        I2 = new javax.swing.JLabel();
        S10 = new javax.swing.JToggleButton();
        V10 = new javax.swing.JSlider();
        C10 = new javax.swing.JButton();
        I10 = new javax.swing.JLabel();
        S18 = new javax.swing.JToggleButton();
        V18 = new javax.swing.JSlider();
        C18 = new javax.swing.JButton();
        I18 = new javax.swing.JLabel();
        S26 = new javax.swing.JToggleButton();
        V26 = new javax.swing.JSlider();
        C26 = new javax.swing.JButton();
        I26 = new javax.swing.JLabel();
        I4 = new javax.swing.JLabel();
        C4 = new javax.swing.JButton();
        V4 = new javax.swing.JSlider();
        S4 = new javax.swing.JToggleButton();
        I12 = new javax.swing.JLabel();
        C12 = new javax.swing.JButton();
        V12 = new javax.swing.JSlider();
        S12 = new javax.swing.JToggleButton();
        I20 = new javax.swing.JLabel();
        C20 = new javax.swing.JButton();
        V20 = new javax.swing.JSlider();
        S20 = new javax.swing.JToggleButton();
        I28 = new javax.swing.JLabel();
        C28 = new javax.swing.JButton();
        V28 = new javax.swing.JSlider();
        S28 = new javax.swing.JToggleButton();
        I5 = new javax.swing.JLabel();
        C5 = new javax.swing.JButton();
        V5 = new javax.swing.JSlider();
        S5 = new javax.swing.JToggleButton();
        I7 = new javax.swing.JLabel();
        C7 = new javax.swing.JButton();
        V7 = new javax.swing.JSlider();
        S7 = new javax.swing.JToggleButton();
        I13 = new javax.swing.JLabel();
        C13 = new javax.swing.JButton();
        V13 = new javax.swing.JSlider();
        S13 = new javax.swing.JToggleButton();
        I21 = new javax.swing.JLabel();
        C21 = new javax.swing.JButton();
        V21 = new javax.swing.JSlider();
        S21 = new javax.swing.JToggleButton();
        I29 = new javax.swing.JLabel();
        C6 = new javax.swing.JButton();
        V6 = new javax.swing.JSlider();
        S6 = new javax.swing.JToggleButton();
        I6 = new javax.swing.JLabel();
        C29 = new javax.swing.JButton();
        V29 = new javax.swing.JSlider();
        S29 = new javax.swing.JToggleButton();
        I14 = new javax.swing.JLabel();
        C14 = new javax.swing.JButton();
        V14 = new javax.swing.JSlider();
        S14 = new javax.swing.JToggleButton();
        I22 = new javax.swing.JLabel();
        C22 = new javax.swing.JButton();
        V22 = new javax.swing.JSlider();
        S22 = new javax.swing.JToggleButton();
        I30 = new javax.swing.JLabel();
        C30 = new javax.swing.JButton();
        V30 = new javax.swing.JSlider();
        S30 = new javax.swing.JToggleButton();
        I8 = new javax.swing.JLabel();
        C8 = new javax.swing.JButton();
        V8 = new javax.swing.JSlider();
        S8 = new javax.swing.JToggleButton();
        I15 = new javax.swing.JLabel();
        C15 = new javax.swing.JButton();
        V15 = new javax.swing.JSlider();
        S15 = new javax.swing.JToggleButton();
        I16 = new javax.swing.JLabel();
        C16 = new javax.swing.JButton();
        V16 = new javax.swing.JSlider();
        S16 = new javax.swing.JToggleButton();
        I23 = new javax.swing.JLabel();
        C23 = new javax.swing.JButton();
        V23 = new javax.swing.JSlider();
        S23 = new javax.swing.JToggleButton();
        I24 = new javax.swing.JLabel();
        C24 = new javax.swing.JButton();
        V24 = new javax.swing.JSlider();
        S24 = new javax.swing.JToggleButton();
        I31 = new javax.swing.JLabel();
        C31 = new javax.swing.JButton();
        V31 = new javax.swing.JSlider();
        S31 = new javax.swing.JToggleButton();
        I32 = new javax.swing.JLabel();
        C32 = new javax.swing.JButton();
        V32 = new javax.swing.JSlider();
        S32 = new javax.swing.JToggleButton();
        I33 = new javax.swing.JLabel();
        C33 = new javax.swing.JButton();
        V33 = new javax.swing.JSlider();
        S33 = new javax.swing.JToggleButton();
        V34 = new javax.swing.JSlider();
        C34 = new javax.swing.JButton();
        I34 = new javax.swing.JLabel();
        S34 = new javax.swing.JToggleButton();
        V35 = new javax.swing.JSlider();
        C35 = new javax.swing.JButton();
        I35 = new javax.swing.JLabel();
        S35 = new javax.swing.JToggleButton();
        V36 = new javax.swing.JSlider();
        C36 = new javax.swing.JButton();
        I36 = new javax.swing.JLabel();
        S36 = new javax.swing.JToggleButton();
        V37 = new javax.swing.JSlider();
        C37 = new javax.swing.JButton();
        I37 = new javax.swing.JLabel();
        S37 = new javax.swing.JToggleButton();
        I38 = new javax.swing.JLabel();
        C38 = new javax.swing.JButton();
        V38 = new javax.swing.JSlider();
        S38 = new javax.swing.JToggleButton();
        I39 = new javax.swing.JLabel();
        C39 = new javax.swing.JButton();
        V39 = new javax.swing.JSlider();
        S39 = new javax.swing.JToggleButton();
        I40 = new javax.swing.JLabel();
        C40 = new javax.swing.JButton();
        V40 = new javax.swing.JSlider();
        S40 = new javax.swing.JToggleButton();
        I41 = new javax.swing.JLabel();
        C41 = new javax.swing.JButton();
        V41 = new javax.swing.JSlider();
        S41 = new javax.swing.JToggleButton();
        C42 = new javax.swing.JButton();
        S42 = new javax.swing.JToggleButton();
        I42 = new javax.swing.JLabel();
        V42 = new javax.swing.JSlider();
        C43 = new javax.swing.JButton();
        S43 = new javax.swing.JToggleButton();
        I43 = new javax.swing.JLabel();
        V43 = new javax.swing.JSlider();
        C44 = new javax.swing.JButton();
        S44 = new javax.swing.JToggleButton();
        I44 = new javax.swing.JLabel();
        V44 = new javax.swing.JSlider();
        I45 = new javax.swing.JLabel();
        C45 = new javax.swing.JButton();
        V45 = new javax.swing.JSlider();
        S45 = new javax.swing.JToggleButton();
        I46 = new javax.swing.JLabel();
        C46 = new javax.swing.JButton();
        V46 = new javax.swing.JSlider();
        S46 = new javax.swing.JToggleButton();
        I47 = new javax.swing.JLabel();
        C47 = new javax.swing.JButton();
        V47 = new javax.swing.JSlider();
        S47 = new javax.swing.JToggleButton();
        I48 = new javax.swing.JLabel();
        C48 = new javax.swing.JButton();
        V48 = new javax.swing.JSlider();
        S48 = new javax.swing.JToggleButton();
        I49 = new javax.swing.JLabel();
        C49 = new javax.swing.JButton();
        V49 = new javax.swing.JSlider();
        S49 = new javax.swing.JToggleButton();
        I50 = new javax.swing.JLabel();
        C50 = new javax.swing.JButton();
        V50 = new javax.swing.JSlider();
        S50 = new javax.swing.JToggleButton();
        I51 = new javax.swing.JLabel();
        C51 = new javax.swing.JButton();
        V51 = new javax.swing.JSlider();
        S51 = new javax.swing.JToggleButton();
        I52 = new javax.swing.JLabel();
        C52 = new javax.swing.JButton();
        V52 = new javax.swing.JSlider();
        S52 = new javax.swing.JToggleButton();
        I53 = new javax.swing.JLabel();
        C53 = new javax.swing.JButton();
        V53 = new javax.swing.JSlider();
        S53 = new javax.swing.JToggleButton();
        S54 = new javax.swing.JToggleButton();
        V54 = new javax.swing.JSlider();
        C54 = new javax.swing.JButton();
        I54 = new javax.swing.JLabel();
        S55 = new javax.swing.JToggleButton();
        V55 = new javax.swing.JSlider();
        C55 = new javax.swing.JButton();
        I55 = new javax.swing.JLabel();
        S56 = new javax.swing.JToggleButton();
        V56 = new javax.swing.JSlider();
        C56 = new javax.swing.JButton();
        I56 = new javax.swing.JLabel();
        I57 = new javax.swing.JLabel();
        C57 = new javax.swing.JButton();
        V57 = new javax.swing.JSlider();
        S57 = new javax.swing.JToggleButton();
        I58 = new javax.swing.JLabel();
        C58 = new javax.swing.JButton();
        V58 = new javax.swing.JSlider();
        S58 = new javax.swing.JToggleButton();
        I59 = new javax.swing.JLabel();
        C59 = new javax.swing.JButton();
        V59 = new javax.swing.JSlider();
        S59 = new javax.swing.JToggleButton();
        I60 = new javax.swing.JLabel();
        C60 = new javax.swing.JButton();
        V60 = new javax.swing.JSlider();
        S60 = new javax.swing.JToggleButton();
        I61 = new javax.swing.JLabel();
        C61 = new javax.swing.JButton();
        V61 = new javax.swing.JSlider();
        S61 = new javax.swing.JToggleButton();
        I62 = new javax.swing.JLabel();
        C62 = new javax.swing.JButton();
        V62 = new javax.swing.JSlider();
        S62 = new javax.swing.JToggleButton();
        I63 = new javax.swing.JLabel();
        C63 = new javax.swing.JButton();
        V63 = new javax.swing.JSlider();
        S63 = new javax.swing.JToggleButton();
        I64 = new javax.swing.JLabel();
        C64 = new javax.swing.JButton();
        V64 = new javax.swing.JSlider();
        S64 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        I65 = new javax.swing.JLabel();
        I66 = new javax.swing.JLabel();
        V65 = new javax.swing.JSlider();
        C65 = new javax.swing.JButton();
        C66 = new javax.swing.JButton();
        V66 = new javax.swing.JSlider();
        S65 = new javax.swing.JToggleButton();
        I67 = new javax.swing.JLabel();
        C67 = new javax.swing.JButton();
        V67 = new javax.swing.JSlider();
        S66 = new javax.swing.JToggleButton();
        I68 = new javax.swing.JLabel();
        C68 = new javax.swing.JButton();
        V68 = new javax.swing.JSlider();
        S67 = new javax.swing.JToggleButton();
        S68 = new javax.swing.JToggleButton();
        I69 = new javax.swing.JLabel();
        C69 = new javax.swing.JButton();
        V69 = new javax.swing.JSlider();
        S69 = new javax.swing.JToggleButton();
        I70 = new javax.swing.JLabel();
        C70 = new javax.swing.JButton();
        V70 = new javax.swing.JSlider();
        S70 = new javax.swing.JToggleButton();
        I71 = new javax.swing.JLabel();
        C71 = new javax.swing.JButton();
        V71 = new javax.swing.JSlider();
        S71 = new javax.swing.JToggleButton();
        I72 = new javax.swing.JLabel();
        C72 = new javax.swing.JButton();
        V72 = new javax.swing.JSlider();
        S72 = new javax.swing.JToggleButton();
        S73 = new javax.swing.JToggleButton();
        V73 = new javax.swing.JSlider();
        C73 = new javax.swing.JButton();
        I73 = new javax.swing.JLabel();
        S74 = new javax.swing.JToggleButton();
        V74 = new javax.swing.JSlider();
        C74 = new javax.swing.JButton();
        I74 = new javax.swing.JLabel();
        S75 = new javax.swing.JToggleButton();
        V75 = new javax.swing.JSlider();
        C75 = new javax.swing.JButton();
        I75 = new javax.swing.JLabel();
        S76 = new javax.swing.JToggleButton();
        V76 = new javax.swing.JSlider();
        C76 = new javax.swing.JButton();
        I76 = new javax.swing.JLabel();
        I77 = new javax.swing.JLabel();
        C77 = new javax.swing.JButton();
        V77 = new javax.swing.JSlider();
        S77 = new javax.swing.JToggleButton();
        I78 = new javax.swing.JLabel();
        C78 = new javax.swing.JButton();
        V78 = new javax.swing.JSlider();
        S78 = new javax.swing.JToggleButton();
        I79 = new javax.swing.JLabel();
        C79 = new javax.swing.JButton();
        V79 = new javax.swing.JSlider();
        S79 = new javax.swing.JToggleButton();
        I80 = new javax.swing.JLabel();
        C80 = new javax.swing.JButton();
        V80 = new javax.swing.JSlider();
        S80 = new javax.swing.JToggleButton();
        I81 = new javax.swing.JLabel();
        C81 = new javax.swing.JButton();
        V81 = new javax.swing.JSlider();
        S81 = new javax.swing.JToggleButton();
        I82 = new javax.swing.JLabel();
        C82 = new javax.swing.JButton();
        V82 = new javax.swing.JSlider();
        S82 = new javax.swing.JToggleButton();
        I83 = new javax.swing.JLabel();
        C83 = new javax.swing.JButton();
        V83 = new javax.swing.JSlider();
        S83 = new javax.swing.JToggleButton();
        I84 = new javax.swing.JLabel();
        C84 = new javax.swing.JButton();
        V84 = new javax.swing.JSlider();
        S84 = new javax.swing.JToggleButton();
        I85 = new javax.swing.JLabel();
        C85 = new javax.swing.JButton();
        V85 = new javax.swing.JSlider();
        S85 = new javax.swing.JToggleButton();
        I86 = new javax.swing.JLabel();
        C86 = new javax.swing.JButton();
        V86 = new javax.swing.JSlider();
        S86 = new javax.swing.JToggleButton();
        I87 = new javax.swing.JLabel();
        C87 = new javax.swing.JButton();
        V87 = new javax.swing.JSlider();
        S87 = new javax.swing.JToggleButton();
        I88 = new javax.swing.JLabel();
        C88 = new javax.swing.JButton();
        V88 = new javax.swing.JSlider();
        S88 = new javax.swing.JToggleButton();
        I89 = new javax.swing.JLabel();
        C89 = new javax.swing.JButton();
        V89 = new javax.swing.JSlider();
        S89 = new javax.swing.JToggleButton();
        I90 = new javax.swing.JLabel();
        C90 = new javax.swing.JButton();
        V90 = new javax.swing.JSlider();
        S90 = new javax.swing.JToggleButton();
        I91 = new javax.swing.JLabel();
        C91 = new javax.swing.JButton();
        V91 = new javax.swing.JSlider();
        S91 = new javax.swing.JToggleButton();
        I92 = new javax.swing.JLabel();
        C92 = new javax.swing.JButton();
        V92 = new javax.swing.JSlider();
        S92 = new javax.swing.JToggleButton();
        I93 = new javax.swing.JLabel();
        C93 = new javax.swing.JButton();
        V93 = new javax.swing.JSlider();
        S93 = new javax.swing.JToggleButton();
        I94 = new javax.swing.JLabel();
        C94 = new javax.swing.JButton();
        V94 = new javax.swing.JSlider();
        S94 = new javax.swing.JToggleButton();
        I95 = new javax.swing.JLabel();
        C95 = new javax.swing.JButton();
        V95 = new javax.swing.JSlider();
        S95 = new javax.swing.JToggleButton();
        I96 = new javax.swing.JLabel();
        C96 = new javax.swing.JButton();
        V96 = new javax.swing.JSlider();
        S96 = new javax.swing.JToggleButton();
        I97 = new javax.swing.JLabel();
        C97 = new javax.swing.JButton();
        V97 = new javax.swing.JSlider();
        S97 = new javax.swing.JToggleButton();
        V98 = new javax.swing.JSlider();
        C98 = new javax.swing.JButton();
        I98 = new javax.swing.JLabel();
        S98 = new javax.swing.JToggleButton();
        V99 = new javax.swing.JSlider();
        C99 = new javax.swing.JButton();
        I99 = new javax.swing.JLabel();
        S99 = new javax.swing.JToggleButton();
        V100 = new javax.swing.JSlider();
        C100 = new javax.swing.JButton();
        I100 = new javax.swing.JLabel();
        S100 = new javax.swing.JToggleButton();
        V101 = new javax.swing.JSlider();
        C101 = new javax.swing.JButton();
        I101 = new javax.swing.JLabel();
        S101 = new javax.swing.JToggleButton();
        I102 = new javax.swing.JLabel();
        C102 = new javax.swing.JButton();
        V102 = new javax.swing.JSlider();
        S102 = new javax.swing.JToggleButton();
        I103 = new javax.swing.JLabel();
        C103 = new javax.swing.JButton();
        V103 = new javax.swing.JSlider();
        S103 = new javax.swing.JToggleButton();
        I104 = new javax.swing.JLabel();
        C104 = new javax.swing.JButton();
        V104 = new javax.swing.JSlider();
        S104 = new javax.swing.JToggleButton();
        I105 = new javax.swing.JLabel();
        C105 = new javax.swing.JButton();
        V105 = new javax.swing.JSlider();
        S105 = new javax.swing.JToggleButton();
        C106 = new javax.swing.JButton();
        S106 = new javax.swing.JToggleButton();
        I106 = new javax.swing.JLabel();
        V106 = new javax.swing.JSlider();
        C107 = new javax.swing.JButton();
        S107 = new javax.swing.JToggleButton();
        I107 = new javax.swing.JLabel();
        V107 = new javax.swing.JSlider();
        C108 = new javax.swing.JButton();
        S108 = new javax.swing.JToggleButton();
        I108 = new javax.swing.JLabel();
        V108 = new javax.swing.JSlider();
        I109 = new javax.swing.JLabel();
        C109 = new javax.swing.JButton();
        V109 = new javax.swing.JSlider();
        S109 = new javax.swing.JToggleButton();
        I110 = new javax.swing.JLabel();
        C110 = new javax.swing.JButton();
        V110 = new javax.swing.JSlider();
        S110 = new javax.swing.JToggleButton();
        I111 = new javax.swing.JLabel();
        C111 = new javax.swing.JButton();
        V111 = new javax.swing.JSlider();
        S111 = new javax.swing.JToggleButton();
        I112 = new javax.swing.JLabel();
        C112 = new javax.swing.JButton();
        V112 = new javax.swing.JSlider();
        S112 = new javax.swing.JToggleButton();
        I113 = new javax.swing.JLabel();
        C113 = new javax.swing.JButton();
        V113 = new javax.swing.JSlider();
        S113 = new javax.swing.JToggleButton();
        I114 = new javax.swing.JLabel();
        C114 = new javax.swing.JButton();
        V114 = new javax.swing.JSlider();
        S114 = new javax.swing.JToggleButton();
        I115 = new javax.swing.JLabel();
        C115 = new javax.swing.JButton();
        V115 = new javax.swing.JSlider();
        S115 = new javax.swing.JToggleButton();
        I116 = new javax.swing.JLabel();
        C116 = new javax.swing.JButton();
        V116 = new javax.swing.JSlider();
        S116 = new javax.swing.JToggleButton();
        I117 = new javax.swing.JLabel();
        C117 = new javax.swing.JButton();
        V117 = new javax.swing.JSlider();
        S117 = new javax.swing.JToggleButton();
        S118 = new javax.swing.JToggleButton();
        V118 = new javax.swing.JSlider();
        C118 = new javax.swing.JButton();
        I118 = new javax.swing.JLabel();
        S119 = new javax.swing.JToggleButton();
        V119 = new javax.swing.JSlider();
        C119 = new javax.swing.JButton();
        I119 = new javax.swing.JLabel();
        S120 = new javax.swing.JToggleButton();
        V120 = new javax.swing.JSlider();
        C120 = new javax.swing.JButton();
        I120 = new javax.swing.JLabel();
        I121 = new javax.swing.JLabel();
        C121 = new javax.swing.JButton();
        V121 = new javax.swing.JSlider();
        S121 = new javax.swing.JToggleButton();
        I122 = new javax.swing.JLabel();
        C122 = new javax.swing.JButton();
        V122 = new javax.swing.JSlider();
        S122 = new javax.swing.JToggleButton();
        I123 = new javax.swing.JLabel();
        C123 = new javax.swing.JButton();
        V123 = new javax.swing.JSlider();
        S123 = new javax.swing.JToggleButton();
        I124 = new javax.swing.JLabel();
        C124 = new javax.swing.JButton();
        V124 = new javax.swing.JSlider();
        S124 = new javax.swing.JToggleButton();
        I125 = new javax.swing.JLabel();
        C125 = new javax.swing.JButton();
        V125 = new javax.swing.JSlider();
        S125 = new javax.swing.JToggleButton();
        I126 = new javax.swing.JLabel();
        C126 = new javax.swing.JButton();
        V126 = new javax.swing.JSlider();
        S126 = new javax.swing.JToggleButton();
        I127 = new javax.swing.JLabel();
        C127 = new javax.swing.JButton();
        V127 = new javax.swing.JSlider();
        S127 = new javax.swing.JToggleButton();
        I128 = new javax.swing.JLabel();
        C128 = new javax.swing.JButton();
        V128 = new javax.swing.JSlider();
        S128 = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(0, 0, 0));

        I1.setBackground(new java.awt.Color(255, 0, 204));
        I1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        V1.setBackground(new java.awt.Color(47, 55, 76));
        V1.setForeground(new java.awt.Color(255, 0, 102));
        V1.setMaximum(10);
        V1.setOrientation(javax.swing.JSlider.VERTICAL);

        C1.setBackground(new java.awt.Color(25, 31, 49));
        C1.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C1.setForeground(new java.awt.Color(255, 255, 255));
        C1.setText("NOMBRE LARGO");
        C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C1ActionPerformed(evt);
            }
        });

        C9.setBackground(new java.awt.Color(102, 102, 102));
        C9.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C9.setForeground(new java.awt.Color(255, 255, 255));
        C9.setText("jButton1");
        C9.setMaximumSize(new java.awt.Dimension(75, 25));
        C9.setMinimumSize(new java.awt.Dimension(75, 25));
        C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C9ActionPerformed(evt);
            }
        });

        V9.setBackground(new java.awt.Color(47, 55, 76));
        V9.setMaximum(10);
        V9.setOrientation(javax.swing.JSlider.VERTICAL);

        S1.setBackground(new java.awt.Color(69, 93, 220));
        S1.setForeground(new java.awt.Color(186, 195, 242));
        S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S1.setContentAreaFilled(false);
        S1.setOpaque(true);
        S1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S1ActionPerformed(evt);
            }
        });

        I17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C17.setBackground(new java.awt.Color(102, 102, 102));
        C17.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C17.setForeground(new java.awt.Color(255, 255, 255));
        C17.setText("jButton1");
        C17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C17ActionPerformed(evt);
            }
        });

        V17.setBackground(new java.awt.Color(47, 55, 76));
        V17.setMaximum(10);
        V17.setOrientation(javax.swing.JSlider.VERTICAL);

        S17.setBackground(new java.awt.Color(69, 93, 220));
        S17.setForeground(new java.awt.Color(186, 195, 242));
        S17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S17.setContentAreaFilled(false);
        S17.setOpaque(true);
        S17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S17ActionPerformed(evt);
            }
        });

        I25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C25.setBackground(new java.awt.Color(102, 102, 102));
        C25.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C25.setForeground(new java.awt.Color(255, 255, 255));
        C25.setText("jButton1");
        C25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C25ActionPerformed(evt);
            }
        });

        V25.setBackground(new java.awt.Color(47, 55, 76));
        V25.setMaximum(10);
        V25.setOrientation(javax.swing.JSlider.VERTICAL);

        S25.setBackground(new java.awt.Color(69, 93, 220));
        S25.setForeground(new java.awt.Color(186, 195, 242));
        S25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S25.setSelected(true);
        S25.setContentAreaFilled(false);
        S25.setOpaque(true);
        S25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S25ActionPerformed(evt);
            }
        });

        S9.setBackground(new java.awt.Color(69, 93, 220));
        S9.setForeground(new java.awt.Color(186, 195, 242));
        S9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S9.setContentAreaFilled(false);
        S9.setOpaque(true);
        S9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S9ActionPerformed(evt);
            }
        });

        I3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C3.setBackground(new java.awt.Color(102, 102, 102));
        C3.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C3.setForeground(new java.awt.Color(255, 255, 255));
        C3.setText("jButton1");
        C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3ActionPerformed(evt);
            }
        });

        V3.setBackground(new java.awt.Color(47, 55, 76));
        V3.setMaximum(10);
        V3.setOrientation(javax.swing.JSlider.VERTICAL);

        S3.setBackground(new java.awt.Color(69, 93, 220));
        S3.setForeground(new java.awt.Color(186, 195, 242));
        S3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S3.setContentAreaFilled(false);
        S3.setOpaque(true);
        S3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S3ActionPerformed(evt);
            }
        });

        I11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C11.setBackground(new java.awt.Color(102, 102, 102));
        C11.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C11.setForeground(new java.awt.Color(255, 255, 255));
        C11.setText("jButton1");
        C11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C11ActionPerformed(evt);
            }
        });

        V11.setBackground(new java.awt.Color(47, 55, 76));
        V11.setMaximum(10);
        V11.setOrientation(javax.swing.JSlider.VERTICAL);

        S11.setBackground(new java.awt.Color(69, 93, 220));
        S11.setForeground(new java.awt.Color(186, 195, 242));
        S11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S11.setContentAreaFilled(false);
        S11.setOpaque(true);
        S11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S11ActionPerformed(evt);
            }
        });

        I19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C19.setBackground(new java.awt.Color(102, 102, 102));
        C19.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C19.setForeground(new java.awt.Color(255, 255, 255));
        C19.setText("jButton1");
        C19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C19ActionPerformed(evt);
            }
        });

        V19.setBackground(new java.awt.Color(47, 55, 76));
        V19.setMaximum(10);
        V19.setOrientation(javax.swing.JSlider.VERTICAL);

        S19.setBackground(new java.awt.Color(69, 93, 220));
        S19.setForeground(new java.awt.Color(186, 195, 242));
        S19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S19.setContentAreaFilled(false);
        S19.setOpaque(true);
        S19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S19ActionPerformed(evt);
            }
        });

        I27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C27.setBackground(new java.awt.Color(102, 102, 102));
        C27.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C27.setForeground(new java.awt.Color(255, 255, 255));
        C27.setText("jButton1");
        C27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C27ActionPerformed(evt);
            }
        });

        V27.setBackground(new java.awt.Color(47, 55, 76));
        V27.setMaximum(10);
        V27.setOrientation(javax.swing.JSlider.VERTICAL);

        S27.setBackground(new java.awt.Color(69, 93, 220));
        S27.setForeground(new java.awt.Color(186, 195, 242));
        S27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S27.setContentAreaFilled(false);
        S27.setOpaque(true);
        S27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S27ActionPerformed(evt);
            }
        });

        S2.setBackground(new java.awt.Color(69, 93, 220));
        S2.setForeground(new java.awt.Color(186, 195, 242));
        S2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S2.setContentAreaFilled(false);
        S2.setOpaque(true);
        S2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S2ActionPerformed(evt);
            }
        });

        V2.setBackground(new java.awt.Color(47, 55, 76));
        V2.setMaximum(10);
        V2.setOrientation(javax.swing.JSlider.VERTICAL);

        C2.setBackground(new java.awt.Color(102, 102, 102));
        C2.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C2.setForeground(new java.awt.Color(255, 255, 255));
        C2.setText("jButton1");
        C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C2ActionPerformed(evt);
            }
        });

        I2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        S10.setBackground(new java.awt.Color(69, 93, 220));
        S10.setForeground(new java.awt.Color(186, 195, 242));
        S10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S10.setContentAreaFilled(false);
        S10.setOpaque(true);
        S10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S10ActionPerformed(evt);
            }
        });

        V10.setBackground(new java.awt.Color(47, 55, 76));
        V10.setMaximum(10);
        V10.setOrientation(javax.swing.JSlider.VERTICAL);
        V10.setPaintLabels(true);

        C10.setBackground(new java.awt.Color(102, 102, 102));
        C10.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C10.setForeground(new java.awt.Color(255, 255, 255));
        C10.setText("jButton1");
        C10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C10ActionPerformed(evt);
            }
        });

        I10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        S18.setBackground(new java.awt.Color(69, 93, 220));
        S18.setForeground(new java.awt.Color(186, 195, 242));
        S18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S18.setContentAreaFilled(false);
        S18.setOpaque(true);
        S18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S18ActionPerformed(evt);
            }
        });

        V18.setBackground(new java.awt.Color(47, 55, 76));
        V18.setMaximum(10);
        V18.setOrientation(javax.swing.JSlider.VERTICAL);

        C18.setBackground(new java.awt.Color(102, 102, 102));
        C18.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C18.setForeground(new java.awt.Color(255, 255, 255));
        C18.setText("jButton1");
        C18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C18ActionPerformed(evt);
            }
        });

        I18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        S26.setBackground(new java.awt.Color(69, 93, 220));
        S26.setForeground(new java.awt.Color(186, 195, 242));
        S26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S26.setContentAreaFilled(false);
        S26.setOpaque(true);
        S26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S26ActionPerformed(evt);
            }
        });

        V26.setBackground(new java.awt.Color(47, 55, 76));
        V26.setMaximum(10);
        V26.setOrientation(javax.swing.JSlider.VERTICAL);

        C26.setBackground(new java.awt.Color(102, 102, 120));
        C26.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C26.setForeground(new java.awt.Color(255, 255, 255));
        C26.setText("jButton1");
        C26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C26ActionPerformed(evt);
            }
        });

        I26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C4.setBackground(new java.awt.Color(102, 102, 102));
        C4.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C4.setForeground(new java.awt.Color(255, 255, 255));
        C4.setText("jButton1");
        C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4ActionPerformed(evt);
            }
        });

        V4.setBackground(new java.awt.Color(47, 55, 76));
        V4.setMaximum(10);
        V4.setOrientation(javax.swing.JSlider.VERTICAL);

        S4.setBackground(new java.awt.Color(69, 93, 220));
        S4.setForeground(new java.awt.Color(186, 195, 242));
        S4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S4.setContentAreaFilled(false);
        S4.setOpaque(true);
        S4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S4ActionPerformed(evt);
            }
        });

        I12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C12.setBackground(new java.awt.Color(102, 102, 102));
        C12.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C12.setForeground(new java.awt.Color(255, 255, 255));
        C12.setText("jButton1");
        C12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C12ActionPerformed(evt);
            }
        });

        V12.setBackground(new java.awt.Color(47, 55, 76));
        V12.setMaximum(10);
        V12.setOrientation(javax.swing.JSlider.VERTICAL);

        S12.setBackground(new java.awt.Color(69, 93, 220));
        S12.setForeground(new java.awt.Color(186, 195, 242));
        S12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S12.setContentAreaFilled(false);
        S12.setOpaque(true);
        S12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S12ActionPerformed(evt);
            }
        });

        I20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C20.setBackground(new java.awt.Color(102, 102, 102));
        C20.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C20.setForeground(new java.awt.Color(255, 255, 255));
        C20.setText("jButton1");
        C20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C20ActionPerformed(evt);
            }
        });

        V20.setBackground(new java.awt.Color(47, 55, 76));
        V20.setMaximum(10);
        V20.setOrientation(javax.swing.JSlider.VERTICAL);

        S20.setBackground(new java.awt.Color(69, 93, 220));
        S20.setForeground(new java.awt.Color(186, 195, 242));
        S20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S20.setContentAreaFilled(false);
        S20.setOpaque(true);
        S20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S20ActionPerformed(evt);
            }
        });

        I28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C28.setBackground(new java.awt.Color(102, 102, 102));
        C28.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C28.setForeground(new java.awt.Color(255, 255, 255));
        C28.setText("jButton1");
        C28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C28ActionPerformed(evt);
            }
        });

        V28.setBackground(new java.awt.Color(47, 55, 76));
        V28.setMaximum(10);
        V28.setOrientation(javax.swing.JSlider.VERTICAL);

        S28.setBackground(new java.awt.Color(69, 93, 220));
        S28.setForeground(new java.awt.Color(186, 195, 242));
        S28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S28.setContentAreaFilled(false);
        S28.setOpaque(true);
        S28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S28ActionPerformed(evt);
            }
        });

        I5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C5.setBackground(new java.awt.Color(102, 102, 102));
        C5.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C5.setForeground(new java.awt.Color(255, 255, 255));
        C5.setText("jButton1");
        C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C5ActionPerformed(evt);
            }
        });

        V5.setBackground(new java.awt.Color(47, 55, 76));
        V5.setMaximum(10);
        V5.setOrientation(javax.swing.JSlider.VERTICAL);

        S5.setBackground(new java.awt.Color(69, 93, 220));
        S5.setForeground(new java.awt.Color(186, 195, 242));
        S5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S5.setSelected(true);
        S5.setContentAreaFilled(false);
        S5.setOpaque(true);
        S5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S5ActionPerformed(evt);
            }
        });

        I7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C7.setBackground(new java.awt.Color(102, 102, 102));
        C7.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C7.setForeground(new java.awt.Color(255, 255, 255));
        C7.setText("jButton1");
        C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C7ActionPerformed(evt);
            }
        });

        V7.setBackground(new java.awt.Color(47, 55, 76));
        V7.setMaximum(10);
        V7.setOrientation(javax.swing.JSlider.VERTICAL);
        V7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V7StateChanged(evt);
            }
        });

        S7.setBackground(new java.awt.Color(69, 93, 220));
        S7.setForeground(new java.awt.Color(186, 195, 242));
        S7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S7.setContentAreaFilled(false);
        S7.setOpaque(true);
        S7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S7ActionPerformed(evt);
            }
        });

        I13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C13.setBackground(new java.awt.Color(102, 102, 102));
        C13.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C13.setForeground(new java.awt.Color(255, 255, 255));
        C13.setText("jButton1");
        C13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C13ActionPerformed(evt);
            }
        });

        V13.setBackground(new java.awt.Color(47, 55, 76));
        V13.setMaximum(10);
        V13.setOrientation(javax.swing.JSlider.VERTICAL);

        S13.setBackground(new java.awt.Color(69, 93, 220));
        S13.setForeground(new java.awt.Color(186, 195, 242));
        S13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S13.setContentAreaFilled(false);
        S13.setOpaque(true);
        S13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S13ActionPerformed(evt);
            }
        });

        I21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C21.setBackground(new java.awt.Color(102, 102, 102));
        C21.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C21.setForeground(new java.awt.Color(255, 255, 255));
        C21.setText("jButton1");
        C21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C21ActionPerformed(evt);
            }
        });

        V21.setBackground(new java.awt.Color(47, 55, 76));
        V21.setMaximum(10);
        V21.setOrientation(javax.swing.JSlider.VERTICAL);

        S21.setBackground(new java.awt.Color(69, 93, 220));
        S21.setForeground(new java.awt.Color(186, 195, 242));
        S21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S21.setContentAreaFilled(false);
        S21.setOpaque(true);
        S21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S21ActionPerformed(evt);
            }
        });

        I29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C6.setBackground(new java.awt.Color(102, 102, 102));
        C6.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C6.setForeground(new java.awt.Color(255, 255, 255));
        C6.setText("jButton1");
        C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6ActionPerformed(evt);
            }
        });

        V6.setBackground(new java.awt.Color(47, 55, 76));
        V6.setMaximum(10);
        V6.setOrientation(javax.swing.JSlider.VERTICAL);

        S6.setBackground(new java.awt.Color(69, 93, 220));
        S6.setForeground(new java.awt.Color(186, 195, 242));
        S6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S6.setContentAreaFilled(false);
        S6.setOpaque(true);
        S6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S6ActionPerformed(evt);
            }
        });

        I6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C29.setBackground(new java.awt.Color(102, 102, 102));
        C29.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C29.setForeground(new java.awt.Color(255, 255, 255));
        C29.setText("jButton1");
        C29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C29ActionPerformed(evt);
            }
        });

        V29.setBackground(new java.awt.Color(47, 55, 76));
        V29.setMaximum(10);
        V29.setOrientation(javax.swing.JSlider.VERTICAL);

        S29.setBackground(new java.awt.Color(69, 93, 220));
        S29.setForeground(new java.awt.Color(186, 195, 242));
        S29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S29.setContentAreaFilled(false);
        S29.setOpaque(true);
        S29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S29ActionPerformed(evt);
            }
        });

        I14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C14.setBackground(new java.awt.Color(102, 102, 102));
        C14.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C14.setForeground(new java.awt.Color(255, 255, 255));
        C14.setText("jButton1");
        C14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C14ActionPerformed(evt);
            }
        });

        V14.setBackground(new java.awt.Color(47, 55, 76));
        V14.setMaximum(10);
        V14.setOrientation(javax.swing.JSlider.VERTICAL);
        V14.setPaintLabels(true);

        S14.setBackground(new java.awt.Color(69, 93, 220));
        S14.setForeground(new java.awt.Color(186, 195, 242));
        S14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S14.setContentAreaFilled(false);
        S14.setOpaque(true);
        S14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S14ActionPerformed(evt);
            }
        });

        I22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C22.setBackground(new java.awt.Color(102, 102, 102));
        C22.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C22.setForeground(new java.awt.Color(255, 255, 255));
        C22.setText("jButton1");
        C22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C22ActionPerformed(evt);
            }
        });

        V22.setBackground(new java.awt.Color(47, 55, 76));
        V22.setMaximum(10);
        V22.setOrientation(javax.swing.JSlider.VERTICAL);

        S22.setBackground(new java.awt.Color(69, 93, 220));
        S22.setForeground(new java.awt.Color(186, 195, 242));
        S22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S22.setContentAreaFilled(false);
        S22.setOpaque(true);
        S22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S22ActionPerformed(evt);
            }
        });

        I30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C30.setBackground(new java.awt.Color(102, 102, 102));
        C30.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C30.setForeground(new java.awt.Color(255, 255, 255));
        C30.setText("jButton1");
        C30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C30ActionPerformed(evt);
            }
        });

        V30.setBackground(new java.awt.Color(47, 55, 76));
        V30.setMaximum(10);
        V30.setOrientation(javax.swing.JSlider.VERTICAL);

        S30.setBackground(new java.awt.Color(69, 93, 220));
        S30.setForeground(new java.awt.Color(186, 195, 242));
        S30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S30.setContentAreaFilled(false);
        S30.setOpaque(true);
        S30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S30ActionPerformed(evt);
            }
        });

        I8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C8.setBackground(new java.awt.Color(102, 102, 102));
        C8.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C8.setForeground(new java.awt.Color(255, 255, 255));
        C8.setText("jButton1");
        C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C8ActionPerformed(evt);
            }
        });

        V8.setBackground(new java.awt.Color(47, 55, 76));
        V8.setMaximum(10);
        V8.setOrientation(javax.swing.JSlider.VERTICAL);

        S8.setBackground(new java.awt.Color(69, 93, 220));
        S8.setForeground(new java.awt.Color(186, 195, 242));
        S8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S8.setContentAreaFilled(false);
        S8.setOpaque(true);
        S8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S8ActionPerformed(evt);
            }
        });

        I15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C15.setBackground(new java.awt.Color(102, 102, 102));
        C15.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C15.setForeground(new java.awt.Color(255, 255, 255));
        C15.setText("jButton1");
        C15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C15ActionPerformed(evt);
            }
        });

        V15.setBackground(new java.awt.Color(47, 55, 76));
        V15.setMaximum(10);
        V15.setOrientation(javax.swing.JSlider.VERTICAL);

        S15.setBackground(new java.awt.Color(69, 93, 220));
        S15.setForeground(new java.awt.Color(186, 195, 242));
        S15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S15.setContentAreaFilled(false);
        S15.setOpaque(true);
        S15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S15ActionPerformed(evt);
            }
        });

        I16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C16.setBackground(new java.awt.Color(102, 102, 102));
        C16.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C16.setForeground(new java.awt.Color(255, 255, 255));
        C16.setText("jButton1");
        C16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C16ActionPerformed(evt);
            }
        });

        V16.setBackground(new java.awt.Color(47, 55, 76));
        V16.setMaximum(10);
        V16.setOrientation(javax.swing.JSlider.VERTICAL);

        S16.setBackground(new java.awt.Color(69, 93, 220));
        S16.setForeground(new java.awt.Color(186, 195, 242));
        S16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S16.setContentAreaFilled(false);
        S16.setOpaque(true);
        S16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S16ActionPerformed(evt);
            }
        });

        I23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C23.setBackground(new java.awt.Color(102, 102, 102));
        C23.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C23.setForeground(new java.awt.Color(255, 255, 255));
        C23.setText("jButton1");
        C23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C23ActionPerformed(evt);
            }
        });

        V23.setBackground(new java.awt.Color(47, 55, 76));
        V23.setMaximum(10);
        V23.setOrientation(javax.swing.JSlider.VERTICAL);

        S23.setBackground(new java.awt.Color(69, 93, 220));
        S23.setForeground(new java.awt.Color(186, 195, 242));
        S23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S23.setContentAreaFilled(false);
        S23.setOpaque(true);
        S23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S23ActionPerformed(evt);
            }
        });

        I24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C24.setBackground(new java.awt.Color(102, 102, 102));
        C24.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C24.setForeground(new java.awt.Color(255, 255, 255));
        C24.setText("jButton1");
        C24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C24ActionPerformed(evt);
            }
        });

        V24.setBackground(new java.awt.Color(47, 55, 76));
        V24.setMaximum(10);
        V24.setOrientation(javax.swing.JSlider.VERTICAL);

        S24.setBackground(new java.awt.Color(69, 93, 220));
        S24.setForeground(new java.awt.Color(186, 195, 242));
        S24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S24.setContentAreaFilled(false);
        S24.setOpaque(true);
        S24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S24ActionPerformed(evt);
            }
        });

        I31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C31.setBackground(new java.awt.Color(102, 102, 102));
        C31.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C31.setForeground(new java.awt.Color(255, 255, 255));
        C31.setText("jButton1");
        C31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C31ActionPerformed(evt);
            }
        });

        V31.setBackground(new java.awt.Color(47, 55, 76));
        V31.setMaximum(10);
        V31.setOrientation(javax.swing.JSlider.VERTICAL);

        S31.setBackground(new java.awt.Color(69, 93, 220));
        S31.setForeground(new java.awt.Color(186, 195, 242));
        S31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S31.setContentAreaFilled(false);
        S31.setOpaque(true);
        S31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S31ActionPerformed(evt);
            }
        });

        I32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C32.setBackground(new java.awt.Color(102, 102, 102));
        C32.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C32.setForeground(new java.awt.Color(255, 255, 255));
        C32.setText("jButton1");
        C32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C32ActionPerformed(evt);
            }
        });

        V32.setBackground(new java.awt.Color(47, 55, 76));
        V32.setMaximum(10);
        V32.setOrientation(javax.swing.JSlider.VERTICAL);

        S32.setBackground(new java.awt.Color(69, 93, 220));
        S32.setForeground(new java.awt.Color(186, 195, 242));
        S32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S32.setContentAreaFilled(false);
        S32.setOpaque(true);
        S32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S32ActionPerformed(evt);
            }
        });

        I33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C33.setBackground(new java.awt.Color(102, 102, 102));
        C33.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C33.setForeground(new java.awt.Color(255, 255, 255));
        C33.setText("jButton1");
        C33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C33ActionPerformed(evt);
            }
        });

        V33.setBackground(new java.awt.Color(47, 55, 76));
        V33.setMaximum(10);
        V33.setOrientation(javax.swing.JSlider.VERTICAL);
        V33.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V33StateChanged(evt);
            }
        });

        S33.setBackground(new java.awt.Color(69, 93, 220));
        S33.setForeground(new java.awt.Color(186, 195, 242));
        S33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S33.setContentAreaFilled(false);
        S33.setOpaque(true);
        S33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S33ActionPerformed(evt);
            }
        });

        V34.setBackground(new java.awt.Color(47, 55, 76));
        V34.setMaximum(10);
        V34.setOrientation(javax.swing.JSlider.VERTICAL);
        V34.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V34StateChanged(evt);
            }
        });

        C34.setBackground(new java.awt.Color(102, 102, 102));
        C34.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C34.setForeground(new java.awt.Color(255, 255, 255));
        C34.setText("jButton1");
        C34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C34ActionPerformed(evt);
            }
        });

        I34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        S34.setBackground(new java.awt.Color(69, 93, 220));
        S34.setForeground(new java.awt.Color(186, 195, 242));
        S34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S34.setContentAreaFilled(false);
        S34.setOpaque(true);
        S34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S34ActionPerformed(evt);
            }
        });

        V35.setBackground(new java.awt.Color(47, 55, 76));
        V35.setMaximum(10);
        V35.setOrientation(javax.swing.JSlider.VERTICAL);
        V35.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V35StateChanged(evt);
            }
        });

        C35.setBackground(new java.awt.Color(102, 102, 102));
        C35.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C35.setForeground(new java.awt.Color(255, 255, 255));
        C35.setText("jButton1");
        C35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C35ActionPerformed(evt);
            }
        });

        I35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        S35.setBackground(new java.awt.Color(69, 93, 220));
        S35.setForeground(new java.awt.Color(186, 195, 242));
        S35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S35.setContentAreaFilled(false);
        S35.setOpaque(true);
        S35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S35ActionPerformed(evt);
            }
        });

        V36.setBackground(new java.awt.Color(47, 55, 76));
        V36.setMaximum(10);
        V36.setOrientation(javax.swing.JSlider.VERTICAL);
        V36.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V36StateChanged(evt);
            }
        });

        C36.setBackground(new java.awt.Color(102, 102, 102));
        C36.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C36.setForeground(new java.awt.Color(255, 255, 255));
        C36.setText("jButton1");
        C36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C36ActionPerformed(evt);
            }
        });

        I36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        S36.setBackground(new java.awt.Color(69, 93, 220));
        S36.setForeground(new java.awt.Color(186, 195, 242));
        S36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S36.setContentAreaFilled(false);
        S36.setOpaque(true);
        S36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S36ActionPerformed(evt);
            }
        });

        V37.setBackground(new java.awt.Color(47, 55, 76));
        V37.setMaximum(10);
        V37.setOrientation(javax.swing.JSlider.VERTICAL);
        V37.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V37StateChanged(evt);
            }
        });

        C37.setBackground(new java.awt.Color(102, 102, 102));
        C37.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C37.setForeground(new java.awt.Color(255, 255, 255));
        C37.setText("jButton1");
        C37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C37ActionPerformed(evt);
            }
        });

        I37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        S37.setBackground(new java.awt.Color(69, 93, 220));
        S37.setForeground(new java.awt.Color(186, 195, 242));
        S37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S37.setContentAreaFilled(false);
        S37.setOpaque(true);
        S37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S37ActionPerformed(evt);
            }
        });

        I38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C38.setBackground(new java.awt.Color(102, 102, 102));
        C38.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C38.setForeground(new java.awt.Color(255, 255, 255));
        C38.setText("jButton1");
        C38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C38ActionPerformed(evt);
            }
        });

        V38.setBackground(new java.awt.Color(47, 55, 76));
        V38.setMaximum(10);
        V38.setOrientation(javax.swing.JSlider.VERTICAL);
        V38.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V38StateChanged(evt);
            }
        });

        S38.setBackground(new java.awt.Color(69, 93, 220));
        S38.setForeground(new java.awt.Color(186, 195, 242));
        S38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S38.setContentAreaFilled(false);
        S38.setOpaque(true);
        S38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S38ActionPerformed(evt);
            }
        });

        I39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C39.setBackground(new java.awt.Color(102, 102, 102));
        C39.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C39.setForeground(new java.awt.Color(255, 255, 255));
        C39.setText("jButton1");
        C39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C39ActionPerformed(evt);
            }
        });

        V39.setBackground(new java.awt.Color(47, 55, 76));
        V39.setMaximum(10);
        V39.setOrientation(javax.swing.JSlider.VERTICAL);
        V39.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V39StateChanged(evt);
            }
        });

        S39.setBackground(new java.awt.Color(69, 93, 220));
        S39.setForeground(new java.awt.Color(186, 195, 242));
        S39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S39.setContentAreaFilled(false);
        S39.setOpaque(true);
        S39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S39ActionPerformed(evt);
            }
        });

        I40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C40.setBackground(new java.awt.Color(102, 102, 102));
        C40.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C40.setForeground(new java.awt.Color(255, 255, 255));
        C40.setText("jButton1");
        C40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C40ActionPerformed(evt);
            }
        });

        V40.setBackground(new java.awt.Color(47, 55, 76));
        V40.setMaximum(10);
        V40.setOrientation(javax.swing.JSlider.VERTICAL);
        V40.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V40StateChanged(evt);
            }
        });

        S40.setBackground(new java.awt.Color(69, 93, 220));
        S40.setForeground(new java.awt.Color(186, 195, 242));
        S40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S40.setContentAreaFilled(false);
        S40.setOpaque(true);
        S40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S40ActionPerformed(evt);
            }
        });

        I41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C41.setBackground(new java.awt.Color(102, 102, 102));
        C41.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C41.setForeground(new java.awt.Color(255, 255, 255));
        C41.setText("jButton1");
        C41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C41ActionPerformed(evt);
            }
        });

        V41.setBackground(new java.awt.Color(47, 55, 76));
        V41.setMaximum(10);
        V41.setOrientation(javax.swing.JSlider.VERTICAL);
        V41.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V41StateChanged(evt);
            }
        });

        S41.setBackground(new java.awt.Color(69, 93, 220));
        S41.setForeground(new java.awt.Color(186, 195, 242));
        S41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S41.setContentAreaFilled(false);
        S41.setOpaque(true);
        S41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S41ActionPerformed(evt);
            }
        });

        C42.setBackground(new java.awt.Color(102, 102, 102));
        C42.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C42.setForeground(new java.awt.Color(255, 255, 255));
        C42.setText("jButton1");
        C42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C42ActionPerformed(evt);
            }
        });

        S42.setBackground(new java.awt.Color(69, 93, 220));
        S42.setForeground(new java.awt.Color(186, 195, 242));
        S42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S42.setContentAreaFilled(false);
        S42.setOpaque(true);
        S42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S42ActionPerformed(evt);
            }
        });

        I42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        V42.setBackground(new java.awt.Color(47, 55, 76));
        V42.setMaximum(10);
        V42.setOrientation(javax.swing.JSlider.VERTICAL);
        V42.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V42StateChanged(evt);
            }
        });

        C43.setBackground(new java.awt.Color(102, 102, 102));
        C43.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C43.setForeground(new java.awt.Color(255, 255, 255));
        C43.setText("jButton1");
        C43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C43ActionPerformed(evt);
            }
        });

        S43.setBackground(new java.awt.Color(69, 93, 220));
        S43.setForeground(new java.awt.Color(186, 195, 242));
        S43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S43.setContentAreaFilled(false);
        S43.setOpaque(true);
        S43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S43ActionPerformed(evt);
            }
        });

        I43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        V43.setBackground(new java.awt.Color(47, 55, 76));
        V43.setMaximum(10);
        V43.setOrientation(javax.swing.JSlider.VERTICAL);
        V43.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V43StateChanged(evt);
            }
        });

        C44.setBackground(new java.awt.Color(102, 102, 102));
        C44.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C44.setForeground(new java.awt.Color(255, 255, 255));
        C44.setText("jButton1");
        C44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C44ActionPerformed(evt);
            }
        });

        S44.setBackground(new java.awt.Color(69, 93, 220));
        S44.setForeground(new java.awt.Color(186, 195, 242));
        S44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S44.setContentAreaFilled(false);
        S44.setOpaque(true);
        S44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S44ActionPerformed(evt);
            }
        });

        I44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        V44.setBackground(new java.awt.Color(47, 55, 76));
        V44.setMaximum(10);
        V44.setOrientation(javax.swing.JSlider.VERTICAL);
        V44.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V44StateChanged(evt);
            }
        });

        I45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C45.setBackground(new java.awt.Color(102, 102, 102));
        C45.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C45.setForeground(new java.awt.Color(255, 255, 255));
        C45.setText("jButton1");
        C45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C45ActionPerformed(evt);
            }
        });

        V45.setBackground(new java.awt.Color(47, 55, 76));
        V45.setMaximum(10);
        V45.setOrientation(javax.swing.JSlider.VERTICAL);
        V45.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V45StateChanged(evt);
            }
        });

        S45.setBackground(new java.awt.Color(69, 93, 220));
        S45.setForeground(new java.awt.Color(186, 195, 242));
        S45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S45.setContentAreaFilled(false);
        S45.setOpaque(true);
        S45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S45ActionPerformed(evt);
            }
        });

        I46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C46.setBackground(new java.awt.Color(102, 102, 102));
        C46.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C46.setForeground(new java.awt.Color(255, 255, 255));
        C46.setText("jButton1");
        C46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C46ActionPerformed(evt);
            }
        });

        V46.setBackground(new java.awt.Color(47, 55, 76));
        V46.setMaximum(10);
        V46.setOrientation(javax.swing.JSlider.VERTICAL);
        V46.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V46StateChanged(evt);
            }
        });

        S46.setBackground(new java.awt.Color(69, 93, 220));
        S46.setForeground(new java.awt.Color(186, 195, 242));
        S46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S46.setContentAreaFilled(false);
        S46.setOpaque(true);
        S46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S46ActionPerformed(evt);
            }
        });

        I47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C47.setBackground(new java.awt.Color(102, 102, 102));
        C47.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C47.setForeground(new java.awt.Color(255, 255, 255));
        C47.setText("jButton1");
        C47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C47ActionPerformed(evt);
            }
        });

        V47.setBackground(new java.awt.Color(47, 55, 76));
        V47.setMaximum(10);
        V47.setOrientation(javax.swing.JSlider.VERTICAL);
        V47.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V47StateChanged(evt);
            }
        });

        S47.setBackground(new java.awt.Color(69, 93, 220));
        S47.setForeground(new java.awt.Color(186, 195, 242));
        S47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S47.setContentAreaFilled(false);
        S47.setOpaque(true);
        S47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S47ActionPerformed(evt);
            }
        });

        I48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C48.setBackground(new java.awt.Color(102, 102, 102));
        C48.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C48.setForeground(new java.awt.Color(255, 255, 255));
        C48.setText("jButton1");
        C48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C48ActionPerformed(evt);
            }
        });

        V48.setBackground(new java.awt.Color(47, 55, 76));
        V48.setMaximum(10);
        V48.setOrientation(javax.swing.JSlider.VERTICAL);
        V48.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V48StateChanged(evt);
            }
        });

        S48.setBackground(new java.awt.Color(69, 93, 220));
        S48.setForeground(new java.awt.Color(186, 195, 242));
        S48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S48.setContentAreaFilled(false);
        S48.setOpaque(true);
        S48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S48ActionPerformed(evt);
            }
        });

        I49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C49.setBackground(new java.awt.Color(102, 102, 102));
        C49.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C49.setForeground(new java.awt.Color(255, 255, 255));
        C49.setText("jButton1");
        C49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C49ActionPerformed(evt);
            }
        });

        V49.setBackground(new java.awt.Color(47, 55, 76));
        V49.setMaximum(10);
        V49.setOrientation(javax.swing.JSlider.VERTICAL);
        V49.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V49StateChanged(evt);
            }
        });

        S49.setBackground(new java.awt.Color(69, 93, 220));
        S49.setForeground(new java.awt.Color(186, 195, 242));
        S49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S49.setContentAreaFilled(false);
        S49.setOpaque(true);
        S49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S49ActionPerformed(evt);
            }
        });

        I50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C50.setBackground(new java.awt.Color(102, 102, 102));
        C50.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C50.setForeground(new java.awt.Color(255, 255, 255));
        C50.setText("jButton1");
        C50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C50ActionPerformed(evt);
            }
        });

        V50.setBackground(new java.awt.Color(47, 55, 76));
        V50.setMaximum(10);
        V50.setOrientation(javax.swing.JSlider.VERTICAL);
        V50.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V50StateChanged(evt);
            }
        });

        S50.setBackground(new java.awt.Color(69, 93, 220));
        S50.setForeground(new java.awt.Color(186, 195, 242));
        S50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S50.setContentAreaFilled(false);
        S50.setOpaque(true);
        S50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S50ActionPerformed(evt);
            }
        });

        I51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C51.setBackground(new java.awt.Color(102, 102, 102));
        C51.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C51.setForeground(new java.awt.Color(255, 255, 255));
        C51.setText("jButton1");
        C51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C51ActionPerformed(evt);
            }
        });

        V51.setBackground(new java.awt.Color(47, 55, 76));
        V51.setMaximum(10);
        V51.setOrientation(javax.swing.JSlider.VERTICAL);
        V51.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V51StateChanged(evt);
            }
        });

        S51.setBackground(new java.awt.Color(69, 93, 220));
        S51.setForeground(new java.awt.Color(186, 195, 242));
        S51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S51.setContentAreaFilled(false);
        S51.setOpaque(true);
        S51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S51ActionPerformed(evt);
            }
        });

        I52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C52.setBackground(new java.awt.Color(102, 102, 102));
        C52.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C52.setForeground(new java.awt.Color(255, 255, 255));
        C52.setText("jButton1");
        C52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C52ActionPerformed(evt);
            }
        });

        V52.setBackground(new java.awt.Color(47, 55, 76));
        V52.setMaximum(10);
        V52.setOrientation(javax.swing.JSlider.VERTICAL);
        V52.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V52StateChanged(evt);
            }
        });

        S52.setBackground(new java.awt.Color(69, 93, 220));
        S52.setForeground(new java.awt.Color(186, 195, 242));
        S52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S52.setContentAreaFilled(false);
        S52.setOpaque(true);
        S52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S52ActionPerformed(evt);
            }
        });

        I53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C53.setBackground(new java.awt.Color(102, 102, 102));
        C53.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C53.setForeground(new java.awt.Color(255, 255, 255));
        C53.setText("jButton1");
        C53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C53ActionPerformed(evt);
            }
        });

        V53.setBackground(new java.awt.Color(47, 55, 76));
        V53.setMaximum(10);
        V53.setOrientation(javax.swing.JSlider.VERTICAL);
        V53.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V53StateChanged(evt);
            }
        });

        S53.setBackground(new java.awt.Color(69, 93, 220));
        S53.setForeground(new java.awt.Color(186, 195, 242));
        S53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S53.setContentAreaFilled(false);
        S53.setOpaque(true);
        S53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S53ActionPerformed(evt);
            }
        });

        S54.setBackground(new java.awt.Color(69, 93, 220));
        S54.setForeground(new java.awt.Color(186, 195, 242));
        S54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S54.setContentAreaFilled(false);
        S54.setOpaque(true);
        S54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S54ActionPerformed(evt);
            }
        });

        V54.setBackground(new java.awt.Color(47, 55, 76));
        V54.setMaximum(10);
        V54.setOrientation(javax.swing.JSlider.VERTICAL);
        V54.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V54StateChanged(evt);
            }
        });

        C54.setBackground(new java.awt.Color(102, 102, 102));
        C54.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C54.setForeground(new java.awt.Color(255, 255, 255));
        C54.setText("jButton1");
        C54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C54ActionPerformed(evt);
            }
        });

        I54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        S55.setBackground(new java.awt.Color(69, 93, 220));
        S55.setForeground(new java.awt.Color(186, 195, 242));
        S55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S55.setContentAreaFilled(false);
        S55.setOpaque(true);
        S55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S55ActionPerformed(evt);
            }
        });

        V55.setBackground(new java.awt.Color(47, 55, 76));
        V55.setMaximum(10);
        V55.setOrientation(javax.swing.JSlider.VERTICAL);
        V55.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V55StateChanged(evt);
            }
        });

        C55.setBackground(new java.awt.Color(102, 102, 102));
        C55.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C55.setForeground(new java.awt.Color(255, 255, 255));
        C55.setText("jButton1");
        C55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C55ActionPerformed(evt);
            }
        });

        I55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        S56.setBackground(new java.awt.Color(69, 93, 220));
        S56.setForeground(new java.awt.Color(186, 195, 242));
        S56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S56.setContentAreaFilled(false);
        S56.setOpaque(true);
        S56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S56ActionPerformed(evt);
            }
        });

        V56.setBackground(new java.awt.Color(47, 55, 76));
        V56.setMaximum(10);
        V56.setOrientation(javax.swing.JSlider.VERTICAL);
        V56.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V56StateChanged(evt);
            }
        });

        C56.setBackground(new java.awt.Color(102, 102, 102));
        C56.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C56.setForeground(new java.awt.Color(255, 255, 255));
        C56.setText("jButton1");
        C56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C56ActionPerformed(evt);
            }
        });

        I56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C57.setBackground(new java.awt.Color(102, 102, 102));
        C57.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C57.setForeground(new java.awt.Color(255, 255, 255));
        C57.setText("jButton1");
        C57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C57ActionPerformed(evt);
            }
        });

        V57.setBackground(new java.awt.Color(47, 55, 76));
        V57.setMaximum(10);
        V57.setOrientation(javax.swing.JSlider.VERTICAL);
        V57.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V57StateChanged(evt);
            }
        });

        S57.setBackground(new java.awt.Color(69, 93, 220));
        S57.setForeground(new java.awt.Color(186, 195, 242));
        S57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S57.setContentAreaFilled(false);
        S57.setOpaque(true);
        S57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S57ActionPerformed(evt);
            }
        });

        I58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C58.setBackground(new java.awt.Color(102, 102, 102));
        C58.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C58.setForeground(new java.awt.Color(255, 255, 255));
        C58.setText("jButton1");
        C58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C58ActionPerformed(evt);
            }
        });

        V58.setBackground(new java.awt.Color(47, 55, 76));
        V58.setMaximum(10);
        V58.setOrientation(javax.swing.JSlider.VERTICAL);
        V58.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V58StateChanged(evt);
            }
        });

        S58.setBackground(new java.awt.Color(69, 93, 220));
        S58.setForeground(new java.awt.Color(186, 195, 242));
        S58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S58.setContentAreaFilled(false);
        S58.setOpaque(true);
        S58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S58ActionPerformed(evt);
            }
        });

        I59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C59.setBackground(new java.awt.Color(102, 102, 102));
        C59.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C59.setForeground(new java.awt.Color(255, 255, 255));
        C59.setText("jButton1");
        C59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C59ActionPerformed(evt);
            }
        });

        V59.setBackground(new java.awt.Color(47, 55, 76));
        V59.setMaximum(10);
        V59.setOrientation(javax.swing.JSlider.VERTICAL);
        V59.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V59StateChanged(evt);
            }
        });

        S59.setBackground(new java.awt.Color(69, 93, 220));
        S59.setForeground(new java.awt.Color(186, 195, 242));
        S59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S59.setContentAreaFilled(false);
        S59.setOpaque(true);
        S59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S59ActionPerformed(evt);
            }
        });

        I60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C60.setBackground(new java.awt.Color(102, 102, 102));
        C60.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C60.setForeground(new java.awt.Color(255, 255, 255));
        C60.setText("jButton1");
        C60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C60ActionPerformed(evt);
            }
        });

        V60.setBackground(new java.awt.Color(47, 55, 76));
        V60.setMaximum(10);
        V60.setOrientation(javax.swing.JSlider.VERTICAL);
        V60.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V60StateChanged(evt);
            }
        });

        S60.setBackground(new java.awt.Color(69, 93, 220));
        S60.setForeground(new java.awt.Color(186, 195, 242));
        S60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S60.setContentAreaFilled(false);
        S60.setOpaque(true);
        S60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S60ActionPerformed(evt);
            }
        });

        I61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C61.setBackground(new java.awt.Color(102, 102, 102));
        C61.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C61.setForeground(new java.awt.Color(255, 255, 255));
        C61.setText("jButton1");
        C61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C61ActionPerformed(evt);
            }
        });

        V61.setBackground(new java.awt.Color(47, 55, 76));
        V61.setMaximum(10);
        V61.setOrientation(javax.swing.JSlider.VERTICAL);
        V61.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V61StateChanged(evt);
            }
        });

        S61.setBackground(new java.awt.Color(69, 93, 220));
        S61.setForeground(new java.awt.Color(186, 195, 242));
        S61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S61.setContentAreaFilled(false);
        S61.setOpaque(true);
        S61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S61ActionPerformed(evt);
            }
        });

        I62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C62.setBackground(new java.awt.Color(102, 102, 102));
        C62.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C62.setForeground(new java.awt.Color(255, 255, 255));
        C62.setText("jButton1");
        C62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C62ActionPerformed(evt);
            }
        });

        V62.setBackground(new java.awt.Color(47, 55, 76));
        V62.setMaximum(10);
        V62.setOrientation(javax.swing.JSlider.VERTICAL);
        V62.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V62StateChanged(evt);
            }
        });

        S62.setBackground(new java.awt.Color(69, 93, 220));
        S62.setForeground(new java.awt.Color(186, 195, 242));
        S62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S62.setContentAreaFilled(false);
        S62.setOpaque(true);
        S62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S62ActionPerformed(evt);
            }
        });

        I63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C63.setBackground(new java.awt.Color(102, 102, 102));
        C63.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C63.setForeground(new java.awt.Color(255, 255, 255));
        C63.setText("jButton1");
        C63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C63ActionPerformed(evt);
            }
        });

        V63.setBackground(new java.awt.Color(47, 55, 76));
        V63.setMaximum(10);
        V63.setOrientation(javax.swing.JSlider.VERTICAL);
        V63.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V63StateChanged(evt);
            }
        });

        S63.setBackground(new java.awt.Color(69, 93, 220));
        S63.setForeground(new java.awt.Color(186, 195, 242));
        S63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S63.setContentAreaFilled(false);
        S63.setOpaque(true);
        S63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S63ActionPerformed(evt);
            }
        });

        I64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C64.setBackground(new java.awt.Color(102, 102, 102));
        C64.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C64.setForeground(new java.awt.Color(255, 255, 255));
        C64.setText("jButton1");
        C64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C64ActionPerformed(evt);
            }
        });

        V64.setBackground(new java.awt.Color(47, 55, 76));
        V64.setMaximum(10);
        V64.setOrientation(javax.swing.JSlider.VERTICAL);
        V64.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V64StateChanged(evt);
            }
        });

        S64.setBackground(new java.awt.Color(69, 93, 220));
        S64.setForeground(new java.awt.Color(186, 195, 242));
        S64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/microfono.png"))); // NOI18N
        S64.setContentAreaFilled(false);
        S64.setOpaque(true);
        S64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S64ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);

        I65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I65);
        I65.setBounds(36, 12, 115, 170);

        I66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I66);
        I66.setBounds(184, 12, 115, 170);

        V65.setBackground(new java.awt.Color(47, 55, 76));
        V65.setForeground(new java.awt.Color(255, 0, 102));
        V65.setMaximum(10);
        V65.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V65);
        V65.setBounds(12, 12, 22, 170);

        C65.setBackground(new java.awt.Color(25, 31, 49));
        C65.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C65.setForeground(new java.awt.Color(255, 255, 255));
        C65.setText("NOMBRE LARGO");
        C65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C65ActionPerformed(evt);
            }
        });
        jPanel1.add(C65);
        C65.setBounds(54, 183, 97, 42);

        C66.setBackground(new java.awt.Color(102, 102, 102));
        C66.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C66.setForeground(new java.awt.Color(255, 255, 255));
        C66.setText("jButton1");
        C66.setMaximumSize(new java.awt.Dimension(75, 25));
        C66.setMinimumSize(new java.awt.Dimension(75, 25));
        C66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C66ActionPerformed(evt);
            }
        });
        jPanel1.add(C66);
        C66.setBounds(202, 183, 97, 42);

        V66.setBackground(new java.awt.Color(47, 55, 76));
        V66.setMaximum(10);
        V66.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V66);
        V66.setBounds(160, 12, 22, 170);

        S65.setBackground(new java.awt.Color(69, 93, 220));
        S65.setForeground(new java.awt.Color(186, 195, 242));
        S65.setText("S");
        S65.setContentAreaFilled(false);
        S65.setOpaque(true);
        S65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S65ActionPerformed(evt);
            }
        });
        jPanel1.add(S65);
        S65.setBounds(12, 183, 42, 42);

        I67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I67);
        I67.setBounds(333, 12, 115, 170);

        C67.setBackground(new java.awt.Color(102, 102, 102));
        C67.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C67.setForeground(new java.awt.Color(255, 255, 255));
        C67.setText("jButton1");
        C67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C67ActionPerformed(evt);
            }
        });
        jPanel1.add(C67);
        C67.setBounds(351, 183, 97, 42);

        V67.setBackground(new java.awt.Color(47, 55, 76));
        V67.setMaximum(10);
        V67.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V67);
        V67.setBounds(309, 12, 22, 170);

        S66.setBackground(new java.awt.Color(69, 93, 220));
        S66.setForeground(new java.awt.Color(186, 195, 242));
        S66.setText("S");
        S66.setContentAreaFilled(false);
        S66.setOpaque(true);
        S66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S66ActionPerformed(evt);
            }
        });
        jPanel1.add(S66);
        S66.setBounds(309, 183, 42, 42);

        I68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I68);
        I68.setBounds(482, 12, 115, 170);

        C68.setBackground(new java.awt.Color(102, 102, 102));
        C68.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C68.setForeground(new java.awt.Color(255, 255, 255));
        C68.setText("jButton1");
        C68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C68ActionPerformed(evt);
            }
        });
        jPanel1.add(C68);
        C68.setBounds(500, 183, 97, 42);

        V68.setBackground(new java.awt.Color(47, 55, 76));
        V68.setMaximum(10);
        V68.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V68);
        V68.setBounds(458, 12, 22, 170);

        S67.setBackground(new java.awt.Color(69, 93, 220));
        S67.setForeground(new java.awt.Color(186, 195, 242));
        S67.setSelected(true);
        S67.setText("S");
        S67.setContentAreaFilled(false);
        S67.setOpaque(true);
        S67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S67ActionPerformed(evt);
            }
        });
        jPanel1.add(S67);
        S67.setBounds(458, 183, 42, 42);

        S68.setBackground(new java.awt.Color(69, 93, 220));
        S68.setForeground(new java.awt.Color(186, 195, 242));
        S68.setText("S");
        S68.setContentAreaFilled(false);
        S68.setOpaque(true);
        S68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S68ActionPerformed(evt);
            }
        });
        jPanel1.add(S68);
        S68.setBounds(160, 183, 42, 42);

        I69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I69);
        I69.setBounds(630, 12, 115, 170);

        C69.setBackground(new java.awt.Color(102, 102, 102));
        C69.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C69.setForeground(new java.awt.Color(255, 255, 255));
        C69.setText("jButton1");
        C69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C69ActionPerformed(evt);
            }
        });
        jPanel1.add(C69);
        C69.setBounds(649, 183, 97, 42);

        V69.setBackground(new java.awt.Color(47, 55, 76));
        V69.setMaximum(10);
        V69.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V69);
        V69.setBounds(607, 12, 22, 170);

        S69.setBackground(new java.awt.Color(69, 93, 220));
        S69.setForeground(new java.awt.Color(186, 195, 242));
        S69.setText("S");
        S69.setContentAreaFilled(false);
        S69.setOpaque(true);
        S69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S69ActionPerformed(evt);
            }
        });
        jPanel1.add(S69);
        S69.setBounds(607, 183, 42, 42);

        I70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I70);
        I70.setBounds(780, 12, 115, 170);

        C70.setBackground(new java.awt.Color(102, 102, 102));
        C70.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C70.setForeground(new java.awt.Color(255, 255, 255));
        C70.setText("jButton1");
        C70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C70ActionPerformed(evt);
            }
        });
        jPanel1.add(C70);
        C70.setBounds(798, 183, 97, 42);

        V70.setBackground(new java.awt.Color(47, 55, 76));
        V70.setMaximum(10);
        V70.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V70);
        V70.setBounds(756, 12, 22, 170);

        S70.setBackground(new java.awt.Color(69, 93, 220));
        S70.setForeground(new java.awt.Color(186, 195, 242));
        S70.setText("S");
        S70.setContentAreaFilled(false);
        S70.setOpaque(true);
        S70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S70ActionPerformed(evt);
            }
        });
        jPanel1.add(S70);
        S70.setBounds(756, 183, 42, 42);

        I71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I71);
        I71.setBounds(928, 12, 115, 170);

        C71.setBackground(new java.awt.Color(102, 102, 102));
        C71.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C71.setForeground(new java.awt.Color(255, 255, 255));
        C71.setText("jButton1");
        C71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C71ActionPerformed(evt);
            }
        });
        jPanel1.add(C71);
        C71.setBounds(947, 183, 97, 42);

        V71.setBackground(new java.awt.Color(47, 55, 76));
        V71.setMaximum(10);
        V71.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V71);
        V71.setBounds(905, 12, 22, 170);

        S71.setBackground(new java.awt.Color(69, 93, 220));
        S71.setForeground(new java.awt.Color(186, 195, 242));
        S71.setText("S");
        S71.setContentAreaFilled(false);
        S71.setOpaque(true);
        S71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S71ActionPerformed(evt);
            }
        });
        jPanel1.add(S71);
        S71.setBounds(905, 183, 42, 42);

        I72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I72);
        I72.setBounds(1076, 12, 115, 170);

        C72.setBackground(new java.awt.Color(102, 102, 102));
        C72.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        C72.setForeground(new java.awt.Color(255, 255, 255));
        C72.setText("jButton1");
        C72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C72ActionPerformed(evt);
            }
        });
        jPanel1.add(C72);
        C72.setBounds(1096, 183, 97, 42);

        V72.setBackground(new java.awt.Color(47, 55, 76));
        V72.setMaximum(10);
        V72.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V72);
        V72.setBounds(1054, 12, 22, 170);

        S72.setBackground(new java.awt.Color(69, 93, 220));
        S72.setForeground(new java.awt.Color(186, 195, 242));
        S72.setText("S");
        S72.setContentAreaFilled(false);
        S72.setOpaque(true);
        S72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S72ActionPerformed(evt);
            }
        });
        jPanel1.add(S72);
        S72.setBounds(1054, 183, 42, 42);

        S73.setBackground(new java.awt.Color(69, 93, 220));
        S73.setForeground(new java.awt.Color(186, 195, 242));
        S73.setText("S");
        S73.setContentAreaFilled(false);
        S73.setOpaque(true);
        S73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S73ActionPerformed(evt);
            }
        });
        jPanel1.add(S73);
        S73.setBounds(12, 445, 42, 42);

        V73.setBackground(new java.awt.Color(47, 55, 76));
        V73.setMaximum(10);
        V73.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V73);
        V73.setBounds(12, 275, 22, 170);

        C73.setBackground(new java.awt.Color(102, 102, 102));
        C73.setForeground(new java.awt.Color(255, 255, 255));
        C73.setText("jButton1");
        C73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C73ActionPerformed(evt);
            }
        });
        jPanel1.add(C73);
        C73.setBounds(54, 445, 97, 42);

        I73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I73);
        I73.setBounds(36, 275, 115, 170);

        S74.setBackground(new java.awt.Color(69, 93, 220));
        S74.setForeground(new java.awt.Color(186, 195, 242));
        S74.setText("S");
        S74.setContentAreaFilled(false);
        S74.setOpaque(true);
        S74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S74ActionPerformed(evt);
            }
        });
        jPanel1.add(S74);
        S74.setBounds(160, 445, 42, 42);

        V74.setBackground(new java.awt.Color(47, 55, 76));
        V74.setMaximum(10);
        V74.setOrientation(javax.swing.JSlider.VERTICAL);
        V74.setPaintLabels(true);
        jPanel1.add(V74);
        V74.setBounds(160, 275, 22, 170);

        C74.setBackground(new java.awt.Color(102, 102, 102));
        C74.setForeground(new java.awt.Color(255, 255, 255));
        C74.setText("jButton1");
        C74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C74ActionPerformed(evt);
            }
        });
        jPanel1.add(C74);
        C74.setBounds(202, 445, 97, 42);

        I74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I74);
        I74.setBounds(184, 275, 115, 170);

        S75.setBackground(new java.awt.Color(69, 93, 220));
        S75.setForeground(new java.awt.Color(186, 195, 242));
        S75.setText("S");
        S75.setContentAreaFilled(false);
        S75.setOpaque(true);
        S75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S75ActionPerformed(evt);
            }
        });
        jPanel1.add(S75);
        S75.setBounds(309, 445, 42, 42);

        V75.setBackground(new java.awt.Color(47, 55, 76));
        V75.setMaximum(10);
        V75.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V75);
        V75.setBounds(309, 275, 22, 170);

        C75.setBackground(new java.awt.Color(102, 102, 102));
        C75.setForeground(new java.awt.Color(255, 255, 255));
        C75.setText("jButton1");
        C75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C75ActionPerformed(evt);
            }
        });
        jPanel1.add(C75);
        C75.setBounds(351, 445, 97, 42);

        I75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I75);
        I75.setBounds(333, 275, 115, 170);

        S76.setBackground(new java.awt.Color(69, 93, 220));
        S76.setForeground(new java.awt.Color(186, 195, 242));
        S76.setText("S");
        S76.setContentAreaFilled(false);
        S76.setOpaque(true);
        S76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S76ActionPerformed(evt);
            }
        });
        jPanel1.add(S76);
        S76.setBounds(458, 445, 42, 42);

        V76.setBackground(new java.awt.Color(47, 55, 76));
        V76.setMaximum(10);
        V76.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V76);
        V76.setBounds(458, 275, 22, 170);

        C76.setBackground(new java.awt.Color(102, 102, 120));
        C76.setForeground(new java.awt.Color(255, 255, 255));
        C76.setText("jButton1");
        C76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C76ActionPerformed(evt);
            }
        });
        jPanel1.add(C76);
        C76.setBounds(500, 445, 97, 42);

        I76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I76);
        I76.setBounds(482, 275, 115, 170);

        I77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I77);
        I77.setBounds(630, 275, 115, 170);

        C77.setBackground(new java.awt.Color(102, 102, 102));
        C77.setForeground(new java.awt.Color(255, 255, 255));
        C77.setText("jButton1");
        C77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C77ActionPerformed(evt);
            }
        });
        jPanel1.add(C77);
        C77.setBounds(649, 445, 97, 42);

        V77.setBackground(new java.awt.Color(47, 55, 76));
        V77.setMaximum(10);
        V77.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V77);
        V77.setBounds(607, 275, 22, 170);

        S77.setBackground(new java.awt.Color(69, 93, 220));
        S77.setForeground(new java.awt.Color(186, 195, 242));
        S77.setText("S");
        S77.setContentAreaFilled(false);
        S77.setOpaque(true);
        S77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S77ActionPerformed(evt);
            }
        });
        jPanel1.add(S77);
        S77.setBounds(607, 445, 42, 42);

        I78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I78);
        I78.setBounds(780, 275, 115, 170);

        C78.setBackground(new java.awt.Color(102, 102, 102));
        C78.setForeground(new java.awt.Color(255, 255, 255));
        C78.setText("jButton1");
        C78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C78ActionPerformed(evt);
            }
        });
        jPanel1.add(C78);
        C78.setBounds(798, 445, 97, 42);

        V78.setBackground(new java.awt.Color(47, 55, 76));
        V78.setMaximum(10);
        V78.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V78);
        V78.setBounds(756, 275, 22, 170);

        S78.setBackground(new java.awt.Color(69, 93, 220));
        S78.setForeground(new java.awt.Color(186, 195, 242));
        S78.setText("S");
        S78.setContentAreaFilled(false);
        S78.setOpaque(true);
        S78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S78ActionPerformed(evt);
            }
        });
        jPanel1.add(S78);
        S78.setBounds(756, 445, 42, 42);

        I79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I79);
        I79.setBounds(928, 275, 115, 170);

        C79.setBackground(new java.awt.Color(102, 102, 102));
        C79.setForeground(new java.awt.Color(255, 255, 255));
        C79.setText("jButton1");
        C79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C79ActionPerformed(evt);
            }
        });
        jPanel1.add(C79);
        C79.setBounds(947, 445, 97, 42);

        V79.setBackground(new java.awt.Color(47, 55, 76));
        V79.setMaximum(10);
        V79.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V79);
        V79.setBounds(905, 275, 22, 170);

        S79.setBackground(new java.awt.Color(69, 93, 220));
        S79.setForeground(new java.awt.Color(186, 195, 242));
        S79.setText("S");
        S79.setContentAreaFilled(false);
        S79.setOpaque(true);
        S79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S79ActionPerformed(evt);
            }
        });
        jPanel1.add(S79);
        S79.setBounds(905, 445, 42, 42);

        I80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I80);
        I80.setBounds(1076, 275, 115, 170);

        C80.setBackground(new java.awt.Color(102, 102, 102));
        C80.setForeground(new java.awt.Color(255, 255, 255));
        C80.setText("jButton1");
        C80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C80ActionPerformed(evt);
            }
        });
        jPanel1.add(C80);
        C80.setBounds(1096, 445, 97, 42);

        V80.setBackground(new java.awt.Color(47, 55, 76));
        V80.setMaximum(10);
        V80.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V80);
        V80.setBounds(1054, 275, 22, 170);

        S80.setBackground(new java.awt.Color(69, 93, 220));
        S80.setForeground(new java.awt.Color(186, 195, 242));
        S80.setText("S");
        S80.setContentAreaFilled(false);
        S80.setOpaque(true);
        S80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S80ActionPerformed(evt);
            }
        });
        jPanel1.add(S80);
        S80.setBounds(1054, 445, 42, 42);

        I81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I81);
        I81.setBounds(36, 550, 115, 170);

        C81.setBackground(new java.awt.Color(102, 102, 102));
        C81.setForeground(new java.awt.Color(255, 255, 255));
        C81.setText("jButton1");
        C81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C81ActionPerformed(evt);
            }
        });
        jPanel1.add(C81);
        C81.setBounds(54, 721, 97, 42);

        V81.setBackground(new java.awt.Color(47, 55, 76));
        V81.setMaximum(10);
        V81.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V81);
        V81.setBounds(12, 550, 22, 170);

        S81.setBackground(new java.awt.Color(69, 93, 220));
        S81.setForeground(new java.awt.Color(186, 195, 242));
        S81.setText("S");
        S81.setContentAreaFilled(false);
        S81.setOpaque(true);
        S81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S81ActionPerformed(evt);
            }
        });
        jPanel1.add(S81);
        S81.setBounds(12, 721, 42, 42);

        I82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I82);
        I82.setBounds(36, 900, 115, 170);

        C82.setBackground(new java.awt.Color(102, 102, 102));
        C82.setForeground(new java.awt.Color(255, 255, 255));
        C82.setText("jButton1");
        C82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C82ActionPerformed(evt);
            }
        });
        jPanel1.add(C82);
        C82.setBounds(54, 1071, 97, 42);

        V82.setBackground(new java.awt.Color(47, 55, 76));
        V82.setMaximum(10);
        V82.setOrientation(javax.swing.JSlider.VERTICAL);
        V82.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V82StateChanged(evt);
            }
        });
        jPanel1.add(V82);
        V82.setBounds(20, 900, 22, 170);

        S82.setBackground(new java.awt.Color(69, 93, 220));
        S82.setForeground(new java.awt.Color(186, 195, 242));
        S82.setText("SOLO");
        S82.setContentAreaFilled(false);
        S82.setOpaque(true);
        S82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S82ActionPerformed(evt);
            }
        });
        jPanel1.add(S82);
        S82.setBounds(20, 1071, 42, 42);

        I83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I83);
        I83.setBounds(184, 550, 115, 170);

        C83.setBackground(new java.awt.Color(102, 102, 102));
        C83.setForeground(new java.awt.Color(255, 255, 255));
        C83.setText("jButton1");
        C83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C83ActionPerformed(evt);
            }
        });
        jPanel1.add(C83);
        C83.setBounds(202, 721, 97, 42);

        V83.setBackground(new java.awt.Color(47, 55, 76));
        V83.setMaximum(10);
        V83.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V83);
        V83.setBounds(160, 550, 22, 170);

        S83.setBackground(new java.awt.Color(69, 93, 220));
        S83.setForeground(new java.awt.Color(186, 195, 242));
        S83.setText("S");
        S83.setContentAreaFilled(false);
        S83.setOpaque(true);
        S83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S83ActionPerformed(evt);
            }
        });
        jPanel1.add(S83);
        S83.setBounds(160, 721, 42, 42);

        I84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I84);
        I84.setBounds(333, 550, 115, 170);

        C84.setBackground(new java.awt.Color(102, 102, 102));
        C84.setForeground(new java.awt.Color(255, 255, 255));
        C84.setText("jButton1");
        C84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C84ActionPerformed(evt);
            }
        });
        jPanel1.add(C84);
        C84.setBounds(351, 721, 97, 42);

        V84.setBackground(new java.awt.Color(47, 55, 76));
        V84.setMaximum(10);
        V84.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V84);
        V84.setBounds(309, 550, 22, 170);

        S84.setBackground(new java.awt.Color(69, 93, 220));
        S84.setForeground(new java.awt.Color(186, 195, 242));
        S84.setText("S");
        S84.setContentAreaFilled(false);
        S84.setOpaque(true);
        S84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S84ActionPerformed(evt);
            }
        });
        jPanel1.add(S84);
        S84.setBounds(309, 721, 42, 42);

        I85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I85);
        I85.setBounds(482, 550, 115, 170);

        C85.setBackground(new java.awt.Color(102, 102, 102));
        C85.setForeground(new java.awt.Color(255, 255, 255));
        C85.setText("jButton1");
        C85.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C85ActionPerformed(evt);
            }
        });
        jPanel1.add(C85);
        C85.setBounds(500, 721, 97, 42);

        V85.setBackground(new java.awt.Color(47, 55, 76));
        V85.setMaximum(10);
        V85.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V85);
        V85.setBounds(458, 550, 22, 170);

        S85.setBackground(new java.awt.Color(69, 93, 220));
        S85.setForeground(new java.awt.Color(186, 195, 242));
        S85.setText("S");
        S85.setContentAreaFilled(false);
        S85.setOpaque(true);
        S85.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S85ActionPerformed(evt);
            }
        });
        jPanel1.add(S85);
        S85.setBounds(458, 721, 42, 42);

        I86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I86);
        I86.setBounds(630, 550, 115, 170);

        C86.setBackground(new java.awt.Color(102, 102, 102));
        C86.setForeground(new java.awt.Color(255, 255, 255));
        C86.setText("jButton1");
        C86.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C86ActionPerformed(evt);
            }
        });
        jPanel1.add(C86);
        C86.setBounds(649, 721, 97, 42);

        V86.setBackground(new java.awt.Color(47, 55, 76));
        V86.setMaximum(10);
        V86.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V86);
        V86.setBounds(607, 550, 22, 170);

        S86.setBackground(new java.awt.Color(69, 93, 220));
        S86.setForeground(new java.awt.Color(186, 195, 242));
        S86.setText("S");
        S86.setContentAreaFilled(false);
        S86.setOpaque(true);
        S86.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S86ActionPerformed(evt);
            }
        });
        jPanel1.add(S86);
        S86.setBounds(607, 721, 42, 42);

        I87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I87);
        I87.setBounds(780, 550, 115, 170);

        C87.setBackground(new java.awt.Color(102, 102, 102));
        C87.setForeground(new java.awt.Color(255, 255, 255));
        C87.setText("jButton1");
        C87.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C87ActionPerformed(evt);
            }
        });
        jPanel1.add(C87);
        C87.setBounds(798, 721, 97, 42);

        V87.setBackground(new java.awt.Color(47, 55, 76));
        V87.setMaximum(10);
        V87.setOrientation(javax.swing.JSlider.VERTICAL);
        V87.setPaintLabels(true);
        jPanel1.add(V87);
        V87.setBounds(756, 550, 22, 170);

        S87.setBackground(new java.awt.Color(69, 93, 220));
        S87.setForeground(new java.awt.Color(186, 195, 242));
        S87.setText("S");
        S87.setContentAreaFilled(false);
        S87.setOpaque(true);
        S87.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S87ActionPerformed(evt);
            }
        });
        jPanel1.add(S87);
        S87.setBounds(756, 721, 42, 42);

        I88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I88);
        I88.setBounds(1076, 550, 115, 170);

        C88.setBackground(new java.awt.Color(102, 102, 102));
        C88.setForeground(new java.awt.Color(255, 255, 255));
        C88.setText("jButton1");
        C88.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C88ActionPerformed(evt);
            }
        });
        jPanel1.add(C88);
        C88.setBounds(947, 721, 97, 42);

        V88.setBackground(new java.awt.Color(47, 55, 76));
        V88.setMaximum(10);
        V88.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V88);
        V88.setBounds(910, 550, 22, 170);

        S88.setBackground(new java.awt.Color(69, 93, 220));
        S88.setForeground(new java.awt.Color(186, 195, 242));
        S88.setText("S");
        S88.setContentAreaFilled(false);
        S88.setOpaque(true);
        S88.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S88ActionPerformed(evt);
            }
        });
        jPanel1.add(S88);
        S88.setBounds(910, 721, 42, 42);

        I89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I89);
        I89.setBounds(928, 550, 115, 170);

        C89.setBackground(new java.awt.Color(102, 102, 102));
        C89.setForeground(new java.awt.Color(255, 255, 255));
        C89.setText("jButton1");
        C89.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C89ActionPerformed(evt);
            }
        });
        jPanel1.add(C89);
        C89.setBounds(1096, 721, 97, 42);

        V89.setBackground(new java.awt.Color(47, 55, 76));
        V89.setMaximum(10);
        V89.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V89);
        V89.setBounds(1054, 550, 22, 170);

        S89.setBackground(new java.awt.Color(69, 93, 220));
        S89.setForeground(new java.awt.Color(186, 195, 242));
        S89.setText("S");
        S89.setContentAreaFilled(false);
        S89.setOpaque(true);
        S89.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S89ActionPerformed(evt);
            }
        });
        jPanel1.add(S89);
        S89.setBounds(1054, 721, 42, 42);

        I90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I90);
        I90.setBounds(184, 900, 115, 170);

        C90.setBackground(new java.awt.Color(102, 102, 102));
        C90.setForeground(new java.awt.Color(255, 255, 255));
        C90.setText("jButton1");
        C90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C90ActionPerformed(evt);
            }
        });
        jPanel1.add(C90);
        C90.setBounds(202, 1071, 97, 42);

        V90.setBackground(new java.awt.Color(47, 55, 76));
        V90.setMaximum(10);
        V90.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V90);
        V90.setBounds(160, 900, 22, 170);

        S90.setBackground(new java.awt.Color(69, 93, 220));
        S90.setForeground(new java.awt.Color(186, 195, 242));
        S90.setText("SOLO");
        S90.setContentAreaFilled(false);
        S90.setOpaque(true);
        S90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S90ActionPerformed(evt);
            }
        });
        jPanel1.add(S90);
        S90.setBounds(160, 1071, 42, 42);

        I91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I91);
        I91.setBounds(333, 900, 115, 170);

        C91.setBackground(new java.awt.Color(102, 102, 102));
        C91.setForeground(new java.awt.Color(255, 255, 255));
        C91.setText("jButton1");
        C91.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C91ActionPerformed(evt);
            }
        });
        jPanel1.add(C91);
        C91.setBounds(351, 1071, 97, 42);

        V91.setBackground(new java.awt.Color(47, 55, 76));
        V91.setMaximum(10);
        V91.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V91);
        V91.setBounds(309, 900, 22, 170);

        S91.setBackground(new java.awt.Color(69, 93, 220));
        S91.setForeground(new java.awt.Color(186, 195, 242));
        S91.setText("SOLO");
        S91.setContentAreaFilled(false);
        S91.setOpaque(true);
        S91.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S91ActionPerformed(evt);
            }
        });
        jPanel1.add(S91);
        S91.setBounds(310, 1071, 42, 42);

        I92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I92);
        I92.setBounds(482, 900, 115, 170);

        C92.setBackground(new java.awt.Color(102, 102, 102));
        C92.setForeground(new java.awt.Color(255, 255, 255));
        C92.setText("jButton1");
        C92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C92ActionPerformed(evt);
            }
        });
        jPanel1.add(C92);
        C92.setBounds(500, 1071, 97, 42);

        V92.setBackground(new java.awt.Color(47, 55, 76));
        V92.setMaximum(10);
        V92.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V92);
        V92.setBounds(458, 900, 22, 170);

        S92.setBackground(new java.awt.Color(69, 93, 220));
        S92.setForeground(new java.awt.Color(186, 195, 242));
        S92.setText("SOLO");
        S92.setContentAreaFilled(false);
        S92.setOpaque(true);
        S92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S92ActionPerformed(evt);
            }
        });
        jPanel1.add(S92);
        S92.setBounds(458, 1071, 42, 42);

        I93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I93);
        I93.setBounds(630, 900, 115, 170);

        C93.setBackground(new java.awt.Color(102, 102, 102));
        C93.setForeground(new java.awt.Color(255, 255, 255));
        C93.setText("jButton1");
        C93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C93ActionPerformed(evt);
            }
        });
        jPanel1.add(C93);
        C93.setBounds(649, 1071, 97, 42);

        V93.setBackground(new java.awt.Color(47, 55, 76));
        V93.setMaximum(10);
        V93.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V93);
        V93.setBounds(607, 900, 22, 170);

        S93.setBackground(new java.awt.Color(69, 93, 220));
        S93.setForeground(new java.awt.Color(186, 195, 242));
        S93.setText("SOLO");
        S93.setContentAreaFilled(false);
        S93.setOpaque(true);
        S93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S93ActionPerformed(evt);
            }
        });
        jPanel1.add(S93);
        S93.setBounds(607, 1071, 42, 42);

        I94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I94);
        I94.setBounds(780, 900, 115, 170);

        C94.setBackground(new java.awt.Color(102, 102, 102));
        C94.setForeground(new java.awt.Color(255, 255, 255));
        C94.setText("jButton1");
        C94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C94ActionPerformed(evt);
            }
        });
        jPanel1.add(C94);
        C94.setBounds(798, 1071, 97, 42);

        V94.setBackground(new java.awt.Color(47, 55, 76));
        V94.setMaximum(10);
        V94.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V94);
        V94.setBounds(756, 900, 22, 170);

        S94.setBackground(new java.awt.Color(69, 93, 220));
        S94.setForeground(new java.awt.Color(186, 195, 242));
        S94.setText("SOLO");
        S94.setContentAreaFilled(false);
        S94.setOpaque(true);
        S94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S94ActionPerformed(evt);
            }
        });
        jPanel1.add(S94);
        S94.setBounds(756, 1071, 42, 42);

        I95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I95);
        I95.setBounds(928, 900, 115, 170);

        C95.setBackground(new java.awt.Color(102, 102, 102));
        C95.setForeground(new java.awt.Color(255, 255, 255));
        C95.setText("jButton1");
        C95.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C95ActionPerformed(evt);
            }
        });
        jPanel1.add(C95);
        C95.setBounds(947, 1071, 97, 42);

        V95.setBackground(new java.awt.Color(47, 55, 76));
        V95.setMaximum(10);
        V95.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V95);
        V95.setBounds(910, 900, 22, 170);

        S95.setBackground(new java.awt.Color(69, 93, 220));
        S95.setForeground(new java.awt.Color(186, 195, 242));
        S95.setText("SOLO");
        S95.setContentAreaFilled(false);
        S95.setOpaque(true);
        S95.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S95ActionPerformed(evt);
            }
        });
        jPanel1.add(S95);
        S95.setBounds(910, 1071, 42, 42);

        I96.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I96);
        I96.setBounds(1100, 900, 115, 170);

        C96.setBackground(new java.awt.Color(102, 102, 102));
        C96.setForeground(new java.awt.Color(255, 255, 255));
        C96.setText("jButton1");
        C96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C96ActionPerformed(evt);
            }
        });
        jPanel1.add(C96);
        C96.setBounds(1110, 1071, 97, 42);

        V96.setBackground(new java.awt.Color(47, 55, 76));
        V96.setMaximum(10);
        V96.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanel1.add(V96);
        V96.setBounds(1070, 900, 22, 170);

        S96.setBackground(new java.awt.Color(69, 93, 220));
        S96.setForeground(new java.awt.Color(186, 195, 242));
        S96.setText("SOLO");
        S96.setContentAreaFilled(false);
        S96.setOpaque(true);
        S96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S96ActionPerformed(evt);
            }
        });
        jPanel1.add(S96);
        S96.setBounds(1070, 1071, 42, 42);

        I97.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I97);
        I97.setBounds(49, 1147, 100, 157);

        C97.setBackground(new java.awt.Color(102, 102, 102));
        C97.setForeground(new java.awt.Color(255, 255, 255));
        C97.setText("jButton1");
        C97.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C97ActionPerformed(evt);
            }
        });
        jPanel1.add(C97);
        C97.setBounds(49, 1317, 100, 38);

        V97.setBackground(new java.awt.Color(47, 55, 76));
        V97.setMaximum(10);
        V97.setOrientation(javax.swing.JSlider.VERTICAL);
        V97.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V97StateChanged(evt);
            }
        });
        jPanel1.add(V97);
        V97.setBounds(20, 1146, 30, 170);

        S97.setBackground(new java.awt.Color(69, 93, 220));
        S97.setForeground(new java.awt.Color(186, 195, 242));
        S97.setText("SOLO");
        S97.setContentAreaFilled(false);
        S97.setOpaque(true);
        S97.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S97ActionPerformed(evt);
            }
        });
        jPanel1.add(S97);
        S97.setBounds(20, 1320, 30, 30);

        V98.setBackground(new java.awt.Color(47, 55, 76));
        V98.setMaximum(10);
        V98.setOrientation(javax.swing.JSlider.VERTICAL);
        V98.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V98StateChanged(evt);
            }
        });
        jPanel1.add(V98);
        V98.setBounds(170, 1146, 20, 170);

        C98.setBackground(new java.awt.Color(102, 102, 102));
        C98.setForeground(new java.awt.Color(255, 255, 255));
        C98.setText("jButton1");
        C98.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C98ActionPerformed(evt);
            }
        });
        jPanel1.add(C98);
        C98.setBounds(195, 1317, 100, 38);

        I98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I98);
        I98.setBounds(195, 1147, 100, 157);

        S98.setBackground(new java.awt.Color(69, 93, 220));
        S98.setForeground(new java.awt.Color(186, 195, 242));
        S98.setText("SOLO");
        S98.setContentAreaFilled(false);
        S98.setOpaque(true);
        S98.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S98ActionPerformed(evt);
            }
        });
        jPanel1.add(S98);
        S98.setBounds(170, 1320, 30, 30);

        V99.setBackground(new java.awt.Color(47, 55, 76));
        V99.setMaximum(10);
        V99.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V99StateChanged(evt);
            }
        });
        jPanel1.add(V99);
        V99.setBounds(302, 1362, 137, 26);

        C99.setBackground(new java.awt.Color(102, 102, 102));
        C99.setForeground(new java.awt.Color(255, 255, 255));
        C99.setText("jButton1");
        C99.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C99ActionPerformed(evt);
            }
        });
        jPanel1.add(C99);
        C99.setBounds(302, 1317, 137, 38);

        I99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I99);
        I99.setBounds(302, 1147, 137, 157);

        S99.setBackground(new java.awt.Color(69, 93, 220));
        S99.setForeground(new java.awt.Color(186, 195, 242));
        S99.setText("SOLO");
        S99.setContentAreaFilled(false);
        S99.setOpaque(true);
        S99.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S99ActionPerformed(evt);
            }
        });
        jPanel1.add(S99);
        S99.setBounds(342, 1395, 63, 16);

        V100.setBackground(new java.awt.Color(47, 55, 76));
        V100.setMaximum(10);
        V100.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V100StateChanged(evt);
            }
        });
        jPanel1.add(V100);
        V100.setBounds(446, 1362, 137, 26);

        C100.setBackground(new java.awt.Color(102, 102, 102));
        C100.setForeground(new java.awt.Color(255, 255, 255));
        C100.setText("jButton1");
        C100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C100ActionPerformed(evt);
            }
        });
        jPanel1.add(C100);
        C100.setBounds(446, 1317, 137, 38);

        I100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I100);
        I100.setBounds(446, 1147, 137, 157);

        S100.setBackground(new java.awt.Color(69, 93, 220));
        S100.setForeground(new java.awt.Color(186, 195, 242));
        S100.setText("SOLO");
        S100.setContentAreaFilled(false);
        S100.setOpaque(true);
        S100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S100ActionPerformed(evt);
            }
        });
        jPanel1.add(S100);
        S100.setBounds(486, 1395, 63, 16);

        V101.setBackground(new java.awt.Color(47, 55, 76));
        V101.setMaximum(10);
        V101.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V101StateChanged(evt);
            }
        });
        jPanel1.add(V101);
        V101.setBounds(590, 1362, 137, 26);

        C101.setBackground(new java.awt.Color(102, 102, 102));
        C101.setForeground(new java.awt.Color(255, 255, 255));
        C101.setText("jButton1");
        C101.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C101ActionPerformed(evt);
            }
        });
        jPanel1.add(C101);
        C101.setBounds(590, 1317, 137, 38);

        I101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I101);
        I101.setBounds(590, 1147, 137, 157);

        S101.setBackground(new java.awt.Color(69, 93, 220));
        S101.setForeground(new java.awt.Color(186, 195, 242));
        S101.setText("SOLO");
        S101.setContentAreaFilled(false);
        S101.setOpaque(true);
        S101.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S101ActionPerformed(evt);
            }
        });
        jPanel1.add(S101);
        S101.setBounds(630, 1395, 63, 16);

        I102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I102);
        I102.setBounds(734, 1147, 137, 157);

        C102.setBackground(new java.awt.Color(102, 102, 102));
        C102.setForeground(new java.awt.Color(255, 255, 255));
        C102.setText("jButton1");
        C102.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C102ActionPerformed(evt);
            }
        });
        jPanel1.add(C102);
        C102.setBounds(734, 1317, 137, 38);

        V102.setBackground(new java.awt.Color(47, 55, 76));
        V102.setMaximum(10);
        V102.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V102StateChanged(evt);
            }
        });
        jPanel1.add(V102);
        V102.setBounds(734, 1362, 137, 26);

        S102.setBackground(new java.awt.Color(69, 93, 220));
        S102.setForeground(new java.awt.Color(186, 195, 242));
        S102.setText("SOLO");
        S102.setContentAreaFilled(false);
        S102.setOpaque(true);
        S102.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S102ActionPerformed(evt);
            }
        });
        jPanel1.add(S102);
        S102.setBounds(774, 1395, 63, 16);

        I103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I103);
        I103.setBounds(878, 1147, 137, 157);

        C103.setBackground(new java.awt.Color(102, 102, 102));
        C103.setForeground(new java.awt.Color(255, 255, 255));
        C103.setText("jButton1");
        C103.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C103ActionPerformed(evt);
            }
        });
        jPanel1.add(C103);
        C103.setBounds(878, 1317, 137, 38);

        V103.setBackground(new java.awt.Color(47, 55, 76));
        V103.setMaximum(10);
        V103.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V103StateChanged(evt);
            }
        });
        jPanel1.add(V103);
        V103.setBounds(878, 1362, 137, 26);

        S103.setBackground(new java.awt.Color(69, 93, 220));
        S103.setForeground(new java.awt.Color(186, 195, 242));
        S103.setText("SOLO");
        S103.setContentAreaFilled(false);
        S103.setOpaque(true);
        S103.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S103ActionPerformed(evt);
            }
        });
        jPanel1.add(S103);
        S103.setBounds(918, 1395, 63, 16);

        I104.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I104);
        I104.setBounds(1022, 1147, 137, 157);

        C104.setBackground(new java.awt.Color(102, 102, 102));
        C104.setForeground(new java.awt.Color(255, 255, 255));
        C104.setText("jButton1");
        C104.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C104ActionPerformed(evt);
            }
        });
        jPanel1.add(C104);
        C104.setBounds(1022, 1317, 137, 38);

        V104.setBackground(new java.awt.Color(47, 55, 76));
        V104.setMaximum(10);
        V104.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V104StateChanged(evt);
            }
        });
        jPanel1.add(V104);
        V104.setBounds(1022, 1362, 137, 26);

        S104.setBackground(new java.awt.Color(69, 93, 220));
        S104.setForeground(new java.awt.Color(186, 195, 242));
        S104.setText("SOLO");
        S104.setContentAreaFilled(false);
        S104.setOpaque(true);
        S104.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S104ActionPerformed(evt);
            }
        });
        jPanel1.add(S104);
        S104.setBounds(1062, 1395, 63, 16);

        I105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I105);
        I105.setBounds(12, 1418, 137, 157);

        C105.setBackground(new java.awt.Color(102, 102, 102));
        C105.setForeground(new java.awt.Color(255, 255, 255));
        C105.setText("jButton1");
        C105.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C105ActionPerformed(evt);
            }
        });
        jPanel1.add(C105);
        C105.setBounds(12, 1588, 137, 38);

        V105.setBackground(new java.awt.Color(47, 55, 76));
        V105.setMaximum(10);
        V105.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V105StateChanged(evt);
            }
        });
        jPanel1.add(V105);
        V105.setBounds(12, 1633, 137, 26);

        S105.setBackground(new java.awt.Color(69, 93, 220));
        S105.setForeground(new java.awt.Color(186, 195, 242));
        S105.setText("SOLO");
        S105.setContentAreaFilled(false);
        S105.setOpaque(true);
        S105.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S105ActionPerformed(evt);
            }
        });
        jPanel1.add(S105);
        S105.setBounds(52, 1666, 63, 16);

        C106.setBackground(new java.awt.Color(102, 102, 102));
        C106.setForeground(new java.awt.Color(255, 255, 255));
        C106.setText("jButton1");
        C106.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C106ActionPerformed(evt);
            }
        });
        jPanel1.add(C106);
        C106.setBounds(158, 1588, 137, 38);

        S106.setBackground(new java.awt.Color(69, 93, 220));
        S106.setForeground(new java.awt.Color(186, 195, 242));
        S106.setText("SOLO");
        S106.setContentAreaFilled(false);
        S106.setOpaque(true);
        S106.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S106ActionPerformed(evt);
            }
        });
        jPanel1.add(S106);
        S106.setBounds(198, 1666, 63, 16);

        I106.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I106);
        I106.setBounds(158, 1418, 137, 157);

        V106.setBackground(new java.awt.Color(47, 55, 76));
        V106.setMaximum(10);
        V106.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V106StateChanged(evt);
            }
        });
        jPanel1.add(V106);
        V106.setBounds(158, 1633, 137, 26);

        C107.setBackground(new java.awt.Color(102, 102, 102));
        C107.setForeground(new java.awt.Color(255, 255, 255));
        C107.setText("jButton1");
        C107.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C107ActionPerformed(evt);
            }
        });
        jPanel1.add(C107);
        C107.setBounds(302, 1588, 137, 38);

        S107.setBackground(new java.awt.Color(69, 93, 220));
        S107.setForeground(new java.awt.Color(186, 195, 242));
        S107.setText("SOLO");
        S107.setContentAreaFilled(false);
        S107.setOpaque(true);
        S107.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S107ActionPerformed(evt);
            }
        });
        jPanel1.add(S107);
        S107.setBounds(342, 1666, 63, 16);

        I107.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I107);
        I107.setBounds(302, 1418, 137, 157);

        V107.setBackground(new java.awt.Color(47, 55, 76));
        V107.setMaximum(10);
        V107.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V107StateChanged(evt);
            }
        });
        jPanel1.add(V107);
        V107.setBounds(302, 1633, 137, 26);

        C108.setBackground(new java.awt.Color(102, 102, 102));
        C108.setForeground(new java.awt.Color(255, 255, 255));
        C108.setText("jButton1");
        C108.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C108ActionPerformed(evt);
            }
        });
        jPanel1.add(C108);
        C108.setBounds(446, 1588, 137, 38);

        S108.setBackground(new java.awt.Color(69, 93, 220));
        S108.setForeground(new java.awt.Color(186, 195, 242));
        S108.setText("SOLO");
        S108.setContentAreaFilled(false);
        S108.setOpaque(true);
        S108.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S108ActionPerformed(evt);
            }
        });
        jPanel1.add(S108);
        S108.setBounds(486, 1666, 63, 16);

        I108.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I108);
        I108.setBounds(446, 1418, 137, 157);

        V108.setBackground(new java.awt.Color(47, 55, 76));
        V108.setMaximum(10);
        V108.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V108StateChanged(evt);
            }
        });
        jPanel1.add(V108);
        V108.setBounds(446, 1633, 137, 26);

        I109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I109);
        I109.setBounds(590, 1418, 137, 157);

        C109.setBackground(new java.awt.Color(102, 102, 102));
        C109.setForeground(new java.awt.Color(255, 255, 255));
        C109.setText("jButton1");
        C109.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C109ActionPerformed(evt);
            }
        });
        jPanel1.add(C109);
        C109.setBounds(590, 1588, 137, 38);

        V109.setBackground(new java.awt.Color(47, 55, 76));
        V109.setMaximum(10);
        V109.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V109StateChanged(evt);
            }
        });
        jPanel1.add(V109);
        V109.setBounds(590, 1633, 137, 26);

        S109.setBackground(new java.awt.Color(69, 93, 220));
        S109.setForeground(new java.awt.Color(186, 195, 242));
        S109.setText("SOLO");
        S109.setContentAreaFilled(false);
        S109.setOpaque(true);
        S109.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S109ActionPerformed(evt);
            }
        });
        jPanel1.add(S109);
        S109.setBounds(630, 1666, 63, 16);

        I110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I110);
        I110.setBounds(734, 1418, 137, 157);

        C110.setBackground(new java.awt.Color(102, 102, 102));
        C110.setForeground(new java.awt.Color(255, 255, 255));
        C110.setText("jButton1");
        C110.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C110ActionPerformed(evt);
            }
        });
        jPanel1.add(C110);
        C110.setBounds(734, 1588, 137, 38);

        V110.setBackground(new java.awt.Color(47, 55, 76));
        V110.setMaximum(10);
        V110.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V110StateChanged(evt);
            }
        });
        jPanel1.add(V110);
        V110.setBounds(734, 1633, 137, 26);

        S110.setBackground(new java.awt.Color(69, 93, 220));
        S110.setForeground(new java.awt.Color(186, 195, 242));
        S110.setText("SOLO");
        S110.setContentAreaFilled(false);
        S110.setOpaque(true);
        S110.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S110ActionPerformed(evt);
            }
        });
        jPanel1.add(S110);
        S110.setBounds(774, 1666, 63, 16);

        I111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I111);
        I111.setBounds(878, 1418, 137, 157);

        C111.setBackground(new java.awt.Color(102, 102, 102));
        C111.setForeground(new java.awt.Color(255, 255, 255));
        C111.setText("jButton1");
        C111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C111ActionPerformed(evt);
            }
        });
        jPanel1.add(C111);
        C111.setBounds(878, 1588, 137, 38);

        V111.setBackground(new java.awt.Color(47, 55, 76));
        V111.setMaximum(10);
        V111.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V111StateChanged(evt);
            }
        });
        jPanel1.add(V111);
        V111.setBounds(878, 1633, 137, 26);

        S111.setBackground(new java.awt.Color(69, 93, 220));
        S111.setForeground(new java.awt.Color(186, 195, 242));
        S111.setText("SOLO");
        S111.setContentAreaFilled(false);
        S111.setOpaque(true);
        S111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S111ActionPerformed(evt);
            }
        });
        jPanel1.add(S111);
        S111.setBounds(918, 1666, 63, 16);

        I112.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I112);
        I112.setBounds(1022, 1418, 137, 157);

        C112.setBackground(new java.awt.Color(102, 102, 102));
        C112.setForeground(new java.awt.Color(255, 255, 255));
        C112.setText("jButton1");
        C112.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C112ActionPerformed(evt);
            }
        });
        jPanel1.add(C112);
        C112.setBounds(1022, 1588, 137, 38);

        V112.setBackground(new java.awt.Color(47, 55, 76));
        V112.setMaximum(10);
        V112.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V112StateChanged(evt);
            }
        });
        jPanel1.add(V112);
        V112.setBounds(1022, 1633, 137, 26);

        S112.setBackground(new java.awt.Color(69, 93, 220));
        S112.setForeground(new java.awt.Color(186, 195, 242));
        S112.setText("SOLO");
        S112.setContentAreaFilled(false);
        S112.setOpaque(true);
        S112.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S112ActionPerformed(evt);
            }
        });
        jPanel1.add(S112);
        S112.setBounds(1062, 1666, 63, 16);

        I113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I113);
        I113.setBounds(12, 1689, 137, 157);

        C113.setBackground(new java.awt.Color(102, 102, 102));
        C113.setForeground(new java.awt.Color(255, 255, 255));
        C113.setText("jButton1");
        C113.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C113ActionPerformed(evt);
            }
        });
        jPanel1.add(C113);
        C113.setBounds(12, 1859, 137, 38);

        V113.setBackground(new java.awt.Color(47, 55, 76));
        V113.setMaximum(10);
        V113.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V113StateChanged(evt);
            }
        });
        jPanel1.add(V113);
        V113.setBounds(12, 1904, 137, 26);

        S113.setBackground(new java.awt.Color(69, 93, 220));
        S113.setForeground(new java.awt.Color(186, 195, 242));
        S113.setText("SOLO");
        S113.setContentAreaFilled(false);
        S113.setOpaque(true);
        S113.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S113ActionPerformed(evt);
            }
        });
        jPanel1.add(S113);
        S113.setBounds(52, 1937, 63, 16);

        I114.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I114);
        I114.setBounds(158, 1689, 137, 157);

        C114.setBackground(new java.awt.Color(102, 102, 102));
        C114.setForeground(new java.awt.Color(255, 255, 255));
        C114.setText("jButton1");
        C114.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C114ActionPerformed(evt);
            }
        });
        jPanel1.add(C114);
        C114.setBounds(158, 1859, 137, 38);

        V114.setBackground(new java.awt.Color(47, 55, 76));
        V114.setMaximum(10);
        V114.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V114StateChanged(evt);
            }
        });
        jPanel1.add(V114);
        V114.setBounds(158, 1904, 137, 26);

        S114.setBackground(new java.awt.Color(69, 93, 220));
        S114.setForeground(new java.awt.Color(186, 195, 242));
        S114.setText("SOLO");
        S114.setContentAreaFilled(false);
        S114.setOpaque(true);
        S114.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S114ActionPerformed(evt);
            }
        });
        jPanel1.add(S114);
        S114.setBounds(198, 1937, 63, 16);

        I115.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I115);
        I115.setBounds(302, 1689, 137, 157);

        C115.setBackground(new java.awt.Color(102, 102, 102));
        C115.setForeground(new java.awt.Color(255, 255, 255));
        C115.setText("jButton1");
        C115.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C115ActionPerformed(evt);
            }
        });
        jPanel1.add(C115);
        C115.setBounds(302, 1859, 137, 38);

        V115.setBackground(new java.awt.Color(47, 55, 76));
        V115.setMaximum(10);
        V115.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V115StateChanged(evt);
            }
        });
        jPanel1.add(V115);
        V115.setBounds(302, 1904, 137, 26);

        S115.setBackground(new java.awt.Color(69, 93, 220));
        S115.setForeground(new java.awt.Color(186, 195, 242));
        S115.setText("SOLO");
        S115.setContentAreaFilled(false);
        S115.setOpaque(true);
        S115.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S115ActionPerformed(evt);
            }
        });
        jPanel1.add(S115);
        S115.setBounds(342, 1937, 63, 16);

        I116.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I116);
        I116.setBounds(446, 1689, 137, 157);

        C116.setBackground(new java.awt.Color(102, 102, 102));
        C116.setForeground(new java.awt.Color(255, 255, 255));
        C116.setText("jButton1");
        C116.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C116ActionPerformed(evt);
            }
        });
        jPanel1.add(C116);
        C116.setBounds(446, 1859, 137, 38);

        V116.setBackground(new java.awt.Color(47, 55, 76));
        V116.setMaximum(10);
        V116.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V116StateChanged(evt);
            }
        });
        jPanel1.add(V116);
        V116.setBounds(446, 1902, 137, 26);

        S116.setBackground(new java.awt.Color(69, 93, 220));
        S116.setForeground(new java.awt.Color(186, 195, 242));
        S116.setText("SOLO");
        S116.setContentAreaFilled(false);
        S116.setOpaque(true);
        S116.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S116ActionPerformed(evt);
            }
        });
        jPanel1.add(S116);
        S116.setBounds(486, 1935, 63, 16);

        I117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I117);
        I117.setBounds(590, 1689, 137, 157);

        C117.setBackground(new java.awt.Color(102, 102, 102));
        C117.setForeground(new java.awt.Color(255, 255, 255));
        C117.setText("jButton1");
        C117.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C117ActionPerformed(evt);
            }
        });
        jPanel1.add(C117);
        C117.setBounds(590, 1859, 137, 38);

        V117.setBackground(new java.awt.Color(47, 55, 76));
        V117.setMaximum(10);
        V117.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V117StateChanged(evt);
            }
        });
        jPanel1.add(V117);
        V117.setBounds(590, 1904, 137, 26);

        S117.setBackground(new java.awt.Color(69, 93, 220));
        S117.setForeground(new java.awt.Color(186, 195, 242));
        S117.setText("SOLO");
        S117.setContentAreaFilled(false);
        S117.setOpaque(true);
        S117.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S117ActionPerformed(evt);
            }
        });
        jPanel1.add(S117);
        S117.setBounds(630, 1937, 63, 16);

        S118.setBackground(new java.awt.Color(69, 93, 220));
        S118.setForeground(new java.awt.Color(186, 195, 242));
        S118.setText("SOLO");
        S118.setContentAreaFilled(false);
        S118.setOpaque(true);
        S118.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S118ActionPerformed(evt);
            }
        });
        jPanel1.add(S118);
        S118.setBounds(774, 1937, 63, 16);

        V118.setBackground(new java.awt.Color(47, 55, 76));
        V118.setMaximum(10);
        V118.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V118StateChanged(evt);
            }
        });
        jPanel1.add(V118);
        V118.setBounds(734, 1904, 137, 26);

        C118.setBackground(new java.awt.Color(102, 102, 102));
        C118.setForeground(new java.awt.Color(255, 255, 255));
        C118.setText("jButton1");
        C118.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C118ActionPerformed(evt);
            }
        });
        jPanel1.add(C118);
        C118.setBounds(734, 1859, 137, 38);

        I118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I118);
        I118.setBounds(734, 1689, 137, 157);

        S119.setBackground(new java.awt.Color(69, 93, 220));
        S119.setForeground(new java.awt.Color(186, 195, 242));
        S119.setText("SOLO");
        S119.setContentAreaFilled(false);
        S119.setOpaque(true);
        S119.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S119ActionPerformed(evt);
            }
        });
        jPanel1.add(S119);
        S119.setBounds(918, 1937, 63, 16);

        V119.setBackground(new java.awt.Color(47, 55, 76));
        V119.setMaximum(10);
        V119.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V119StateChanged(evt);
            }
        });
        jPanel1.add(V119);
        V119.setBounds(878, 1904, 137, 26);

        C119.setBackground(new java.awt.Color(102, 102, 102));
        C119.setForeground(new java.awt.Color(255, 255, 255));
        C119.setText("jButton1");
        C119.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C119ActionPerformed(evt);
            }
        });
        jPanel1.add(C119);
        C119.setBounds(878, 1859, 137, 38);

        I119.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I119);
        I119.setBounds(878, 1689, 137, 157);

        S120.setBackground(new java.awt.Color(69, 93, 220));
        S120.setForeground(new java.awt.Color(186, 195, 242));
        S120.setText("SOLO");
        S120.setContentAreaFilled(false);
        S120.setOpaque(true);
        S120.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S120ActionPerformed(evt);
            }
        });
        jPanel1.add(S120);
        S120.setBounds(1062, 1937, 63, 16);

        V120.setBackground(new java.awt.Color(47, 55, 76));
        V120.setMaximum(10);
        V120.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V120StateChanged(evt);
            }
        });
        jPanel1.add(V120);
        V120.setBounds(1022, 1904, 137, 26);

        C120.setBackground(new java.awt.Color(102, 102, 102));
        C120.setForeground(new java.awt.Color(255, 255, 255));
        C120.setText("jButton1");
        C120.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C120ActionPerformed(evt);
            }
        });
        jPanel1.add(C120);
        C120.setBounds(1022, 1859, 137, 38);

        I120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I120);
        I120.setBounds(1022, 1689, 137, 157);

        I121.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I121);
        I121.setBounds(12, 1960, 137, 157);

        C121.setBackground(new java.awt.Color(102, 102, 102));
        C121.setForeground(new java.awt.Color(255, 255, 255));
        C121.setText("jButton1");
        C121.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C121ActionPerformed(evt);
            }
        });
        jPanel1.add(C121);
        C121.setBounds(12, 2130, 137, 38);

        V121.setBackground(new java.awt.Color(47, 55, 76));
        V121.setMaximum(10);
        V121.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V121StateChanged(evt);
            }
        });
        jPanel1.add(V121);
        V121.setBounds(12, 2175, 137, 26);

        S121.setBackground(new java.awt.Color(69, 93, 220));
        S121.setForeground(new java.awt.Color(186, 195, 242));
        S121.setText("SOLO");
        S121.setContentAreaFilled(false);
        S121.setOpaque(true);
        S121.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S121ActionPerformed(evt);
            }
        });
        jPanel1.add(S121);
        S121.setBounds(52, 2208, 63, 16);

        I122.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I122);
        I122.setBounds(158, 1960, 137, 157);

        C122.setBackground(new java.awt.Color(102, 102, 102));
        C122.setForeground(new java.awt.Color(255, 255, 255));
        C122.setText("jButton1");
        C122.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C122ActionPerformed(evt);
            }
        });
        jPanel1.add(C122);
        C122.setBounds(158, 2130, 137, 38);

        V122.setBackground(new java.awt.Color(47, 55, 76));
        V122.setMaximum(10);
        V122.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V122StateChanged(evt);
            }
        });
        jPanel1.add(V122);
        V122.setBounds(158, 2175, 137, 26);

        S122.setBackground(new java.awt.Color(69, 93, 220));
        S122.setForeground(new java.awt.Color(186, 195, 242));
        S122.setText("SOLO");
        S122.setContentAreaFilled(false);
        S122.setOpaque(true);
        S122.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S122ActionPerformed(evt);
            }
        });
        jPanel1.add(S122);
        S122.setBounds(198, 2208, 63, 16);

        I123.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I123);
        I123.setBounds(302, 1960, 137, 157);

        C123.setBackground(new java.awt.Color(102, 102, 102));
        C123.setForeground(new java.awt.Color(255, 255, 255));
        C123.setText("jButton1");
        C123.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C123ActionPerformed(evt);
            }
        });
        jPanel1.add(C123);
        C123.setBounds(302, 2130, 137, 38);

        V123.setBackground(new java.awt.Color(47, 55, 76));
        V123.setMaximum(10);
        V123.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V123StateChanged(evt);
            }
        });
        jPanel1.add(V123);
        V123.setBounds(302, 2175, 137, 26);

        S123.setBackground(new java.awt.Color(69, 93, 220));
        S123.setForeground(new java.awt.Color(186, 195, 242));
        S123.setText("SOLO");
        S123.setContentAreaFilled(false);
        S123.setOpaque(true);
        S123.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S123ActionPerformed(evt);
            }
        });
        jPanel1.add(S123);
        S123.setBounds(342, 2208, 63, 16);

        I124.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I124);
        I124.setBounds(446, 1960, 137, 157);

        C124.setBackground(new java.awt.Color(102, 102, 102));
        C124.setForeground(new java.awt.Color(255, 255, 255));
        C124.setText("jButton1");
        C124.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C124ActionPerformed(evt);
            }
        });
        jPanel1.add(C124);
        C124.setBounds(446, 2130, 137, 38);

        V124.setBackground(new java.awt.Color(47, 55, 76));
        V124.setMaximum(10);
        V124.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V124StateChanged(evt);
            }
        });
        jPanel1.add(V124);
        V124.setBounds(446, 2175, 137, 26);

        S124.setBackground(new java.awt.Color(69, 93, 220));
        S124.setForeground(new java.awt.Color(186, 195, 242));
        S124.setText("SOLO");
        S124.setContentAreaFilled(false);
        S124.setOpaque(true);
        S124.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S124ActionPerformed(evt);
            }
        });
        jPanel1.add(S124);
        S124.setBounds(486, 2208, 63, 16);

        I125.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I125);
        I125.setBounds(590, 1960, 137, 157);

        C125.setBackground(new java.awt.Color(102, 102, 102));
        C125.setForeground(new java.awt.Color(255, 255, 255));
        C125.setText("jButton1");
        C125.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C125ActionPerformed(evt);
            }
        });
        jPanel1.add(C125);
        C125.setBounds(590, 2130, 137, 38);

        V125.setBackground(new java.awt.Color(47, 55, 76));
        V125.setMaximum(10);
        V125.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V125StateChanged(evt);
            }
        });
        jPanel1.add(V125);
        V125.setBounds(590, 2175, 137, 26);

        S125.setBackground(new java.awt.Color(69, 93, 220));
        S125.setForeground(new java.awt.Color(186, 195, 242));
        S125.setText("SOLO");
        S125.setContentAreaFilled(false);
        S125.setOpaque(true);
        S125.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S125ActionPerformed(evt);
            }
        });
        jPanel1.add(S125);
        S125.setBounds(630, 2208, 63, 16);

        I126.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I126);
        I126.setBounds(734, 1960, 137, 157);

        C126.setBackground(new java.awt.Color(102, 102, 102));
        C126.setForeground(new java.awt.Color(255, 255, 255));
        C126.setText("jButton1");
        C126.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C126ActionPerformed(evt);
            }
        });
        jPanel1.add(C126);
        C126.setBounds(734, 2130, 137, 38);

        V126.setBackground(new java.awt.Color(47, 55, 76));
        V126.setMaximum(10);
        V126.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V126StateChanged(evt);
            }
        });
        jPanel1.add(V126);
        V126.setBounds(734, 2175, 137, 26);

        S126.setBackground(new java.awt.Color(69, 93, 220));
        S126.setForeground(new java.awt.Color(186, 195, 242));
        S126.setText("SOLO");
        S126.setContentAreaFilled(false);
        S126.setOpaque(true);
        S126.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S126ActionPerformed(evt);
            }
        });
        jPanel1.add(S126);
        S126.setBounds(774, 2208, 63, 16);

        I127.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I127);
        I127.setBounds(878, 1960, 137, 157);

        C127.setBackground(new java.awt.Color(102, 102, 102));
        C127.setForeground(new java.awt.Color(255, 255, 255));
        C127.setText("jButton1");
        C127.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C127ActionPerformed(evt);
            }
        });
        jPanel1.add(C127);
        C127.setBounds(878, 2130, 137, 38);

        V127.setBackground(new java.awt.Color(47, 55, 76));
        V127.setMaximum(10);
        V127.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V127StateChanged(evt);
            }
        });
        jPanel1.add(V127);
        V127.setBounds(878, 2175, 137, 26);

        S127.setBackground(new java.awt.Color(69, 93, 220));
        S127.setForeground(new java.awt.Color(186, 195, 242));
        S127.setText("SOLO");
        S127.setContentAreaFilled(false);
        S127.setOpaque(true);
        S127.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S127ActionPerformed(evt);
            }
        });
        jPanel1.add(S127);
        S127.setBounds(918, 2208, 63, 16);

        I128.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(I128);
        I128.setBounds(1022, 1960, 137, 157);

        C128.setBackground(new java.awt.Color(102, 102, 102));
        C128.setForeground(new java.awt.Color(255, 255, 255));
        C128.setText("jButton1");
        C128.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C128ActionPerformed(evt);
            }
        });
        jPanel1.add(C128);
        C128.setBounds(1022, 2130, 137, 38);

        V128.setBackground(new java.awt.Color(47, 55, 76));
        V128.setMaximum(10);
        V128.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V128StateChanged(evt);
            }
        });
        jPanel1.add(V128);
        V128.setBounds(1022, 2175, 137, 26);

        S128.setBackground(new java.awt.Color(69, 93, 220));
        S128.setForeground(new java.awt.Color(186, 195, 242));
        S128.setText("SOLO");
        S128.setContentAreaFilled(false);
        S128.setOpaque(true);
        S128.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S128ActionPerformed(evt);
            }
        });
        jPanel1.add(S128);
        S128.setBounds(1062, 2208, 63, 16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(V1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(V9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I17, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I25, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I11, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I19, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(I27, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(S1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(S9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C17, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S25, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C25, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C19, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S27, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C27, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(V2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(V10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I18, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I26, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I20, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V28, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(I28, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(S2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(S10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C18, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S26, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C26, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C12, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C20, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S28, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C28, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(V5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(V13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I13, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I21, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I29, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V29, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I14, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I30, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V30, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(I22, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(S5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(S13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C21, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S29, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C29, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C14, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S22, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C22, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C30, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(V7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(V8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I15, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I16, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I23, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I24, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V31, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I31, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V32, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(I32, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(S7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(S8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C16, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C23, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C24, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S31, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C31, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S32, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C32, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(V33, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I33, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(V34, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I34, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V35, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I35, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V36, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I36, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V37, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I37, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V38, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I38, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V39, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I39, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V40, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(I40, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(S33, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C33, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(S34, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C34, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S35, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C35, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S36, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C36, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S37, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C37, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S38, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C38, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S39, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C39, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S40, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C40, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(V41, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I41, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(V42, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I42, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V43, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I43, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V44, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I44, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V45, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I45, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V46, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I46, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V47, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I47, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V48, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(I48, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(S41, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C41, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(S42, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C42, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S43, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C43, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S44, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C44, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S45, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C45, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S46, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C46, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S47, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C47, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S48, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C48, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(V49, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I49, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(V50, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I50, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V51, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I51, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V52, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I52, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V53, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I53, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V54, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I54, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V55, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I55, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V56, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(I56, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(S49, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C49, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(S50, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C50, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S51, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C51, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S52, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C52, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S53, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C53, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S54, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C54, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S55, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C55, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S56, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C56, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(V57, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(I57, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(V58, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I58, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V59, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I59, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V60, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I60, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V61, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I61, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V62, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(I62, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(V63, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(I63, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(V64, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(I64, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(S57, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C57, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(S58, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C58, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S59, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C59, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S60, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C60, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S61, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C61, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S62, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C62, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S63, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C63, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(S64, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(C64, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(V1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V17, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I17, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V25, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I25, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V11, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I11, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V19, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I19, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V27, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I27, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S25, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C25, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S27, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C27, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(V2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V10, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I10, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V18, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I18, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V26, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I26, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V20, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I20, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V28, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I28, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S26, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C26, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S28, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C28, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(V5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V13, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I13, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V21, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I21, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I29, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V29, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V14, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I14, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V22, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I30, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V30, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I22, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S29, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C29, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S22, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C22, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(V7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V8, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I8, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V15, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I15, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V16, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I16, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V23, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I23, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V24, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I24, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V31, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I31, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V32, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I32, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S31, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C31, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S32, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C32, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(V33, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I33, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V34, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I34, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V35, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I35, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V36, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I36, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V37, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I37, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V38, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I38, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V39, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I39, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V40, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I40, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S33, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C33, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S34, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C34, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S35, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C35, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S36, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C36, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S37, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C37, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S38, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C38, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S39, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C39, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S40, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C40, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(V41, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I41, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V42, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I42, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V43, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I43, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V44, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I44, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V45, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I45, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V46, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I46, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V47, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I47, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V48, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I48, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S41, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C41, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S42, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C42, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S43, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C43, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S44, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C44, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S45, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C45, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S46, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C46, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S47, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C47, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S48, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C48, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(V49, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I49, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V50, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I50, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V51, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I51, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V52, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I52, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V53, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I53, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V54, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I54, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V55, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I55, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V56, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I56, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S49, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C49, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S50, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C50, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S51, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C51, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S52, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C52, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S53, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C53, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S54, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C54, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S55, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C55, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S56, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C56, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(V57, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I57, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V58, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I58, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V59, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I59, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V60, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I60, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V61, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I61, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V62, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I62, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V63, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I63, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V64, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I64, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S57, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C57, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S58, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C58, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S59, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C59, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S60, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C60, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S61, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C61, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S62, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C62, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S63, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C63, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S64, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C64, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void C19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C19ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(19, C19, V19, 6);
      
    
    }//GEN-LAST:event_C19ActionPerformed

    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C1ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(1, C1, V1, 0); 
        /*
        var[1]=true;
      if(!var[2])
      {// TODO add your handling code here:     
    alternar[0]++;   
    if(alternar[0]==1){
                     
         try{
            Conf=X.Read("config.xml"); 
            audio[0]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(0),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),0,V1,C1,socket);
            audio[0].start();
            C1.setBackground(Color.GREEN);
            
         }
         catch(Exception e){
            System.out.println("Error de hilo ");
            C1.setBackground(Color.RED);
            S1.setEnabled(false);
            var[1]=false;
         }
         
                     
    }   

    if(alternar[0]==2){
         audio[0].detener();
         audio[0].stop();
         alternar[0]=0;
         C1.setBackground(Color.BLUE);
         var[1]=false;
         
         
    }
      }
        */
    }//GEN-LAST:event_C1ActionPerformed

    private void C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C9ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(9, C9, V9, 1);
        /*
        var[17]=true;
        if(!var[18])
        {
       alternar[8]++;   
    if(alternar[8]==1){
         Conf=X.Read("config.xml");                
         try{
             audio[8]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(8),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),1,V9,C9,socket);
             audio[8].start();
              C9.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C9.setBackground(Color.RED);
               var[17]=false;
         }     
    }   
    if(alternar[8]==2){
         audio[8].detener();
         audio[8].stop();
         alternar[8]=0;
         C9.setBackground(Color.BLUE);
         var[17]=false;
         
    }
        }
      */
    }//GEN-LAST:event_C9ActionPerformed

    private void C17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C17ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(17, C17, V17, 2);
        
    }//GEN-LAST:event_C17ActionPerformed
    private void C25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C25ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(25, C25, V25, 3);
            
    }//GEN-LAST:event_C25ActionPerformed

    private void C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C3ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(3, C3, V3, 4);
            
    }//GEN-LAST:event_C3ActionPerformed

    private void C11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C11ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(11, C11, V11, 5);
         
    }//GEN-LAST:event_C11ActionPerformed

    private void C27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C27ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(27, C27, V27, 7);
         
    }//GEN-LAST:event_C27ActionPerformed

    private void S1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S1ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        
        mainfunctionSOLO(1, C1, S1, V1, 0);
        /*
        var[2]=true;
        if(S1.isSelected()&&!var[1]){
            DisableVolumeExcept(V1,S1,2,0);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audioSolo[0]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(0),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),0,V1,C1,socket);
             audioSolo[0].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               var[2]=false;
         }     
        }
        if(S1.isSelected()&&var[1])
        {
            DisableVolumeExcept(V1,S1,2,0);
        }
        if(!S1.isSelected()){
            EnableVolume();
            var[2]=false;
            audioSolo[0].detener();
            audioSolo[0].stop();
        }
        */
    }//GEN-LAST:event_S1ActionPerformed

    private void S9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S9ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        mainfunctionSOLO(9, C9, S9, V9, 1);
        
        /*
        var[18]=true;
        if(S9.isSelected()&&!var[17]){
            DisableVolumeExcept(V9,S9,18,8);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[8]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(8),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),1,V9,C9,socket);
             audio[8].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               var[18]=false;
         }     
        }
        if(S9.isSelected()&&var[17])
        {
            DisableVolumeExcept(V9,S9,18,8);
        }
        if(!S9.isSelected()){
            EnableVolume();
            var[18]=false;
            audio[8].detener();
            audio[8].stop();
        }
        */
    }//GEN-LAST:event_S9ActionPerformed

    private void C33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C33ActionPerformed
        mainfunctionAudioChannel(33, C33, V33, 32);
    }//GEN-LAST:event_C33ActionPerformed

    private void C34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C34ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(34, C34, V34, 33);
    }//GEN-LAST:event_C34ActionPerformed

    private void C35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C35ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(35, C35, V35, 34);
        
    }//GEN-LAST:event_C35ActionPerformed

    private void C36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C36ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(36, C36, V36, 35);
    }//GEN-LAST:event_C36ActionPerformed

    private void C37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C37ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(37, C37, V37, 36);
    }//GEN-LAST:event_C37ActionPerformed

    private void C38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C38ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(38, C38, V38, 37);
    }//GEN-LAST:event_C38ActionPerformed

    private void C39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C39ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(39, C39, V39, 38);
    }//GEN-LAST:event_C39ActionPerformed

    private void C40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C40ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(40, C40, V40, 39);
    }//GEN-LAST:event_C40ActionPerformed

    private void C41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C41ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(41, C41, V41, 40);
    }//GEN-LAST:event_C41ActionPerformed

    private void C42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C42ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(42, C42, V42, 41);
    }//GEN-LAST:event_C42ActionPerformed

    private void C43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C43ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(43, C43, V43, 42);
    }//GEN-LAST:event_C43ActionPerformed

    private void C44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C44ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(44, C44, V44, 43);
    }//GEN-LAST:event_C44ActionPerformed

    private void C45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C45ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(45, C45, V45, 44);
    }//GEN-LAST:event_C45ActionPerformed

    private void C46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C46ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(46, C46, V46, 45);
    }//GEN-LAST:event_C46ActionPerformed

    private void C47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C47ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(47, C47, V47, 46);
    }//GEN-LAST:event_C47ActionPerformed

    private void C48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C48ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(48, C48, V48, 47);
    }//GEN-LAST:event_C48ActionPerformed

    private void C49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C49ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(49, C49, V49, 48);
    }//GEN-LAST:event_C49ActionPerformed

    private void C50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C50ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(50, C50, V50, 49);
    }//GEN-LAST:event_C50ActionPerformed

    private void C51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C51ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(51, C51, V51, 50);
    }//GEN-LAST:event_C51ActionPerformed

    private void C52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C52ActionPerformed
        // TODO add your handling code here:
         mainfunctionAudioChannel(52, C52, V52, 51);
    }//GEN-LAST:event_C52ActionPerformed

    private void C53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C53ActionPerformed
        // TODO add your handling code here:
         mainfunctionAudioChannel(53, C53, V53, 52);
    }//GEN-LAST:event_C53ActionPerformed

    private void C54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C54ActionPerformed
        // TODO add your handling code here:
         mainfunctionAudioChannel(54, C54, V54, 53);
    }//GEN-LAST:event_C54ActionPerformed

    private void C55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C55ActionPerformed
        // TODO add your handling code here:
         mainfunctionAudioChannel(55, C55, V55, 54);
    }//GEN-LAST:event_C55ActionPerformed

    private void C56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C56ActionPerformed
        // TODO add your handling code here:
         mainfunctionAudioChannel(56, C56, V56, 55);
    }//GEN-LAST:event_C56ActionPerformed

    private void C57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C57ActionPerformed
        // TODO add your handling code here:
         mainfunctionAudioChannel(57, C57, V57, 56);
    }//GEN-LAST:event_C57ActionPerformed

    private void C58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C58ActionPerformed
         mainfunctionAudioChannel(58, C58, V58, 57);
        // TODO add your handling code here:
    }//GEN-LAST:event_C58ActionPerformed

    private void C59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C59ActionPerformed
        // TODO add your handling code here:
         mainfunctionAudioChannel(59, C59, V59, 58);
    }//GEN-LAST:event_C59ActionPerformed

    private void C60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C60ActionPerformed
        // TODO add your handling code here:
         mainfunctionAudioChannel(60, C60, V60, 59);
    }//GEN-LAST:event_C60ActionPerformed

    private void C61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C61ActionPerformed
        // TODO add your handling code here:
         mainfunctionAudioChannel(61, C61, V61, 60);
    }//GEN-LAST:event_C61ActionPerformed

    private void C62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C62ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(62, C62, V62, 61);
    }//GEN-LAST:event_C62ActionPerformed

    private void C63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C63ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(63, C63, V63, 62);
    }//GEN-LAST:event_C63ActionPerformed

    private void C64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C64ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(64, C64, V64, 63);
    }//GEN-LAST:event_C64ActionPerformed

    private void S33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S33ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(33, C33, S33, V33, 32);
    }//GEN-LAST:event_S33ActionPerformed

    private void S34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S34ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(34, C34, S34, V34, 33);
    }//GEN-LAST:event_S34ActionPerformed

    private void S35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S35ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(35, C35, S35, V35, 34);
    }//GEN-LAST:event_S35ActionPerformed

    private void S36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S36ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(36, C36, S36, V36, 35);
    }//GEN-LAST:event_S36ActionPerformed

    private void S37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S37ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(37, C37, S37, V37, 36);
    }//GEN-LAST:event_S37ActionPerformed

    private void S38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S38ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(38, C38, S38, V38, 37);
    }//GEN-LAST:event_S38ActionPerformed

    private void S39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S39ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(39, C39, S39, V39, 38);
    }//GEN-LAST:event_S39ActionPerformed

    private void S40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S40ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(40, C40, S40, V40, 39);
    }//GEN-LAST:event_S40ActionPerformed

    private void S41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S41ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(41, C41, S41, V41, 40);
    }//GEN-LAST:event_S41ActionPerformed

    private void S42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S42ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(42, C42, S42, V42, 41);
    }//GEN-LAST:event_S42ActionPerformed

    private void S43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S43ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(43, C43, S43, V43, 42);
    }//GEN-LAST:event_S43ActionPerformed

    private void S44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S44ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(44, C44, S44, V44, 43);
    }//GEN-LAST:event_S44ActionPerformed

    private void S45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S45ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(45, C45, S45, V45, 44);
    }//GEN-LAST:event_S45ActionPerformed

    private void S46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S46ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(46, C46, S46, V46, 45);
    }//GEN-LAST:event_S46ActionPerformed

    private void S47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S47ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(47, C47, S47, V47, 46);
    }//GEN-LAST:event_S47ActionPerformed

    private void S48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S48ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(48, C48, S48, V48, 47);
    }//GEN-LAST:event_S48ActionPerformed

    private void S49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S49ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(49, C49, S49, V49, 48);
    }//GEN-LAST:event_S49ActionPerformed

    private void S50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S50ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(50, C50, S50, V50, 49);
    }//GEN-LAST:event_S50ActionPerformed

    private void S51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S51ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(51, C51, S51, V51, 50);
    }//GEN-LAST:event_S51ActionPerformed

    private void S52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S52ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(52, C52, S52, V52, 51);
    }//GEN-LAST:event_S52ActionPerformed

    private void S53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S53ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(53, C53, S53, V53, 52);
    }//GEN-LAST:event_S53ActionPerformed

    private void S54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S54ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(54, C54, S54, V54, 53);
    }//GEN-LAST:event_S54ActionPerformed

    private void S55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S55ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(55, C55, S55, V55, 54);
    }//GEN-LAST:event_S55ActionPerformed

    private void S56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S56ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(56, C56, S56, V56, 55);
    }//GEN-LAST:event_S56ActionPerformed

    private void S57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S57ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(57, C57, S57, V57, 56);
    }//GEN-LAST:event_S57ActionPerformed

    private void S58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S58ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(58, C58, S58, V58, 57);
    }//GEN-LAST:event_S58ActionPerformed

    private void S59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S59ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(59, C59, S59, V59, 58);
    }//GEN-LAST:event_S59ActionPerformed

    private void S60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S60ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(60, C60, S60, V60, 59);
    }//GEN-LAST:event_S60ActionPerformed

    private void S61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S61ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(61, C61, S61, V61, 60);
    }//GEN-LAST:event_S61ActionPerformed

    private void S62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S62ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(62, C62, S62, V62, 61);
    }//GEN-LAST:event_S62ActionPerformed

    private void S63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S63ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(63, C63, S63, V63, 62);
    }//GEN-LAST:event_S63ActionPerformed

    private void S64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S64ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(64, C64, S64, V64, 63);
    }//GEN-LAST:event_S64ActionPerformed

    private void S2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S2ActionPerformed
        // TODO add your handling code here:
        
        mainfunctionSOLO(2, C2, S2, V2, 8);
        
    }//GEN-LAST:event_S2ActionPerformed

    private void S10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S10ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(10, C10, S10, V10, 9);
        
        
    }//GEN-LAST:event_S10ActionPerformed

    private void S18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S18ActionPerformed
        // TODO add your handling code here:
        
        mainfunctionSOLO(18, C18, S18, V18, 10);
        
    }//GEN-LAST:event_S18ActionPerformed

    private void S26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S26ActionPerformed
        // TODO add your handling code here:
       mainfunctionSOLO(26, C26, S26, V26, 11);
    }//GEN-LAST:event_S26ActionPerformed

    private void S4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S4ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(4, C4, S4, V4, 12);
        
       
    }//GEN-LAST:event_S4ActionPerformed

    private void S12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S12ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(12, C12, S12, V12, 13);
        
    }//GEN-LAST:event_S12ActionPerformed

    private void S20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S20ActionPerformed
        // TODO add your handling code here:
          mainfunctionSOLO(20, C20, S20, V20, 14);
    }//GEN-LAST:event_S20ActionPerformed

    private void S28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S28ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(28, C28, S28, V28, 15);
         
    }//GEN-LAST:event_S28ActionPerformed

    private void S5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S5ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(5, C5, S5, V5, 16);
    }//GEN-LAST:event_S5ActionPerformed

    private void S13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S13ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(13, C13, S13, V13, 17);
        
         
    }//GEN-LAST:event_S13ActionPerformed

    private void S21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S21ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(21, C21, S21, V21, 18);
        
       
    }//GEN-LAST:event_S21ActionPerformed

    private void S6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S6ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(6, C6, S6, V6, 19);
       
    }//GEN-LAST:event_S6ActionPerformed

    private void S29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S29ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(29, C29, S29, V29, 20);
        
         
    }//GEN-LAST:event_S29ActionPerformed

    private void S14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S14ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(14, C14, S14, V14, 21);
        
    }//GEN-LAST:event_S14ActionPerformed

    private void S22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S22ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(22, C22, S22, V22, 22);
        
        
    }//GEN-LAST:event_S22ActionPerformed

    private void S30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S30ActionPerformed
        // TODO add your handling code here:
        
        mainfunctionSOLO(30, C30, S30, V30, 23);
        
       
    }//GEN-LAST:event_S30ActionPerformed

    private void S32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S32ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(32, C32, S32, V32, 31);
        
    }//GEN-LAST:event_S32ActionPerformed

    private void S31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S31ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(31, C31, S31, V31, 30);
        
       
    }//GEN-LAST:event_S31ActionPerformed

    private void S24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S24ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(24, C24, S24, V24, 29);
        
       
    }//GEN-LAST:event_S24ActionPerformed

    private void S23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S23ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(23, C23, S23, V23, 28);
        
    }//GEN-LAST:event_S23ActionPerformed

    private void S16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S16ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(16, C16, S16, V16, 27);
        /*
        var[32]=true;
        if(S16.isSelected()&&!var[31]){
            DisableVolumeExcept(V16,S16,32,15);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             
             audio[15]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(15),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),27,V16,C16,socket);
             audio[15].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[32]=false;
         }     
        }
        if(S16.isSelected()&&var[31])
        {
            DisableVolumeExcept(V16,S16,32,15);
        }
        if(!S16.isSelected()){
            EnableVolume();
            var[32]=false;
            audio[15].detener();
            audio[15].stop();
        }
        */
    }//GEN-LAST:event_S16ActionPerformed

    private void S15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S15ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(15, C15, S15, V15, 26);
        
    }//GEN-LAST:event_S15ActionPerformed

    private void S8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S8ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(8, C8, S8, V8, 25);
        
        
    }//GEN-LAST:event_S8ActionPerformed

    private void S7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S7ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(7, C7, S7, V7, 24);
        
         
    }//GEN-LAST:event_S7ActionPerformed

    private void C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C5ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(25, C5, V5, 16);
        
    }//GEN-LAST:event_C5ActionPerformed

    private void C13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C13ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(13, C13, V13, 17);
         
    }//GEN-LAST:event_C13ActionPerformed

    private void C21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C21ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(21, C21, V21, 18);
         
    }//GEN-LAST:event_C21ActionPerformed

    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(6, C6, V6, 19);
         
    }//GEN-LAST:event_C6ActionPerformed

    private void C29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C29ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(29, C29, V29, 20);
        
    }//GEN-LAST:event_C29ActionPerformed

    private void C14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C14ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(14, C14, V14, 21);
         
    }//GEN-LAST:event_C14ActionPerformed

    private void C22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C22ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(22, C22, V22, 22);
        
    }//GEN-LAST:event_C22ActionPerformed

    private void C30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C30ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(30, C30, V30, 23);
        
    }//GEN-LAST:event_C30ActionPerformed

    private void C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C7ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(7, C7, V7, 24);
         
    }//GEN-LAST:event_C7ActionPerformed

    private void C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C8ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(8, C8, V8, 25);
        
    }//GEN-LAST:event_C8ActionPerformed

    private void C15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C15ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(15, C15, V15, 26);
        
    }//GEN-LAST:event_C15ActionPerformed

    private void C16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C16ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(16, C16, V16, 27);
         
    }//GEN-LAST:event_C16ActionPerformed

    private void C23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C23ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(23, C23, V23, 28);
         
    }//GEN-LAST:event_C23ActionPerformed

    private void C24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C24ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(24, C24, V24, 29);
        
      
    }//GEN-LAST:event_C24ActionPerformed

    private void C31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C31ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(31, C31, V31, 30);
        
    }//GEN-LAST:event_C31ActionPerformed

    private void C32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C32ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(32, C32, V32, 31);
        
    }//GEN-LAST:event_C32ActionPerformed

    private void S27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S27ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        
        mainfunctionSOLO(27, C27, S27, V27, 7);
        /*
        var[54]=true;
        if(S27.isSelected()&&!var[53]){
            DisableVolumeExcept(V27,S27,54,26);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[26]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(26),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),7,V27,C27,socket);
             audio[26].start();
              
             
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
            var[54]=false;
         }     
        }
        if(S27.isSelected()&&var[53])
        {
            DisableVolumeExcept(V27,S27,54,26);
        }
        if(!S27.isSelected()){
            EnableVolume();
            var[54]=false;
            audio[26].detener();
            audio[26].stop();
        }
        */
    }//GEN-LAST:event_S27ActionPerformed

    private void S17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S17ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        mainfunctionSOLO(17, C17, S17, V17, 2);
    }//GEN-LAST:event_S17ActionPerformed

    private void S25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S25ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        
        mainfunctionSOLO(25, C25, S25, V25, 3);
        
        
    }//GEN-LAST:event_S25ActionPerformed

    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C2ActionPerformed
        // TODO add your handling code here:
        
        mainfunctionAudioChannel(2, C2, V2, 8);
                
    }//GEN-LAST:event_C2ActionPerformed

    private void V7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V7StateChanged
        // TODO add your handling code here:
        ganancias.set(6,V7.getValue());
    }//GEN-LAST:event_V7StateChanged

    private void V33StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V33StateChanged
        // TODO add your handling code here:
        ganancias.set(32,V33.getValue());
    }//GEN-LAST:event_V33StateChanged

    private void V34StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V34StateChanged
        // TODO add your handling code here:
        ganancias.set(33,V34.getValue());
    }//GEN-LAST:event_V34StateChanged

    private void V35StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V35StateChanged
        // TODO add your handling code here:
        ganancias.set(34,V35.getValue());
    }//GEN-LAST:event_V35StateChanged

    private void V36StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V36StateChanged
        // TODO add your handling code here:
        ganancias.set(35,V36.getValue());
    }//GEN-LAST:event_V36StateChanged

    private void V37StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V37StateChanged
        // TODO add your handling code here:
        ganancias.set(36,V37.getValue());
    }//GEN-LAST:event_V37StateChanged

    private void V38StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V38StateChanged
        // TODO add your handling code here:
        ganancias.set(37,V38.getValue());
    }//GEN-LAST:event_V38StateChanged

    private void V39StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V39StateChanged
        // TODO add your handling code here:
        ganancias.set(38,V39.getValue());
    }//GEN-LAST:event_V39StateChanged

    private void V40StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V40StateChanged
        // TODO add your handling code here:
        ganancias.set(39,V40.getValue());
    }//GEN-LAST:event_V40StateChanged

    private void V41StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V41StateChanged
        // TODO add your handling code here:
        ganancias.set(40,V41.getValue());
    }//GEN-LAST:event_V41StateChanged

    private void V42StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V42StateChanged
        // TODO add your handling code here:
        ganancias.set(41,V42.getValue());
    }//GEN-LAST:event_V42StateChanged

    private void V43StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V43StateChanged
        // TODO add your handling code here:
        ganancias.set(42,V43.getValue());
    }//GEN-LAST:event_V43StateChanged

    private void V44StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V44StateChanged
        // TODO add your handling code here:
        ganancias.set(43,V44.getValue());
    }//GEN-LAST:event_V44StateChanged

    private void V45StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V45StateChanged
        // TODO add your handling code here:
        ganancias.set(44,V45.getValue());
    }//GEN-LAST:event_V45StateChanged

    private void V46StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V46StateChanged
        // TODO add your handling code here:
        ganancias.set(45,V46.getValue());
    }//GEN-LAST:event_V46StateChanged

    private void V47StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V47StateChanged
        // TODO add your handling code here:
        ganancias.set(46,V47.getValue());
    }//GEN-LAST:event_V47StateChanged

    private void V48StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V48StateChanged
        // TODO add your handling code here:
        ganancias.set(47,V48.getValue());
    }//GEN-LAST:event_V48StateChanged

    private void V49StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V49StateChanged
        // TODO add your handling code here:
        ganancias.set(48,V49.getValue());
    }//GEN-LAST:event_V49StateChanged

    private void V50StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V50StateChanged
        // TODO add your handling code here:
        ganancias.set(49,V50.getValue());
    }//GEN-LAST:event_V50StateChanged

    private void V51StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V51StateChanged
        // TODO add your handling code here:
        ganancias.set(50,V51.getValue());
    }//GEN-LAST:event_V51StateChanged

    private void V52StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V52StateChanged
        // TODO add your handling code here:
        ganancias.set(51,V52.getValue());
    }//GEN-LAST:event_V52StateChanged

    private void V53StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V53StateChanged
        // TODO add your handling code here:
        ganancias.set(52,V53.getValue());
    }//GEN-LAST:event_V53StateChanged

    private void V54StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V54StateChanged
        // TODO add your handling code here:
        ganancias.set(53,V54.getValue());
    }//GEN-LAST:event_V54StateChanged

    private void V55StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V55StateChanged
        // TODO add your handling code here:
        ganancias.set(54,V55.getValue());
    }//GEN-LAST:event_V55StateChanged

    private void V56StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V56StateChanged
        // TODO add your handling code here:
        ganancias.set(55,V56.getValue());
    }//GEN-LAST:event_V56StateChanged

    private void V57StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V57StateChanged
        // TODO add your handling code here:
        ganancias.set(56,V57.getValue());
    }//GEN-LAST:event_V57StateChanged

    private void V58StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V58StateChanged
        // TODO add your handling code here:
        ganancias.set(57,V58.getValue());
    }//GEN-LAST:event_V58StateChanged

    private void V59StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V59StateChanged
        // TODO add your handling code here:
        ganancias.set(58,V59.getValue());
    }//GEN-LAST:event_V59StateChanged

    private void V60StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V60StateChanged
        // TODO add your handling code here:
        ganancias.set(59,V60.getValue());
    }//GEN-LAST:event_V60StateChanged

    private void V61StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V61StateChanged
        // TODO add your handling code here:
        ganancias.set(60,V61.getValue());
    }//GEN-LAST:event_V61StateChanged

    private void V62StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V62StateChanged
        // TODO add your handling code here:
        ganancias.set(61,V62.getValue());
    }//GEN-LAST:event_V62StateChanged

    private void V63StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V63StateChanged
        // TODO add your handling code here:
        ganancias.set(62,V63.getValue());
    }//GEN-LAST:event_V63StateChanged

    private void V64StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V64StateChanged
        // TODO add your handling code here:
        ganancias.set(63,V64.getValue());
    }//GEN-LAST:event_V64StateChanged

    private void C28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C28ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(28, C28, V28, 15);
    }//GEN-LAST:event_C28ActionPerformed

    private void C20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C20ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(20, C20, V20, 14);
    }//GEN-LAST:event_C20ActionPerformed

    private void C12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C12ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(12, C12, V12, 13);
    }//GEN-LAST:event_C12ActionPerformed

    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(4, C4, V4, 12);
    }//GEN-LAST:event_C4ActionPerformed

    private void C26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C26ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(26, C26, V26, 11);
    }//GEN-LAST:event_C26ActionPerformed

    private void C18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C18ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(18, C18, V18, 10);
    }//GEN-LAST:event_C18ActionPerformed

    private void C10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C10ActionPerformed
        // TODO add your handling code here:
        mainfunctionAudioChannel(10, C10, V10, 9);
    }//GEN-LAST:event_C10ActionPerformed

    private void S19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S19ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(19, C19, S19, V19, 6);
    }//GEN-LAST:event_S19ActionPerformed

    private void S3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S3ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(3, C3, S3, V3, 4);
    }//GEN-LAST:event_S3ActionPerformed

    private void S11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S11ActionPerformed
        // TODO add your handling code here:
        mainfunctionSOLO(11, C11, S11, V11, 5);
    }//GEN-LAST:event_S11ActionPerformed

    private void C65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C65ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C65ActionPerformed

    private void C66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C66ActionPerformed

    private void S65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S65ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S65ActionPerformed

    private void C67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C67ActionPerformed

    private void S66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S66ActionPerformed

    private void C68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C68ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C68ActionPerformed

    private void S67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S67ActionPerformed

    private void S68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S68ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S68ActionPerformed

    private void C69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C69ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C69ActionPerformed

    private void S69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S69ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S69ActionPerformed

    private void C70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C70ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C70ActionPerformed

    private void S70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S70ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S70ActionPerformed

    private void C71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C71ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C71ActionPerformed

    private void S71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S71ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S71ActionPerformed

    private void C72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C72ActionPerformed

    private void S72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S72ActionPerformed

    private void S73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S73ActionPerformed

    private void C73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C73ActionPerformed

    private void S74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S74ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S74ActionPerformed

    private void C74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C74ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C74ActionPerformed

    private void S75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S75ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S75ActionPerformed

    private void C75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C75ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C75ActionPerformed

    private void S76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S76ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S76ActionPerformed

    private void C76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C76ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C76ActionPerformed

    private void C77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C77ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C77ActionPerformed

    private void S77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S77ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S77ActionPerformed

    private void C78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C78ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C78ActionPerformed

    private void S78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S78ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S78ActionPerformed

    private void C79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C79ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C79ActionPerformed

    private void S79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S79ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S79ActionPerformed

    private void C80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C80ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C80ActionPerformed

    private void S80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S80ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S80ActionPerformed

    private void C81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C81ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C81ActionPerformed

    private void S81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S81ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S81ActionPerformed

    private void C82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C82ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C82ActionPerformed

    private void V82StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V82StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V82StateChanged

    private void S82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S82ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S82ActionPerformed

    private void C83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C83ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C83ActionPerformed

    private void S83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S83ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S83ActionPerformed

    private void C84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C84ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C84ActionPerformed

    private void S84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S84ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S84ActionPerformed

    private void C85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C85ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C85ActionPerformed

    private void S85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S85ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S85ActionPerformed

    private void C86ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C86ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C86ActionPerformed

    private void S86ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S86ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S86ActionPerformed

    private void C87ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C87ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C87ActionPerformed

    private void S87ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S87ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S87ActionPerformed

    private void C88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C88ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C88ActionPerformed

    private void S88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S88ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S88ActionPerformed

    private void C89ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C89ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C89ActionPerformed

    private void S89ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S89ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S89ActionPerformed

    private void C90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C90ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C90ActionPerformed

    private void S90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S90ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S90ActionPerformed

    private void C91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C91ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C91ActionPerformed

    private void S91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S91ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S91ActionPerformed

    private void C92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C92ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C92ActionPerformed

    private void S92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S92ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S92ActionPerformed

    private void C93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C93ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C93ActionPerformed

    private void S93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S93ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S93ActionPerformed

    private void C94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C94ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C94ActionPerformed

    private void S94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S94ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S94ActionPerformed

    private void C95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C95ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C95ActionPerformed

    private void S95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S95ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S95ActionPerformed

    private void C96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C96ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C96ActionPerformed

    private void S96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S96ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S96ActionPerformed

    private void C97ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C97ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C97ActionPerformed

    private void V97StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V97StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V97StateChanged

    private void S97ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S97ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S97ActionPerformed

    private void V98StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V98StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V98StateChanged

    private void C98ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C98ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C98ActionPerformed

    private void S98ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S98ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S98ActionPerformed

    private void V99StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V99StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V99StateChanged

    private void C99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C99ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C99ActionPerformed

    private void S99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S99ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S99ActionPerformed

    private void V100StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V100StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V100StateChanged

    private void C100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C100ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C100ActionPerformed

    private void S100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S100ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S100ActionPerformed

    private void V101StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V101StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V101StateChanged

    private void C101ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C101ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C101ActionPerformed

    private void S101ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S101ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S101ActionPerformed

    private void C102ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C102ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C102ActionPerformed

    private void V102StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V102StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V102StateChanged

    private void S102ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S102ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S102ActionPerformed

    private void C103ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C103ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C103ActionPerformed

    private void V103StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V103StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V103StateChanged

    private void S103ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S103ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S103ActionPerformed

    private void C104ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C104ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C104ActionPerformed

    private void V104StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V104StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V104StateChanged

    private void S104ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S104ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S104ActionPerformed

    private void C105ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C105ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C105ActionPerformed

    private void V105StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V105StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V105StateChanged

    private void S105ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S105ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S105ActionPerformed

    private void C106ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C106ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C106ActionPerformed

    private void S106ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S106ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S106ActionPerformed

    private void V106StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V106StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V106StateChanged

    private void C107ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C107ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C107ActionPerformed

    private void S107ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S107ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S107ActionPerformed

    private void V107StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V107StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V107StateChanged

    private void C108ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C108ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C108ActionPerformed

    private void S108ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S108ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S108ActionPerformed

    private void V108StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V108StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V108StateChanged

    private void C109ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C109ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C109ActionPerformed

    private void V109StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V109StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V109StateChanged

    private void S109ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S109ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S109ActionPerformed

    private void C110ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C110ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C110ActionPerformed

    private void V110StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V110StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V110StateChanged

    private void S110ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S110ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S110ActionPerformed

    private void C111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C111ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C111ActionPerformed

    private void V111StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V111StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V111StateChanged

    private void S111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S111ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S111ActionPerformed

    private void C112ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C112ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C112ActionPerformed

    private void V112StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V112StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V112StateChanged

    private void S112ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S112ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S112ActionPerformed

    private void C113ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C113ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C113ActionPerformed

    private void V113StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V113StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V113StateChanged

    private void S113ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S113ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S113ActionPerformed

    private void C114ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C114ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C114ActionPerformed

    private void V114StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V114StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V114StateChanged

    private void S114ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S114ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S114ActionPerformed

    private void C115ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C115ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C115ActionPerformed

    private void V115StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V115StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V115StateChanged

    private void S115ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S115ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S115ActionPerformed

    private void C116ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C116ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C116ActionPerformed

    private void V116StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V116StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V116StateChanged

    private void S116ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S116ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S116ActionPerformed

    private void C117ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C117ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C117ActionPerformed

    private void V117StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V117StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V117StateChanged

    private void S117ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S117ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S117ActionPerformed

    private void S118ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S118ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S118ActionPerformed

    private void V118StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V118StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V118StateChanged

    private void C118ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C118ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C118ActionPerformed

    private void S119ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S119ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S119ActionPerformed

    private void V119StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V119StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V119StateChanged

    private void C119ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C119ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C119ActionPerformed

    private void S120ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S120ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S120ActionPerformed

    private void V120StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V120StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V120StateChanged

    private void C120ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C120ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C120ActionPerformed

    private void C121ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C121ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C121ActionPerformed

    private void V121StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V121StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V121StateChanged

    private void S121ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S121ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S121ActionPerformed

    private void C122ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C122ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C122ActionPerformed

    private void V122StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V122StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V122StateChanged

    private void S122ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S122ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S122ActionPerformed

    private void C123ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C123ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C123ActionPerformed

    private void V123StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V123StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V123StateChanged

    private void S123ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S123ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S123ActionPerformed

    private void C124ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C124ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C124ActionPerformed

    private void V124StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V124StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V124StateChanged

    private void S124ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S124ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S124ActionPerformed

    private void C125ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C125ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C125ActionPerformed

    private void V125StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V125StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V125StateChanged

    private void S125ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S125ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S125ActionPerformed

    private void C126ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C126ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C126ActionPerformed

    private void V126StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V126StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V126StateChanged

    private void S126ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S126ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S126ActionPerformed

    private void C127ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C127ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C127ActionPerformed

    private void V127StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V127StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V127StateChanged

    private void S127ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S127ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S127ActionPerformed

    private void C128ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C128ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C128ActionPerformed

    private void V128StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V128StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_V128StateChanged

    private void S128ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S128ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S128ActionPerformed
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton C1;
    private javax.swing.JButton C10;
    private javax.swing.JButton C100;
    private javax.swing.JButton C101;
    private javax.swing.JButton C102;
    private javax.swing.JButton C103;
    private javax.swing.JButton C104;
    private javax.swing.JButton C105;
    private javax.swing.JButton C106;
    private javax.swing.JButton C107;
    private javax.swing.JButton C108;
    private javax.swing.JButton C109;
    private javax.swing.JButton C11;
    private javax.swing.JButton C110;
    private javax.swing.JButton C111;
    private javax.swing.JButton C112;
    private javax.swing.JButton C113;
    private javax.swing.JButton C114;
    private javax.swing.JButton C115;
    private javax.swing.JButton C116;
    private javax.swing.JButton C117;
    private javax.swing.JButton C118;
    private javax.swing.JButton C119;
    private javax.swing.JButton C12;
    private javax.swing.JButton C120;
    private javax.swing.JButton C121;
    private javax.swing.JButton C122;
    private javax.swing.JButton C123;
    private javax.swing.JButton C124;
    private javax.swing.JButton C125;
    private javax.swing.JButton C126;
    private javax.swing.JButton C127;
    private javax.swing.JButton C128;
    private javax.swing.JButton C13;
    private javax.swing.JButton C14;
    private javax.swing.JButton C15;
    private javax.swing.JButton C16;
    private javax.swing.JButton C17;
    private javax.swing.JButton C18;
    private javax.swing.JButton C19;
    private javax.swing.JButton C2;
    private javax.swing.JButton C20;
    private javax.swing.JButton C21;
    private javax.swing.JButton C22;
    private javax.swing.JButton C23;
    private javax.swing.JButton C24;
    private javax.swing.JButton C25;
    private javax.swing.JButton C26;
    private javax.swing.JButton C27;
    private javax.swing.JButton C28;
    private javax.swing.JButton C29;
    private javax.swing.JButton C3;
    private javax.swing.JButton C30;
    private javax.swing.JButton C31;
    private javax.swing.JButton C32;
    private javax.swing.JButton C33;
    private javax.swing.JButton C34;
    private javax.swing.JButton C35;
    private javax.swing.JButton C36;
    private javax.swing.JButton C37;
    private javax.swing.JButton C38;
    private javax.swing.JButton C39;
    private javax.swing.JButton C4;
    private javax.swing.JButton C40;
    private javax.swing.JButton C41;
    private javax.swing.JButton C42;
    private javax.swing.JButton C43;
    private javax.swing.JButton C44;
    private javax.swing.JButton C45;
    private javax.swing.JButton C46;
    private javax.swing.JButton C47;
    private javax.swing.JButton C48;
    private javax.swing.JButton C49;
    private javax.swing.JButton C5;
    private javax.swing.JButton C50;
    private javax.swing.JButton C51;
    private javax.swing.JButton C52;
    private javax.swing.JButton C53;
    private javax.swing.JButton C54;
    private javax.swing.JButton C55;
    private javax.swing.JButton C56;
    private javax.swing.JButton C57;
    private javax.swing.JButton C58;
    private javax.swing.JButton C59;
    private javax.swing.JButton C6;
    private javax.swing.JButton C60;
    private javax.swing.JButton C61;
    private javax.swing.JButton C62;
    private javax.swing.JButton C63;
    private javax.swing.JButton C64;
    private javax.swing.JButton C65;
    private javax.swing.JButton C66;
    private javax.swing.JButton C67;
    private javax.swing.JButton C68;
    private javax.swing.JButton C69;
    private javax.swing.JButton C7;
    private javax.swing.JButton C70;
    private javax.swing.JButton C71;
    private javax.swing.JButton C72;
    private javax.swing.JButton C73;
    private javax.swing.JButton C74;
    private javax.swing.JButton C75;
    private javax.swing.JButton C76;
    private javax.swing.JButton C77;
    private javax.swing.JButton C78;
    private javax.swing.JButton C79;
    private javax.swing.JButton C8;
    private javax.swing.JButton C80;
    private javax.swing.JButton C81;
    private javax.swing.JButton C82;
    private javax.swing.JButton C83;
    private javax.swing.JButton C84;
    private javax.swing.JButton C85;
    private javax.swing.JButton C86;
    private javax.swing.JButton C87;
    private javax.swing.JButton C88;
    private javax.swing.JButton C89;
    private javax.swing.JButton C9;
    private javax.swing.JButton C90;
    private javax.swing.JButton C91;
    private javax.swing.JButton C92;
    private javax.swing.JButton C93;
    private javax.swing.JButton C94;
    private javax.swing.JButton C95;
    private javax.swing.JButton C96;
    private javax.swing.JButton C97;
    private javax.swing.JButton C98;
    private javax.swing.JButton C99;
    private javax.swing.JLabel I1;
    private javax.swing.JLabel I10;
    private javax.swing.JLabel I100;
    private javax.swing.JLabel I101;
    private javax.swing.JLabel I102;
    private javax.swing.JLabel I103;
    private javax.swing.JLabel I104;
    private javax.swing.JLabel I105;
    private javax.swing.JLabel I106;
    private javax.swing.JLabel I107;
    private javax.swing.JLabel I108;
    private javax.swing.JLabel I109;
    private javax.swing.JLabel I11;
    private javax.swing.JLabel I110;
    private javax.swing.JLabel I111;
    private javax.swing.JLabel I112;
    private javax.swing.JLabel I113;
    private javax.swing.JLabel I114;
    private javax.swing.JLabel I115;
    private javax.swing.JLabel I116;
    private javax.swing.JLabel I117;
    private javax.swing.JLabel I118;
    private javax.swing.JLabel I119;
    private javax.swing.JLabel I12;
    private javax.swing.JLabel I120;
    private javax.swing.JLabel I121;
    private javax.swing.JLabel I122;
    private javax.swing.JLabel I123;
    private javax.swing.JLabel I124;
    private javax.swing.JLabel I125;
    private javax.swing.JLabel I126;
    private javax.swing.JLabel I127;
    private javax.swing.JLabel I128;
    private javax.swing.JLabel I13;
    private javax.swing.JLabel I14;
    private javax.swing.JLabel I15;
    private javax.swing.JLabel I16;
    private javax.swing.JLabel I17;
    private javax.swing.JLabel I18;
    private javax.swing.JLabel I19;
    private javax.swing.JLabel I2;
    private javax.swing.JLabel I20;
    private javax.swing.JLabel I21;
    private javax.swing.JLabel I22;
    private javax.swing.JLabel I23;
    private javax.swing.JLabel I24;
    private javax.swing.JLabel I25;
    private javax.swing.JLabel I26;
    private javax.swing.JLabel I27;
    private javax.swing.JLabel I28;
    private javax.swing.JLabel I29;
    private javax.swing.JLabel I3;
    private javax.swing.JLabel I30;
    private javax.swing.JLabel I31;
    private javax.swing.JLabel I32;
    private javax.swing.JLabel I33;
    private javax.swing.JLabel I34;
    private javax.swing.JLabel I35;
    private javax.swing.JLabel I36;
    private javax.swing.JLabel I37;
    private javax.swing.JLabel I38;
    private javax.swing.JLabel I39;
    private javax.swing.JLabel I4;
    private javax.swing.JLabel I40;
    private javax.swing.JLabel I41;
    private javax.swing.JLabel I42;
    private javax.swing.JLabel I43;
    private javax.swing.JLabel I44;
    private javax.swing.JLabel I45;
    private javax.swing.JLabel I46;
    private javax.swing.JLabel I47;
    private javax.swing.JLabel I48;
    private javax.swing.JLabel I49;
    private javax.swing.JLabel I5;
    private javax.swing.JLabel I50;
    private javax.swing.JLabel I51;
    private javax.swing.JLabel I52;
    private javax.swing.JLabel I53;
    private javax.swing.JLabel I54;
    private javax.swing.JLabel I55;
    private javax.swing.JLabel I56;
    private javax.swing.JLabel I57;
    private javax.swing.JLabel I58;
    private javax.swing.JLabel I59;
    private javax.swing.JLabel I6;
    private javax.swing.JLabel I60;
    private javax.swing.JLabel I61;
    private javax.swing.JLabel I62;
    private javax.swing.JLabel I63;
    private javax.swing.JLabel I64;
    private javax.swing.JLabel I65;
    private javax.swing.JLabel I66;
    private javax.swing.JLabel I67;
    private javax.swing.JLabel I68;
    private javax.swing.JLabel I69;
    private javax.swing.JLabel I7;
    private javax.swing.JLabel I70;
    private javax.swing.JLabel I71;
    private javax.swing.JLabel I72;
    private javax.swing.JLabel I73;
    private javax.swing.JLabel I74;
    private javax.swing.JLabel I75;
    private javax.swing.JLabel I76;
    private javax.swing.JLabel I77;
    private javax.swing.JLabel I78;
    private javax.swing.JLabel I79;
    private javax.swing.JLabel I8;
    private javax.swing.JLabel I80;
    private javax.swing.JLabel I81;
    private javax.swing.JLabel I82;
    private javax.swing.JLabel I83;
    private javax.swing.JLabel I84;
    private javax.swing.JLabel I85;
    private javax.swing.JLabel I86;
    private javax.swing.JLabel I87;
    private javax.swing.JLabel I88;
    private javax.swing.JLabel I89;
    private javax.swing.JLabel I9;
    private javax.swing.JLabel I90;
    private javax.swing.JLabel I91;
    private javax.swing.JLabel I92;
    private javax.swing.JLabel I93;
    private javax.swing.JLabel I94;
    private javax.swing.JLabel I95;
    private javax.swing.JLabel I96;
    private javax.swing.JLabel I97;
    private javax.swing.JLabel I98;
    private javax.swing.JLabel I99;
    private javax.swing.JToggleButton S1;
    private javax.swing.JToggleButton S10;
    private javax.swing.JToggleButton S100;
    private javax.swing.JToggleButton S101;
    private javax.swing.JToggleButton S102;
    private javax.swing.JToggleButton S103;
    private javax.swing.JToggleButton S104;
    private javax.swing.JToggleButton S105;
    private javax.swing.JToggleButton S106;
    private javax.swing.JToggleButton S107;
    private javax.swing.JToggleButton S108;
    private javax.swing.JToggleButton S109;
    private javax.swing.JToggleButton S11;
    private javax.swing.JToggleButton S110;
    private javax.swing.JToggleButton S111;
    private javax.swing.JToggleButton S112;
    private javax.swing.JToggleButton S113;
    private javax.swing.JToggleButton S114;
    private javax.swing.JToggleButton S115;
    private javax.swing.JToggleButton S116;
    private javax.swing.JToggleButton S117;
    private javax.swing.JToggleButton S118;
    private javax.swing.JToggleButton S119;
    private javax.swing.JToggleButton S12;
    private javax.swing.JToggleButton S120;
    private javax.swing.JToggleButton S121;
    private javax.swing.JToggleButton S122;
    private javax.swing.JToggleButton S123;
    private javax.swing.JToggleButton S124;
    private javax.swing.JToggleButton S125;
    private javax.swing.JToggleButton S126;
    private javax.swing.JToggleButton S127;
    private javax.swing.JToggleButton S128;
    private javax.swing.JToggleButton S13;
    private javax.swing.JToggleButton S14;
    private javax.swing.JToggleButton S15;
    private javax.swing.JToggleButton S16;
    private javax.swing.JToggleButton S17;
    private javax.swing.JToggleButton S18;
    private javax.swing.JToggleButton S19;
    private javax.swing.JToggleButton S2;
    private javax.swing.JToggleButton S20;
    private javax.swing.JToggleButton S21;
    private javax.swing.JToggleButton S22;
    private javax.swing.JToggleButton S23;
    private javax.swing.JToggleButton S24;
    private javax.swing.JToggleButton S25;
    private javax.swing.JToggleButton S26;
    private javax.swing.JToggleButton S27;
    private javax.swing.JToggleButton S28;
    private javax.swing.JToggleButton S29;
    private javax.swing.JToggleButton S3;
    private javax.swing.JToggleButton S30;
    private javax.swing.JToggleButton S31;
    private javax.swing.JToggleButton S32;
    private javax.swing.JToggleButton S33;
    private javax.swing.JToggleButton S34;
    private javax.swing.JToggleButton S35;
    private javax.swing.JToggleButton S36;
    private javax.swing.JToggleButton S37;
    private javax.swing.JToggleButton S38;
    private javax.swing.JToggleButton S39;
    private javax.swing.JToggleButton S4;
    private javax.swing.JToggleButton S40;
    private javax.swing.JToggleButton S41;
    private javax.swing.JToggleButton S42;
    private javax.swing.JToggleButton S43;
    private javax.swing.JToggleButton S44;
    private javax.swing.JToggleButton S45;
    private javax.swing.JToggleButton S46;
    private javax.swing.JToggleButton S47;
    private javax.swing.JToggleButton S48;
    private javax.swing.JToggleButton S49;
    private javax.swing.JToggleButton S5;
    private javax.swing.JToggleButton S50;
    private javax.swing.JToggleButton S51;
    private javax.swing.JToggleButton S52;
    private javax.swing.JToggleButton S53;
    private javax.swing.JToggleButton S54;
    private javax.swing.JToggleButton S55;
    private javax.swing.JToggleButton S56;
    private javax.swing.JToggleButton S57;
    private javax.swing.JToggleButton S58;
    private javax.swing.JToggleButton S59;
    private javax.swing.JToggleButton S6;
    private javax.swing.JToggleButton S60;
    private javax.swing.JToggleButton S61;
    private javax.swing.JToggleButton S62;
    private javax.swing.JToggleButton S63;
    private javax.swing.JToggleButton S64;
    private javax.swing.JToggleButton S65;
    private javax.swing.JToggleButton S66;
    private javax.swing.JToggleButton S67;
    private javax.swing.JToggleButton S68;
    private javax.swing.JToggleButton S69;
    private javax.swing.JToggleButton S7;
    private javax.swing.JToggleButton S70;
    private javax.swing.JToggleButton S71;
    private javax.swing.JToggleButton S72;
    private javax.swing.JToggleButton S73;
    private javax.swing.JToggleButton S74;
    private javax.swing.JToggleButton S75;
    private javax.swing.JToggleButton S76;
    private javax.swing.JToggleButton S77;
    private javax.swing.JToggleButton S78;
    private javax.swing.JToggleButton S79;
    private javax.swing.JToggleButton S8;
    private javax.swing.JToggleButton S80;
    private javax.swing.JToggleButton S81;
    private javax.swing.JToggleButton S82;
    private javax.swing.JToggleButton S83;
    private javax.swing.JToggleButton S84;
    private javax.swing.JToggleButton S85;
    private javax.swing.JToggleButton S86;
    private javax.swing.JToggleButton S87;
    private javax.swing.JToggleButton S88;
    private javax.swing.JToggleButton S89;
    private javax.swing.JToggleButton S9;
    private javax.swing.JToggleButton S90;
    private javax.swing.JToggleButton S91;
    private javax.swing.JToggleButton S92;
    private javax.swing.JToggleButton S93;
    private javax.swing.JToggleButton S94;
    private javax.swing.JToggleButton S95;
    private javax.swing.JToggleButton S96;
    private javax.swing.JToggleButton S97;
    private javax.swing.JToggleButton S98;
    private javax.swing.JToggleButton S99;
    private javax.swing.JSlider V1;
    private javax.swing.JSlider V10;
    private javax.swing.JSlider V100;
    private javax.swing.JSlider V101;
    private javax.swing.JSlider V102;
    private javax.swing.JSlider V103;
    private javax.swing.JSlider V104;
    private javax.swing.JSlider V105;
    private javax.swing.JSlider V106;
    private javax.swing.JSlider V107;
    private javax.swing.JSlider V108;
    private javax.swing.JSlider V109;
    private javax.swing.JSlider V11;
    private javax.swing.JSlider V110;
    private javax.swing.JSlider V111;
    private javax.swing.JSlider V112;
    private javax.swing.JSlider V113;
    private javax.swing.JSlider V114;
    private javax.swing.JSlider V115;
    private javax.swing.JSlider V116;
    private javax.swing.JSlider V117;
    private javax.swing.JSlider V118;
    private javax.swing.JSlider V119;
    private javax.swing.JSlider V12;
    private javax.swing.JSlider V120;
    private javax.swing.JSlider V121;
    private javax.swing.JSlider V122;
    private javax.swing.JSlider V123;
    private javax.swing.JSlider V124;
    private javax.swing.JSlider V125;
    private javax.swing.JSlider V126;
    private javax.swing.JSlider V127;
    private javax.swing.JSlider V128;
    private javax.swing.JSlider V13;
    private javax.swing.JSlider V14;
    private javax.swing.JSlider V15;
    private javax.swing.JSlider V16;
    private javax.swing.JSlider V17;
    private javax.swing.JSlider V18;
    private javax.swing.JSlider V19;
    private javax.swing.JSlider V2;
    private javax.swing.JSlider V20;
    private javax.swing.JSlider V21;
    private javax.swing.JSlider V22;
    private javax.swing.JSlider V23;
    private javax.swing.JSlider V24;
    private javax.swing.JSlider V25;
    private javax.swing.JSlider V26;
    private javax.swing.JSlider V27;
    private javax.swing.JSlider V28;
    private javax.swing.JSlider V29;
    private javax.swing.JSlider V3;
    private javax.swing.JSlider V30;
    private javax.swing.JSlider V31;
    private javax.swing.JSlider V32;
    private javax.swing.JSlider V33;
    private javax.swing.JSlider V34;
    private javax.swing.JSlider V35;
    private javax.swing.JSlider V36;
    private javax.swing.JSlider V37;
    private javax.swing.JSlider V38;
    private javax.swing.JSlider V39;
    private javax.swing.JSlider V4;
    private javax.swing.JSlider V40;
    private javax.swing.JSlider V41;
    private javax.swing.JSlider V42;
    private javax.swing.JSlider V43;
    private javax.swing.JSlider V44;
    private javax.swing.JSlider V45;
    private javax.swing.JSlider V46;
    private javax.swing.JSlider V47;
    private javax.swing.JSlider V48;
    private javax.swing.JSlider V49;
    private javax.swing.JSlider V5;
    private javax.swing.JSlider V50;
    private javax.swing.JSlider V51;
    private javax.swing.JSlider V52;
    private javax.swing.JSlider V53;
    private javax.swing.JSlider V54;
    private javax.swing.JSlider V55;
    private javax.swing.JSlider V56;
    private javax.swing.JSlider V57;
    private javax.swing.JSlider V58;
    private javax.swing.JSlider V59;
    private javax.swing.JSlider V6;
    private javax.swing.JSlider V60;
    private javax.swing.JSlider V61;
    private javax.swing.JSlider V62;
    private javax.swing.JSlider V63;
    private javax.swing.JSlider V64;
    private javax.swing.JSlider V65;
    private javax.swing.JSlider V66;
    private javax.swing.JSlider V67;
    private javax.swing.JSlider V68;
    private javax.swing.JSlider V69;
    private javax.swing.JSlider V7;
    private javax.swing.JSlider V70;
    private javax.swing.JSlider V71;
    private javax.swing.JSlider V72;
    private javax.swing.JSlider V73;
    private javax.swing.JSlider V74;
    private javax.swing.JSlider V75;
    private javax.swing.JSlider V76;
    private javax.swing.JSlider V77;
    private javax.swing.JSlider V78;
    private javax.swing.JSlider V79;
    private javax.swing.JSlider V8;
    private javax.swing.JSlider V80;
    private javax.swing.JSlider V81;
    private javax.swing.JSlider V82;
    private javax.swing.JSlider V83;
    private javax.swing.JSlider V84;
    private javax.swing.JSlider V85;
    private javax.swing.JSlider V86;
    private javax.swing.JSlider V87;
    private javax.swing.JSlider V88;
    private javax.swing.JSlider V89;
    private javax.swing.JSlider V9;
    private javax.swing.JSlider V90;
    private javax.swing.JSlider V91;
    private javax.swing.JSlider V92;
    private javax.swing.JSlider V93;
    private javax.swing.JSlider V94;
    private javax.swing.JSlider V95;
    private javax.swing.JSlider V96;
    private javax.swing.JSlider V97;
    private javax.swing.JSlider V98;
    private javax.swing.JSlider V99;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
