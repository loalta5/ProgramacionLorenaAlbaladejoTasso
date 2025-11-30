package com.emergencias.alert;

import com.emergencias.model.EmergencyEvent;
import java.io.FileWriter;
import java.io.IOException;

public class AlertSender {
    public void sendAlert(EmergencyEvent event) {
        if (event != null) {
            System.out.println("--- Módulo de Notificación ---");
            System.out.println("Enviando alerta a 112: " + event.getTipo() + " en " + event.getUbicacion());

            // Persistir en un archivo de texto
            try (FileWriter writer = new FileWriter("alertas.txt", true)) {
                writer.write(event.toString() + "\n");
                System.out.println("Alerta registrada en 'alertas.txt'");
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo de alertas: " + e.getMessage());
            }
        } else {
            System.out.println("No se envió ninguna alerta porque el evento era nulo.");
        }
    }
}
