/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceMark;

import models.CurrentUser;
import models.StatusMessage;

/**
 *
 * @author leone
 */
public class CommentSender implements Runnable{
    
    private int retries = 0;
    private String Jsonbuilder;
    public CommentSender(String Jsonbuilder){
        this.Jsonbuilder=Jsonbuilder;
    }
    
    @Override
    public void run() {
        while(retries<=5){
            try{
                StatusMessage statusMessage = HttpClientExecutor.sendPostRequest(CurrentUser.url+"/bitacora", Jsonbuilder).get();

                if(statusMessage.statuscode==201){
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
