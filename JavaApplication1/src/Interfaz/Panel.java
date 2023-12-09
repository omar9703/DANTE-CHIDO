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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import models.CommentRequest;
import models.CurrentUser;
import models.ListCharacters;
import static models.ListCharacters.characters;
import serviceMark.AliasHandlerEvent;
import serviceMark.CommentSender;
/**
 *
 * @author leone
 */
public class Panel extends javax.swing.JFrame implements AliasHandlerEvent.EventoListener{

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
   private LocalDateTime timeInit;
   private LocalDateTime timeEnd;
   
   private ArrayList<String> charactersList;
   
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
       Conf = new Configuracion();
       
    
        
        Conf=Xread.Read("config.xml");
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
      
      jLabel4.setIcon(new javax.swing.ImageIcon("fondosinlogo.png"));
         
      EtiquetaConect.setVisible(false);
      EtiquetaStatus.setVisible(false);
      MSG.setVisible(false);
      networks.setVisible(false);
      jLabel1.setVisible(false);
      
        LoadImageProject(Conf);
        
        //suscribe event
        AliasHandlerEvent.suscribeEvento(this);
        
        charactersList = new ArrayList<String>();
    }
    
    @Override
    public void onEvento(String mensaje) {
        // Manejar el evento
        jTextArea2.setText(jTextArea2.getText()+" "+mensaje+" ");
        if(!charactersList.contains(mensaje)){
            charactersList.add(mensaje);
        }
        
         
    }
    
    public void LoadImageProject(Configuracion C){
        if(!C.GetpathImageProject().equals("0")){
                try{
                    jLabel3.setIcon(new javax.swing.ImageIcon(C.GetpathImageProject()));
                    this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }      
        }
       else{
           try{
                jLabel3.setIcon(new javax.swing.ImageIcon("no_image.png"));
                this.repaint();
                }
                catch(Exception ex){
                    System.out.println("ERROR DE ESCRITURA");
                }
       
       }
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

        jLabel3 = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        Bsettings = new javax.swing.JButton();
        cLEAR = new javax.swing.JButton();
        primero = new javax.swing.JButton();
        segundo = new javax.swing.JButton();
        cuarto = new javax.swing.JButton();
        Bmixer2 = new javax.swing.JButton();
        Bmixer1 = new javax.swing.JButton();
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
        jLabel5 = new javax.swing.JLabel();
        vPrincipal = new javax.swing.JSlider();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 102));
        setForeground(new java.awt.Color(0, 51, 102));
        getContentPane().setLayout(null);

        jLabel3.setBackground(new java.awt.Color(102, 41, 188));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(694, 10, 180, 120);

        scrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        scrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(scrollPane1);
        scrollPane1.setBounds(360, 140, 990, 590);

        Bsettings.setBackground(new java.awt.Color(231, 25, 76));
        Bsettings.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        Bsettings.setForeground(new java.awt.Color(255, 255, 255));
        Bsettings.setText("SETTINGS");
        Bsettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsettingsActionPerformed(evt);
            }
        });
        getContentPane().add(Bsettings);
        Bsettings.setBounds(910, 20, 120, 40);

        cLEAR.setBackground(new java.awt.Color(69, 93, 220));
        cLEAR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cLEAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/basura.png"))); // NOI18N
        cLEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cLEARActionPerformed(evt);
            }
        });
        getContentPane().add(cLEAR);
        cLEAR.setBounds(1190, 20, 120, 40);

        primero.setBackground(new java.awt.Color(35, 38, 49));
        primero.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 30)); // NOI18N
        primero.setForeground(new java.awt.Color(65, 71, 90));
        primero.setText("01-16");
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
        primero.setBounds(0, 180, 84, 70);

        segundo.setBackground(new java.awt.Color(35, 38, 49));
        segundo.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 30)); // NOI18N
        segundo.setForeground(new java.awt.Color(65, 71, 90));
        segundo.setText("17-32");
        segundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundoActionPerformed(evt);
            }
        });
        getContentPane().add(segundo);
        segundo.setBounds(0, 250, 84, 70);

        cuarto.setBackground(new java.awt.Color(35, 38, 49));
        cuarto.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 30)); // NOI18N
        cuarto.setForeground(new java.awt.Color(65, 71, 90));
        cuarto.setText("49-64");
        cuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuartoActionPerformed(evt);
            }
        });
        getContentPane().add(cuarto);
        cuarto.setBounds(0, 390, 84, 70);

        Bmixer2.setBackground(new java.awt.Color(231, 25, 76));
        Bmixer2.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 14)); // NOI18N
        Bmixer2.setForeground(new java.awt.Color(255, 255, 255));
        Bmixer2.setText("TERMINAR");
        Bmixer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bmixer2ActionPerformed(evt);
            }
        });
        getContentPane().add(Bmixer2);
        Bmixer2.setBounds(240, 147, 120, 40);

        Bmixer1.setBackground(new java.awt.Color(51, 51, 255));
        Bmixer1.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 14)); // NOI18N
        Bmixer1.setForeground(new java.awt.Color(255, 255, 255));
        Bmixer1.setLabel("INICIAR");
        Bmixer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bmixer1ActionPerformed(evt);
            }
        });
        getContentPane().add(Bmixer1);
        Bmixer1.setBounds(90, 150, 120, 40);

        Bmixer.setBackground(new java.awt.Color(231, 25, 76));
        Bmixer.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 24)); // NOI18N
        Bmixer.setForeground(new java.awt.Color(255, 255, 255));
        Bmixer.setText("MIXER");
        Bmixer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BmixerActionPerformed(evt);
            }
        });
        getContentPane().add(Bmixer);
        Bmixer.setBounds(1050, 20, 120, 40);

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
        jTextArea1.setPreferredSize(new java.awt.Dimension(167, 94));
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
        MSG.setBounds(780, 60, 40, 20);
        getContentPane().add(alarma);
        alarma.setBounds(310, 20, 52, 54);

        networks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                networksActionPerformed(evt);
            }
        });
        getContentPane().add(networks);
        networks.setBounds(880, 690, 130, 22);

        tercero.setBackground(new java.awt.Color(35, 38, 49));
        tercero.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 30)); // NOI18N
        tercero.setForeground(new java.awt.Color(65, 71, 90));
        tercero.setText("33-48");
        tercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terceroActionPerformed(evt);
            }
        });
        getContentPane().add(tercero);
        tercero.setBounds(0, 320, 84, 70);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(1030, 690, 127, 20);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(-30, 0, 1890, 740);

        vPrincipal.setBackground(new java.awt.Color(0, 0, 0));
        vPrincipal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vPrincipalStateChanged(evt);
            }
        });
        getContentPane().add(vPrincipal);
        vPrincipal.setBounds(950, 75, 360, 30);

        jPanel4.setBackground(new java.awt.Color(35, 38, 49));
        getContentPane().add(jPanel4);
        jPanel4.setBounds(83, 390, 7, 70);

        jPanel3.setBackground(new java.awt.Color(35, 38, 49));
        getContentPane().add(jPanel3);
        jPanel3.setBounds(83, 320, 7, 70);

        jPanel2.setBackground(new java.awt.Color(35, 38, 49));
        getContentPane().add(jPanel2);
        jPanel2.setBounds(83, 250, 7, 70);

        jPanel1.setBackground(java.awt.Color.red);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(83, 180, 7, 70);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/bocina.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(910, 70, 40, 40);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 1350, 740);

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextArea2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextArea2FocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(90, 186, 270, 540);

        jButton1.setBackground(new java.awt.Color(51, 204, 0));
        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(190, 120, 63, 23);

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

    private void Bmixer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bmixer1ActionPerformed
        // TODO add your handling code here:
        timeInit=LocalDateTime.now();
        
        
    }//GEN-LAST:event_Bmixer1ActionPerformed

    private void Bmixer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bmixer2ActionPerformed
        // TODO add your handling code here:
        timeEnd=LocalDateTime.now();
    }//GEN-LAST:event_Bmixer2ActionPerformed

    private void jTextArea2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea2FocusGained
        // TODO add your handling code here:
        System.out.println("empiezo a escribir");
    }//GEN-LAST:event_jTextArea2FocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(timeInit==null){
            //no time
            JOptionPane.showMessageDialog(null, "No se ha insertado una hora de Inicio");
            return;
        }
        
        if(jTextArea2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No se ha insertado un comentario");
            return;
        }
        
        if(timeEnd!=null){
            Duration duration = Duration.between(timeInit, timeEnd);
            long horasDiferencia = duration.toHours();

            // Verificar si la diferencia es positiva o negativa
            if (horasDiferencia < 0) {
                JOptionPane.showMessageDialog(null, "La fecha de termino NO debe de ser menor a la fecha inicial");
                return;
            }
            
        }
        
        //armar el json
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        
        CommentRequest request = new CommentRequest();
        request.setComentario(jTextArea2.getText().trim());
        request.setIDEvento(CurrentUser.evento);
        request.setProyectId((int)CurrentUser.idproyecto);
        request.setUsuarioId((int)CurrentUser.idUsuario);
        request.setFechaInicio(timeInit.toString());
        if(timeEnd!=null){
            request.setFechaFin(timeEnd.toString());
        }
        
        String json = gson.toJson(request);
        ArrayList<String> copiaLista = new ArrayList<>(charactersList);
        new Thread(new CommentSender(json,copiaLista)).start();
        timeInit=null;
        timeEnd=null;
        charactersList.clear();
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
    private javax.swing.JButton Bmixer1;
    private javax.swing.JButton Bmixer2;
    private javax.swing.JButton Bsettings;
    private javax.swing.JLabel EtiquetaConect;
    private javax.swing.JLabel EtiquetaStatus;
    private javax.swing.JLabel Etiquetacanales;
    private javax.swing.JTextField MSG;
    private javax.swing.JLabel alarma;
    private javax.swing.JButton cLEAR;
    private javax.swing.JButton cuarto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JComboBox<String> networks;
    private javax.swing.JButton primero;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JButton segundo;
    private javax.swing.JButton tercero;
    private javax.swing.JSlider vPrincipal;
    // End of variables declaration//GEN-END:variables
}
