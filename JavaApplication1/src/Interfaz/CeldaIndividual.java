/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

/**
 *
 * @author ocamp
 */
import Datos.Configuracion;
import Datos.GlobalConfig;
import Negocio.ThreadAudio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class CeldaIndividual extends JPanel{
    private final int ANCHO_CELDA = 148;
    private final int ALTO_CELDA = 240;
    private final int ALTO_SUPERIOR = 190;
    private int numeroCelda;
    Configuracion Conf;
    // Componentes de la celda
    public JSlider slider;
    public JLabel labelCentral;
    public JToggleButton toggleButton;
    public JButton botonPrincipal;
    private MixerDynamic md;
    
    public CeldaIndividual(int numeroCelda, Configuracion conf, MixerDynamic md) {
        this.numeroCelda = numeroCelda;
        this.Conf = conf;
        this.md = md; 
        inicializarCelda();
    }
    
    private void inicializarCelda() {
        setLayout(null); // Layout nulo para posicionamiento absoluto
        setBackground(Color.black);
        setPreferredSize(new Dimension(ANCHO_CELDA, ALTO_CELDA));
        setMinimumSize(new Dimension(ANCHO_CELDA, ALTO_CELDA));
        setMaximumSize(new Dimension(ANCHO_CELDA, ALTO_CELDA));
        
        // Inicializar componentes
        inicializarComponentes();
        
        // Posicionar componentes
        posicionarComponentes();
    }
    
    private void inicializarComponentes() {
        // Slider vertical
        slider = crearSliderVertical();
        
        // JLabel principal
        labelCentral = crearLabelCentral();
        
        // ToggleButton con icono
        toggleButton = crearToggleButton();
        
        // Botón principal
        botonPrincipal = crearBotonPrincipal();
    }
    
    private void posicionarComponentes() {
        // Slider vertical (22x190) en posición (0,0)
        slider.setBounds(0, 0, 22, ALTO_SUPERIOR);
        add(slider);
        
        // JLabel principal (116x190) en posición (24,0)
        labelCentral.setBounds(24, 0, 116, ALTO_SUPERIOR);
        add(labelCentral);
        
        // ToggleButton (42x42) en posición (0,194)
        toggleButton.setBounds(0, ALTO_SUPERIOR + 4, 42, 42);
        add(toggleButton);
        
        // Botón principal (96x42) en posición (44,194)
        botonPrincipal.setBounds(44, ALTO_SUPERIOR + 4, 96, 42);
        add(botonPrincipal);
    }
    
    private JSlider crearSliderVertical() {
         JSlider slider = new JSlider(JSlider.VERTICAL, 0, 100, 50);
        slider.setBackground(new Color(47,55,76));
        slider.setMajorTickSpacing(0);
        slider.setMinorTickSpacing(0);
        slider.setMaximum(10);
        slider.setMinimum(0);
        slider.setOrientation(SwingConstants.VERTICAL);
        slider.setPaintTicks(false);
        slider.setPaintTrack(true);
        slider.setPaintLabels(false);
        slider.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e)
            {
                md.ganancias.set(numeroCelda, slider.getValue());
            }
    });
        
    
        return slider;
    }
    
    private JLabel crearLabelCentral() {
        JLabel label = new JLabel();
        //label.setOpaque(true);
        label.setBackground(Color.white);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 10));
        label.setForeground(Color.black);
        if(!Conf.GetListaImages().get(numeroCelda).equals("0")){
        try{
                    label.setIcon(new javax.swing.ImageIcon(Conf.GetListaImages().get(numeroCelda)));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                label.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
        return label;
    }
    
    private JToggleButton crearToggleButton() {
       JToggleButton toggle = new JToggleButton();
        toggle.setFont(new Font("Arial", Font.BOLD, 9));
        toggle.setBackground(new Color(69, 93, 220));
        toggle.setForeground(new Color(186, 195, 242));
        toggle.setFocusPainted(false);
        try {
            toggle.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/Interfaz/microfono.png"))));
        } catch (IOException ex) {
            Logger.getLogger(MixerDynamic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        toggle.addActionListener((ActionEvent e) -> {
             md.var[(numeroCelda + 1)*2]=true;
        if(toggle.isSelected()&&!md.var[((numeroCelda + 1)*2)-1]){
            md.DisableVolumeExcept(slider,toggleButton,(numeroCelda + 1)*2,(numeroCelda + 1)-1);
            System.out.println("activado");
                        
         try{
             md.audioSolo[(numeroCelda + 1)-1]=new ThreadAudio(GlobalConfig.Network,GlobalConfig.ListaPuertos.get(0),GlobalConfig.multicast,md.P,GlobalConfig.Frecuencia,GlobalConfig.Muestra,numeroCelda,slider,this.botonPrincipal,md.socket);
             md.audioSolo[(numeroCelda + 1)-1].start();
             md.varNoexcept[(numeroCelda + 1)] = true; 
              
         }
         catch(Exception ex){
             System.out.println("Error de hilo ");
               md.var[(numeroCelda + 1)*2]=false;
         }     
        }
        if(toggle.isSelected()&&md.var[((numeroCelda + 1)*2)-1])
        {
            md.DisableVolumeExcept(slider,toggleButton,(numeroCelda + 1)*2,(numeroCelda + 1)-1);
        }
        if(!toggle.isSelected()){
            md.EnableVolume();
            md.var[(numeroCelda + 1)*2]=false;
            try{
                if(md.varNoexcept[(numeroCelda + 1)]){
                    md.audioSolo[(numeroCelda + 1)-1].detener();
                    md.audioSolo[(numeroCelda + 1)-1].stop();
                }
                
            }
            catch(Exception ex)
            {
            }
            
            toggle.setBackground(new Color(69, 93, 220));
            toggle.setIcon(new ImageIcon(getClass().getResource("microfono.png")));
        }
        });
        
        return toggle;
    }
    
    private JButton crearBotonPrincipal() {
        JButton boton = new JButton(Conf.GetAlias().get(numeroCelda));
        boton.setFont(new Font("Arial", Font.BOLD, 17));
        boton.setBackground(new Color(25, 31, 49));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setMargin(new Insets(2,2,2,2));
        boton.addActionListener((ActionEvent e) -> {
        md.var[((numeroCelda + 1)*2)-1]=true;
        if(!md.var[(numeroCelda + 1)*2])
        {// TODO add your handling code here:     
            md.alternar[(numeroCelda + 1)-1]++;   
            if(md.alternar[(numeroCelda + 1)-1]==1){

                 try{
                                                   
                    md.audio[(numeroCelda + 1)-1]=new ThreadAudio(GlobalConfig.Network,GlobalConfig.ListaPuertos.get(0),GlobalConfig.multicast,md.P,GlobalConfig.Frecuencia,GlobalConfig.Muestra,numeroCelda,slider,boton,md.socket);
                    md.audio[(numeroCelda + 1)-1].start();
                    boton.setBackground(Color.GREEN);
                    md.varNoexcept[(numeroCelda + 1)] = false; 
                                     
                 }
                 catch(Exception ex){
                    System.out.println("Error de hilo ");
                    boton.setBackground(Color.RED);
                    slider.setEnabled(false);
                    //var[index-1]=false;
                 }


            }   

            if(md.alternar[(numeroCelda + 1)-1]==2){
                 md.audio[(numeroCelda + 1)-1].detener();
                 md.audio[(numeroCelda + 1)-1].stop();
                 md.alternar[(numeroCelda + 1)-1]=0;
                 boton.setBackground(new Color(25, 31, 49));
                 //var[index-1]=false;
                    
                 md.var[((numeroCelda + 1)*2)-1]=false;
            }
        }
        });
        
        return boton;
    }
    public int getValorSlider() {
        return slider.getValue();
    }
    
    public void setValorSlider(int valor) {
        slider.setValue(valor);
    }
    
    public boolean isToggleActivado() {
        return toggleButton.isSelected();
    }
    
    public void setToggleActivado(boolean activado) {
        toggleButton.setSelected(activado);
        if (activado) {
            toggleButton.setBackground(new Color(220, 255, 220));
        } else {
            toggleButton.setBackground(new Color(245, 245, 245));
        }
    }
    
    public String getTextoLabel() {
        return labelCentral.getText();
    }
    
    public void setTextoLabel(String texto) {
        labelCentral.setText(texto);
        System.out.println(texto);
        this.revalidate();
        this.updateUI();
    }
    
    public int getNumeroCelda() {
        return numeroCelda;
    }
    
    public JSlider getSlider() {
        return slider;
    }
    
    public JToggleButton getToggleButton() {
        return toggleButton;
    }
    
    public JButton getBotonPrincipal() {
        return botonPrincipal;
    }
    
}
