package com.example.saboresdecolombia;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;

public class Plato {
    private final String nombre;
    private final String origen;
    private final String ingredientes;
    private final String historia;
    private final String urlVideo;
    private final String urlReceta;
    private final int imagenResId;
    private final List<ImagenGaleria> galeria;
    private final Cocinero cocinero;
    private boolean favorito;

    public Plato(String nombre, String origen, String ingredientes, String historia,
                 String urlVideo, String urlReceta, int imagenResId, List<ImagenGaleria> galeria, Cocinero cocinero) {
        this.nombre = nombre;
        this.origen = origen;
        this.ingredientes = ingredientes;
        this.historia = historia;
        this.urlVideo = urlVideo;
        this.urlReceta = urlReceta;
        this.imagenResId = imagenResId;
        this.galeria = galeria;
        this.cocinero = cocinero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getHistoria() {
        return historia;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public String getUrlReceta() {
        return urlReceta;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public List<ImagenGaleria> getGaleria() {
        return galeria;
    }

    public Cocinero getCocinero() {
        return cocinero;
    }

    public boolean esFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    // Nombre del recurso de video local (res/raw) de este plato: "Bandeja paisa" -> "bandeja_paisa"
    public String getNombreRecurso() {
        String sinTildes = Normalizer.normalize(nombre, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return sinTildes.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", "_");
    }

    // Alterna el estado de favorito (marcar / desmarcar)
    public void marcarFavorito() {
        favorito = !favorito;
    }
}
