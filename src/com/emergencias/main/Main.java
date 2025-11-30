package com.emergencias.main;

import com.emergencias.alert.AlertSender;
import com.emergencias.detector.EmergencyDetector;
import com.emergencias.model.EmergencyEvent;
import com.emergencias.AplicacionEmergencias;

public class Main {
    public static void main(String[] args) {
        com.emergencias.AplicacionEmergencias.cargarDatosUsuario();
        System.out.println("Iniciando Sistema de Gestión de Emergencias...");
        System.out.println(AplicacionEmergencias.getDatosUsuario());

        EmergencyDetector detector = new EmergencyDetector();
        AlertSender sender = new AlertSender();

        // 1. Detectar el evento
        EmergencyEvent event = detector.detectEvent();

        // 2. Enviar la alerta si se detectó algo
        sender.sendAlert(event);

        System.out.println("Sistema finalizado.");
    }
}
