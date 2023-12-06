/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceMark;

import com.google.gson.Gson;
import java.util.ArrayList;
import models.CommentResponse;
import models.CurrentUser;
import models.StatusMessage;
import models.ListCharacters;
import static models.ListCharacters.characters;
import models.Usuario;
/**
 *
 * @author leone
 */
public class CommentSender implements Runnable{
    
    private int retries = 0;
    private String Jsonbuilder;
    private ArrayList<String> characters;
    public CommentSender(String Jsonbuilder, ArrayList<String> characters){
        this.Jsonbuilder=Jsonbuilder;
        this.characters = characters;
    }
    
    @Override
    public void run() {
        while(retries<=5){
            try{
                StatusMessage statusMessage = HttpClientExecutor.sendPostRequest(CurrentUser.url+"/bitacora", Jsonbuilder).get();

                if(statusMessage.statuscode==201){
                    //send characters if there are
                    if(this.characters.size()!=0){
                        //call characterthread
                        CommentResponse response = new Gson().fromJson(statusMessage.data, CommentResponse.class);
                        new Thread(new CharacterSender(response.getId(),this.characters)).start();
                    }
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
