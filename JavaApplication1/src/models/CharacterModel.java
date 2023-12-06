/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author leone
 */
public class CharacterModel {
    
    private int comentarioId;
    private String personaje;

    public CharacterModel(int comentarioId, String personaje) {
        this.comentarioId = comentarioId;
        this.personaje = personaje;
    }

    public int getComentarioId() {
        return comentarioId;
    }

    public String getPersonaje() {
        return personaje;
    }
    
}
