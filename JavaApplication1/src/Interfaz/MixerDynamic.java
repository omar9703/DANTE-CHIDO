package Interfaz;

import Datos.Configuracion;
import Datos.GlobalConfig;
import Datos.XmlRead;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class MixerDynamic extends JPanel {
    private int contadorPaneles = 0;
    public Configuracion Conf;
    private final int ANCHO_CELDA = 148;
    private final int ALTO_CELDA = 240;
    private XmlRead X;
    private Panel P;
    public MixerDynamic() {
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
                inicializarPanel();
    }
    
    private void inicializarPanel() {
        setLayout(new GridLayout(0, 8, 8, 70));
        setBorder(BorderFactory.createEmptyBorder(5, 2, 8, 8));
        setBackground( Color.black);
        
        agregarPaneles(64);
    }
    
    public void agregarPanel() {
       CeldaIndividual celda = new CeldaIndividual(contadorPaneles,Conf);
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
    public java.util.List<CeldaIndividual> getTodasLasCeldas() {
        java.util.List<CeldaIndividual> celdas = new java.util.ArrayList<>();
        for (Component comp : getComponents()) {
            if (comp instanceof CeldaIndividual) {
                celdas.add((CeldaIndividual) comp);
            }
        }
        return celdas;
    }

    

}