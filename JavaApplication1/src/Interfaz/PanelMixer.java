/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import Negocio.ThreadAudio;
import Negocio.ThreadSocket;
import Datos.Configuracion;
import Datos.XmlRead;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import javafx.scene.control.RadioButton;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
/**
 *
 * @author leone
 */
public class PanelMixer extends javax.swing.JPanel {
    
    private ArrayList<Integer> ganancias;
    private ArrayList<String> canales;
    private int Frecuencia;
    private int Muestra;
  
    private int alternarSOLO = 0;
    
    private  int alternar0=0;
    private  int alternar1=0;
    private  int alternar2=0;
    private  int alternar3=0;
    private  int alternar4=0;
    private  int alternar5=0;
    private  int alternar6=0;
    private  int alternar7=0;
    private  int alternar8=0;
    private  int alternar9=0;
    private  int alternar10=0;
    private  int alternar11=0;
    private  int alternar12=0;
    private  int alternar13=0;
    private  int alternar14=0;
    private  int alternar15=0;
    private  int alternar16=0;
    private  int alternar17=0;
    private  int alternar18=0;
    private  int alternar19=0;
    private  int alternar20=0;
    private  int alternar21=0;
    private  int alternar22=0;
    private  int alternar23=0;
    private  int alternar24=0;
    private  int alternar25=0;
    private  int alternar26=0;
    private  int alternar27=0;
    private  int alternar28=0;
    private  int alternar29=0;
    private  int alternar30=0;
    private  int alternar31=0;
    private  int alternar32=0;
    
    
    private ThreadAudio audio0;
    private ThreadAudio audio1;
    private ThreadAudio audio2;       
    private ThreadAudio audio3;
    private ThreadAudio audio4;
    private ThreadAudio audio5;
    private ThreadAudio audio6;
    private ThreadAudio audio7;
    private ThreadAudio audio8;
    private ThreadAudio audio9;
    private ThreadAudio audio10;
    private ThreadAudio audio11;
    private ThreadAudio audio12;
    private ThreadAudio audio13;
    private ThreadAudio audio14;
    private ThreadAudio audio15;
    private ThreadAudio audio16;
    private ThreadAudio audio17;
    private ThreadAudio audio18;
    private ThreadAudio audio19;
    private ThreadAudio audio20;
    private ThreadAudio audio21;
    private ThreadAudio audio22;
    private ThreadAudio audio23;
    private ThreadAudio audio24;
    private ThreadAudio audio25;
    private ThreadAudio audio26;
    private ThreadAudio audio27;
    private ThreadAudio audio28;
    private ThreadAudio audio29;
    private ThreadAudio audio30;
    private ThreadAudio audio31;
    private ThreadAudio audio32;
    
    private ThreadAudio[] audio = new ThreadAudio[32];
    
    private boolean LastAtivate0=false;
    private boolean LastAtivate1=false;
    private boolean LastAtivate2=false;
    private boolean LastAtivate3=false;
    private boolean LastAtivate4=false;
    private boolean LastAtivate5=false;
    private boolean LastAtivate6=false;
    private boolean LastAtivate7=false;
    private boolean LastAtivate8=false;
    private boolean LastAtivate9=false;
    private boolean LastAtivate10=false;
    private boolean LastAtivate11=false;
    private boolean LastAtivate12=false;
    private boolean LastAtivate13=false;
    private boolean LastAtivate14=false;
    private boolean LastAtivate15=false;
    private boolean LastAtivate16=false;
    private boolean LastAtivate17=false;
    private boolean LastAtivate18=false;
    private boolean LastAtivate19=false;
    private boolean LastAtivate20=false;
    private boolean LastAtivate21=false;
    private boolean LastAtivate22=false;
    private boolean LastAtivate23=false;
    private boolean LastAtivate24=false;
    private boolean LastAtivate25=false;
    private boolean LastAtivate26=false;
    private boolean LastAtivate27=false;
    private boolean LastAtivate28=false;
    private boolean LastAtivate29=false;
    private boolean LastAtivate30=false;
    private boolean LastAtivate31=false;
    
    private boolean audioforceddown0 = false;
    private boolean audioforceddown1 = false;
    private boolean audioforceddown2 = false;
    private boolean audioforceddown3 = false;
    private boolean audioforceddown4 = false;
    private boolean audioforceddown5 = false;
    private boolean audioforceddown6 = false;
    private boolean audioforceddown7 = false;
    private boolean audioforceddown8 = false;
    private boolean audioforceddown9 = false;
    private boolean audioforceddown10 = false;
    private boolean audioforceddown11 = false;
    private boolean audioforceddown12 = false;
    private boolean audioforceddown13 = false;
    private boolean audioforceddown14 = false;
    private boolean audioforceddown15 = false;
    private boolean audioforceddown16 = false;
    private boolean audioforceddown17 = false;
    private boolean audioforceddown18 = false;
    private boolean audioforceddown19 = false;
    private boolean audioforceddown20 = false;
    private boolean audioforceddown21 = false;
    private boolean audioforceddown22 = false;
    private boolean audioforceddown23 = false;
    private boolean audioforceddown24 = false;
    private boolean audioforceddown25 = false;
    private boolean audioforceddown26 = false;
    private boolean audioforceddown27 = false;
    private boolean audioforceddown28 = false;
    private boolean audioforceddown29 = false;
    private boolean audioforceddown30 = false;
    private boolean audioforceddown31 = false;
    
    
    public Boolean[] var = new Boolean[65];
   
    
   
    
    private Configuracion Conf;
    private XmlRead X;
    private Panel P;
    
    private ThreadSocket socket;
    /**
     * Creates new form NewJPanel
     */
    public PanelMixer(Panel P) {
        this.P=P;
        initComponents();
        X=new XmlRead();
       
        Conf= X.Read("config.xml");
        canales=new ArrayList<String>();
        ganancias=new ArrayList<Integer>();
        for(int x=0;x<65;x++)
            var[x]=false;
        
        for(int i=0;i<32;i++){
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
        
        
        
        
        LoadImages(Conf);
        LoadAlias(Conf);
        
        
        ////instanciar hilosocket
         
         //socket.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
    
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
      
       
       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        C1 = new javax.swing.JButton();
        V1 = new javax.swing.JSlider();
        C2 = new javax.swing.JButton();
        V2 = new javax.swing.JSlider();
        C3 = new javax.swing.JButton();
        V3 = new javax.swing.JSlider();
        C4 = new javax.swing.JButton();
        V4 = new javax.swing.JSlider();
        C9 = new javax.swing.JButton();
        V9 = new javax.swing.JSlider();
        V12 = new javax.swing.JSlider();
        C12 = new javax.swing.JButton();
        V11 = new javax.swing.JSlider();
        C11 = new javax.swing.JButton();
        V10 = new javax.swing.JSlider();
        C10 = new javax.swing.JButton();
        C13 = new javax.swing.JButton();
        V13 = new javax.swing.JSlider();
        V14 = new javax.swing.JSlider();
        C14 = new javax.swing.JButton();
        C5 = new javax.swing.JButton();
        V5 = new javax.swing.JSlider();
        V6 = new javax.swing.JSlider();
        C6 = new javax.swing.JButton();
        C17 = new javax.swing.JButton();
        V17 = new javax.swing.JSlider();
        V20 = new javax.swing.JSlider();
        C20 = new javax.swing.JButton();
        V19 = new javax.swing.JSlider();
        C19 = new javax.swing.JButton();
        V18 = new javax.swing.JSlider();
        C18 = new javax.swing.JButton();
        C21 = new javax.swing.JButton();
        V21 = new javax.swing.JSlider();
        V22 = new javax.swing.JSlider();
        C22 = new javax.swing.JButton();
        C25 = new javax.swing.JButton();
        V25 = new javax.swing.JSlider();
        V28 = new javax.swing.JSlider();
        C28 = new javax.swing.JButton();
        V27 = new javax.swing.JSlider();
        C27 = new javax.swing.JButton();
        V26 = new javax.swing.JSlider();
        C26 = new javax.swing.JButton();
        C29 = new javax.swing.JButton();
        V29 = new javax.swing.JSlider();
        V30 = new javax.swing.JSlider();
        C30 = new javax.swing.JButton();
        I25 = new javax.swing.JLabel();
        I26 = new javax.swing.JLabel();
        I9 = new javax.swing.JLabel();
        I17 = new javax.swing.JLabel();
        I1 = new javax.swing.JLabel();
        I27 = new javax.swing.JLabel();
        I10 = new javax.swing.JLabel();
        I18 = new javax.swing.JLabel();
        I2 = new javax.swing.JLabel();
        I12 = new javax.swing.JLabel();
        I11 = new javax.swing.JLabel();
        I19 = new javax.swing.JLabel();
        I3 = new javax.swing.JLabel();
        I20 = new javax.swing.JLabel();
        I28 = new javax.swing.JLabel();
        I4 = new javax.swing.JLabel();
        I29 = new javax.swing.JLabel();
        I13 = new javax.swing.JLabel();
        I21 = new javax.swing.JLabel();
        I5 = new javax.swing.JLabel();
        I22 = new javax.swing.JLabel();
        I30 = new javax.swing.JLabel();
        I6 = new javax.swing.JLabel();
        I14 = new javax.swing.JLabel();
        I15 = new javax.swing.JLabel();
        I7 = new javax.swing.JLabel();
        I8 = new javax.swing.JLabel();
        I16 = new javax.swing.JLabel();
        I24 = new javax.swing.JLabel();
        I23 = new javax.swing.JLabel();
        I31 = new javax.swing.JLabel();
        I32 = new javax.swing.JLabel();
        C7 = new javax.swing.JButton();
        C8 = new javax.swing.JButton();
        C15 = new javax.swing.JButton();
        C16 = new javax.swing.JButton();
        C23 = new javax.swing.JButton();
        C24 = new javax.swing.JButton();
        C31 = new javax.swing.JButton();
        C32 = new javax.swing.JButton();
        V7 = new javax.swing.JSlider();
        V8 = new javax.swing.JSlider();
        V15 = new javax.swing.JSlider();
        V16 = new javax.swing.JSlider();
        V23 = new javax.swing.JSlider();
        V24 = new javax.swing.JSlider();
        V31 = new javax.swing.JSlider();
        V32 = new javax.swing.JSlider();
        S1 = new javax.swing.JToggleButton();
        S9 = new javax.swing.JToggleButton();
        S17 = new javax.swing.JToggleButton();
        S25 = new javax.swing.JToggleButton();
        S3 = new javax.swing.JToggleButton();
        S11 = new javax.swing.JToggleButton();
        S19 = new javax.swing.JToggleButton();
        S27 = new javax.swing.JToggleButton();
        S2 = new javax.swing.JToggleButton();
        S10 = new javax.swing.JToggleButton();
        S18 = new javax.swing.JToggleButton();
        S26 = new javax.swing.JToggleButton();
        S4 = new javax.swing.JToggleButton();
        S12 = new javax.swing.JToggleButton();
        S20 = new javax.swing.JToggleButton();
        S28 = new javax.swing.JToggleButton();
        S5 = new javax.swing.JToggleButton();
        S13 = new javax.swing.JToggleButton();
        S21 = new javax.swing.JToggleButton();
        S6 = new javax.swing.JToggleButton();
        S29 = new javax.swing.JToggleButton();
        S14 = new javax.swing.JToggleButton();
        S22 = new javax.swing.JToggleButton();
        S30 = new javax.swing.JToggleButton();
        S7 = new javax.swing.JToggleButton();
        S8 = new javax.swing.JToggleButton();
        S15 = new javax.swing.JToggleButton();
        S16 = new javax.swing.JToggleButton();
        S23 = new javax.swing.JToggleButton();
        S24 = new javax.swing.JToggleButton();
        S31 = new javax.swing.JToggleButton();
        S32 = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(4, 5, 8));

        C1.setBackground(new java.awt.Color(102, 102, 102));
        C1.setFont(new java.awt.Font("Montserrat", 0, 21)); // NOI18N
        C1.setForeground(new java.awt.Color(255, 255, 255));
        C1.setText("CHANNEL 1");
        C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C1ActionPerformed(evt);
            }
        });

        V1.setMaximum(10);
        V1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V1StateChanged(evt);
            }
        });

        C2.setBackground(new java.awt.Color(102, 102, 102));
        C2.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C2.setForeground(new java.awt.Color(255, 255, 255));
        C2.setText("CHANNEL 2");
        C2.setMaximumSize(new java.awt.Dimension(151, 35));
        C2.setMinimumSize(new java.awt.Dimension(151, 35));
        C2.setPreferredSize(new java.awt.Dimension(151, 35));
        C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C2ActionPerformed(evt);
            }
        });

        V2.setMaximum(10);
        V2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V2StateChanged(evt);
            }
        });

        C3.setBackground(new java.awt.Color(102, 102, 102));
        C3.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C3.setForeground(new java.awt.Color(255, 255, 255));
        C3.setText("CHANNEL 3");
        C3.setMaximumSize(new java.awt.Dimension(151, 35));
        C3.setMinimumSize(new java.awt.Dimension(151, 35));
        C3.setPreferredSize(new java.awt.Dimension(151, 35));
        C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3ActionPerformed(evt);
            }
        });

        V3.setMaximum(10);
        V3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V3StateChanged(evt);
            }
        });

        C4.setBackground(new java.awt.Color(102, 102, 102));
        C4.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C4.setForeground(new java.awt.Color(255, 255, 255));
        C4.setText("CHANNEL 4");
        C4.setMaximumSize(new java.awt.Dimension(151, 35));
        C4.setMinimumSize(new java.awt.Dimension(151, 35));
        C4.setPreferredSize(new java.awt.Dimension(151, 35));
        C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4ActionPerformed(evt);
            }
        });

        V4.setMaximum(10);
        V4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V4StateChanged(evt);
            }
        });

        C9.setBackground(new java.awt.Color(102, 102, 102));
        C9.setFont(new java.awt.Font("Montserrat", 0, 21)); // NOI18N
        C9.setForeground(new java.awt.Color(255, 255, 255));
        C9.setText("CHANNEL 9");
        C9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C9ActionPerformed(evt);
            }
        });

        V9.setMaximum(10);
        V9.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V9StateChanged(evt);
            }
        });

        V12.setMaximum(10);
        V12.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V12StateChanged(evt);
            }
        });

        C12.setBackground(new java.awt.Color(102, 102, 102));
        C12.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C12.setForeground(new java.awt.Color(255, 255, 255));
        C12.setText("CHANNEL 12");
        C12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C12ActionPerformed(evt);
            }
        });

        V11.setMaximum(10);
        V11.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V11StateChanged(evt);
            }
        });

        C11.setBackground(new java.awt.Color(102, 102, 102));
        C11.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C11.setForeground(new java.awt.Color(255, 255, 255));
        C11.setText("CHANNEL 11");
        C11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C11ActionPerformed(evt);
            }
        });

        V10.setMaximum(10);
        V10.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V10StateChanged(evt);
            }
        });

        C10.setBackground(new java.awt.Color(102, 102, 102));
        C10.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C10.setForeground(new java.awt.Color(255, 255, 255));
        C10.setText("CHANNEL 10");
        C10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C10ActionPerformed(evt);
            }
        });

        C13.setBackground(new java.awt.Color(102, 102, 102));
        C13.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C13.setForeground(new java.awt.Color(255, 255, 255));
        C13.setText("CHANNEL 13");
        C13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C13ActionPerformed(evt);
            }
        });

        V13.setMaximum(10);
        V13.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V13StateChanged(evt);
            }
        });

        V14.setMaximum(10);
        V14.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V14StateChanged(evt);
            }
        });

        C14.setBackground(new java.awt.Color(102, 102, 102));
        C14.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C14.setForeground(new java.awt.Color(255, 255, 255));
        C14.setText("CHANNEL 14");
        C14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C14ActionPerformed(evt);
            }
        });

        C5.setBackground(new java.awt.Color(102, 102, 102));
        C5.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C5.setForeground(new java.awt.Color(255, 255, 255));
        C5.setText("CHANNEL5");
        C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C5ActionPerformed(evt);
            }
        });

        V5.setMaximum(10);
        V5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V5StateChanged(evt);
            }
        });

        V6.setMaximum(10);
        V6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V6StateChanged(evt);
            }
        });

        C6.setBackground(new java.awt.Color(102, 102, 102));
        C6.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C6.setForeground(new java.awt.Color(255, 255, 255));
        C6.setText("CHANNEL 6");
        C6.setMaximumSize(new java.awt.Dimension(151, 35));
        C6.setMinimumSize(new java.awt.Dimension(151, 35));
        C6.setPreferredSize(new java.awt.Dimension(151, 35));
        C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6ActionPerformed(evt);
            }
        });

        C17.setBackground(new java.awt.Color(102, 102, 102));
        C17.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C17.setForeground(new java.awt.Color(255, 255, 255));
        C17.setText("CHANNEL 17");
        C17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C17ActionPerformed(evt);
            }
        });

        V17.setMaximum(10);
        V17.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V17StateChanged(evt);
            }
        });

        V20.setMaximum(10);
        V20.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V20StateChanged(evt);
            }
        });

        C20.setBackground(new java.awt.Color(102, 102, 102));
        C20.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C20.setForeground(new java.awt.Color(255, 255, 255));
        C20.setText("CHANNEL 20");
        C20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C20ActionPerformed(evt);
            }
        });

        V19.setMaximum(10);
        V19.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V19StateChanged(evt);
            }
        });

        C19.setBackground(new java.awt.Color(102, 102, 102));
        C19.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C19.setForeground(new java.awt.Color(255, 255, 255));
        C19.setText("CHANNEL 19");
        C19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C19ActionPerformed(evt);
            }
        });

        V18.setMaximum(10);
        V18.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V18StateChanged(evt);
            }
        });

        C18.setBackground(new java.awt.Color(102, 102, 102));
        C18.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C18.setForeground(new java.awt.Color(255, 255, 255));
        C18.setText("CHANNEL 18");
        C18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C18ActionPerformed(evt);
            }
        });

        C21.setBackground(new java.awt.Color(102, 102, 102));
        C21.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C21.setForeground(new java.awt.Color(255, 255, 255));
        C21.setText("CHANNEL 21");
        C21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C21ActionPerformed(evt);
            }
        });

        V21.setMaximum(10);
        V21.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V21StateChanged(evt);
            }
        });

        V22.setMaximum(10);
        V22.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V22StateChanged(evt);
            }
        });

        C22.setBackground(new java.awt.Color(102, 102, 102));
        C22.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C22.setForeground(new java.awt.Color(255, 255, 255));
        C22.setText("CHANNEL 22");
        C22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C22ActionPerformed(evt);
            }
        });

        C25.setBackground(new java.awt.Color(102, 102, 102));
        C25.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C25.setForeground(new java.awt.Color(255, 255, 255));
        C25.setText("CHANNEL 25");
        C25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C25ActionPerformed(evt);
            }
        });

        V25.setMaximum(10);
        V25.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V25StateChanged(evt);
            }
        });

        V28.setMaximum(10);
        V28.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V28StateChanged(evt);
            }
        });

        C28.setBackground(new java.awt.Color(102, 102, 102));
        C28.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C28.setForeground(new java.awt.Color(255, 255, 255));
        C28.setText("CHANNEL 28");
        C28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C28ActionPerformed(evt);
            }
        });

        V27.setMaximum(10);
        V27.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V27StateChanged(evt);
            }
        });

        C27.setBackground(new java.awt.Color(102, 102, 102));
        C27.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C27.setForeground(new java.awt.Color(255, 255, 255));
        C27.setText("CHANNEL 27");
        C27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C27ActionPerformed(evt);
            }
        });

        V26.setMaximum(10);
        V26.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V26StateChanged(evt);
            }
        });

        C26.setBackground(new java.awt.Color(102, 102, 102));
        C26.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C26.setForeground(new java.awt.Color(255, 255, 255));
        C26.setText("CHANNEL 26");
        C26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C26ActionPerformed(evt);
            }
        });

        C29.setBackground(new java.awt.Color(102, 102, 102));
        C29.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C29.setForeground(new java.awt.Color(255, 255, 255));
        C29.setText("CHANNEL 29");
        C29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C29ActionPerformed(evt);
            }
        });

        V29.setMaximum(10);
        V29.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V29StateChanged(evt);
            }
        });

        V30.setMaximum(10);
        V30.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                V30StateChanged(evt);
            }
        });

        C30.setBackground(new java.awt.Color(102, 102, 102));
        C30.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
        C30.setForeground(new java.awt.Color(255, 255, 255));
        C30.setText("CHANNEL 30");
        C30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C30ActionPerformed(evt);
            }
        });

        I25.setForeground(new java.awt.Color(255, 255, 255));
        I25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I26.setBackground(new java.awt.Color(255, 255, 255));
        I26.setForeground(new java.awt.Color(255, 255, 255));
        I26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I9.setForeground(new java.awt.Color(255, 255, 255));
        I9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I17.setForeground(new java.awt.Color(255, 255, 255));
        I17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I1.setBackground(new java.awt.Color(255, 255, 255));
        I1.setForeground(new java.awt.Color(255, 255, 255));
        I1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I27.setBackground(new java.awt.Color(255, 255, 255));
        I27.setForeground(new java.awt.Color(255, 255, 255));
        I27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I10.setBackground(new java.awt.Color(255, 255, 255));
        I10.setForeground(new java.awt.Color(255, 255, 255));
        I10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I18.setBackground(new java.awt.Color(255, 255, 255));
        I18.setForeground(new java.awt.Color(255, 255, 255));
        I18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I2.setBackground(new java.awt.Color(255, 255, 255));
        I2.setForeground(new java.awt.Color(255, 255, 255));
        I2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I12.setBackground(new java.awt.Color(255, 255, 255));
        I12.setForeground(new java.awt.Color(255, 255, 255));
        I12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I11.setBackground(new java.awt.Color(255, 255, 255));
        I11.setForeground(new java.awt.Color(255, 255, 255));
        I11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I19.setBackground(new java.awt.Color(255, 255, 255));
        I19.setForeground(new java.awt.Color(255, 255, 255));
        I19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I3.setBackground(new java.awt.Color(255, 255, 255));
        I3.setForeground(new java.awt.Color(255, 255, 255));
        I3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I20.setBackground(new java.awt.Color(255, 255, 255));
        I20.setForeground(new java.awt.Color(255, 255, 255));
        I20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I28.setBackground(new java.awt.Color(255, 255, 255));
        I28.setForeground(new java.awt.Color(255, 255, 255));
        I28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I4.setBackground(new java.awt.Color(255, 255, 255));
        I4.setForeground(new java.awt.Color(255, 255, 255));
        I4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I29.setBackground(new java.awt.Color(255, 255, 255));
        I29.setForeground(new java.awt.Color(255, 255, 255));
        I29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I13.setBackground(new java.awt.Color(255, 255, 255));
        I13.setForeground(new java.awt.Color(255, 255, 255));
        I13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I21.setBackground(new java.awt.Color(255, 255, 255));
        I21.setForeground(new java.awt.Color(255, 255, 255));
        I21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I5.setBackground(new java.awt.Color(255, 255, 255));
        I5.setForeground(new java.awt.Color(255, 255, 255));
        I5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I22.setBackground(new java.awt.Color(255, 255, 255));
        I22.setForeground(new java.awt.Color(255, 255, 255));
        I22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I30.setBackground(new java.awt.Color(255, 255, 255));
        I30.setForeground(new java.awt.Color(255, 255, 255));
        I30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I6.setBackground(new java.awt.Color(255, 255, 255));
        I6.setForeground(new java.awt.Color(255, 255, 255));
        I6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I14.setBackground(new java.awt.Color(255, 255, 255));
        I14.setForeground(new java.awt.Color(255, 255, 255));
        I14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        I32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        C7.setBackground(new java.awt.Color(102, 102, 102));
        C7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        C7.setForeground(new java.awt.Color(255, 255, 255));
        C7.setText("CHANNEL");
        C7.setMaximumSize(new java.awt.Dimension(151, 33));
        C7.setMinimumSize(new java.awt.Dimension(151, 33));
        C7.setPreferredSize(new java.awt.Dimension(151, 33));
        C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C7ActionPerformed(evt);
            }
        });

        C8.setBackground(new java.awt.Color(102, 102, 102));
        C8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        C8.setForeground(new java.awt.Color(255, 255, 255));
        C8.setText("CHANNEL");
        C8.setMaximumSize(new java.awt.Dimension(151, 33));
        C8.setMinimumSize(new java.awt.Dimension(151, 33));
        C8.setPreferredSize(new java.awt.Dimension(151, 33));
        C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C8ActionPerformed(evt);
            }
        });

        C15.setBackground(new java.awt.Color(102, 102, 102));
        C15.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        C15.setForeground(new java.awt.Color(255, 255, 255));
        C15.setText("CHANNEL");
        C15.setMaximumSize(new java.awt.Dimension(151, 33));
        C15.setMinimumSize(new java.awt.Dimension(151, 33));
        C15.setPreferredSize(new java.awt.Dimension(151, 33));
        C15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C15ActionPerformed(evt);
            }
        });

        C16.setBackground(new java.awt.Color(102, 102, 102));
        C16.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        C16.setForeground(new java.awt.Color(255, 255, 255));
        C16.setText("CHANNEL");
        C16.setMaximumSize(new java.awt.Dimension(151, 33));
        C16.setMinimumSize(new java.awt.Dimension(151, 33));
        C16.setPreferredSize(new java.awt.Dimension(151, 33));
        C16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C16ActionPerformed(evt);
            }
        });

        C23.setBackground(new java.awt.Color(102, 102, 102));
        C23.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        C23.setForeground(new java.awt.Color(255, 255, 255));
        C23.setText("CHANNEL");
        C23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C23ActionPerformed(evt);
            }
        });

        C24.setBackground(new java.awt.Color(102, 102, 102));
        C24.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        C24.setForeground(new java.awt.Color(255, 255, 255));
        C24.setText("CHANNEL");
        C24.setToolTipText("");
        C24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C24ActionPerformed(evt);
            }
        });

        C31.setBackground(new java.awt.Color(102, 102, 102));
        C31.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        C31.setForeground(new java.awt.Color(255, 255, 255));
        C31.setText("CHANNEL");
        C31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C31ActionPerformed(evt);
            }
        });

        C32.setBackground(new java.awt.Color(102, 102, 102));
        C32.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        C32.setForeground(new java.awt.Color(255, 255, 255));
        C32.setText("CHANNEL");
        C32.setMaximumSize(new java.awt.Dimension(151, 33));
        C32.setMinimumSize(new java.awt.Dimension(151, 33));
        C32.setPreferredSize(new java.awt.Dimension(151, 33));
        C32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C32ActionPerformed(evt);
            }
        });

        V7.setMaximum(10);

        V8.setMaximum(10);

        V15.setMaximum(10);

        V16.setMaximum(10);

        V23.setMaximum(10);

        V24.setMaximum(10);

        V31.setMaximum(10);

        V32.setMaximum(10);

        S1.setText("SOLO");
        S1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S1ActionPerformed(evt);
            }
        });

        S9.setText("SOLO");
        S9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S9ActionPerformed(evt);
            }
        });

        S17.setText("SOLO");
        S17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S17ActionPerformed(evt);
            }
        });

        S25.setText("SOLO");
        S25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S25ActionPerformed(evt);
            }
        });

        S3.setText("SOLO");
        S3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S3ActionPerformed(evt);
            }
        });

        S11.setText("SOLO");
        S11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S11ActionPerformed(evt);
            }
        });

        S19.setText("SOLO");
        S19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S19ActionPerformed(evt);
            }
        });

        S27.setText("SOLO");
        S27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S27ActionPerformed(evt);
            }
        });

        S2.setText("SOLO");
        S2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S2ActionPerformed(evt);
            }
        });

        S10.setText("SOLO");
        S10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S10ActionPerformed(evt);
            }
        });

        S18.setText("SOLO");
        S18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S18ActionPerformed(evt);
            }
        });

        S26.setText("SOLO");
        S26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S26ActionPerformed(evt);
            }
        });

        S4.setText("SOLO");
        S4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S4ActionPerformed(evt);
            }
        });

        S12.setText("SOLO");
        S12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S12ActionPerformed(evt);
            }
        });

        S20.setText("SOLO");
        S20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S20ActionPerformed(evt);
            }
        });

        S28.setText("SOLO");
        S28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S28ActionPerformed(evt);
            }
        });

        S5.setText("SOLO");
        S5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S5ActionPerformed(evt);
            }
        });

        S13.setText("SOLO");
        S13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S13ActionPerformed(evt);
            }
        });

        S21.setText("SOLO");
        S21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S21ActionPerformed(evt);
            }
        });

        S6.setText("SOLO");
        S6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S6ActionPerformed(evt);
            }
        });

        S29.setText("SOLO");
        S29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S29ActionPerformed(evt);
            }
        });

        S14.setText("SOLO");
        S14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S14ActionPerformed(evt);
            }
        });

        S22.setText("SOLO");
        S22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S22ActionPerformed(evt);
            }
        });

        S30.setText("SOLO");
        S30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S30ActionPerformed(evt);
            }
        });

        S7.setText("SOLO");
        S7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S7ActionPerformed(evt);
            }
        });

        S8.setText("SOLO");
        S8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S8ActionPerformed(evt);
            }
        });

        S15.setText("SOLO");
        S15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S15ActionPerformed(evt);
            }
        });

        S16.setText("SOLO");
        S16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S16ActionPerformed(evt);
            }
        });

        S23.setText("SOLO");
        S23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S23ActionPerformed(evt);
            }
        });

        S24.setText("SOLO");
        S24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S24ActionPerformed(evt);
            }
        });

        S31.setText("SOLO");
        S31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S31ActionPerformed(evt);
            }
        });

        S32.setText("SOLO");
        S32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(I1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(I5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(V2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(V1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(C9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(I13, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(I9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(I2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(I10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(I7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(I8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C13, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(V13, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(C10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(I15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(I16, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(I23, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(C21, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C29, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(I17, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(C17, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(I21, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(V21, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(V17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(I18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(V6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(V29, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(I25, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(C25, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(V25, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(I26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(I29, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(I3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(I6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(V3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(I4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(V18, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(V26, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(V4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 10, Short.MAX_VALUE)
                                                .addComponent(I24, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(I31, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(V14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(C14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(C22, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(I32, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(C30, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(I14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(I22, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(10, 10, 10)
                                                    .addComponent(I30, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(I11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(C11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(V11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(I12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(I20, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(I28, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(I19, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(I27, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(V19, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(V27, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(C19, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(C27, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(151, 151, 151)
                                                .addComponent(V22, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(V30, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(17, 17, 17))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(441, 441, 441)
                                        .addComponent(V12, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(V20, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(V28, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(C18, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C26, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C12, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C20, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C28, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(V7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(V8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(V15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(V16, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(V23, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(V24, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(V31, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(C15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(C16, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(C23, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(C24, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(C31, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(C32, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(V32, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(S1)
                        .addGap(79, 79, 79)
                        .addComponent(S9)
                        .addGap(88, 88, 88)
                        .addComponent(S17)
                        .addGap(102, 102, 102)
                        .addComponent(S25)
                        .addGap(83, 83, 83)
                        .addComponent(S3)
                        .addGap(78, 78, 78)
                        .addComponent(S11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(S19)
                        .addGap(94, 94, 94)
                        .addComponent(S27)
                        .addGap(66, 66, 66))))
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(S5)
                .addGap(92, 92, 92)
                .addComponent(S13)
                .addGap(87, 87, 87)
                .addComponent(S21)
                .addGap(77, 77, 77)
                .addComponent(S6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(S29)
                .addGap(83, 83, 83)
                .addComponent(S14)
                .addGap(91, 91, 91)
                .addComponent(S22)
                .addGap(102, 102, 102)
                .addComponent(S30)
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(S7)
                .addGap(87, 87, 87)
                .addComponent(S8)
                .addGap(88, 88, 88)
                .addComponent(S15)
                .addGap(95, 95, 95)
                .addComponent(S16)
                .addGap(81, 81, 81)
                .addComponent(S23)
                .addGap(86, 86, 86)
                .addComponent(S24)
                .addGap(95, 95, 95)
                .addComponent(S31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(S32)
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(S2)
                .addGap(84, 84, 84)
                .addComponent(S10)
                .addGap(91, 91, 91)
                .addComponent(S18)
                .addGap(86, 86, 86)
                .addComponent(S26)
                .addGap(85, 85, 85)
                .addComponent(S4)
                .addGap(89, 89, 89)
                .addComponent(S12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(S20)
                .addGap(85, 85, 85)
                .addComponent(S28)
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(I9, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(I1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I17, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I25, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I11, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I19, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I27, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(C9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C17, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C25, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C19, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C27, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(V27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(V1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(V17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(V19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(V25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(V9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(V3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(V11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(S11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(S3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(S25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(S27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(S19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(I28, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I10, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I18, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I26, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I20, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I12, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C18, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C26, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C20, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C28, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(V2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(S10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(S2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(S18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(S4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(S26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(S12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(S28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(S20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(I29, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I13, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I22, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I30, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(C21, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C29, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C22, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C30, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(I5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I21, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I14, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(V5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(V13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(V21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(V29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(V30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(V22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(V14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(V6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(S5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(I15, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I8, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I16, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I24, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I23, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(I31, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C23, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C24, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C31, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C32, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(I32, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(V7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(V32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C1ActionPerformed

      var[1]=true;
      if(!var[2])
      {// TODO add your handling code here:     
    alternar0++;   
    if(alternar0==1){
                     
         try{
            Conf=X.Read("config.xml"); 
            audio0=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(0),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),0,V1,C1,socket);
            audio0.start();
            C1.setBackground(Color.GREEN);
            
         }
         catch(Exception e){
            System.out.println("Error de hilo ");
            C1.setBackground(Color.RED);
            S1.setEnabled(false);
            var[1]=false;
         }
         
                     
    }   

    if(alternar0==2){
         audio0.detener();
         audio0.stop();
         alternar0=0;
         C1.setBackground(Color.BLUE);
         var[1]=false;
         
         
    }
      }
        
      /*         
        alternar0++;   
    if(alternar0==1){
         Conf=X.Read("configuracion.xml");        
         audio0=new ThreadAudio(Conf.GetLista().get(0),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),1);
         audio0.start();   
         C1.setBackground(Color.GREEN);
    }   

    if(alternar0==2){
         audio0.detener();
         audio0.stop();
         alternar0=0;
         C1.setBackground(Color.white);
        }*/
         
       /* 
        alternar0++;
        
        if(alternar0==1){
         canales.set(0,"ON");
         ganancias.set(0,V1.getValue());
         C1.setBackground(Color.BLUE.darker());
        }
        else if(alternar0==2){
            alternar0=0;
              canales.set(0,"OFF");
         ganancias.set(0,V1.getValue());
           C1.setBackground(Color.GRAY.darker());
        
        }
        */
        
    }//GEN-LAST:event_C1ActionPerformed

    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C2ActionPerformed
        // TODO add your handling code here:
        //leer configuracion
          // AlternarAudio(alternar1, audio1, 2, Conf, C2,V2);
    var[3]=true;
    if(!var[4])
    {
       alternar1++;   
    if(alternar1==1){
         Conf=X.Read("config.xml");        
         
         try{
             audio1=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(1),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),8,V2,C2,socket);
             audio1.start();
              C2.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C2.setBackground(Color.RED);
               S2.setEnabled(false);
               S2.setSelected(false);
               var[3]=false;
         }
                   
      
    }   

    if(alternar1==2){
         audio1.detener();
         audio1.stop();
         alternar1=0;
         C2.setBackground(Color.BLUE);
        var[3]=false;
         
    }    
    }   
        /*
       alternar1++;
        
        if(alternar1==1){
         canales.set(1,"ON");
         ganancias.set(1,V2.getValue());
         C2.setBackground(Color.BLUE.darker());
        }
        else if(alternar1==2){
            alternar1=0;
              canales.set(1,"OFF");
         ganancias.set(1,V2.getValue());
           C2.setBackground(Color.GRAY.darker());
        
        }*/
    
    
    }//GEN-LAST:event_C2ActionPerformed

    private void C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C3ActionPerformed
        // TODO add your handling code here:
           //AlternarAudio(alternar2, audio2, 3, Conf, C3,V3);
       var[5]=true;
       if(!var[6])
       {
    alternar2++;   
    if(alternar2==1){
         Conf=X.Read("config.xml");        
         
         try{
             audio2=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(2),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),4,V3,C3,socket);
             audio2.start();
              C3.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C3.setBackground(Color.RED);
               var[5]=false;
         }
                   
      
    }   

    if(alternar2==2){
         audio2.detener();
         audio2.stop();
         alternar2=0;
         C3.setBackground(Color.BLUE);
         var[5]=false;
         
    }
       }   
           
        /*
        alternar2++;
        
        if(alternar2==1){
         canales.set(2,"ON");
         ganancias.set(2,V3.getValue());
         C3.setBackground(Color.BLUE.darker());
        }
        else if(alternar2==2){
            alternar2=0;
              canales.set(2,"OFF");
         ganancias.set(2,V3.getValue());
           C3.setBackground(Color.GRAY.darker());
        
        }*/
      
    }//GEN-LAST:event_C3ActionPerformed

    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4ActionPerformed
        // TODO add your handling code here:
      //     AlternarAudio(alternar3, audio3, 4, Conf, C4,V4);
      var[7]=true;
      if(!var[8])
      {
    alternar3++;   
    if(alternar3==1){
         Conf=X.Read("config.xml");                
         try{
             audio3=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(3),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),12,V4,C4,socket);
             audio3.start();
              C4.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C4.setBackground(Color.RED);
               var[7]=false;
         }     
    }   
    if(alternar3==2){
         audio3.detener();
         audio3.stop();
         alternar3=0;
         C4.setBackground(Color.BLUE);
         var[7]=false;
         
    }
      }
      
      
        /*
        alternar3++;
        
        if(alternar3==1){
         canales.set(3,"ON");
         ganancias.set(3,V4.getValue());
         C4.setBackground(Color.BLUE.darker());
        }
        else if(alternar3==2){
            alternar3=0;
              canales.set(3,"OFF");
         ganancias.set(3,V4.getValue());
           C4.setBackground(Color.GRAY.darker());
        
        }*/
        
    }//GEN-LAST:event_C4ActionPerformed

    private void C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C5ActionPerformed
        // TODO add your handling code here:
        var[9]=true;
        if(!var[10])
        {
       // AlternarAudio(alternar4, audio4, 5, Conf, C5,V5);
        alternar4++;   
    if(alternar4==1){
         Conf=X.Read("config.xml");                
         try{
             
             audio4=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(4),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),16,V5,C5,socket);
             audio4.start();
              C5.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C5.setBackground(Color.RED);
              var[9]=false;
         }     
    }   
    if(alternar4==2){
         audio4.detener();
         audio4.stop();
         alternar4=0;
         C5.setBackground(Color.BLUE);
         var[9]=false;
         
    }
        }
        /*
        alternar4++;
        
        if(alternar4==1){
         canales.set(4,"ON");
         ganancias.set(4,V5.getValue());
         C5.setBackground(Color.BLUE.darker());
        }
        else if(alternar4==2){
            alternar4=0;
              canales.set(4,"OFF");
         ganancias.set(4,V5.getValue());
           C5.setBackground(Color.GRAY.darker());
        
        }
*/
 
    }//GEN-LAST:event_C5ActionPerformed

    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6ActionPerformed
        // TODO add your handling code here:
       var[11]=true;
       if(!var[12])
       {
       //   AlternarAudio(alternar5, audio5,6,Conf,C6,V6);
           alternar5++;   
    if(alternar5==1){
         Conf=X.Read("config.xml");                
         try{
             audio5=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(5),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),19,V6,C6,socket);
             audio5.start();
              C6.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C6.setBackground(Color.RED);
               var[11]=false;
         }     
    }   
    if(alternar5==2){
         audio5.detener();
         audio5.stop();
         alternar5=0;
         C6.setBackground(Color.BLUE);
         var[11]=false;
         
    }}
       
        /*
         alternar5++;
        
        if(alternar5==1){
         canales.set(5,"ON");
         ganancias.set(5,V6.getValue());
         C6.setBackground(Color.BLUE.darker());
        }
        else if(alternar5==2){
            alternar5=0;
              canales.set(5,"OFF");
         ganancias.set(5,V6.getValue());
           C6.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C6ActionPerformed

    private void V9StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V9StateChanged
        // TODO add your handling code here:
        ganancias.set(8,V9.getValue());
    }//GEN-LAST:event_V9StateChanged

    private void V10StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V10StateChanged
        // TODO add your handling code here:
        ganancias.set(9,V10.getValue());
    }//GEN-LAST:event_V10StateChanged

    private void V11StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V11StateChanged
        // TODO add your handling code here:
        ganancias.set(10,V11.getValue());
    }//GEN-LAST:event_V11StateChanged

    private void V12StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V12StateChanged
        // TODO add your handling code here:
        ganancias.set(11,V12.getValue());
    }//GEN-LAST:event_V12StateChanged

    private void V13StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V13StateChanged
        // TODO add your handling code here:
        ganancias.set(12,V13.getValue());
    }//GEN-LAST:event_V13StateChanged

    private void V14StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V14StateChanged
        // TODO add your handling code here:
        ganancias.set(13,V14.getValue());
    }//GEN-LAST:event_V14StateChanged

    private void V17StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V17StateChanged
        // TODO add your handling code here:
        ganancias.set(16,V17.getValue());
    }//GEN-LAST:event_V17StateChanged

    private void V18StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V18StateChanged
        // TODO add your handling code here:
        ganancias.set(17,V18.getValue());
    }//GEN-LAST:event_V18StateChanged

    private void V19StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V19StateChanged
        // TODO add your handling code here:
        ganancias.set(18,V19.getValue());
    }//GEN-LAST:event_V19StateChanged

    private void V20StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V20StateChanged
        // TODO add your handling code here:
        ganancias.set(19,V20.getValue());
    }//GEN-LAST:event_V20StateChanged

    private void V21StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V21StateChanged
        // TODO add your handling code here:
        ganancias.set(20,V21.getValue());
    }//GEN-LAST:event_V21StateChanged

    private void V22StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V22StateChanged
        // TODO add your handling code here:
        ganancias.set(21,V22.getValue());
    }//GEN-LAST:event_V22StateChanged

    private void V30StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V30StateChanged
        // TODO add your handling code here:
        ganancias.set(29,V30.getValue());
    }//GEN-LAST:event_V30StateChanged

    private void V25StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V25StateChanged
        // TODO add your handling code here:
        ganancias.set(24,V25.getValue());
    }//GEN-LAST:event_V25StateChanged

    private void V26StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V26StateChanged
        // TODO add your handling code here:
        ganancias.set(25,V26.getValue());
    }//GEN-LAST:event_V26StateChanged

    private void V27StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V27StateChanged
        // TODO add your handling code here:
        ganancias.set(26,V27.getValue());
    }//GEN-LAST:event_V27StateChanged

    private void V28StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V28StateChanged
        // TODO add your handling code here:
        ganancias.set(27,V28.getValue());
    }//GEN-LAST:event_V28StateChanged

    private void V29StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V29StateChanged
        // TODO add your handling code here:
        ganancias.set(28,V29.getValue());
    }//GEN-LAST:event_V29StateChanged

    private void C9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C9ActionPerformed
        // TODO add your handling code here:
      //   AlternarAudio(alternar8, audio8,9,Conf,C9,V9);
        var[17]=true;
        if(!var[18])
        {
       alternar8++;   
    if(alternar8==1){
         Conf=X.Read("config.xml");                
         try{
             audio8=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(8),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),1,V9,C9,socket);
             audio8.start();
              C9.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C9.setBackground(Color.RED);
               var[17]=false;
         }     
    }   
    if(alternar8==2){
         audio8.detener();
         audio8.stop();
         alternar8=0;
         C9.setBackground(Color.BLUE);
         var[17]=false;
         
    }
        }
      
        /*
                  alternar8++;
               if(alternar8==1){
              canales.set(8,"ON");
            ganancias.set(8,V9.getValue());
                         C9.setBackground(Color.BLUE.darker());
            System.out.println(V9.getValue());
        }
          else if(alternar8==2){
                  alternar8=0;
              canales.set(8,"OFF");
            ganancias.set(8,V9.getValue());
                         C9.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C9ActionPerformed

    private void C10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C10ActionPerformed
        // TODO add your handling code here:
        var[19]=true;
        if(!var[20])
        {
          //   AlternarAudio(alternar9, audio9,10,Conf,C10,V10);
       alternar9++;   
    if(alternar9==1){
         Conf=X.Read("config.xml");                
         try{
             audio9=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(9),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),9,V10,C10,socket);
             audio9.start();
              C10.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C10.setBackground(Color.RED);
              var[19]=false;
         }     
    }   
    if(alternar9==2){
         audio9.detener();
         audio9.stop();
         alternar9=0;
         C10.setBackground(Color.BLUE);
         var[19]=false;
        
    }
        }
        /*
                  alternar9++;
               if(alternar9==1){
              canales.set(9,"ON");
            ganancias.set(9,V10.getValue());
                         C10.setBackground(Color.BLUE.darker());
            System.out.println(V10.getValue());
        }
          else if(alternar9==2){
                  alternar9=0;
              canales.set(9,"OFF");
            ganancias.set(9,V10.getValue());
                         C10.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C10ActionPerformed

    private void C11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C11ActionPerformed
        // TODO add your handling code here:
        var[21]=true;
        if(!var[22])
        {
        //AlternarAudio(alternar10, audio10,11,Conf,C11,V11);
            alternar10++;   
    if(alternar10==1){
         Conf=X.Read("config.xml");                
         try{
             audio10=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(10),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),5,V11,C11,socket);
             audio10.start();
              C11.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C11.setBackground(Color.RED);
               var[21]=false;
         }     
    }   
    if(alternar10==2){
         audio10.detener();
         audio10.stop();
         alternar10=0;
         C11.setBackground(Color.BLUE);
         var[21]=false;
         
    }
        }
        
        /*
                  alternar10++;
               if(alternar10==1){
              canales.set(10,"ON");
            ganancias.set(10,V11.getValue());
                         C11.setBackground(Color.BLUE.darker());
            System.out.println(V11.getValue());
        }
          else if(alternar10==2){
                  alternar10=0;
              canales.set(10,"OFF");
            ganancias.set(10,V11.getValue());
                         C11.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C11ActionPerformed

    private void C12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C12ActionPerformed
        // TODO add your handling code here:
         //  AlternarAudio(alternar11, audio11,12,Conf,C12,V12);
         var[23]=true;
         if(!var[24])
         {
                alternar11++;   
    if(alternar11==1){
         Conf=X.Read("config.xml");                
         try{
             audio11=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(11),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),13,V12,C12,socket);
             audio11.start();
              C12.setBackground(Color.GREEN);
             
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C12.setBackground(Color.RED);
                var[23]=false;
               
         }     
    }   
    if(alternar11==2){
         audio11.detener();
         audio11.stop();
         alternar11=0;
         C12.setBackground(Color.BLUE);
         var[23]=false;
         
    }
         }
           /*
                  alternar11++;
               if(alternar11==1){
              canales.set(11,"ON");
            ganancias.set(11,V12.getValue());
                         C12.setBackground(Color.BLUE.darker());
            System.out.println(V12.getValue());
        }
          else if(alternar11==2){
                  alternar11=0;
              canales.set(11,"OFF");
            ganancias.set(11,V12.getValue());
                         C12.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C12ActionPerformed

    private void C13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C13ActionPerformed
        // TODO add your handling code here:
          // AlternarAudio(alternar12, audio12,13,Conf,C13,V13);
          var[25]=true;
          if(!var[26])
          {
       alternar12++;   
    if(alternar12==1){
         Conf=X.Read("config.xml");                
         try{
             audio12=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(12),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),17,V13,C13,socket);
             audio12.start();
              C13.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C13.setBackground(Color.RED);
               var[25]=false;
         }     
    }   
    if(alternar12==2){
         audio12.detener();
         audio12.stop();
         alternar12=0;
         C13.setBackground(Color.BLUE);
         var[25]=false;
         
    }}
          
        /*
                  alternar12++;
               if(alternar12==1){
              canales.set(12,"ON");
            ganancias.set(12,V13.getValue());
                         C13.setBackground(Color.BLUE.darker());
            System.out.println(V13.getValue());
        }
          else if(alternar12==2){
                  alternar12=0;
              canales.set(12,"OFF");
            ganancias.set(12,V13.getValue());
                         C13.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C13ActionPerformed

    private void C14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C14ActionPerformed
        // TODO add your handling code here:
        // AlternarAudio(alternar13, audio13,14,Conf,C14,V14);    
        var[27]=true;
        if(!var[28])
        {
          alternar13++;   
    if(alternar13==1){
         Conf=X.Read("config.xml");                
         try{
             audio13=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(13),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),21,V14,C14,socket);
             audio13.start();
              C14.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C14.setBackground(Color.RED);
               var[27]=false;
         }     
    }   
    if(alternar13==2){
         audio13.detener();
         audio13.stop();
         alternar13=0;
         C14.setBackground(Color.BLUE);
         var[27]=false;
    }}
        
        
        /*
              alternar13++;
               if(alternar13==1){
              canales.set(13,"ON");
            ganancias.set(13,V14.getValue());
                         C14.setBackground(Color.BLUE.darker());
            System.out.println(V14.getValue());
        }
          else if(alternar13==2){
                  alternar13=0;
              canales.set(13,"OFF");
            ganancias.set(13,V14.getValue());
                         C14.setBackground(Color.GRAY.darker());
        
        }*/
                 
    }//GEN-LAST:event_C14ActionPerformed

    private void C17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C17ActionPerformed
        // TODO add your handling code here:
        var[33]=true;
        if(!var[34])
        {
      //  AlternarAudio(alternar16,audio16,17,Conf,C17,V17); 
      
        alternar16++;   
    if(alternar16==1){
         Conf=X.Read("config.xml");                
         try{
             audio16=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(16),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),2,V17,C17,socket);
             audio16.start();
              C17.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C17.setBackground(Color.RED);
               var[33]=false;
         }     
    }   
    if(alternar16==2){
         audio16.detener();
         audio16.stop();
         alternar16=0;
         C17.setBackground(Color.BLUE);
         var[33]=true;
         
    }}
        
        /*
                  alternar16++;
               if(alternar16==1){
              canales.set(16,"ON");
            ganancias.set(16,V17.getValue());
                         C17.setBackground(Color.BLUE.darker());
            System.out.println(V17.getValue());
        }
          else if(alternar16==2){
                  alternar16=0;
              canales.set(16,"OFF");
            ganancias.set(16,V17.getValue());
                         C17.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C17ActionPerformed

    private void C18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C18ActionPerformed
        // TODO add your handling code here:
      //  AlternarAudio(alternar17,audio17,18,Conf,C18,V18); 
        var[35]=true;
        if(!var[36])
        {
         alternar17++;   
    if(alternar17==1){
         Conf=X.Read("config.xml");                
         try{
             audio17=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(17),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),10,V18,C18,socket);
             audio17.start();
              C18.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C18.setBackground(Color.RED);
               var[35]=false;
         }     
    }   
    if(alternar17==2){
         audio17.detener();
         audio17.stop();
         alternar17=0;
         C18.setBackground(Color.BLUE);
         var[35]=false;
    }
    }
      
      
        /*
                  alternar17++;
               if(alternar17==1){
              canales.set(17,"ON");
            ganancias.set(17,V18.getValue());
                         C18.setBackground(Color.BLUE.darker());
            System.out.println(V18.getValue());
        }
          else if(alternar17==2){
                  alternar17=0;
              canales.set(17,"OFF");
            ganancias.set(17,V18.getValue());
                         C18.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C18ActionPerformed

    private void C19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C19ActionPerformed
        // TODO add your handling code here:
      //  AlternarAudio(alternar18,audio18,19,Conf,C19,V19); 
      var[37]=true;
      if(!var[38]);
       alternar18++;   
    if(alternar18==1){
         Conf=X.Read("config.xml");                
         try{
             audio18=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(18),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),6,V19,C19,socket);
             audio18.start();
              C19.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C19.setBackground(Color.RED);
               var[37]=false;
         }     
    }   
    if(alternar18==2){
         audio18.detener();
         audio18.stop();
         alternar18=0;
         C19.setBackground(Color.BLUE);
         var[37]=false;
    }
        /*
        
                  alternar18++;
               if(alternar18==1){
              canales.set(18,"ON");
            ganancias.set(18,V19.getValue());
                         C19.setBackground(Color.BLUE.darker());
            System.out.println(V19.getValue());
        }
          else if(alternar18==2){
                  alternar18=0;
              canales.set(18,"OFF");
            ganancias.set(18,V19.getValue());
                         C19.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C19ActionPerformed

    private void C20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C20ActionPerformed
        // TODO add your handling code here:
          // AlternarAudio(alternar19,audio19,20,Conf,C20,V20); 
          var[39]=true;
          if(!var[40])
          {
       alternar19++;   
    if(alternar19==1){
         Conf=X.Read("config.xml");                
         try{
             audio19=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(19),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),14,V20,C20,socket);
             audio19.start();
              C20.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C20.setBackground(Color.RED);
               var[39]=false;
               
         }     
    }   
    if(alternar19==2){
         audio19.detener();
         audio19.stop();
         alternar19=0;
         C20.setBackground(Color.BLUE);
         var[39]=false;
    } 
    }
          
               /*          
                  alternar19++;
               if(alternar19==1){
              canales.set(19,"ON");
            ganancias.set(19,V20.getValue());
                         C20.setBackground(Color.BLUE.darker());
            System.out.println(V20.getValue());
        }
          else if(alternar19==2){
                  alternar19=0;
              canales.set(19,"OFF");
            ganancias.set(19,V20.getValue());
                         C20.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C20ActionPerformed

    private void C21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C21ActionPerformed
        // TODO add your handling code here:
      //  AlternarAudio(alternar20,audio20,21,Conf,C21,V21); 
      var[41]=true;
      if(!var[42])
      {
        alternar20++;   
    if(alternar20==1){
         Conf=X.Read("config.xml");                
         try{
             audio20=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(20),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),18,V21,C21,socket);
             audio20.start();
              C21.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C21.setBackground(Color.RED);
               var[41]=false;
         }     
    }   
    if(alternar20==2){
         audio20.detener();
         audio20.stop();
         alternar20=0;
         C21.setBackground(Color.BLUE);
         var[41]=false;
    }   
    }
      
        /*
                  alternar20++;
               if(alternar20==1){
              canales.set(20,"ON");
            ganancias.set(20,V21.getValue());
                         C21.setBackground(Color.BLUE.darker());
            System.out.println(V21.getValue());
        }
          else if(alternar20==2){
                  alternar20=0;
              canales.set(20,"OFF");
            ganancias.set(20,V21.getValue());
                         C21.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C21ActionPerformed

    private void C22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C22ActionPerformed
        // TODO add your handling code here:
       // AlternarAudio(alternar21,audio21,22,Conf,C22,V22);
        var[43]=true;
       if(!var[44])
       {
        alternar21++;   
    if(alternar21==1){
         Conf=X.Read("config.xml");                
         try{
             audio21=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(21),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),22,V22,C22,socket);
             audio21.start();
              C22.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C22.setBackground(Color.RED);
               var[43]=false;
         }     
    }   
    if(alternar21==2){
         audio21.detener();
         audio21.stop();
         alternar21=0;
         C22.setBackground(Color.BLUE);
         var[43]=false;
    }  
    }
       
        /*
                  alternar21++;
               if(alternar21==1){
              canales.set(21,"ON");
            ganancias.set(21,V22.getValue());
                         C22.setBackground(Color.BLUE.darker());
            System.out.println(V22.getValue());
        }
          else if(alternar21==2){
                  alternar21=0;
              canales.set(21,"OFF");
            ganancias.set(21,V22.getValue());
                         C22.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C22ActionPerformed

    private void C25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C25ActionPerformed
        // TODO add your handling code here:
        var[49]=true;
      //  AlternarAudio(alternar24,audio24,25,Conf,C25,V25);
      if(!var[50])
      {
       alternar24++;   
    if(alternar24==1){
         Conf=X.Read("config.xml");                
         try{
             audio24=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(24),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),3,V25,C25,socket);
             audio24.start();
              C25.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C25.setBackground(Color.RED);
               var[49]=false;
         }     
    }   
    if(alternar24==2){
         audio24.detener();
         audio24.stop();
         alternar24=0;
         C25.setBackground(Color.BLUE);
         var[49]=false;
    }
    }
      
        /*
                  alternar24++;
               if(alternar24==1){
              canales.set(24,"ON");
            ganancias.set(24,V25.getValue());
                         C25.setBackground(Color.BLUE.darker());
            System.out.println(V25.getValue());
        }
          else if(alternar24==2){
                  alternar24=0;
              canales.set(24,"OFF");
            ganancias.set(24,V25.getValue());
                         C25.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C25ActionPerformed

    private void C26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C26ActionPerformed
        // TODO add your handling code here:
      //  AlternarAudio(alternar25,audio25,26,Conf,C26,V26);
      var[51]=true;
      if(!var[52])
      {
       alternar25++;   
    if(alternar25==1){
         Conf=X.Read("config.xml");                
         try{
             audio25=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(25),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),11,V26,C26,socket);
             audio25.start();
              C26.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C26.setBackground(Color.RED);
               var[51]=false;
         }     
    }   
    if(alternar25==2){
         audio25.detener();
         audio25.stop();
         alternar25=0;
         C26.setBackground(Color.BLUE);
         var[51]=false;
    }
    }
      
        /*
                  alternar25++;
               if(alternar25==1){
              canales.set(25,"ON");
            ganancias.set(25,V26.getValue());
                         C26.setBackground(Color.BLUE.darker());
            System.out.println(V26.getValue());
        }
          else if(alternar25==2){
                  alternar25=0;
              canales.set(25,"OFF");
            ganancias.set(25,V26.getValue());
                         C26.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C26ActionPerformed

    private void C27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C27ActionPerformed
        // TODO add your handling code here:
      //  AlternarAudio(alternar26,audio26,27,Conf,C27,V27);
      var[53]=true;
      if(!var[54])
      {
           alternar26++;   
    if(alternar26==1){
         Conf=X.Read("config.xml");                
         try{
             audio26=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(26),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),7,V27,C27,socket);
             audio26.start();
              C27.setBackground(Color.GREEN);
             
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C27.setBackground(Color.RED);
            var[53]=false;
         }     
    }   
    if(alternar26==2){
         audio26.detener();
         audio26.stop();
         alternar26=0;
         C27.setBackground(Color.BLUE);
         var[53]=false;
    }   
    }
    }//GEN-LAST:event_C27ActionPerformed

    private void C28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C28ActionPerformed
        // TODO add your handling code here:
       //  AlternarAudio(alternar27,audio27,28,Conf,C28,V28);
       var[55]=true;
       if(!var[56])
       {
             alternar27++;   
    if(alternar27==1){
         Conf=X.Read("config.xml");                
         try{
             audio27=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(27),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),15,V28,C28,socket);
             audio27.start();
              C28.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C28.setBackground(Color.RED);
               var[55]=false;
         }     
    }   
    if(alternar27==2){
         audio27.detener();
         audio27.stop();
         alternar27=0;
         C28.setBackground(Color.BLUE);
         var[55]=false;
    }
    }
       
        /*
                  alternar27++;
               if(alternar27==1){
              canales.set(27,"ON");
            ganancias.set(27,V28.getValue());
                         C28.setBackground(Color.BLUE.darker());
            System.out.println(V28.getValue());
        }
          else if(alternar27==2){
                  alternar27=0;
              canales.set(27,"OFF");
            ganancias.set(27,V28.getValue());
                         C28.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C28ActionPerformed

    private void C29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C29ActionPerformed
        // TODO add your handling code here:
        //  AlternarAudio(alternar28,audio28,29,Conf,C29,V29);
        var[57]=true;
        if(!var[58])
        {
       alternar28++;   
    if(alternar28==1){
         Conf=X.Read("config.xml");                
         try{
             audio28=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(28),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),20,V29,C29,socket);
             audio28.start();
              C29.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C29.setBackground(Color.RED);
                var[57]=false;
         }     
    }   
    if(alternar28==2){
         audio28.detener();
         audio28.stop();
         alternar28=0;
         C29.setBackground(Color.BLUE);
         var[57]=false;
    }
    }
        
        /*
                  alternar28++;
               if(alternar28==1){
              canales.set(28,"ON");
            ganancias.set(28,V29.getValue());
                         C29.setBackground(Color.BLUE.darker());
            System.out.println(V29.getValue());
        }
          else if(alternar28==2){
                  alternar28=0;
              canales.set(28,"OFF");
            ganancias.set(28,V29.getValue());
                         C29.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C29ActionPerformed

    private void C30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C30ActionPerformed
        // TODO add your handling code here:
     //   AlternarAudio(alternar29,audio29,30,Conf,C30,V30);
     var[59]=true;
     if(!var[60])
     {
       alternar29++;   
    if(alternar29==1){
         Conf=X.Read("config.xml");                
         try{
             audio29=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(29),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),23,V30,C30,socket);
             audio29.start();
              C30.setBackground(Color.GREEN);
             
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C30.setBackground(Color.RED);
               var[59]=false;
         }     
    }   
    if(alternar29==2){
         audio29.detener();
         audio29.stop();
         alternar29=0;
         C30.setBackground(Color.BLUE);
         var[59]=false;
        
    }
     }
        /*
                  alternar29++;
               if(alternar29==1){
              canales.set(29,"ON");
            ganancias.set(29,V30.getValue());
                         C30.setBackground(Color.BLUE.darker());
            System.out.println(V30.getValue());
        }
          else if(alternar29==2){
                  alternar29=0;
              canales.set(29,"OFF");
            ganancias.set(29,V30.getValue());
                         C30.setBackground(Color.GRAY.darker());
        
        }*/
    }//GEN-LAST:event_C30ActionPerformed

    private void V1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V1StateChanged
        // TODO add your handling code here:
         ganancias.set(0,V1.getValue());
    }//GEN-LAST:event_V1StateChanged

    private void V2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V2StateChanged
        // TODO add your handling code here:
        ganancias.set(1,V2.getValue());
    }//GEN-LAST:event_V2StateChanged

    private void V3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V3StateChanged
        // TODO add your handling code here:
        ganancias.set(2,V3.getValue());
    }//GEN-LAST:event_V3StateChanged

    private void V4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V4StateChanged
        // TODO add your handling code here:
        ganancias.set(3,V4.getValue());
    }//GEN-LAST:event_V4StateChanged

    private void V5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V5StateChanged
        // TODO add your handling code here:
        ganancias.set(4,V5.getValue());
    }//GEN-LAST:event_V5StateChanged

    private void V6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_V6StateChanged
        // TODO add your handling code here:
        ganancias.set(5,V6.getValue());
    }//GEN-LAST:event_V6StateChanged

    private void C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C7ActionPerformed
        // TODO add your handling code here:
        var[13]=true;
        if(!var[14])
        {
            alternar6++;   
    if(alternar6==1){
         Conf=X.Read("config.xml");                
         try{
             
             audio6=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(6),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),24,V7,C7,socket);
             audio6.start();
              C7.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C7.setBackground(Color.RED);
               var[13]=false;
              
         }     
    }   
    if(alternar6==2){
         audio6.detener();
         audio6.stop();
         alternar6=0;
         C7.setBackground(Color.BLUE);
         var[13]=false;
    }
        }
    }//GEN-LAST:event_C7ActionPerformed

    private void C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C8ActionPerformed
        // TODO add your handling code here:
        var[15]=true;
        if(!var[16])
        {
            alternar7++;   
    if(alternar7==1){
         Conf=X.Read("config.xml");                
         try{
             
             audio7=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(7),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),25,V8,C8,socket);
             audio7.start();
              C8.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C8.setBackground(Color.RED);
               var[15]=false;
         }     
    }   
    if(alternar7==2){
         audio7.detener();
         audio7.stop();
         alternar7=0;
         C8.setBackground(Color.BLUE);
         var[15]=false;
       
    }
        }
    }//GEN-LAST:event_C8ActionPerformed

    private void C15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C15ActionPerformed
        // TODO add your handling code here:
        var[29]=true;
        if(!var[30])
        {
            alternar14++;   
    if(alternar14==1){
         Conf=X.Read("config.xml");                
         try{
             
             audio14=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(14),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),26,V15,C15,socket);
             audio14.start();
              C15.setBackground(Color.GREEN);
          
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C15.setBackground(Color.RED);
               var[29]=false;
         }  
    
    }   
    if(alternar14==2){
         audio14.detener();
         audio14.stop();
         alternar14=0;
         C15.setBackground(Color.BLUE);
         var[29]=false;
         
    }
        }
    }//GEN-LAST:event_C15ActionPerformed

    private void C16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C16ActionPerformed
        // TODO add your handling code here:
        var[31]=true;
        if(!var[32])
        {
            alternar15++;   
    if(alternar15==1){
         Conf=X.Read("config.xml");                
         try{
             
             audio15=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(15),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),27,V16,C16,socket);
             audio15.start();
              C16.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C16.setBackground(Color.RED);
               var[31]=false;
         }     
    }   
    if(alternar15==2){
         audio15.detener();
         audio15.stop();
         alternar15=0;
         C16.setBackground(Color.BLUE);
         var[31]=false;
        
    }
        }
    }//GEN-LAST:event_C16ActionPerformed

    private void C23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C23ActionPerformed
        // TODO add your handling code here:
        var[45]=true;
        if(!var[46])
        {
            alternar22++;   
    if(alternar22==1){
         Conf=X.Read("config.xml");                
         try{
             
             audio22=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(22),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),28,V23,C23,socket);
             audio22.start();
              C23.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C23.setBackground(Color.RED);
              var[45]=false;
         }     
    }   
    if(alternar22==2){
         audio22.detener();
         audio22.stop();
         alternar22=0;
         C23.setBackground(Color.BLUE);
         var[45]=false;
         
    }
        }
    }//GEN-LAST:event_C23ActionPerformed

    private void C24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C24ActionPerformed
        // TODO add your handling code here:
        var[47]=true;
        if(!var[48])
        {
            alternar23++;   
    if(alternar23==1){
         Conf=X.Read("config.xml");                
         try{
             
             audio23=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(23),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),29,V24,C24,socket);
             audio23.start();
              C24.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C24.setBackground(Color.RED);
               var[47]=false;
         }     
    }   
    if(alternar23==2){
         audio23.detener();
         audio23.stop();
         alternar23=0;
         C24.setBackground(Color.BLUE);
         var[47]=false;
    }
    }
    }//GEN-LAST:event_C24ActionPerformed

    private void C31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C31ActionPerformed
        // TODO add your handling code here:
        var[61]=true;
        if(!var[62])
        {
            alternar30++;   
    if(alternar30==1){
         Conf=X.Read("config.xml");                
         try{
             
             audio30=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(30),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),30,V31,C31,socket);
             audio30.start();
              C31.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C31.setBackground(Color.RED);
               var[61]=false;
         }     
    }   
    if(alternar30==2){
         audio30.detener();
         audio30.stop();
         alternar30=0;
         C31.setBackground(Color.BLUE);
         var[61]=false;
    }}
    }//GEN-LAST:event_C31ActionPerformed

    private void C32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C32ActionPerformed
        // TODO add your handling code here:
        var[63]=true;
        if(!var[64])
        {
            alternar31++;   
    if(alternar31==1){
         Conf=X.Read("config.xml");                
         try{
             
             audio31=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(31),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),31,V32,C32,socket);
             audio31.start();
              C32.setBackground(Color.GREEN);
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               C32.setBackground(Color.RED);
               var[63]=false;
         }     
    }   
    if(alternar31==2){
         audio31.detener();
         audio31.stop();
         alternar4=0;
         C32.setBackground(Color.BLUE);
         var[63]=false;
         
    }
        }
    }//GEN-LAST:event_C32ActionPerformed

    
    ///////
    
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
                if(component.equals(j))
                    ((JSlider)component).setValue(10);
                
                if (!component.equals(j)){
                    ((JSlider) component).setValue(0);
                    ((JSlider) component).disable();
                }
            }
            if(component instanceof JToggleButton)
            {
                if(component.equals(r))
                    r.setSelected(true);
                
                else
                    ((JToggleButton)component).setSelected(false);
            }
          }
        for (int x=0;x<65;x++)
        {
            if(!(x==y))
            {
                var[x]=false;
            }
        }
        for (int x=0;x<32;x++)
        {
            if(!(hilo==x))
            {
                try{
                audio[x].detener();
                audio[x].stop();
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
    
    
    
    private void S1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S1ActionPerformed
        // TODO add your handling code here:
        var[2]=true;
        if(S1.isSelected()&&!var[1]){
            DisableVolumeExcept(V1,S1,2,0);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[0]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(0),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),0,V1,C1,socket);
             audio[0].start();
              
              
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
            audio[0].detener();
            audio[0].stop();
        }
    }//GEN-LAST:event_S1ActionPerformed

    private void S9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S9ActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_S9ActionPerformed

    private void S17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S17ActionPerformed

        // TODO add your handling code here:
        var[34]=true;
        if(S17.isSelected()&&!var[33]){
            DisableVolumeExcept(V17,S17,34,16);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[16]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(16),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),2,V17,C17,socket);
             audio[16].start();
            
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[34]=false;
         }
        }
        if(S17.isSelected()&&var[33])
        {
            DisableVolumeExcept(V17,S17,34,16);
        }
        if(!S17.isSelected()){
            EnableVolume();
            var[34]=false;
            audio[16].detener();
            audio[16].stop();
        }
    }//GEN-LAST:event_S17ActionPerformed

    private void S25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S25ActionPerformed
        // TODO add your handling code here:
         var[50]=true;
        if(S25.isSelected()&&!var[49]){
            DisableVolumeExcept(V25,S25,50,24);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[24]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(24),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),3,V25,C25,socket);
             audio[24].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[50]=false;
         }     
        }
        if(S25.isSelected()&&var[49])
        {
            DisableVolumeExcept(V25,S25,50,24);
        }
        if(!S25.isSelected()){
            EnableVolume();
            var[50]=false;
            audio[24].detener();
            audio[24].stop();
        }
    }//GEN-LAST:event_S25ActionPerformed

    private void S3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S3ActionPerformed
        // TODO add your handling code here:
        var[6]=true;
        
        if(S3.isSelected()&&!var[5]){
            DisableVolumeExcept(V3,S3,6,2);
            System.out.println("activado");
             Conf=X.Read("config.xml");        
         
         try{
             audio[2]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(2),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),4,V3,C3,socket);
             audio[2].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[6]=false;
         }
        }
        if(S3.isSelected()&&var[5]){
            DisableVolumeExcept(V3,S3,6,2);
            
        }
        if(!S3.isSelected()){
            EnableVolume();
            var[6]=false;
            audio[2].detener();
            audio[2].stop();
        }
    }//GEN-LAST:event_S3ActionPerformed

    private void S11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S11ActionPerformed
        // TODO add your handling code here:
        var[22]=true;
        if(S11.isSelected()&&!var[21]){
            DisableVolumeExcept(V11,S11,22,10);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[10]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(10),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),5,V11,C11,socket);
             audio[10].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[22]=false;
         }     
        }
        if(S11.isSelected()&&var[21])
        {
            DisableVolumeExcept(V11,S11,22,10);
        }
        if(!S11.isSelected()){
            EnableVolume();
            var[22]=false;
            audio[10].detener();
            audio[10].stop();
        }
    }//GEN-LAST:event_S11ActionPerformed

    private void S19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S19ActionPerformed
        // TODO add your handling code here:
         var[38]=true;
        if(S19.isSelected()&&!var[37]){
            DisableVolumeExcept(V19,S19,38,18);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[18]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(18),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),6,V19,C19,socket);
             audio[18].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[38]=false;
         }     
        }
        if(S19.isSelected()&&var[37])
        {
            DisableVolumeExcept(V19,S19,38,18);
        }
        if(!S19.isSelected()){
            EnableVolume();
            var[38]=false;
            audio[18].detener();
            audio[18].stop();
        }
    }//GEN-LAST:event_S19ActionPerformed

    private void S27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S27ActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_S27ActionPerformed

    private void S2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S2ActionPerformed
        // TODO add your handling code here:
        var[4]=true;
        if(S2.isSelected()&&!var[3]){
            DisableVolumeExcept(V2,S2,4,1);
            System.out.println("activado");
            Conf=X.Read("config.xml");        
         
         try{
             audio[1]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(1),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),8,V2,C2,socket);
             audio[1].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[4]=false;
         }
        }
        if(S2.isSelected()&&var[3]){
            DisableVolumeExcept(V2,S2,4,1);
        }
        if(!S2.isSelected()){
            EnableVolume();
            var[4]=false;
            audio[1].detener();
            audio[1].stop();
        }
    }//GEN-LAST:event_S2ActionPerformed

    private void S10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S10ActionPerformed
        // TODO add your handling code here:
        var[20]=true;
        if(S10.isSelected()&&!var[19]){
            DisableVolumeExcept(V10,S10,20,9);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[9]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(9),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),9,V10,C10,socket);
             audio[9].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
              var[20]=false;
         }     
        }
        if(S10.isSelected()&&var[19])
        {
            DisableVolumeExcept(V10,S10,20,9);
        }
        if(!S10.isSelected()){
            EnableVolume();
            var[20]=false;
            audio[9].detener();
            audio[9].stop();
            
        }
    }//GEN-LAST:event_S10ActionPerformed

    private void S18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S18ActionPerformed
        // TODO add your handling code here:
         var[36]=true;
        if(S18.isSelected()&&!var[35]){
            DisableVolumeExcept(V18,S18,36,17);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[17]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(17),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),10,V18,C18,socket);
             audio[17].start();
             
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
              
               var[36]=false;
         }     
        }
        if(S18.isSelected()&&var[35])
        {
            DisableVolumeExcept(V18,S18,36,17);
        }
        if(!S18.isSelected()){
            EnableVolume();
            var[36]=false;
            audio[17].detener();
            audio[17].stop();
        }
    }//GEN-LAST:event_S18ActionPerformed

    private void S26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S26ActionPerformed
        // TODO add your handling code here:
         var[52]=true;
        if(S26.isSelected()&&!var[51]){
            DisableVolumeExcept(V26,S26,52,25);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[25]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(25),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),11,V26,C26,socket);
             audio[25].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[52]=false;
         }     
        }
        if(S26.isSelected()&&var[51])
        {
            DisableVolumeExcept(V26,S26,52,25);
        }
        if(!S26.isSelected()){
            EnableVolume();
            var[52]=false;
            audio[25].detener();
            audio[25].stop();
        }
    }//GEN-LAST:event_S26ActionPerformed

    private void S4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S4ActionPerformed
        // TODO add your handling code here:
        var[8]=true;
        if(S4.isSelected()&&!var[7]){
            DisableVolumeExcept(V4,S4,8,3);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[3]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(3),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),12,V4,C4,socket);
             audio[3].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[8]=false;
         }     
        }
        if(S4.isSelected()&&var[7]){
            DisableVolumeExcept(V4,S4,8,3);
        }
        if(!S4.isSelected()){
            EnableVolume();
            var[8]=false;
            audio[3].detener();
            audio[3].stop();
        }
    }//GEN-LAST:event_S4ActionPerformed

    private void S12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S12ActionPerformed
        // TODO add your handling code here:
        var[24]=true;
        if(S12.isSelected()&&!var[23]){
            DisableVolumeExcept(V12,S12,24,11);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[11]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(11),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),13,V12,C12,socket);
             audio[11].start();
              
             
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
                var[24]=false;
               
         }     
        }
        if(S12.isSelected()&&var[23])
        {
            DisableVolumeExcept(V12,S12,24,11);
        }
        if(!S12.isSelected()){
            EnableVolume();
            var[24]=false;
            audio[11].detener();
            audio[11].stop();
        }
    }//GEN-LAST:event_S12ActionPerformed

    private void S20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S20ActionPerformed
        // TODO add your handling code here:
         var[40]=true;
        if(S20.isSelected()&&!var[39]){
            DisableVolumeExcept(V20,S20,40,19);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[19]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(19),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),14,V20,C20,socket);
             audio[19].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
              
               var[40]=false;
               
         }     
        }
        if(S20.isSelected()&&var[39])
        {
            DisableVolumeExcept(V20,S20,40,19);
        }
        if(!S20.isSelected()){
            EnableVolume();
            var[40]=false;
            audio[19].detener();
            audio[19].stop();
        }
    }//GEN-LAST:event_S20ActionPerformed

    private void S28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S28ActionPerformed
        // TODO add your handling code here:
        var[56]=true;
        if(S28.isSelected()&&!var[55]){
            DisableVolumeExcept(V28,S28,56,27);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[27]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(27),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),15,V28,C28,socket);
             audio[27].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[56]=false;
         }     
        }
        if(S28.isSelected()&&var[55])
        {
            DisableVolumeExcept(V28,S28,56,27);
        }
        if(!S28.isSelected()){
            EnableVolume();
            var[56]=false;
            audio[27].detener();
            audio[27].stop();
        }
    }//GEN-LAST:event_S28ActionPerformed

    private void S5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S5ActionPerformed
        // TODO add your handling code here:
        var[10]=true;
        if(S5.isSelected()&&!var[9]){
            DisableVolumeExcept(V5,S5,10,4);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             
             audio[4]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(4),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),16,V5,C5,socket);
             audio[4].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
              
              var[10]=false;
         }     
        }
        if(S5.isSelected()&&var[9])
        {
            DisableVolumeExcept(V5,S5,20,4);
        }
        if(!S5.isSelected()){
            EnableVolume();
            var[10]=false;
             audio[4].detener();
            audio[4].stop();
        }
    }//GEN-LAST:event_S5ActionPerformed

    private void S13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S13ActionPerformed
        // TODO add your handling code here:
        var[26]=true;
        if(S13.isSelected()&&!var[25]){
            DisableVolumeExcept(V13,S13,26,12);
            System.out.println("activado");
            Conf = X.Read("config.xml");
            try{
             audio[12]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(12),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),17,V13,C13,socket);
             audio[12].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[26]=false;
         }     
        }
        if(S13.isSelected()&&var[25])
        {
            DisableVolumeExcept(V13,S13,26,12);
        }
        if(!S13.isSelected()){
            EnableVolume();
            var[26]=false;
            audio[12].detener();
            audio[12].stop();
        }
    }//GEN-LAST:event_S13ActionPerformed

    private void S21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S21ActionPerformed
        // TODO add your handling code here:
         var[42]=true;
        if(S21.isSelected()&&!var[41]){
            DisableVolumeExcept(V21,S21,42,20);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[20]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(20),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),18,V21,C21,socket);
             audio[20].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[42]=false;
         }     
        }
        if(S21.isSelected()&&var[41])
        {
            DisableVolumeExcept(V21,S21,42,20);
        }
        if(!S21.isSelected()){
            EnableVolume();
            var[42]=false;
             audio[20].detener();
            audio[20].stop();
        }
    }//GEN-LAST:event_S21ActionPerformed

    private void S6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S6ActionPerformed
        // TODO add your handling code here:
        var[12]=true;
        if(S6.isSelected()&&!var[11]){
            DisableVolumeExcept(V6,S6,12,5);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[5]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(5),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),19,V6,C6,socket);
             audio[5].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[12]=false;
         }     
        }
        if(S6.isSelected()&&var[11])
        {
            DisableVolumeExcept(V6,S6,12,5);
            
        }
        if(!S6.isSelected()){
            EnableVolume();
            var[12]=false;
            audio[5].detener();
            audio[5].stop();
        }
    }//GEN-LAST:event_S6ActionPerformed

    private void S29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S29ActionPerformed
        // TODO add your handling code here:
         var[58]=true;
        if(S29.isSelected()&&!var[57]){
            DisableVolumeExcept(V29,S29,58,28);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[28]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(28),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),20,V29,C29,socket);
             audio[28].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
                var[58]=false;
         }     
        }
        if(S29.isSelected()&&var[57])
        {
            DisableVolumeExcept(V29,S29,58,28);
        }
        if(!S29.isSelected()){
            EnableVolume();
            var[58]=false;
            audio[28].detener();
            audio[28].stop();
        }
    }//GEN-LAST:event_S29ActionPerformed

    private void S14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S14ActionPerformed
        // TODO add your handling code here:
        var[28]=true;
        
        if(S14.isSelected()&&!var[28]){
            DisableVolumeExcept(V14,S14,28,13);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[13]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(13),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),21,V14,C14,socket);
             audio[13].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[28]=false;
         }     
        }
        if(S14.isSelected()&&var[28])
        {
            DisableVolumeExcept(V14,S14,28,13);
        }
        if(!S14.isSelected()){
            EnableVolume();
            var[28]=false;
            audio[13].detener();
            audio[13].stop();
        }
    }//GEN-LAST:event_S14ActionPerformed

    private void S22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S22ActionPerformed
        // TODO add your handling code here:
        var[44]=true;
        if(S22.isSelected()&&!var[43]){
            DisableVolumeExcept(V22,S22,44,21);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[21]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(21),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),22,V22,C22,socket);
             audio[21].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[44]=false;
         }     
        }
        if(S22.isSelected()&&var[43])
        {
            DisableVolumeExcept(V22,S22,44,21);
        }
        if(!S22.isSelected()){
            EnableVolume();
            var[44]=false;
            audio[21].detener();
            audio[21].stop();
        }
    }//GEN-LAST:event_S22ActionPerformed

    private void S30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S30ActionPerformed
        // TODO add your handling code here:
         var[60]=true;
        if(S30.isSelected()&&!var[59]){
            DisableVolumeExcept(V30,S30,60,29);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             audio[29]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(29),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),23,V30,C30,socket);
             audio[29].start();
              
             
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[60]=false;
         }     
        }
        if(S30.isSelected()&&var[59])
        {
            DisableVolumeExcept(V30,S30,60,29);
        }
        if(!S30.isSelected()){
            EnableVolume();
            var[60]=false;
            audio[29].detener();
            audio[29].stop();
        }
    }//GEN-LAST:event_S30ActionPerformed

    private void S7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S7ActionPerformed
        // TODO add your handling code here:
         var[14]=true;
        if(S7.isSelected()&&!var[13]){
            DisableVolumeExcept(V7,S7,14,6);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             
             audio[6]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(6),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),24,V7,C7,socket);
             audio[6].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
              var[14]=false;
              
         }     
        }
        if(S7.isSelected()&&var[13])
        {
            DisableVolumeExcept(V7,S7,14,6);
        }
        if(!S7.isSelected()){
            EnableVolume();
            var[14]=false;
            audio[6].detener();
            audio[6].stop();
        }
    }//GEN-LAST:event_S7ActionPerformed

    private void S8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S8ActionPerformed
        // TODO add your handling code here:
        var[16]=true;
        if(S8.isSelected()&&!var[15]){
            DisableVolumeExcept(V8,S8,16,7);
            System.out.println("activado");
             Conf=X.Read("config.xml");                
         try{
             
             audio[7]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(7),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),25,V8,C8,socket);
             audio[7].start();
             
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
              
               var[16]=false;
         }    
        }
        if(S8.isSelected()&&var[15])
        {
            DisableVolumeExcept(V8,S8,16,7);
        }
        if(!S8.isSelected()){
            EnableVolume();
            var[16]=false;
            audio[7].detener();
            audio[7].stop();
        }
    }//GEN-LAST:event_S8ActionPerformed

    private void S15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S15ActionPerformed
        // TODO add your handling code here:
        var[30]=true;
        if(S15.isSelected()&&!var[29]){
            DisableVolumeExcept(V15,S15,30,14);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             
             audio[14]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(14),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),26,V15,C15,socket);
             audio[14].start();
              
          
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[30]=false;
         }  
        }
        if(S15.isSelected()&&var[29])
        {
            DisableVolumeExcept(V15,S15,30,14);
        }
        if(!S15.isSelected()){
            EnableVolume();
            var[30]=false;
            audio[7].detener();
            audio[7].stop();
        }
    }//GEN-LAST:event_S15ActionPerformed

    private void S16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S16ActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_S16ActionPerformed

    private void S23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S23ActionPerformed
        // TODO add your handling code here:
         var[46]=true;
        if(S23.isSelected()&&!var[45]){
            DisableVolumeExcept(V23,S23,46,22);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             
             audio[22]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(22),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),28,V23,C23,socket);
             audio[22].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
              var[46]=false;
         }     
        }
        if(S23.isSelected()&&var[45])
        {
            DisableVolumeExcept(V23,S23,46,22);
        }
        if(!S23.isSelected()){
            EnableVolume();
            var[46]=false;
            audio[22].detener();
            audio[22].stop();
        }
    }//GEN-LAST:event_S23ActionPerformed

    private void S24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S24ActionPerformed
        // TODO add your handling code here:
        var[48]=true;
        if(S24.isSelected()&&!var[47]){
            DisableVolumeExcept(V24,S24,48,23);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             
             audio[23]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(23),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),29,V24,C24,socket);
             audio[23].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[48]=false;
         }     
        }
        if(S24.isSelected()&&var[47])
        {
            DisableVolumeExcept(V24,S24,48,23);
        }
        if(!S24.isSelected()){
            EnableVolume();
            var[48]=false;
            audio[23].detener();
            audio[23].stop();
        }
    }//GEN-LAST:event_S24ActionPerformed

    private void S31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S31ActionPerformed
        // TODO add your handling code here:
         var[62]=true;
        if(S31.isSelected()&&!var[61]){
            DisableVolumeExcept(V31,S31,62,30);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             
             audio[30]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(30),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),30,V31,C31,socket);
             audio[30].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
              
               var[62]=false;
         }     
        }
        if(S31.isSelected()&&var[61])
        {
            DisableVolumeExcept(V31,S31,62,30);
        }
        if(!S31.isSelected()){
            EnableVolume();
            var[62]=false;
             audio[23].detener();
            audio[23].stop();
        }
    }//GEN-LAST:event_S31ActionPerformed

    private void S32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S32ActionPerformed
        // TODO add your handling code here:
        var[64]=true;
        if(S32.isSelected()&&!var[63]){
            DisableVolumeExcept(V32,S32,64,31);
            System.out.println("activado");
            Conf=X.Read("config.xml");                
         try{
             
             audio[31]=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(31),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),31,V32,C32,socket);
             audio[31].start();
              
              
         }
         catch(Exception e){
             System.out.println("Error de hilo ");
               
               var[64]=false;
         }     
        }
        if(S32.isSelected()&&var[63])
        {
            DisableVolumeExcept(V32,S32,64,31);
        }
        if(!S32.isSelected()){
            EnableVolume();
            var[64]=false;
            audio[31].detener();
            audio[31].stop();
        }
    }//GEN-LAST:event_S32ActionPerformed

    
    
    
    private  void AlternarAudio(int alternar,ThreadAudio audio,int numerocanal,Configuracion C,JButton J,JSlider V){
          
          alternar++;   
    if(alternar==1){
         C=X.Read("config.xml");        
         
         audio=new ThreadAudio(Conf.GetNet(),Conf.GetLista().get(0),Conf.GetMultiCast(),P,Conf.GetFrecuencia(),Conf.GetMuestra(),numerocanal,V,C1,socket);
         audio.start();   
         J.setBackground(Color.GREEN);
    }   

    if(alternar==2){
         audio.detener();
         audio.stop();
         alternar=0;
         J.setBackground(Color.white);
        }
    }
    
    

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
    private javax.swing.JButton C4;
    private javax.swing.JButton C5;
    private javax.swing.JButton C6;
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
    private javax.swing.JLabel I4;
    private javax.swing.JLabel I5;
    private javax.swing.JLabel I6;
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
    private javax.swing.JToggleButton S4;
    private javax.swing.JToggleButton S5;
    private javax.swing.JToggleButton S6;
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
    private javax.swing.JSlider V4;
    private javax.swing.JSlider V5;
    private javax.swing.JSlider V6;
    private javax.swing.JSlider V7;
    private javax.swing.JSlider V8;
    private javax.swing.JSlider V9;
    // End of variables declaration//GEN-END:variables

    private void elseif() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
