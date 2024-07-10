/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.util.ArrayList;

/**
 *
 * @author ocamp
 */
public class ConfigTags {
    
     private ArrayList<String> namesComments;
      private ArrayList<String> command;
      public String url;
      public String nameFiles;
      public String ShortcutNew;
      public String ShortcutAdd;
      public String ShortcutCancel;
      public String ShortcutColor1;
      public String ShortcutColor2;
      public String ShortcutColor3; 
      public String Color1;
      public String Color2;
      public String Color3;
      public void setNames(ArrayList<String> names)
      {
          this.namesComments =  names;
      }
      
      public ArrayList<String> getNames()
      {
          
         return this.namesComments;
          
      }
      public void setCommands(ArrayList<String> names)
      {
          this.command =  names;
      }
      
      public ArrayList<String> getCommands()
      {
          
         return this.command;
          
      }
}
