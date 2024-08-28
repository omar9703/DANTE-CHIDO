/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import Datos.ConfigTags;
import Negocio.Coordinador;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import Datos.XmlRead;
import Datos.Configuracion;
import Datos.WriteXml;
import Datos.volumen;
import javax.swing.JButton;
import javax.swing.JComboBox;
import Negocio.NetworkInterfaces;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import static javax.swing.JComponent.WHEN_FOCUSED;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author leone
 */
public class Panel extends javax.swing.JFrame {

    /**
     * Creates new form Panel
     */
    public String shortNew = " ";
    public String shortAdd = " ";
    public String shortCancel = " ";
     private final SimpleDateFormat sdf  = new SimpleDateFormat("HH:mm");
    private int   currentSecond;
    private Calendar calendar;
    List<String[]> list;
   public DefaultTableModel model;
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
   JFileChooser chooser;
   String choosertitle;
   public ArrayList<String> comandos;
   public Boolean FileFound;
   public String URL;
   public Boolean added = true;
   public String User;
   public String fileName;
   ArrayList<String> colors ;
    public Panel()  {
       initComponents();
       Bmixer1.setVisible(false);
       colors = new ArrayList<>();
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
       int condition = WHEN_FOCUSED;  
      // get our maps for binding from the chatEnterArea JTextArea
      InputMap inputMap = jTextArea2.getInputMap(condition);
      ActionMap actionMap = jTextArea2.getActionMap();
      KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
inputMap.put(enterStroke, enterStroke.toString());
      final Panel p = this;
      actionMap.put(enterStroke.toString(), new AbstractAction() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
             p.Bmixer2ActionPerformed(arg0);

         }
      });
       KeyStroke ks = KeyStroke.getKeyStroke("control F12");
        JRootPane rootPane = this.getRootPane();
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, "myAction");
         rootPane.getActionMap().put("myAction", new AbstractAction() {
             
                public void actionPerformed(ActionEvent e) {
                    System.out.println("hello, world");
                    SettingsTags tags = new SettingsTags(p);
                    tags.setVisible(true);
                }
            });

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
        ConfigTags ct = Xread.ReadTagsConfig();
        List<String> auxColors = new ArrayList<>();
        auxColors.add(ct.Color1);
        auxColors.add(ct.Color2);
        auxColors.add(ct.Color3);
        auxColors.add(ct.Color4);
        auxColors.add(ct.Color5);
        auxColors.add(ct.Color6);
        auxColors.add(ct.Color7);
        auxColors.add(ct.Color8);
        shortNew = ct.ShortcutNew;
        this.shortAdd=ct.ShortcutAdd;
        this.shortCancel=ct.ShortcutCancel;
        System.out.println(ct.getCommands());
        comandos = ct.getCommands();
        for(int x = 0; x<ct.getCommands().size();x++)
        {
            if (!" ".equals(ct.getCommands().get(x)))
            {
                final int index = x;
            jTextArea2.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(ct.getCommands().get(x)), "Enter"+x);
            jTextArea2.getActionMap().put("Enter"+x, new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    jComboBox1.setSelectedItem(auxColors.get(index));
                    jTextArea2.setText(jTextArea2.getText() + " " + ct.getNames().get(index));
                    
                }
            });
            }
        }

         if (!" ".equals(shortNew))
        {
            rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(shortNew), "newButton");
            rootPane.getActionMap().put("newButton", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e) {
                    p.Bmixer2ActionPerformed(e);
                    
                }
            });
        }
         if (!" ".equals(shortAdd))
        {
            rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(shortAdd), "addButton");
            rootPane.getActionMap().put("addButton", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e) {
                    p.Bmixer1ActionPerformed(e);
                    
                }
            });
        }
        if (!" ".equals(shortCancel))
        {
            rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(shortCancel), "canButton");
            rootPane.getActionMap().put("canButton", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e) {
                    p.Bmixer3ActionPerformed(e);
                    
                }
            });
        } 
        String[] columnNames = {"Hora",
                        "Descripción","Color"};
        model = new DefaultTableModel(columnNames,0);
       
        jTable1.setModel(model);
        
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        
        String[] header = {"markIn", "markOut", "take","comment"};
            list = new ArrayList<>();
            list.add(header);
            this.fileName = ct.nameFiles;
         try
            {
            CSVReader reader = new CSVReaderBuilder(new FileReader(ct.url+"\\"+this.fileName+".csv")).build();
            String [] nextLine;
            URL = ct.url;
            
            ReadColorsFile();
            System.out.println(colors);
            int index = 0;
            while ((nextLine = reader.readNext()) != null) {
        // nextLine[] is an array of values from the line
                if (!nextLine[0].contains("markIn"))
                {
                    String[] aux = {nextLine[0], nextLine[1], nextLine[2],nextLine[3]};
                    list.add(aux);
                    String[] item = {nextLine[0],nextLine[3],colors.get(index)}; 
                    System.out.println(nextLine[0] +" "+ nextLine[1] +" "+ nextLine[2] +" "+ nextLine[3]);
                    model.insertRow(0, item);
                    index++;
                }
     }
     FileFound = true;
                     }
            catch(IOException I)
                       {
                                FileFound = false; 
                             }
            catch(CsvException I)
                       {
                             FileFound = false;    
                             }
            
            
            SetListenerTable();
           
     this.SetTimer();
     jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     jTable1.getColumnModel().getColumn(0).setPreferredWidth(75);
     jTable1.getColumnModel().getColumn(1).setPreferredWidth(180);
     jTable1.getColumnModel().getColumn(2).setPreferredWidth(42);
    
    }
    public void SetTimer()
    {
        
        reset();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask(){
            public void run(){
                if( currentSecond == 60 ) {
                    reset();
                }
                jLabel5.setText( String.format("%s:%02d", sdf.format(calendar.getTime()), currentSecond ));
                currentSecond++;
            }
        }, 0, 1000 );
    }
     private void reset(){
        calendar = Calendar.getInstance();
        currentSecond = calendar.get(Calendar.SECOND);
    }
    public void SetListenerTable()
    {
        final Panel p1 = this;
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            // do some actions here, for example
            // print first column value from selected row
            int viewRow = jTable1.getSelectedRow();
            System.out.println(viewRow);
            if (!event.getValueIsAdjusting() && viewRow != -1) {
            
            System.out.println(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            EditPanel p = new EditPanel(p1,viewRow);
            p.setVisible(true);
            jTable1.clearSelection();
            }
        }
    });
    }
    public void ResetSettings()
    {
        
        for(int x = 0; x<comandos.size();x++)
        {
            if (!" ".equals(comandos.get(x)))
            {
                jTextArea2.getInputMap().remove(KeyStroke.getKeyStroke(comandos.get(x)));
            }
        }
        
        if (!" ".equals(shortNew))
        {
            rootPane.getInputMap().remove(KeyStroke.getKeyStroke(shortNew));
        }
        if (!" ".equals(shortAdd))
        {
            rootPane.getInputMap().remove(KeyStroke.getKeyStroke(shortAdd));
        }
        if (!" ".equals(shortCancel))
        {
            rootPane.getInputMap().remove(KeyStroke.getKeyStroke(shortCancel));
        }
        ConfigTags ct = Xread.ReadTagsConfig();
        shortNew = ct.ShortcutNew;
        shortAdd = ct.ShortcutAdd;
        shortCancel = ct.ShortcutCancel;
        System.out.println(ct.getCommands());
        comandos = ct.getCommands();
        List<String> auxColors = new ArrayList<>();
        auxColors.add(ct.Color1);
        auxColors.add(ct.Color2);
        auxColors.add(ct.Color3);
        auxColors.add(ct.Color4);
        auxColors.add(ct.Color5);
        auxColors.add(ct.Color6);
        auxColors.add(ct.Color7);
        auxColors.add(ct.Color8);
        
      // tell input map that we are handling the enter key
      
      final Panel p = this;
        for(int x = 0; x<ct.getCommands().size();x++)
        {
            if (!" ".equals(ct.getCommands().get(x)))
            {
                final int index = x;
            jTextArea2.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(ct.getCommands().get(x)), "Enter"+x);
            jTextArea2.getActionMap().put("Enter"+x, new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    jComboBox1.setSelectedItem(auxColors.get(index));
                    jTextArea2.setText(jTextArea2.getText() + " " + ct.getNames().get(index));
                    
                }
            });
            }
        }
        if (!" ".equals(shortNew))
        {
            rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(shortNew), "newButton");
            rootPane.getActionMap().put("newButton", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e) {
                    p.Bmixer2ActionPerformed(e);
                    
                }
            });
        }
        if (!" ".equals(shortAdd))
        {
            rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(shortAdd), "addButton");
            rootPane.getActionMap().put("addButton", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e) {
                    p.Bmixer1ActionPerformed(e);
                    
                }
            });
        }
        if (!" ".equals(shortCancel))
        {
            rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(shortCancel), "canButton");
            rootPane.getActionMap().put("canButton", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e) {
                    p.Bmixer3ActionPerformed(e);
                    
                }
            });
        }      
    }
    public void SetNewProject(String url, String nombre)
    {
         WriteXml xl = new WriteXml();
        int rows = model.getRowCount(); 
        for(int i = rows - 1; i >=0; i--)
        {
            model.removeRow(i); 
        }
        xl.WriteRouteCSV(url,nombre);
        URL = url;
        this.fileName = nombre;
        try
                 {
                CSVReader reader = new CSVReaderBuilder(new FileReader(url+"\\"+nombre+".csv")).build();
                String [] nextLine;
                list.clear();
                colors.clear();
                ReadColorsFile();
                String[] header = {"markIn", "markOut", "take","comment"};
            list = new ArrayList<>();
            list.add(header);
            int index = 0;
     while ((nextLine = reader.readNext()) != null) {
        // nextLine[] is an array of values from the line
        if (!nextLine[0].contains("markIn"))
        {
            String[] aux = {nextLine[0], nextLine[1], nextLine[2],nextLine[3]};
            
            list.add(aux);
            String[] item = {nextLine[0],nextLine[3],colors.get(index)}; 
            System.out.println(nextLine[0] +" "+ nextLine[1] +" "+ nextLine[2] +" "+ nextLine[3]);
            model.insertRow(0, item);
            index++;
        }
     }
     FileFound = true;
                     }
            catch(IOException I)
                       {
                                FileFound = false; 
                             }
            catch(CsvException I)
                       {
                             FileFound = false;    
                             }
         
    }
    public void ResetFolderFile(String url, String name )
    {
        this.URL = url;
        this.fileName = name;
        int rows = model.getRowCount(); 
    for(int i = rows - 1; i >=0; i--)
    {
        model.removeRow(i); 
    }
    list.clear();
    colors.clear();
    WriteColorsFile();
    String[] header = {"markIn", "markOut", "take","comment"};
            list = new ArrayList<>();
            list.add(header);
    try
                 {
            CSVReader reader = new CSVReaderBuilder(new FileReader(URL+"\\"+this.fileName+".csv")).build();
            FileFound = true;
                 }
     catch(IOException I)
                       {
                                FileFound = false; 
                             }
    }
    
    public void UpdateFile()
    {

        // default all fields are enclosed in double quotes
        // default separator is a comma
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(URL+"\\"+this.fileName+".csv"));
        
            writer.writeAll(list);
            System.out.println(list);
              int rows = model.getRowCount(); 
            for(int i = rows - 1; i >=0; i--)
            {
                model.removeRow(i); 
            }
            WriteColorsFile();
            for(int x=1;x<list.size();x++)
            {
                String[] item = {list.get(x)[0],list.get(x)[3],colors.get(x - 1)};
                model.insertRow(0, item);
            }           
            writer.close(); 
        }catch (IOException ex) {
                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            }      
    }
    
    public void WriteColorsFile()
    {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(URL+"\\colors.txt", false));
            
        for(String list : colors)
        {      
                System.out.println(list);            
                writer.write(list);
                writer.newLine();               
        }
        WritetxtFile();
        writer.close();
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Error al crear txt");
                ex.printStackTrace();
                }
    }
    
    public void WritetxtFile()
    {
        if (list.size() > 1)
    {
        GenerateExcel();
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(URL+"\\"+this.fileName+".txt", false));
            int index2 = 0;
        for(String[] list : list)
        {
            if (list[0] != "markIn")
            {
                String str = list[0];
                char ch = ';';
                int index = 8;
                str = str.substring(0, index) + ch + str.substring(index + 1);
                System.out.println(str);       
                String aux = User+"\t" + str + "\tV1\t" + colors.get(index2) +"\t" + "Take: " + list[2] + " Quality: 0 " + "LOGGER " + list[3] + "\t1";
                System.out.println(aux);            
                writer.write(aux+"\n");
                index2++;
            }
        }
        writer.close();
       
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Error al crear txt");
                ex.printStackTrace();
                }
    }
    else
    {
        
    }
    }
    private void GenerateExcel()
    {
        Workbook workbook = new XSSFWorkbook();

    Sheet sheet = workbook.createSheet("test");

    Row header = sheet.createRow(0);

    CellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setAlignment(HorizontalAlignment.CENTER);
    headerStyle.setVerticalAlignment(VerticalAlignment.TOP);
    
    XSSFFont font = ((XSSFWorkbook) workbook).createFont();
    font.setFontName("Calibri");
    font.setFontHeightInPoints((short) 11);
    font.setBold(true);
    headerStyle.setFont(font);

    Cell headerCell = header.createCell(1);
    headerCell.setCellValue("markIn");
    headerCell.setCellStyle(headerStyle);

    headerCell = header.createCell(2);
    headerCell.setCellValue("dur");
    headerCell.setCellStyle(headerStyle);
    
    headerCell = header.createCell(3);
    headerCell.setCellValue("scene");
    headerCell.setCellStyle(headerStyle);
    
    headerCell = header.createCell(4);
    headerCell.setCellValue("comment");
    headerCell.setCellStyle(headerStyle);
    
    headerCell = header.createCell(5);
    headerCell.setCellValue("Custom 1");
    headerCell.setCellStyle(headerStyle);
    
    headerCell = header.createCell(6);
    headerCell.setCellValue("Custom 2");
    headerCell.setCellStyle(headerStyle);
    
    headerCell = header.createCell(7);
    headerCell.setCellValue("take");
    headerCell.setCellStyle(headerStyle);
    
    headerCell = header.createCell(8);
    headerCell.setCellValue("quality");
    headerCell.setCellStyle(headerStyle);
    
    headerCell = header.createCell(9);
    headerCell.setCellValue("selected");
    headerCell.setCellStyle(headerStyle);
    
    headerCell = header.createCell(10);
    headerCell.setCellValue("fps");
    headerCell.setCellStyle(headerStyle);

    CellStyle style = workbook.createCellStyle();
    style.setWrapText(true);
    
    XSSFFont font2 = ((XSSFWorkbook) workbook).createFont();
    font2.setFontName("Calibri");
    font2.setFontHeightInPoints((short) 11);
    font2.setBold(false);
    
    style.setFont(font2);
    style.setVerticalAlignment(VerticalAlignment.DISTRIBUTED);
    
    
    for(int x = list.size()-1; x >= 1; x--)
    {
        Row row = sheet.createRow(list.size()- (x));
        
        Cell cell = row.createCell(0);
        String aux = list.get(x)[2];
        int a = Integer.valueOf(aux);
        String b = String.format("%03d", a);
        cell.setCellValue(b);
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue(list.get(x)[0]);
        cell.setCellStyle(style);
        
        cell = row.createCell(2);
        cell.setCellValue("na");
        cell.setCellStyle(style);
        
        cell = row.createCell(3);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        cell = row.createCell(4);
        cell.setCellValue(list.get(x)[3]);
        cell.setCellStyle(style);
        
        cell = row.createCell(5);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        cell = row.createCell(6);
        cell.setCellValue("");
        cell.setCellStyle(style);
        
        cell = row.createCell(7);
        cell.setCellValue(list.get(x)[2]);
        cell.setCellStyle(style);
        
        cell = row.createCell(8);
        cell.setCellValue(0);
        cell.setCellStyle(style);
        
        cell = row.createCell(9);
        cell.setCellValue("FALSE");
        cell.setCellStyle(style);
        
        cell = row.createCell(10);
        cell.setCellValue("29.97d");
        cell.setCellStyle(style);
    }
    

    File currDir = new File(".");
    String path = currDir.getAbsolutePath();
    String fileLocation = URL+"\\"+this.fileName+".xlsx";

FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        } catch (FileNotFoundException ex) {
             JOptionPane.showMessageDialog(null, "Error al crear xlsx");
            Logger.getLogger(SettingsTags.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, "Error al crear xls");
            Logger.getLogger(SettingsTags.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ReadColorsFile() throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(URL+"\\colors.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
           // process the line.
           System.out.println(line);
           colors.add(line);
        }
        br.close();
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

        scrollPane1 = new java.awt.ScrollPane();
        Bsettings = new javax.swing.JButton();
        cLEAR = new javax.swing.JButton();
        primero = new javax.swing.JButton();
        segundo = new javax.swing.JButton();
        Bmixer4 = new javax.swing.JButton();
        cuarto = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        Bmixer2 = new javax.swing.JButton();
        Bmixer3 = new javax.swing.JButton();
        Bmixer1 = new javax.swing.JButton();
        Bmixer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        EtiquetaStatus = new javax.swing.JLabel();
        EtiquetaConect = new javax.swing.JLabel();
        Etiquetacanales = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        MSG = new javax.swing.JTextField();
        alarma = new javax.swing.JLabel();
        networks = new javax.swing.JComboBox<>();
        tercero = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        vPrincipal = new javax.swing.JSlider();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 102));
        setForeground(new java.awt.Color(0, 51, 102));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        getContentPane().setLayout(null);

        scrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        scrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(scrollPane1);
        scrollPane1.setBounds(390, 140, 960, 590);

        Bsettings.setBackground(new java.awt.Color(231, 25, 76));
        Bsettings.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 20)); // NOI18N
        Bsettings.setForeground(new java.awt.Color(255, 255, 255));
        Bsettings.setText("SETTINGS");
        Bsettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsettingsActionPerformed(evt);
            }
        });
        getContentPane().add(Bsettings);
        Bsettings.setBounds(890, 20, 140, 40);

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

        Bmixer4.setBackground(new java.awt.Color(51, 204, 0));
        Bmixer4.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 20)); // NOI18N
        Bmixer4.setForeground(new java.awt.Color(255, 255, 255));
        Bmixer4.setText("MARKS");
        Bmixer4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bmixer4ActionPerformed(evt);
            }
        });
        getContentPane().add(Bmixer4);
        Bmixer4.setBounds(750, 20, 120, 40);

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cyan", "None", "Red", "Green", "Blue", "Magenta", "Yellow", "Black", "White", "Default" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(290, 120, 90, 22);

        Bmixer2.setBackground(new java.awt.Color(0, 204, 0));
        Bmixer2.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 14)); // NOI18N
        Bmixer2.setForeground(new java.awt.Color(255, 255, 255));
        Bmixer2.setText("Añadir marca");
        Bmixer2.setMargin(new java.awt.Insets(2, 2, 3, 2));
        Bmixer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bmixer2ActionPerformed(evt);
            }
        });
        getContentPane().add(Bmixer2);
        Bmixer2.setBounds(90, 145, 100, 40);

        Bmixer3.setBackground(new java.awt.Color(231, 25, 76));
        Bmixer3.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 12)); // NOI18N
        Bmixer3.setForeground(new java.awt.Color(255, 255, 255));
        Bmixer3.setText("Cancelar marca");
        Bmixer3.setMargin(new java.awt.Insets(2, 1, 3, 1));
        Bmixer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bmixer3ActionPerformed(evt);
            }
        });
        getContentPane().add(Bmixer3);
        Bmixer3.setBounds(280, 145, 110, 40);

        Bmixer1.setBackground(new java.awt.Color(51, 51, 255));
        Bmixer1.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 14)); // NOI18N
        Bmixer1.setForeground(new java.awt.Color(255, 255, 255));
        Bmixer1.setText("Añadir marca");
        Bmixer1.setMargin(new java.awt.Insets(2, 2, 3, 2));
        Bmixer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bmixer1ActionPerformed(evt);
            }
        });
        getContentPane().add(Bmixer1);
        Bmixer1.setBounds(195, 145, 100, 40);

        Bmixer.setBackground(new java.awt.Color(231, 25, 76));
        Bmixer.setFont(new java.awt.Font("Knockout 48 Featherweight", 0, 20)); // NOI18N
        Bmixer.setForeground(new java.awt.Color(255, 255, 255));
        Bmixer.setText("MIXER");
        Bmixer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BmixerActionPerformed(evt);
            }
        });
        getContentPane().add(Bmixer);
        Bmixer.setBounds(1050, 20, 120, 40);

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextArea2.setSelectionColor(new java.awt.Color(0, 0, 0));
        jTextArea2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextArea2FocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(90, 186, 300, 270);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(22);
        jScrollPane3.setViewportView(jTable1);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(90, 460, 300, 260);

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

        jLabel3.setBackground(new java.awt.Color(102, 41, 188));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(680, 10, 180, 120);

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

        vPrincipal.setBackground(new java.awt.Color(0, 0, 0));
        vPrincipal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vPrincipalStateChanged(evt);
            }
        });
        getContentPane().add(vPrincipal);
        vPrincipal.setBounds(950, 75, 360, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(720, 90, 160, 30);

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
       if (!this.added)
       {
           if (!jTextArea2.getText().equals(""))
            {
                list.getLast()[3] = jTextArea2.getText();
                this.UpdateFile();
                jTextArea2.setText("");
                this.added = true;
            }
       }
    }//GEN-LAST:event_Bmixer1ActionPerformed
    
    private void Bmixer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bmixer2ActionPerformed
        // TODO add your handling code here:
        //boton de nuevo
         if (FileFound)
        {
        //if (!jTextArea2.getText().equals(""))
       // {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:S", Locale.US);
            Date date = new Date();
            String result = formatter.format(date);
            String[] item = {result,jTextArea2.getText(),jComboBox1.getSelectedItem().toString()}; 
            colors.add(jComboBox1.getSelectedItem().toString());
            WriteColorsFile();
            //model.addRow(item);jTextArea2.getText()
            model.insertRow(0, item);
            
            
             String[] record1 = {result, "na",""+list.size() ,jTextArea2.getText()};
            jTextArea2.setText("");
            list.add(record1);
            this.added = false;
        // default all fields are enclosed in double quotes
        // default separator is a comma
        //try (CSVWriter writer = new CSVWriter(new FileWriter(URL+"\\"+this.fileName+".csv"))) {
          //  writer.writeAll(list);
        //}   catch (IOException ex) {
              //  Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            //}
        this.UpdateFile();
                jTextArea2.setText("");
        }
        else
        {
            String[] options = new String[] {"Nuevo", "Abrir"};
    int response = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Sin proyecto seleccionado.",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);
              chooser = new JFileChooser(); 
              chooser.setDialogTitle(choosertitle);
    chooser.setCurrentDirectory(new java.io.File("."));
    if (response == 0)
    {   
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
    //    
    }
    else
    {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "CSV");
        chooser.addChoosableFileFilter(filter);
    }
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
        System.out.println("getCurrentDirectory(): " 
         +  chooser.getCurrentDirectory());
        System.out.println("getSelectedFile() : " 
         +  chooser.getSelectedFile());
         WriteXml xl = new WriteXml();
         String resp = "Ingrese el nombre del proyecto";
      boolean auxb = false;
      String auxn = "";
         if (response == 0)
         {
             
             while (!auxb)
             {
                    String input = JOptionPane.showInputDialog(null, resp);                  
            if(input == null || (input != null && ("".equals(input))))   
            {
                auxb = false;
                JOptionPane.showMessageDialog(null, "Ingrese un nombre!");
            }
            else
            {
                auxb = true;
                auxn = input;
            }
             }
        
            xl.WriteRouteCSV(chooser.getSelectedFile().toString(),auxn);
            FileFound = true;
            URL = chooser.getSelectedFile().toString();
            list.clear();
            String[] header = {"markIn", "markOut", "take","comment"};
            list = new ArrayList<>();
            list.add(header);
            this.fileName = auxn;
            this.UpdateFile();
         }

         else
         {
            
            String[] splitted = chooser.getSelectedFile().toString().split( Pattern.quote("\\"));
        
        if (splitted.length > 0)
        {
            
            String name = splitted[splitted.length - 1];
            String auxx = name.substring(0, name.length()-4);
            System.out.println(name + " " + auxx);
            this.URL = chooser.getCurrentDirectory().toString();
            this.fileName = auxx;
        xl.WriteRouteCSV(chooser.getCurrentDirectory().toString(),auxx);
             try
                 {
            CSVReader reader = new CSVReaderBuilder(new FileReader(chooser.getCurrentDirectory().toString()+"\\"+auxx+".csv")).build();
            String [] nextLine;
            list.clear();
            ReadColorsFile();
            String[] header = {"markIn", "markOut", "take","comment"};
                   list = new ArrayList<>();
                   list.add(header);
                   int index = 0;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                if (!nextLine[0].contains("markIn"))
                {
                    String[] aux = {nextLine[0], nextLine[1], nextLine[2],nextLine[3]};

                    list.add(aux);
                    String[] item = {nextLine[0],nextLine[3],colors.get(index)}; 
                    System.out.println(nextLine[0] +" "+ nextLine[1] +" "+ nextLine[2] +" "+ nextLine[3]);
                    model.addRow(item);
                    index = 0;
                }
            }
            FileFound = true;
                     }
            catch(IOException I)
                       {
                                FileFound = false; 
                             }
            catch(CsvException I)
                       {
                             FileFound = false;    
                             }
         }
}  
      }
    else {
      System.out.println("No Selection ");
      }
        }
      
    }//GEN-LAST:event_Bmixer2ActionPerformed

    private void jTextArea2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea2FocusGained
        // TODO add your handling code here:
        System.out.println("empiezo a escribir");
    }//GEN-LAST:event_jTextArea2FocusGained

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_formKeyReleased

    private void Bmixer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bmixer3ActionPerformed
        // TODO add your handling code here:
        if(!added)
        {
            list.removeLast();
            colors.removeLast();
            this.UpdateFile();
        }
    }//GEN-LAST:event_Bmixer3ActionPerformed

    private void Bmixer4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bmixer4ActionPerformed
        // TODO add your handling code here:
        SettingsTags tags = new SettingsTags(this);
                    tags.setVisible(true);
    }//GEN-LAST:event_Bmixer4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    
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
    private javax.swing.JButton Bmixer3;
    private javax.swing.JButton Bmixer4;
    private javax.swing.JButton Bsettings;
    private javax.swing.JLabel EtiquetaConect;
    private javax.swing.JLabel EtiquetaStatus;
    private javax.swing.JLabel Etiquetacanales;
    private javax.swing.JTextField MSG;
    private javax.swing.JLabel alarma;
    private javax.swing.JButton cLEAR;
    private javax.swing.JButton cuarto;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
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
