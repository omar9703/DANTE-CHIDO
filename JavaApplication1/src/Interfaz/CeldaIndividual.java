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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
public class CeldaIndividual extends JPanel{
    private final int ANCHO_CELDA = 148;
    private final int ALTO_CELDA = 240;
    private final int ALTO_SUPERIOR = 190;
    private int numeroCelda;
    Configuracion Conf;
    // Componentes de la celda
    private JSlider slider;
    private JLabel labelCentral;
    private JToggleButton toggleButton;
    private JButton botonPrincipal;
    
    public CeldaIndividual(int numeroCelda, Configuracion conf) {
        this.numeroCelda = numeroCelda;
        this.Conf = conf;
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
            if (toggle.isSelected()) {
                
               
            } else {
                
                
            }
        });
        
        return toggle;
    }
    
    private JButton crearBotonPrincipal() {
        JButton boton = new JButton(Conf.GetAlias().get(numeroCelda));
        boton.setFont(new Font("Arial", Font.BOLD, 17));
        boton.setBackground(new Color(102, 102, 102));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setMargin(new Insets(4,3,4,3));
        boton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this,
                "Ejecutando acción del panel " + (numeroCelda + 1),
                "Botón Principal", 
                JOptionPane.INFORMATION_MESSAGE
            );
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
