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
            togglebutton.setForeground(new Color(186, 195, 242));
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
                    ((JToggleButton)component).setForeground(Color.black);
                }
                
                else
                {
                    ((JToggleButton)component).setSelected(false);
                    ((JToggleButton)component).setBackground(new Color(69, 93, 220));
                    ((JToggleButton)component).setForeground(new Color(186,195,242));
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

        setBackground(new java.awt.Color(51, 51, 51));

        I1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        V1.setBackground(new java.awt.Color(47, 55, 76));
        V1.setForeground(new java.awt.Color(255, 0, 102));
        V1.setMaximum(10);

        C1.setBackground(new java.awt.Color(25, 31, 49));
        C1.setForeground(new java.awt.Color(255, 255, 255));
        C1.setText("jButton1");
        C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C1ActionPerformed(evt);
            }
        });

        C9.setBackground(new java.awt.Color(102, 102, 102));
        C9.setForeground(new java.awt.Color(255, 255, 255));
        C9.setText("jButton1");
        C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C9ActionPerformed(evt);
            }
        });

        V9.setBackground(new java.awt.Color(47, 55, 76));
        V9.setMaximum(10);

        S1.setBackground(new java.awt.Color(69, 93, 220));
        S1.setForeground(new java.awt.Color(186, 195, 242));
        S1.setText("SOLO");
        S1.setContentAreaFilled(false);
        S1.setOpaque(true);
        S1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S1ActionPerformed(evt);
            }
        });

        I17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C17.setBackground(new java.awt.Color(102, 102, 102));
        C17.setForeground(new java.awt.Color(255, 255, 255));
        C17.setText("jButton1");
        C17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C17ActionPerformed(evt);
            }
        });

        V17.setBackground(new java.awt.Color(47, 55, 76));
        V17.setMaximum(10);

        S17.setBackground(new java.awt.Color(69, 93, 220));
        S17.setForeground(new java.awt.Color(186, 195, 242));
        S17.setText("SOLO");
        S17.setContentAreaFilled(false);
        S17.setOpaque(true);
        S17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S17ActionPerformed(evt);
            }
        });

        I25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C25.setBackground(new java.awt.Color(102, 102, 102));
        C25.setForeground(new java.awt.Color(255, 255, 255));
        C25.setText("jButton1");
        C25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C25ActionPerformed(evt);
            }
        });

        V25.setBackground(new java.awt.Color(47, 55, 76));
        V25.setMaximum(10);

        S25.setBackground(new java.awt.Color(69, 93, 220));
        S25.setForeground(new java.awt.Color(186, 195, 242));
        S25.setText("SOLO");
        S25.setContentAreaFilled(false);
        S25.setOpaque(true);
        S25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S25ActionPerformed(evt);
            }
        });

        S9.setBackground(new java.awt.Color(69, 93, 220));
        S9.setForeground(new java.awt.Color(186, 195, 242));
        S9.setText("SOLO");
        S9.setContentAreaFilled(false);
        S9.setOpaque(true);
        S9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S9ActionPerformed(evt);
            }
        });

        I3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C3.setBackground(new java.awt.Color(102, 102, 102));
        C3.setForeground(new java.awt.Color(255, 255, 255));
        C3.setText("jButton1");
        C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3ActionPerformed(evt);
            }
        });

        V3.setBackground(new java.awt.Color(47, 55, 76));
        V3.setMaximum(10);

        S3.setBackground(new java.awt.Color(69, 93, 220));
        S3.setForeground(new java.awt.Color(186, 195, 242));
        S3.setText("SOLO");
        S3.setContentAreaFilled(false);
        S3.setOpaque(true);
        S3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S3ActionPerformed(evt);
            }
        });

        I11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C11.setBackground(new java.awt.Color(102, 102, 102));
        C11.setForeground(new java.awt.Color(255, 255, 255));
        C11.setText("jButton1");
        C11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C11ActionPerformed(evt);
            }
        });

        V11.setBackground(new java.awt.Color(47, 55, 76));
        V11.setMaximum(10);

        S11.setBackground(new java.awt.Color(69, 93, 220));
        S11.setForeground(new java.awt.Color(186, 195, 242));
        S11.setText("SOLO");
        S11.setContentAreaFilled(false);
        S11.setOpaque(true);
        S11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S11ActionPerformed(evt);
            }
        });

        I19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C19.setBackground(new java.awt.Color(102, 102, 102));
        C19.setForeground(new java.awt.Color(255, 255, 255));
        C19.setText("jButton1");
        C19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C19ActionPerformed(evt);
            }
        });

        V19.setBackground(new java.awt.Color(47, 55, 76));
        V19.setMaximum(10);

        S19.setBackground(new java.awt.Color(69, 93, 220));
        S19.setForeground(new java.awt.Color(186, 195, 242));
        S19.setText("SOLO");
        S19.setContentAreaFilled(false);
        S19.setOpaque(true);
        S19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S19ActionPerformed(evt);
            }
        });

        I27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C27.setBackground(new java.awt.Color(102, 102, 102));
        C27.setForeground(new java.awt.Color(255, 255, 255));
        C27.setText("jButton1");
        C27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C27ActionPerformed(evt);
            }
        });

        V27.setBackground(new java.awt.Color(47, 55, 76));
        V27.setMaximum(10);

        S27.setBackground(new java.awt.Color(69, 93, 220));
        S27.setForeground(new java.awt.Color(186, 195, 242));
        S27.setText("SOLO");
        S27.setContentAreaFilled(false);
        S27.setOpaque(true);
        S27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S27ActionPerformed(evt);
            }
        });

        S2.setBackground(new java.awt.Color(69, 93, 220));
        S2.setForeground(new java.awt.Color(186, 195, 242));
        S2.setText("SOLO");
        S2.setContentAreaFilled(false);
        S2.setOpaque(true);
        S2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S2ActionPerformed(evt);
            }
        });

        V2.setBackground(new java.awt.Color(47, 55, 76));
        V2.setMaximum(10);

        C2.setBackground(new java.awt.Color(102, 102, 102));
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
        S10.setText("SOLO");
        S10.setContentAreaFilled(false);
        S10.setOpaque(true);
        S10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S10ActionPerformed(evt);
            }
        });

        V10.setBackground(new java.awt.Color(47, 55, 76));
        V10.setMaximum(10);

        C10.setBackground(new java.awt.Color(102, 102, 102));
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
        S18.setText("SOLO");
        S18.setContentAreaFilled(false);
        S18.setOpaque(true);
        S18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S18ActionPerformed(evt);
            }
        });

        V18.setBackground(new java.awt.Color(47, 55, 76));
        V18.setMaximum(10);

        C18.setBackground(new java.awt.Color(102, 102, 102));
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
        S26.setText("SOLO");
        S26.setContentAreaFilled(false);
        S26.setOpaque(true);
        S26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S26ActionPerformed(evt);
            }
        });

        V26.setBackground(new java.awt.Color(47, 55, 76));
        V26.setMaximum(10);

        C26.setBackground(new java.awt.Color(102, 102, 120));
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
        C4.setForeground(new java.awt.Color(255, 255, 255));
        C4.setText("jButton1");
        C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4ActionPerformed(evt);
            }
        });

        V4.setBackground(new java.awt.Color(47, 55, 76));
        V4.setMaximum(10);

        S4.setBackground(new java.awt.Color(69, 93, 220));
        S4.setForeground(new java.awt.Color(186, 195, 242));
        S4.setText("SOLO");
        S4.setContentAreaFilled(false);
        S4.setOpaque(true);
        S4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S4ActionPerformed(evt);
            }
        });

        I12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C12.setBackground(new java.awt.Color(102, 102, 102));
        C12.setForeground(new java.awt.Color(255, 255, 255));
        C12.setText("jButton1");
        C12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C12ActionPerformed(evt);
            }
        });

        V12.setBackground(new java.awt.Color(47, 55, 76));
        V12.setMaximum(10);

        S12.setBackground(new java.awt.Color(69, 93, 220));
        S12.setForeground(new java.awt.Color(186, 195, 242));
        S12.setText("SOLO");
        S12.setContentAreaFilled(false);
        S12.setOpaque(true);
        S12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S12ActionPerformed(evt);
            }
        });

        I20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C20.setBackground(new java.awt.Color(102, 102, 102));
        C20.setForeground(new java.awt.Color(255, 255, 255));
        C20.setText("jButton1");
        C20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C20ActionPerformed(evt);
            }
        });

        V20.setBackground(new java.awt.Color(47, 55, 76));
        V20.setMaximum(10);

        S20.setBackground(new java.awt.Color(69, 93, 220));
        S20.setForeground(new java.awt.Color(186, 195, 242));
        S20.setText("SOLO");
        S20.setContentAreaFilled(false);
        S20.setOpaque(true);
        S20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S20ActionPerformed(evt);
            }
        });

        I28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C28.setBackground(new java.awt.Color(102, 102, 102));
        C28.setForeground(new java.awt.Color(255, 255, 255));
        C28.setText("jButton1");
        C28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C28ActionPerformed(evt);
            }
        });

        V28.setBackground(new java.awt.Color(47, 55, 76));
        V28.setMaximum(10);

        S28.setBackground(new java.awt.Color(69, 93, 220));
        S28.setForeground(new java.awt.Color(186, 195, 242));
        S28.setText("SOLO");
        S28.setContentAreaFilled(false);
        S28.setOpaque(true);
        S28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S28ActionPerformed(evt);
            }
        });

        I5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C5.setBackground(new java.awt.Color(102, 102, 102));
        C5.setForeground(new java.awt.Color(255, 255, 255));
        C5.setText("jButton1");
        C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C5ActionPerformed(evt);
            }
        });

        V5.setBackground(new java.awt.Color(47, 55, 76));
        V5.setMaximum(10);

        S5.setBackground(new java.awt.Color(69, 93, 220));
        S5.setForeground(new java.awt.Color(186, 195, 242));
        S5.setText("SOLO");
        S5.setContentAreaFilled(false);
        S5.setOpaque(true);
        S5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S5ActionPerformed(evt);
            }
        });

        I7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C7.setBackground(new java.awt.Color(102, 102, 102));
        C7.setForeground(new java.awt.Color(255, 255, 255));
        C7.setText("jButton1");
        C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C7ActionPerformed(evt);
            }
        });

        V7.setBackground(new java.awt.Color(47, 55, 76));
        V7.setMaximum(10);
        V7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V7StateChanged(evt);
            }
        });

        S7.setBackground(new java.awt.Color(69, 93, 220));
        S7.setForeground(new java.awt.Color(186, 195, 242));
        S7.setText("SOLO");
        S7.setContentAreaFilled(false);
        S7.setOpaque(true);
        S7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S7ActionPerformed(evt);
            }
        });

        I13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C13.setBackground(new java.awt.Color(102, 102, 102));
        C13.setForeground(new java.awt.Color(255, 255, 255));
        C13.setText("jButton1");
        C13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C13ActionPerformed(evt);
            }
        });

        V13.setBackground(new java.awt.Color(47, 55, 76));
        V13.setMaximum(10);

        S13.setBackground(new java.awt.Color(69, 93, 220));
        S13.setForeground(new java.awt.Color(186, 195, 242));
        S13.setText("SOLO");
        S13.setContentAreaFilled(false);
        S13.setOpaque(true);
        S13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S13ActionPerformed(evt);
            }
        });

        I21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C21.setBackground(new java.awt.Color(102, 102, 102));
        C21.setForeground(new java.awt.Color(255, 255, 255));
        C21.setText("jButton1");
        C21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C21ActionPerformed(evt);
            }
        });

        V21.setBackground(new java.awt.Color(47, 55, 76));
        V21.setMaximum(10);

        S21.setBackground(new java.awt.Color(69, 93, 220));
        S21.setForeground(new java.awt.Color(186, 195, 242));
        S21.setText("SOLO");
        S21.setContentAreaFilled(false);
        S21.setOpaque(true);
        S21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S21ActionPerformed(evt);
            }
        });

        I29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C6.setBackground(new java.awt.Color(102, 102, 102));
        C6.setForeground(new java.awt.Color(255, 255, 255));
        C6.setText("jButton1");
        C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6ActionPerformed(evt);
            }
        });

        V6.setBackground(new java.awt.Color(47, 55, 76));
        V6.setMaximum(10);

        S6.setBackground(new java.awt.Color(69, 93, 220));
        S6.setForeground(new java.awt.Color(186, 195, 242));
        S6.setText("SOLO");
        S6.setContentAreaFilled(false);
        S6.setOpaque(true);
        S6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S6ActionPerformed(evt);
            }
        });

        I6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C29.setBackground(new java.awt.Color(102, 102, 102));
        C29.setForeground(new java.awt.Color(255, 255, 255));
        C29.setText("jButton1");
        C29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C29ActionPerformed(evt);
            }
        });

        V29.setBackground(new java.awt.Color(47, 55, 76));
        V29.setMaximum(10);

        S29.setBackground(new java.awt.Color(69, 93, 220));
        S29.setForeground(new java.awt.Color(186, 195, 242));
        S29.setText("SOLO");
        S29.setContentAreaFilled(false);
        S29.setOpaque(true);
        S29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S29ActionPerformed(evt);
            }
        });

        I14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C14.setBackground(new java.awt.Color(102, 102, 102));
        C14.setForeground(new java.awt.Color(255, 255, 255));
        C14.setText("jButton1");
        C14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C14ActionPerformed(evt);
            }
        });

        V14.setBackground(new java.awt.Color(47, 55, 76));
        V14.setMaximum(10);

        S14.setBackground(new java.awt.Color(69, 93, 220));
        S14.setForeground(new java.awt.Color(186, 195, 242));
        S14.setText("SOLO");
        S14.setContentAreaFilled(false);
        S14.setOpaque(true);
        S14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S14ActionPerformed(evt);
            }
        });

        I22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C22.setBackground(new java.awt.Color(102, 102, 102));
        C22.setForeground(new java.awt.Color(255, 255, 255));
        C22.setText("jButton1");
        C22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C22ActionPerformed(evt);
            }
        });

        V22.setBackground(new java.awt.Color(47, 55, 76));
        V22.setMaximum(10);

        S22.setBackground(new java.awt.Color(69, 93, 220));
        S22.setForeground(new java.awt.Color(186, 195, 242));
        S22.setText("SOLO");
        S22.setContentAreaFilled(false);
        S22.setOpaque(true);
        S22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S22ActionPerformed(evt);
            }
        });

        I30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C30.setBackground(new java.awt.Color(102, 102, 102));
        C30.setForeground(new java.awt.Color(255, 255, 255));
        C30.setText("jButton1");
        C30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C30ActionPerformed(evt);
            }
        });

        V30.setBackground(new java.awt.Color(47, 55, 76));
        V30.setMaximum(10);

        S30.setBackground(new java.awt.Color(69, 93, 220));
        S30.setForeground(new java.awt.Color(186, 195, 242));
        S30.setText("SOLO");
        S30.setContentAreaFilled(false);
        S30.setOpaque(true);
        S30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S30ActionPerformed(evt);
            }
        });

        I8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C8.setBackground(new java.awt.Color(102, 102, 102));
        C8.setForeground(new java.awt.Color(255, 255, 255));
        C8.setText("jButton1");
        C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C8ActionPerformed(evt);
            }
        });

        V8.setBackground(new java.awt.Color(47, 55, 76));
        V8.setMaximum(10);

        S8.setBackground(new java.awt.Color(69, 93, 220));
        S8.setForeground(new java.awt.Color(186, 195, 242));
        S8.setText("SOLO");
        S8.setContentAreaFilled(false);
        S8.setOpaque(true);
        S8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S8ActionPerformed(evt);
            }
        });

        I15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C15.setBackground(new java.awt.Color(102, 102, 102));
        C15.setForeground(new java.awt.Color(255, 255, 255));
        C15.setText("jButton1");
        C15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C15ActionPerformed(evt);
            }
        });

        V15.setBackground(new java.awt.Color(47, 55, 76));
        V15.setMaximum(10);

        S15.setBackground(new java.awt.Color(69, 93, 220));
        S15.setForeground(new java.awt.Color(186, 195, 242));
        S15.setText("SOLO");
        S15.setContentAreaFilled(false);
        S15.setOpaque(true);
        S15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S15ActionPerformed(evt);
            }
        });

        I16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C16.setBackground(new java.awt.Color(102, 102, 102));
        C16.setForeground(new java.awt.Color(255, 255, 255));
        C16.setText("jButton1");
        C16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C16ActionPerformed(evt);
            }
        });

        V16.setBackground(new java.awt.Color(47, 55, 76));
        V16.setMaximum(10);

        S16.setBackground(new java.awt.Color(69, 93, 220));
        S16.setForeground(new java.awt.Color(186, 195, 242));
        S16.setText("SOLO");
        S16.setContentAreaFilled(false);
        S16.setOpaque(true);
        S16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S16ActionPerformed(evt);
            }
        });

        I23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C23.setBackground(new java.awt.Color(102, 102, 102));
        C23.setForeground(new java.awt.Color(255, 255, 255));
        C23.setText("jButton1");
        C23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C23ActionPerformed(evt);
            }
        });

        V23.setBackground(new java.awt.Color(47, 55, 76));
        V23.setMaximum(10);

        S23.setBackground(new java.awt.Color(69, 93, 220));
        S23.setForeground(new java.awt.Color(186, 195, 242));
        S23.setText("SOLO");
        S23.setContentAreaFilled(false);
        S23.setOpaque(true);
        S23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S23ActionPerformed(evt);
            }
        });

        I24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C24.setBackground(new java.awt.Color(102, 102, 102));
        C24.setForeground(new java.awt.Color(255, 255, 255));
        C24.setText("jButton1");
        C24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C24ActionPerformed(evt);
            }
        });

        V24.setBackground(new java.awt.Color(47, 55, 76));
        V24.setMaximum(10);

        S24.setBackground(new java.awt.Color(69, 93, 220));
        S24.setForeground(new java.awt.Color(186, 195, 242));
        S24.setText("SOLO");
        S24.setContentAreaFilled(false);
        S24.setOpaque(true);
        S24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S24ActionPerformed(evt);
            }
        });

        I31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C31.setBackground(new java.awt.Color(102, 102, 102));
        C31.setForeground(new java.awt.Color(255, 255, 255));
        C31.setText("jButton1");
        C31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C31ActionPerformed(evt);
            }
        });

        V31.setBackground(new java.awt.Color(47, 55, 76));
        V31.setMaximum(10);

        S31.setBackground(new java.awt.Color(69, 93, 220));
        S31.setForeground(new java.awt.Color(186, 195, 242));
        S31.setText("SOLO");
        S31.setContentAreaFilled(false);
        S31.setOpaque(true);
        S31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S31ActionPerformed(evt);
            }
        });

        I32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C32.setBackground(new java.awt.Color(102, 102, 102));
        C32.setForeground(new java.awt.Color(255, 255, 255));
        C32.setText("jButton1");
        C32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C32ActionPerformed(evt);
            }
        });

        V32.setBackground(new java.awt.Color(47, 55, 76));
        V32.setMaximum(10);

        S32.setBackground(new java.awt.Color(69, 93, 220));
        S32.setForeground(new java.awt.Color(186, 195, 242));
        S32.setText("SOLO");
        S32.setContentAreaFilled(false);
        S32.setOpaque(true);
        S32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S32ActionPerformed(evt);
            }
        });

        I33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C33.setBackground(new java.awt.Color(102, 102, 102));
        C33.setForeground(new java.awt.Color(255, 255, 255));
        C33.setText("jButton1");
        C33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C33ActionPerformed(evt);
            }
        });

        V33.setBackground(new java.awt.Color(47, 55, 76));
        V33.setMaximum(10);
        V33.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V33StateChanged(evt);
            }
        });

        S33.setBackground(new java.awt.Color(69, 93, 220));
        S33.setForeground(new java.awt.Color(186, 195, 242));
        S33.setText("SOLO");
        S33.setContentAreaFilled(false);
        S33.setOpaque(true);
        S33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S33ActionPerformed(evt);
            }
        });

        V34.setBackground(new java.awt.Color(47, 55, 76));
        V34.setMaximum(10);
        V34.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V34StateChanged(evt);
            }
        });

        C34.setBackground(new java.awt.Color(102, 102, 102));
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
        S34.setText("SOLO");
        S34.setContentAreaFilled(false);
        S34.setOpaque(true);
        S34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S34ActionPerformed(evt);
            }
        });

        V35.setBackground(new java.awt.Color(47, 55, 76));
        V35.setMaximum(10);
        V35.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V35StateChanged(evt);
            }
        });

        C35.setBackground(new java.awt.Color(102, 102, 102));
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
        S35.setText("SOLO");
        S35.setContentAreaFilled(false);
        S35.setOpaque(true);
        S35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S35ActionPerformed(evt);
            }
        });

        V36.setBackground(new java.awt.Color(47, 55, 76));
        V36.setMaximum(10);
        V36.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V36StateChanged(evt);
            }
        });

        C36.setBackground(new java.awt.Color(102, 102, 102));
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
        S36.setText("SOLO");
        S36.setContentAreaFilled(false);
        S36.setOpaque(true);
        S36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S36ActionPerformed(evt);
            }
        });

        V37.setBackground(new java.awt.Color(47, 55, 76));
        V37.setMaximum(10);
        V37.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V37StateChanged(evt);
            }
        });

        C37.setBackground(new java.awt.Color(102, 102, 102));
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
        S37.setText("SOLO");
        S37.setContentAreaFilled(false);
        S37.setOpaque(true);
        S37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S37ActionPerformed(evt);
            }
        });

        I38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C38.setBackground(new java.awt.Color(102, 102, 102));
        C38.setForeground(new java.awt.Color(255, 255, 255));
        C38.setText("jButton1");
        C38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C38ActionPerformed(evt);
            }
        });

        V38.setBackground(new java.awt.Color(47, 55, 76));
        V38.setMaximum(10);
        V38.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V38StateChanged(evt);
            }
        });

        S38.setBackground(new java.awt.Color(69, 93, 220));
        S38.setForeground(new java.awt.Color(186, 195, 242));
        S38.setText("SOLO");
        S38.setContentAreaFilled(false);
        S38.setOpaque(true);
        S38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S38ActionPerformed(evt);
            }
        });

        I39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C39.setBackground(new java.awt.Color(102, 102, 102));
        C39.setForeground(new java.awt.Color(255, 255, 255));
        C39.setText("jButton1");
        C39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C39ActionPerformed(evt);
            }
        });

        V39.setBackground(new java.awt.Color(47, 55, 76));
        V39.setMaximum(10);
        V39.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V39StateChanged(evt);
            }
        });

        S39.setBackground(new java.awt.Color(69, 93, 220));
        S39.setForeground(new java.awt.Color(186, 195, 242));
        S39.setText("SOLO");
        S39.setContentAreaFilled(false);
        S39.setOpaque(true);
        S39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S39ActionPerformed(evt);
            }
        });

        I40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C40.setBackground(new java.awt.Color(102, 102, 102));
        C40.setForeground(new java.awt.Color(255, 255, 255));
        C40.setText("jButton1");
        C40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C40ActionPerformed(evt);
            }
        });

        V40.setBackground(new java.awt.Color(47, 55, 76));
        V40.setMaximum(10);
        V40.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V40StateChanged(evt);
            }
        });

        S40.setBackground(new java.awt.Color(69, 93, 220));
        S40.setForeground(new java.awt.Color(186, 195, 242));
        S40.setText("SOLO");
        S40.setContentAreaFilled(false);
        S40.setOpaque(true);
        S40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S40ActionPerformed(evt);
            }
        });

        I41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C41.setBackground(new java.awt.Color(102, 102, 102));
        C41.setForeground(new java.awt.Color(255, 255, 255));
        C41.setText("jButton1");
        C41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C41ActionPerformed(evt);
            }
        });

        V41.setBackground(new java.awt.Color(47, 55, 76));
        V41.setMaximum(10);
        V41.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V41StateChanged(evt);
            }
        });

        S41.setBackground(new java.awt.Color(69, 93, 220));
        S41.setForeground(new java.awt.Color(186, 195, 242));
        S41.setText("SOLO");
        S41.setContentAreaFilled(false);
        S41.setOpaque(true);
        S41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S41ActionPerformed(evt);
            }
        });

        C42.setBackground(new java.awt.Color(102, 102, 102));
        C42.setForeground(new java.awt.Color(255, 255, 255));
        C42.setText("jButton1");
        C42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C42ActionPerformed(evt);
            }
        });

        S42.setBackground(new java.awt.Color(69, 93, 220));
        S42.setForeground(new java.awt.Color(186, 195, 242));
        S42.setText("SOLO");
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
        V42.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V42StateChanged(evt);
            }
        });

        C43.setBackground(new java.awt.Color(102, 102, 102));
        C43.setForeground(new java.awt.Color(255, 255, 255));
        C43.setText("jButton1");
        C43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C43ActionPerformed(evt);
            }
        });

        S43.setBackground(new java.awt.Color(69, 93, 220));
        S43.setForeground(new java.awt.Color(186, 195, 242));
        S43.setText("SOLO");
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
        V43.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V43StateChanged(evt);
            }
        });

        C44.setBackground(new java.awt.Color(102, 102, 102));
        C44.setForeground(new java.awt.Color(255, 255, 255));
        C44.setText("jButton1");
        C44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C44ActionPerformed(evt);
            }
        });

        S44.setBackground(new java.awt.Color(69, 93, 220));
        S44.setForeground(new java.awt.Color(186, 195, 242));
        S44.setText("SOLO");
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
        V44.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V44StateChanged(evt);
            }
        });

        I45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C45.setBackground(new java.awt.Color(102, 102, 102));
        C45.setForeground(new java.awt.Color(255, 255, 255));
        C45.setText("jButton1");
        C45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C45ActionPerformed(evt);
            }
        });

        V45.setBackground(new java.awt.Color(47, 55, 76));
        V45.setMaximum(10);
        V45.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V45StateChanged(evt);
            }
        });

        S45.setBackground(new java.awt.Color(69, 93, 220));
        S45.setForeground(new java.awt.Color(186, 195, 242));
        S45.setText("SOLO");
        S45.setContentAreaFilled(false);
        S45.setOpaque(true);
        S45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S45ActionPerformed(evt);
            }
        });

        I46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C46.setBackground(new java.awt.Color(102, 102, 102));
        C46.setForeground(new java.awt.Color(255, 255, 255));
        C46.setText("jButton1");
        C46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C46ActionPerformed(evt);
            }
        });

        V46.setBackground(new java.awt.Color(47, 55, 76));
        V46.setMaximum(10);
        V46.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V46StateChanged(evt);
            }
        });

        S46.setBackground(new java.awt.Color(69, 93, 220));
        S46.setForeground(new java.awt.Color(186, 195, 242));
        S46.setText("SOLO");
        S46.setContentAreaFilled(false);
        S46.setOpaque(true);
        S46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S46ActionPerformed(evt);
            }
        });

        I47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C47.setBackground(new java.awt.Color(102, 102, 102));
        C47.setForeground(new java.awt.Color(255, 255, 255));
        C47.setText("jButton1");
        C47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C47ActionPerformed(evt);
            }
        });

        V47.setBackground(new java.awt.Color(47, 55, 76));
        V47.setMaximum(10);
        V47.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V47StateChanged(evt);
            }
        });

        S47.setBackground(new java.awt.Color(69, 93, 220));
        S47.setForeground(new java.awt.Color(186, 195, 242));
        S47.setText("SOLO");
        S47.setContentAreaFilled(false);
        S47.setOpaque(true);
        S47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S47ActionPerformed(evt);
            }
        });

        I48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C48.setBackground(new java.awt.Color(102, 102, 102));
        C48.setForeground(new java.awt.Color(255, 255, 255));
        C48.setText("jButton1");
        C48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C48ActionPerformed(evt);
            }
        });

        V48.setBackground(new java.awt.Color(47, 55, 76));
        V48.setMaximum(10);
        V48.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V48StateChanged(evt);
            }
        });

        S48.setBackground(new java.awt.Color(69, 93, 220));
        S48.setForeground(new java.awt.Color(186, 195, 242));
        S48.setText("SOLO");
        S48.setContentAreaFilled(false);
        S48.setOpaque(true);
        S48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S48ActionPerformed(evt);
            }
        });

        I49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C49.setBackground(new java.awt.Color(102, 102, 102));
        C49.setForeground(new java.awt.Color(255, 255, 255));
        C49.setText("jButton1");
        C49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C49ActionPerformed(evt);
            }
        });

        V49.setBackground(new java.awt.Color(47, 55, 76));
        V49.setMaximum(10);
        V49.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V49StateChanged(evt);
            }
        });

        S49.setBackground(new java.awt.Color(69, 93, 220));
        S49.setForeground(new java.awt.Color(186, 195, 242));
        S49.setText("SOLO");
        S49.setContentAreaFilled(false);
        S49.setOpaque(true);
        S49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S49ActionPerformed(evt);
            }
        });

        I50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C50.setBackground(new java.awt.Color(102, 102, 102));
        C50.setForeground(new java.awt.Color(255, 255, 255));
        C50.setText("jButton1");
        C50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C50ActionPerformed(evt);
            }
        });

        V50.setBackground(new java.awt.Color(47, 55, 76));
        V50.setMaximum(10);
        V50.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V50StateChanged(evt);
            }
        });

        S50.setBackground(new java.awt.Color(69, 93, 220));
        S50.setForeground(new java.awt.Color(186, 195, 242));
        S50.setText("SOLO");
        S50.setContentAreaFilled(false);
        S50.setOpaque(true);
        S50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S50ActionPerformed(evt);
            }
        });

        I51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C51.setBackground(new java.awt.Color(102, 102, 102));
        C51.setForeground(new java.awt.Color(255, 255, 255));
        C51.setText("jButton1");
        C51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C51ActionPerformed(evt);
            }
        });

        V51.setBackground(new java.awt.Color(47, 55, 76));
        V51.setMaximum(10);
        V51.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V51StateChanged(evt);
            }
        });

        S51.setBackground(new java.awt.Color(69, 93, 220));
        S51.setForeground(new java.awt.Color(186, 195, 242));
        S51.setText("SOLO");
        S51.setContentAreaFilled(false);
        S51.setOpaque(true);
        S51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S51ActionPerformed(evt);
            }
        });

        I52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C52.setBackground(new java.awt.Color(102, 102, 102));
        C52.setForeground(new java.awt.Color(255, 255, 255));
        C52.setText("jButton1");
        C52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C52ActionPerformed(evt);
            }
        });

        V52.setBackground(new java.awt.Color(47, 55, 76));
        V52.setMaximum(10);
        V52.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V52StateChanged(evt);
            }
        });

        S52.setBackground(new java.awt.Color(69, 93, 220));
        S52.setForeground(new java.awt.Color(186, 195, 242));
        S52.setText("SOLO");
        S52.setContentAreaFilled(false);
        S52.setOpaque(true);
        S52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S52ActionPerformed(evt);
            }
        });

        I53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C53.setBackground(new java.awt.Color(102, 102, 102));
        C53.setForeground(new java.awt.Color(255, 255, 255));
        C53.setText("jButton1");
        C53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C53ActionPerformed(evt);
            }
        });

        V53.setBackground(new java.awt.Color(47, 55, 76));
        V53.setMaximum(10);
        V53.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V53StateChanged(evt);
            }
        });

        S53.setBackground(new java.awt.Color(69, 93, 220));
        S53.setForeground(new java.awt.Color(186, 195, 242));
        S53.setText("SOLO");
        S53.setContentAreaFilled(false);
        S53.setOpaque(true);
        S53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S53ActionPerformed(evt);
            }
        });

        S54.setBackground(new java.awt.Color(69, 93, 220));
        S54.setForeground(new java.awt.Color(186, 195, 242));
        S54.setText("SOLO");
        S54.setContentAreaFilled(false);
        S54.setOpaque(true);
        S54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S54ActionPerformed(evt);
            }
        });

        V54.setBackground(new java.awt.Color(47, 55, 76));
        V54.setMaximum(10);
        V54.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V54StateChanged(evt);
            }
        });

        C54.setBackground(new java.awt.Color(102, 102, 102));
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
        S55.setText("SOLO");
        S55.setContentAreaFilled(false);
        S55.setOpaque(true);
        S55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S55ActionPerformed(evt);
            }
        });

        V55.setBackground(new java.awt.Color(47, 55, 76));
        V55.setMaximum(10);
        V55.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V55StateChanged(evt);
            }
        });

        C55.setBackground(new java.awt.Color(102, 102, 102));
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
        S56.setText("SOLO");
        S56.setContentAreaFilled(false);
        S56.setOpaque(true);
        S56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S56ActionPerformed(evt);
            }
        });

        V56.setBackground(new java.awt.Color(47, 55, 76));
        V56.setMaximum(10);
        V56.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V56StateChanged(evt);
            }
        });

        C56.setBackground(new java.awt.Color(102, 102, 102));
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
        C57.setForeground(new java.awt.Color(255, 255, 255));
        C57.setText("jButton1");
        C57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C57ActionPerformed(evt);
            }
        });

        V57.setBackground(new java.awt.Color(47, 55, 76));
        V57.setMaximum(10);
        V57.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V57StateChanged(evt);
            }
        });

        S57.setBackground(new java.awt.Color(69, 93, 220));
        S57.setForeground(new java.awt.Color(186, 195, 242));
        S57.setText("SOLO");
        S57.setContentAreaFilled(false);
        S57.setOpaque(true);
        S57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S57ActionPerformed(evt);
            }
        });

        I58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C58.setBackground(new java.awt.Color(102, 102, 102));
        C58.setForeground(new java.awt.Color(255, 255, 255));
        C58.setText("jButton1");
        C58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C58ActionPerformed(evt);
            }
        });

        V58.setBackground(new java.awt.Color(47, 55, 76));
        V58.setMaximum(10);
        V58.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V58StateChanged(evt);
            }
        });

        S58.setBackground(new java.awt.Color(69, 93, 220));
        S58.setForeground(new java.awt.Color(186, 195, 242));
        S58.setText("SOLO");
        S58.setContentAreaFilled(false);
        S58.setOpaque(true);
        S58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S58ActionPerformed(evt);
            }
        });

        I59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C59.setBackground(new java.awt.Color(102, 102, 102));
        C59.setForeground(new java.awt.Color(255, 255, 255));
        C59.setText("jButton1");
        C59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C59ActionPerformed(evt);
            }
        });

        V59.setBackground(new java.awt.Color(47, 55, 76));
        V59.setMaximum(10);
        V59.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V59StateChanged(evt);
            }
        });

        S59.setBackground(new java.awt.Color(69, 93, 220));
        S59.setForeground(new java.awt.Color(186, 195, 242));
        S59.setText("SOLO");
        S59.setContentAreaFilled(false);
        S59.setOpaque(true);
        S59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S59ActionPerformed(evt);
            }
        });

        I60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C60.setBackground(new java.awt.Color(102, 102, 102));
        C60.setForeground(new java.awt.Color(255, 255, 255));
        C60.setText("jButton1");
        C60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C60ActionPerformed(evt);
            }
        });

        V60.setBackground(new java.awt.Color(47, 55, 76));
        V60.setMaximum(10);
        V60.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V60StateChanged(evt);
            }
        });

        S60.setBackground(new java.awt.Color(69, 93, 220));
        S60.setForeground(new java.awt.Color(186, 195, 242));
        S60.setText("SOLO");
        S60.setContentAreaFilled(false);
        S60.setOpaque(true);
        S60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S60ActionPerformed(evt);
            }
        });

        I61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C61.setBackground(new java.awt.Color(102, 102, 102));
        C61.setForeground(new java.awt.Color(255, 255, 255));
        C61.setText("jButton1");
        C61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C61ActionPerformed(evt);
            }
        });

        V61.setBackground(new java.awt.Color(47, 55, 76));
        V61.setMaximum(10);
        V61.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V61StateChanged(evt);
            }
        });

        S61.setBackground(new java.awt.Color(69, 93, 220));
        S61.setForeground(new java.awt.Color(186, 195, 242));
        S61.setText("SOLO");
        S61.setContentAreaFilled(false);
        S61.setOpaque(true);
        S61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S61ActionPerformed(evt);
            }
        });

        I62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C62.setBackground(new java.awt.Color(102, 102, 102));
        C62.setForeground(new java.awt.Color(255, 255, 255));
        C62.setText("jButton1");
        C62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C62ActionPerformed(evt);
            }
        });

        V62.setBackground(new java.awt.Color(47, 55, 76));
        V62.setMaximum(10);
        V62.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V62StateChanged(evt);
            }
        });

        S62.setBackground(new java.awt.Color(69, 93, 220));
        S62.setForeground(new java.awt.Color(186, 195, 242));
        S62.setText("SOLO");
        S62.setContentAreaFilled(false);
        S62.setOpaque(true);
        S62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S62ActionPerformed(evt);
            }
        });

        I63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C63.setBackground(new java.awt.Color(102, 102, 102));
        C63.setForeground(new java.awt.Color(255, 255, 255));
        C63.setText("jButton1");
        C63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C63ActionPerformed(evt);
            }
        });

        V63.setBackground(new java.awt.Color(47, 55, 76));
        V63.setMaximum(10);
        V63.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V63StateChanged(evt);
            }
        });

        S63.setBackground(new java.awt.Color(69, 93, 220));
        S63.setForeground(new java.awt.Color(186, 195, 242));
        S63.setText("SOLO");
        S63.setContentAreaFilled(false);
        S63.setOpaque(true);
        S63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S63ActionPerformed(evt);
            }
        });

        I64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C64.setBackground(new java.awt.Color(102, 102, 102));
        C64.setForeground(new java.awt.Color(255, 255, 255));
        C64.setText("jButton1");
        C64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C64ActionPerformed(evt);
            }
        });

        V64.setBackground(new java.awt.Color(47, 55, 76));
        V64.setMaximum(10);
        V64.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V64StateChanged(evt);
            }
        });

        S64.setBackground(new java.awt.Color(69, 93, 220));
        S64.setForeground(new java.awt.Color(186, 195, 242));
        S64.setText("SOLO");
        S64.setContentAreaFilled(false);
        S64.setOpaque(true);
        S64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S64ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(I1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(S1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(I9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(S9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(I17, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V17, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C17, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(S17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(I25, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V25, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C25, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(S25)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(I3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(S3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(I11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(S11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(I19, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V19, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C19, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(S19)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(I27, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V27, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C27, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(S27))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(I2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(S2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(I10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(S10)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(I18, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V18, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C18, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(S18)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(I26, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V26, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C26, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(S26)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(I4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(S4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(I12, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V12, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C12, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(S12)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(I20, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V20, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C20, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(S20)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(I28, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V28, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C28, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(S28))))
                            .addComponent(I7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I33, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V33, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C33, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I41, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V41, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C41, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I49, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V49, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C49, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I57, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V57, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C57, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(S7)
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S8)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S15)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I16, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V16, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C16, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S16)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I23, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V23, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C23, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S23)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I24, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V24, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C24, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S24)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I31, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V31, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C31, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S31)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I32, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V32, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C32, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S32))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(S5)
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I13, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V13, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C13, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S13)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I21, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V21, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C21, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S21)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I29, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S6)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V29, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C29, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S29)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S14)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I22, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V22, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C22, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S22)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I30, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V30, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C30, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S30))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(S33)
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I34, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V34, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C34, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S34)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I35, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V35, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C35, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S35)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I36, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V36, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C36, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S36)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I37, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V37, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C37, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S37)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I38, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V38, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C38, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S38)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I39, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V39, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C39, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S39)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I40, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V40, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C40, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S40))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(S41)
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I42, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V42, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C42, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S42)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I43, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V43, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C43, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S43)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I44, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V44, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C44, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S44)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I45, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V45, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C45, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S45)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I46, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V46, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C46, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S46)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I47, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V47, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C47, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S47)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I48, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V48, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C48, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S48))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(S49)
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I50, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V50, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C50, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S50)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(I51, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(V51, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(40, 40, 40)
                                                        .addComponent(S51)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(I52, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(V52, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(40, 40, 40)
                                                        .addComponent(S52))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(C51, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(C52, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I53, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V53, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C53, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S53)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I54, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V54, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C54, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S54)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I55, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V55, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C55, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S55)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I56, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V56, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C56, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S56))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(S57)
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I58, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V58, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C58, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S58)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I59, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V59, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C59, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S59)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I60, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V60, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C60, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S60)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I61, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V61, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C61, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S61)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I62, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V62, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C62, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S62)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I63, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V63, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C63, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S63)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(I64, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(V64, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(C64, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(S64)))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I27, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C27, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I19, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C19, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I11, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I25, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C25, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(I9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(V1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(S1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(S9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I17, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C17, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I10, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I18, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C18, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I26, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C26, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I12, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I20, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C20, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I28, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C28, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I13, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I21, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C21, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I29, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C29, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I14, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I22, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C22, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I30, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C30, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I8, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I15, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I16, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I23, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C23, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I24, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C24, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I31, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C31, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I32, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C32, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I33, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C33, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I34, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C34, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I35, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C35, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I36, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C36, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I37, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C37, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I38, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C38, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I39, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C39, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I40, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C40, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I41, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C41, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S41, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I42, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C42, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S42, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I43, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C43, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S43, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I44, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C44, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S44, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I45, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C45, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S45, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I46, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C46, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S46, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I47, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C47, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S47, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I48, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C48, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S48, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I49, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C49, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S49, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I50, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C50, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S50, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I51, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(C51, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C52, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(V51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S51, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I52, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(V52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S52, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I53, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C53, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S53, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I54, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C54, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S54, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I55, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C55, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S55, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I56, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C56, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S56, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I57, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C57, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S57, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I58, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C58, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S58, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I59, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C59, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S59, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I60, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C60, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S60, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I61, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C61, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S61, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I62, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C62, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S62, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I63, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C63, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S63, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I64, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(C64, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(V64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(S64, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton C1;
    private javax.swing.JButton C10;
    private javax.swing.JButton C11;
    private javax.swing.JButton C12;
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
    private javax.swing.JButton C7;
    private javax.swing.JButton C8;
    private javax.swing.JButton C9;
    private javax.swing.JLabel I1;
    private javax.swing.JLabel I10;
    private javax.swing.JLabel I11;
    private javax.swing.JLabel I12;
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
    private javax.swing.JLabel I7;
    private javax.swing.JLabel I8;
    private javax.swing.JLabel I9;
    private javax.swing.JToggleButton S1;
    private javax.swing.JToggleButton S10;
    private javax.swing.JToggleButton S11;
    private javax.swing.JToggleButton S12;
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
    private javax.swing.JToggleButton S7;
    private javax.swing.JToggleButton S8;
    private javax.swing.JToggleButton S9;
    private javax.swing.JSlider V1;
    private javax.swing.JSlider V10;
    private javax.swing.JSlider V11;
    private javax.swing.JSlider V12;
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
    private javax.swing.JSlider V7;
    private javax.swing.JSlider V8;
    private javax.swing.JSlider V9;
    // End of variables declaration//GEN-END:variables
}
