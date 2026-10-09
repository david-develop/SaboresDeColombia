package com.example.saboresdecolombia;

// Persona (ficticia, con fines académicos) que representa la cocina de una región
public class Cocinero {
    private final String nombre;
    private final String rol;
    private final String estudios;
    private final String experiencia;
    private final int avatarResId;

    public Cocinero(String nombre, String rol, String estudios, String experiencia, int avatarResId) {
        this.nombre = nombre;
        this.rol = rol;
        this.estudios = estudios;
        this.experiencia = experiencia;
        this.avatarResId = avatarResId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public String getEstudios() {
        return estudios;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public int getAvatarResId() {
        return avatarResId;
    }
}
