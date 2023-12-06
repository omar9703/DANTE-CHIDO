/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceMark;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import models.CharacterModel;
import models.CurrentUser;
import static models.ListCharacters.characters;
import models.StatusMessage;

/**
 *
 * @author leone
 */
public class CharacterSender implements Runnable{
    
    private int retries = 0;
    private int proyectoId;
    private List<String> characters;
    private String jsonBuilder="";
    public CharacterSender(int proyectoId, ArrayList<String> characters){
        this.proyectoId=proyectoId;
        this.characters = characters;
        
        List<CharacterModel> listaPersonajesJSON = new ArrayList<>();
        for (String personaje : characters) {
            CharacterModel nuevoPersonaje = new CharacterModel(proyectoId, personaje);
            listaPersonajesJSON.add(nuevoPersonaje);
        }

        // Serializar la lista a formato JSON
        Gson gson = new Gson();
        jsonBuilder = gson.toJson(listaPersonajesJSON);
    }

    @Override
    public void run() {
        while(retries<=5){
            try{
                StatusMessage statusMessage = HttpClientExecutor.sendPostRequest(CurrentUser.url+"/personaje/bulkInsert", jsonBuilder).get();

                if(statusMessage.statuscode==200){
                    //send characters if there are
                    
                    break;
                }
                retries++;
        }
        catch(Exception ex){
            retries++;
        }
            
        }
    }
    
}
