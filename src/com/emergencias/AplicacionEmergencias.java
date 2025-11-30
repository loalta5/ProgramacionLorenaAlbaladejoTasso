package com.emergencias;

import com.emergencias.model.EmergencyEvent;
import com.emergencias.model.UserData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Esta clase maneja la lógica de inicio, carga de datos y demostración.
public class AplicacionEmergencias {

    // Variable estática que almacena los datos del usuario una vez cargados.
    private static UserData datosUsuario;

    /**
     * Método público y estático para que otras clases (como Main o Detector)
     * puedan acceder a la instancia de UserData que ha sido cargada.
     */
    public static UserData getDatosUsuario() {
        return datosUsuario;
    }

    public static void main(String[] args) {

        System.out.println("--- INICIANDO SISTEMA DE EMERGENCIAS ---");

        // 1. Cargar los datos del archivo user_data.txt
        cargarDatosUsuario();

        // 2. Verificar si la carga fue exitosa
        if (datosUsuario != null) {
            System.out.println("\n✅ Datos de Usuario Cargados con Éxito:");
            System.out.println(datosUsuario.toString());

            // 3. Crear un evento de emergencia usando los datos cargados para demostrar el funcionamiento
            crearYReportarEmergenciaEjemplo();

        } else {
            System.out.println("\n❌ ERROR CRÍTICO: No se pudo iniciar el sistema sin datos de usuario.");
            // Si la aplicación principal fuera esta, terminaría aquí.
        }

        System.out.println("\n--- FIN DE LA EJECUCIÓN ---");
    }

    /**
     * Lógica para demostrar la creación de un EmergencyEvent.
     */
    public static void crearYReportarEmergenciaEjemplo() {
        // Creamos un evento de emergencia, pasando el tipo, ubicación y los datos del usuario.
        EmergencyEvent miEmergencia = new EmergencyEvent(
                "Accidente de Tráfico",
                "Avenida Principal, esquina Calle 5",
                datosUsuario // Usamos el objeto UserData que acabamos de cargar
        );

        System.out.println("\n--- 🚨 REPORTE DE EMERGENCIA CREADO ---");
        // El método toString() modificado en EmergencyEvent mostrará la información completa.
        System.out.println(miEmergencia.toString());
    }

    /**
     * Lee el archivo 'user_data.txt', procesa cada línea y crea el objeto UserData.
     */
    public static void cargarDatosUsuario() {
        String archivo = "user_data.txt";
        Map<String, String> dataMap = new HashMap<>(); // Mapa temporal para guardar los pares Clave:Valor

        // El bloque try-with-resources asegura que el BufferedReader se cierra automáticamente.
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            // Lee el archivo línea por línea hasta el final (null)
            while ((linea = br.readLine()) != null) {
                // Solo procesa líneas que contienen los dos puntos
                if (linea.contains(":")) {
                    String[] partes = linea.split(":", 2); // Divide la línea en 2 partes
                    String clave = partes[0].trim();
                    String valor = partes[1].trim();
                    dataMap.put(clave, valor);
                }
            }

            // Verifica que se hayan encontrado las 4 claves esperadas
            if (dataMap.containsKey("Nombre") && dataMap.containsKey("Telefono") &&
                    dataMap.containsKey("TipoSangre") && dataMap.containsKey("ContactoEmergencia")) {

                // Crea la instancia final de UserData con los valores extraídos del mapa
                datosUsuario = new UserData(
                        dataMap.get("Nombre"),
                        dataMap.get("Telefono"),
                        dataMap.get("TipoSangre"),
                        dataMap.get("ContactoEmergencia")
                );
            } else {
                System.err.println("Error de formato: El archivo '" + archivo + "' no contiene todos los campos requeridos.");
            }

        } catch (IOException e) {
            // Se ejecuta si el archivo no se encuentra o hay un problema de lectura
            System.err.println("Error I/O: No se pudo leer el archivo '" + archivo + "'. Asegúrese de que esté en la raíz del proyecto.");
            datosUsuario = null; // Indica que la carga falló
        }
    }
}