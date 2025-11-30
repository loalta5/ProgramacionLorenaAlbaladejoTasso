package com.emergencias.model;

public class UserData {
    private String nombre;
    private String telefono;
    private String tipoSangre;
    private String contactoEmergencia;

    // Constructor para inicializar todos los datos
    public UserData(String nombre, String telefono, String tipoSangre, String contactoEmergencia) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoSangre = tipoSangre;
        this.contactoEmergencia = contactoEmergencia;
    }

    // --- Métodos Getter ---
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    @Override
    public String toString() {
        return "Datos Usuario:\n" +
                "  Nombre: " + nombre + "\n" +
                "  Teléfono: " + telefono + "\n" +
                "  Sangre: " + tipoSangre + "\n" +
                "  Contacto: " + contactoEmergencia;
    }
}
