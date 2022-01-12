/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import Negocio.Coordinador;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import Datos.XmlRead;
import Datos.Configuracion;
import Datos.volumen;
import javax.swing.JButton;
import javax.swing.JComboBox;
import Negocio.NetworkInterfaces;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
/**
 *
 * @author leone
 */
public class Panel extends javax.swing.JFrame {

    /**
     * Creates new form Panel
     */
   private PanelAjustes ajustes;
   //private PanelMixer mixer;
   private newPanelMixer mixer;
   private PanelLogs logs;
   private Coordinador C;
   private Settings set;
   private ArrayList<String> canales;
   private ArrayList<Integer> ganancias;
   private String ReportDay;
   private DateFormat df;
   private Date today;
   private XmlRead Xread;
   private Configuracion Conf;
   private NetworkInterfaces interfaces;
   private ArrayList<String> listaInterfaces;
   private String Red=null;
   public volumen vol;
   private boolean isMixer=false;
   private boolean isSetting = false;
   
    public Panel() {
       initComponents();
       this.setResizable(false);
       this.setSize(new Dimension(1366,766));
       this.setLocationRelativeTo(null);
       C=new Coordinador(this);
       C.SetPanel(this);
       //mixer= new PanelMixer(this);
       mixer= new newPanelMixer(this);
       //ajustes = new PanelAjustes(C,this,mixer);
       set=new Settings(C,this,mixer);
       this.setBackground(Color.yellow);
    //   this.Bconnect.setEnabled(false);
       primero.setBorder(null);
       segundo.setBorder(null);
       tercero.setBorder(null);
       cuarto.setBorder(null);
       
       primero.setBackground(Color.black);
       primero.setForeground(Color.white);
       jPanel1.setBackground(Color.red);

       
       //fecha
       df=new SimpleDateFormat("yyyy/dd/MM HH:mm:ss");
       today=Calendar.getInstance().getTime();
       
         
       Xread=new XmlRead();
         
         //apagar temporalmente logs (boton)
       //this.Bping.setEnabled(false);
    //   this.Bstop.setEnabled(false);
       
       //obtener interfaces
       interfaces=new NetworkInterfaces();
       listaInterfaces=interfaces.GetInterfaces();
       networks.addItem("default");
       for(int i=0;i<listaInterfaces.size();i++){
           if(i%2==0){
            networks.addItem(listaInterfaces.get(i));
           }
           
       }
    
       jScrollPane2.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
        public void adjustmentValueChanged(AdjustmentEvent e) {  
            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
        }
    });   
      vol = new volumen(vPrincipal); 
      
      jLabel4.setIcon(new javax.swing.ImageIcon("nuevoFondo.png"));
         
      EtiquetaConect.setVisible(false);
      EtiquetaStatus.setVisible(false);
      MSG.setVisible(false);
      networks.setVisible(false);
      jLabel1.setVisible(false);

    }
    
    
    public void setpanel(Networks NT){
      scrollPane1.add(NT);
    }
    
    public void setpanel(newPanelMixer mixer){
       
       scrollPane1.add(mixer);
    }
    
    public void setpanel(PanelAjustes ajustes){
       
        scrollPane1.add(ajustes);
        
    }
    
    public void setpanel(PanelLogs logs){
      scrollPane1.add(logs);
    }
    
    public void setpanelAdj(Settings set){
        scrollPane1.add(set);
    }
    
    public void setEtiquetaStatus(String estado){
      this.EtiquetaStatus.setText(estado);
    }
    
    public void setEtiquetaCanales(String canales){
        this.Etiquetacanales.setText(canales);
    }
    
    public void setEtiquetaConect(String ping){
        this.EtiquetaConect.setText(ping);
    }
    
    public void SetCanales(ArrayList<String> canales){
      this.canales=canales;
    }
    
    public ArrayList<String> GetCanales(){
        return mixer.GetCanales();
    }
    
    public void SetCGanancias(ArrayList<Integer> ganancias){
      this.ganancias=ganancias;
    }
    
    public ArrayList<Integer> GetGanancias(){
        return mixer.GetGanacias();
    }
    
    public void SetLog(String Texto){
        today=Calendar.getInstance().getTime();
        ReportDay=df.format(today);
   this.jTextArea1.append(ReportDay+"  -  "+Texto+'\n');
       
    }
    
    public void SetMSG(String Texto,boolean status){
        this.MSG.setText(Texto);
           
    }
    
    
    public void SetTime(){
        Calendar calendario=Calendar.getInstance();
       int hora=calendario.get(Calendar.HOUR_OF_DAY);
       int minuto=calendario.get(Calendar.MINUTE);
       int segundo=calendario.get(Calendar.SECOND);
       String h=Integer.toString(hora);
       String m=Integer.toString(minuto);
       String seg=Integer.toString(segundo);     
       jLabel1.setText(h+":"+m+":"+seg);
    }
    
    
    public void TurnOffSend(){
    //  this.Bconnect.setEnabled(false);
    }
    
    public void TurnOnSend(){
    //  this.Bconnect.setEnabled(true);
    }
    
    public void TurnOffStop(){
    //  this.Bstop.setEnabled(false);
    }
    
    public void TurnOnStop(){
     // this.Bstop.setEnabled(true);
    }
            
    public void SetAlarma(int status){
        if(status==0){
            try{
               alarma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green.png")));
            }
            catch(Exception e){
                System.out.println("error de imagen");
                this.SetLog("error de imagen");
            }
           
        }
        else if(status==1){
        
              try{
               alarma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/yellow.png")));
            }
            catch(Exception e){
                System.out.println("error de imagen");
                this.SetLog("error de imagen");
            }
        }
        
        else if(status==2){
        
              try{
               alarma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red.png")));
            }
            catch(Exception e){
                System.out.println("error de imagen");
                this.SetLog("error de imagen");
            }
        }
    }
    
   
    
    public String GetNetwork(){
        for(int i=0;i<listaInterfaces.size();i++){
            if(Red.equals(listaInterfaces.get(i))){
                return listaInterfaces.get(i+1);
            }
        }
        
      return null;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
    
    @SuppressWarnings("unchecked")
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane1 = new java.awt.ScrollPane();
        Bsettings = new javax.swing.JButton();
        cLEAR = new javax.swing.JButton();
        primero = new javax.swing.JButton();
        segundo = new javax.swing.JButton();
        cuarto = new javax.swing.JButton();
        Bmixer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        EtiquetaStatus = new javax.swing.JLabel();
        EtiquetaConect = new javax.swing.JLabel();
        Etiquetacanales = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        MSG = new javax.swing.JTextField();
        alarma = new javax.swing.JLabel();
        networks = new javax.swing.JComboBox<>();
        tercero = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        vPrincipal = new javax.swing.JSlider();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 102));
        setForeground(new java.awt.Color(0, 51, 102));
        getContentPane().setLayout(null);

        scrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        scrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(scrollPane1);
        scrollPane1.setBounds(90, 140, 1260, 600);

        Bsettings.setBackground(new java.awt.Color(231, 25, 76));
        Bsettings.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Bsettings.setText("SETTINGS");
        Bsettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsettingsActionPerformed(evt);
            }
        });
        getContentPane().add(Bsettings);
        Bsettings.setBounds(890, 60, 120, 40);

        cLEAR.setBackground(new java.awt.Color(69, 93, 220));
        cLEAR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cLEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cLEARActionPerformed(evt);
            }
        });
        getContentPane().add(cLEAR);
        cLEAR.setBounds(1170, 60, 120, 40);

        primero.setBackground(new java.awt.Color(35, 38, 49));
        primero.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        primero.setForeground(new java.awt.Color(65, 71, 90));
        primero.setText("1-16");
        primero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                primeroMousePressed(evt);
            }
        });
        primero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primeroActionPerformed(evt);
            }
        });
        getContentPane().add(primero);
        primero.setBounds(0, 140, 80, 70);

        segundo.setBackground(new java.awt.Color(35, 38, 49));
        segundo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        segundo.setForeground(new java.awt.Color(65, 71, 90));
        segundo.setText("17-32");
        segundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundoActionPerformed(evt);
            }
        });
        getContentPane().add(segundo);
        segundo.setBounds(0, 240, 80, 70);

        cuarto.setBackground(new java.awt.Color(35, 38, 49));
        cuarto.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cuarto.setForeground(new java.awt.Color(65, 71, 90));
        cuarto.setText("49-64");
        cuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuartoActionPerformed(evt);
            }
        });
        getContentPane().add(cuarto);
        cuarto.setBounds(0, 440, 80, 70);

        Bmixer.setBackground(new java.awt.Color(231, 25, 76));
        Bmixer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Bmixer.setText("MIXER");
        Bmixer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BmixerActionPerformed(evt);
            }
        });
        getContentPane().add(Bmixer);
        Bmixer.setBounds(1030, 60, 120, 40);

        jLabel1.setBackground(new java.awt.Color(51, 0, 204));
        jLabel1.setFont(new java.awt.Font("Verdana", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TIME");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(1170, 10, 140, 46);

        EtiquetaStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EtiquetaStatus.setForeground(new java.awt.Color(255, 255, 255));
        EtiquetaStatus.setText("CHECKING SERVICE");
        getContentPane().add(EtiquetaStatus);
        EtiquetaStatus.setBounds(900, 80, 125, 36);

        EtiquetaConect.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EtiquetaConect.setForeground(new java.awt.Color(255, 255, 255));
        EtiquetaConect.setText("FINDING SERVER");
        getContentPane().add(EtiquetaConect);
        EtiquetaConect.setBounds(450, 80, 116, 36);

        Etiquetacanales.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Etiquetacanales.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(Etiquetacanales);
        Etiquetacanales.setBounds(816, 24, 38, 36);

        jTextArea1.setBackground(new java.awt.Color(51, 51, 51));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(220, 970, 410, 50);

        MSG.setBackground(new java.awt.Color(51, 0, 51));
        MSG.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        MSG.setForeground(new java.awt.Color(204, 204, 204));
        MSG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MSGActionPerformed(evt);
            }
        });
        getContentPane().add(MSG);
        MSG.setBounds(580, 60, 290, 33);
        getContentPane().add(alarma);
        alarma.setBounds(310, 20, 52, 54);

        networks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                networksActionPerformed(evt);
            }
        });
        getContentPane().add(networks);
        networks.setBounds(880, 690, 130, 24);

        tercero.setBackground(new java.awt.Color(35, 38, 49));
        tercero.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tercero.setForeground(new java.awt.Color(65, 71, 90));
        tercero.setText("33-48");
        tercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terceroActionPerformed(evt);
            }
        });
        getContentPane().add(tercero);
        tercero.setBounds(0, 340, 80, 70);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(1030, 690, 127, 20);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 1890, 740);

        vPrincipal.setBackground(new java.awt.Color(0, 0, 0));
        vPrincipal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vPrincipalStateChanged(evt);
            }
        });
        getContentPane().add(vPrincipal);
        vPrincipal.setBounds(900, 10, 210, 30);
        getContentPane().add(jPanel4);
        jPanel4.setBounds(80, 440, 10, 70);
        getContentPane().add(jPanel3);
        jPanel3.setBounds(80, 340, 10, 70);
        getContentPane().add(jPanel2);
        jPanel2.setBounds(80, 240, 10, 70);

        jPanel1.setBackground(java.awt.Color.red);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(80, 140, 10, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BsettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BsettingsActionPerformed
        // TODO add your handling code here:
        this.setpanelAdj(set);
      //  this.Bconnect.setEnabled(false);
      //  this.Bstop.setEnabled(false);
      isMixer = false;
      isSetting = true;
    }//GEN-LAST:event_BsettingsActionPerformed

    private void BmixerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BmixerActionPerformed
        // TODO add your handling code here:
        this.setpanel(mixer);
        //this.Bconnect.setEnabled(rootPaneCheckingEnabled);
        //this.Bstop.setEnabled(true);
        isMixer = true;
        isSetting=false;
    }//GEN-LAST:event_BmixerActionPerformed

    private void networksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_networksActionPerformed
        // TODO add your handling code here:
        Red=networks.getSelectedItem().toString();
    }//GEN-LAST:event_networksActionPerformed

    private void MSGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MSGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MSGActionPerformed

    private void vPrincipalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_vPrincipalStateChanged
            vol.setSystemVolume(vPrincipal.getValue());
        // TODO add your handling code here:
    }//GEN-LAST:event_vPrincipalStateChanged

    private void primeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primeroActionPerformed
        // TODO add your handling code here:
        primero.setBackground(Color.black);
        primero.setForeground(Color.white);
        jPanel1.setBackground(Color.red);
        
        
        segundo.setBackground(new Color(35, 38, 49));
        segundo.setForeground(new Color(65, 71, 90));
        jPanel2.setBackground(new Color(35, 38, 49));
        
        tercero.setBackground(new Color(35, 38, 49));
        tercero.setForeground(new Color(65, 71, 90));
        jPanel3.setBackground(new Color(35, 38, 49));
        
        cuarto.setBackground(new Color(35, 38, 49));
        cuarto.setForeground(new Color(65, 71, 90));
        jPanel4.setBackground(new Color(35, 38, 49));
        
        try{
            if(isMixer){
                this.scrollPane1.setScrollPosition(0, 0);
            }
            
        }
        catch(Exception ex){
        }
        
    }//GEN-LAST:event_primeroActionPerformed

    private void segundoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundoActionPerformed
        // TODO add your handling code here:
        segundo.setBackground(Color.black);
        segundo.setForeground(Color.white);
        jPanel2.setBackground(Color.red);
        
        
        primero.setBackground(new Color(35, 38, 49));
        primero.setForeground(new Color(65, 71, 90));
        jPanel1.setBackground(new Color(35, 38, 49));
        
        tercero.setBackground(new Color(35, 38, 49));
        tercero.setForeground(new Color(65, 71, 90));
        jPanel3.setBackground(new Color(35, 38, 49));
        
        cuarto.setBackground(new Color(35, 38, 49));
        cuarto.setForeground(new Color(65, 71, 90));
        jPanel4.setBackground(new Color(35, 38, 49));
        
        try{
            if(isMixer){
                this.scrollPane1.setScrollPosition(0, 525);
            }
            
        }
        catch(Exception ex){
        }
    }//GEN-LAST:event_segundoActionPerformed

    private void terceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terceroActionPerformed
        // TODO add your handling code here:
        tercero.setBackground(Color.black);
        tercero.setForeground(Color.white);
        jPanel3.setBackground(Color.red);
        
        
        primero.setBackground(new Color(35, 38, 49));
        primero.setForeground(new Color(65, 71, 90));
        jPanel1.setBackground(new Color(35, 38, 49));
        
        segundo.setBackground(new Color(35, 38, 49));
        segundo.setForeground(new Color(65, 71, 90));
        jPanel2.setBackground(new Color(35, 38, 49));
        
        cuarto.setBackground(new Color(35, 38, 49));
        cuarto.setForeground(new Color(65, 71, 90));
        jPanel4.setBackground(new Color(35, 38, 49));
        
        try{
            if(isMixer){
                this.scrollPane1.setScrollPosition(0, 1050);
            }
            
        }
        catch(Exception ex){
        }
    }//GEN-LAST:event_terceroActionPerformed

    private void cuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuartoActionPerformed
        // TODO add your handling code here:
        cuarto.setBackground(Color.black);
        cuarto.setForeground(Color.white);
        jPanel4.setBackground(Color.red);
        
        
         primero.setBackground(new Color(35, 38, 49));
        primero.setForeground(new Color(65, 71, 90));
        jPanel1.setBackground(new Color(35, 38, 49));
        
        segundo.setBackground(new Color(35, 38, 49));
        segundo.setForeground(new Color(65, 71, 90));
        jPanel2.setBackground(new Color(35, 38, 49));
        
        tercero.setBackground(new Color(35, 38, 49));
        tercero.setForeground(new Color(65, 71, 90));
        jPanel3.setBackground(new Color(35, 38, 49));
        
        try{
            if(isMixer){
                this.scrollPane1.setScrollPosition(0, 1575);
            }
            
        }
        catch(Exception ex){
        }
    }//GEN-LAST:event_cuartoActionPerformed

    private void cLEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cLEARActionPerformed
        // TODO add your handling code here:
        mixer.ClearAll();
    }//GEN-LAST:event_cLEARActionPerformed

    private void primeroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_primeroMousePressed
    }//GEN-LAST:event_primeroMousePressed

    
    /**
     * @param args the command line arguments
     */
    
    /*
    
    /*
    
    /*
    */
    
    /*
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Panel().setVisible(true);
            }
        });
    }
    */
    
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bmixer;
    private javax.swing.JButton Bsettings;
    private javax.swing.JLabel EtiquetaConect;
    private javax.swing.JLabel EtiquetaStatus;
    private javax.swing.JLabel Etiquetacanales;
    private javax.swing.JTextField MSG;
    private javax.swing.JLabel alarma;
    private javax.swing.JButton cLEAR;
    private javax.swing.JButton cuarto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox<String> networks;
    private javax.swing.JButton primero;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JButton segundo;
    private javax.swing.JButton tercero;
    private javax.swing.JSlider vPrincipal;
    // End of variables declaration//GEN-END:variables
}
