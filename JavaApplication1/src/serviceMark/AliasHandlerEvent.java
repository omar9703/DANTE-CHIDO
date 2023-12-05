/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceMark;

/**
 *
 * @author leone
 */
public class AliasHandlerEvent {
    
    
    public static EventoListener eventoListener;
 
    public static void suscribeEvento(EventoListener listener) {
        eventoListener = listener;
    }

    // Método estático para desuscribirse del evento
    public static void desuscribeEvento() {
        eventoListener = null;
    }

    // Método estático para lanzar el evento
    public static void lanzarEvento(String mensaje) {
        if (eventoListener != null) {
            eventoListener.onEvento(mensaje);
        }
    }

    

    public  interface EventoListener {

        public void onEvento(String mensaje);
    }
    
}
