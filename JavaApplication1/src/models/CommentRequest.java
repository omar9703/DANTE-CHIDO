/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import com.google.gson.annotations.Expose;
import java.time.LocalDateTime;

/**
 *
 * @author leone
 */
public class CommentRequest {
    
    @Expose
    private int proyectId;
    
    @Expose
    private int usuarioId;
    
    @Expose
    private String comentario;
    
    @Expose
    private String fechaInicio;
    
    @Expose
    private String fechaFin;
    
    @Expose
    private String IDEvento;
    
    @Expose
    private boolean isVentana;

    // Constructor vacío (puedes personalizarlo según tus necesidades)
    public CommentRequest(){
    
    }

    // Getters y setters para proyectId
    public int getProyectId() {
        return proyectId;
    }

    public void setProyectId(int proyectId) {
        this.proyectId = proyectId;
    }

    // Getters y setters para usuarioId
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    // Getters y setters para comentario
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    // Getters y setters para fechaInicio
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    // Getters y setters para fechaFin
    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    // Getters y setters para IDEvento
    public String getIDEvento() {
        return IDEvento;
    }

    public void setIDEvento(String IDEvento) {
        this.IDEvento = IDEvento;
    }

    // Getters y setters para isVentana
    public boolean isVentana() {
        return isVentana;
    }

    public void setVentana(boolean ventana) {
        isVentana = ventana;
    }
    
}
