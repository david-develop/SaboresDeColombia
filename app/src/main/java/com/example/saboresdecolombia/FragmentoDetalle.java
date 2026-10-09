package com.example.saboresdecolombia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButtonToggleGroup;

// Fragmento derecho: información del plato seleccionado, dividida en 5 secciones
// (Perfil, Fotos, Video, Web y Botones) que se cambian con el grupo de botones superior.
public class FragmentoDetalle extends Fragment {

    private Plato platoSeleccionado;
    private boolean videoCargado;
    private boolean webCargada;

    // Los favoritos se guardan en el dispositivo (clave: nombre del plato)
    private static final String PREFS_FAVORITOS = "favoritos";

    // Encabezado y opciones
    private TextView txtNombrePlato;
    private ImageView imgFavorito;
    private MaterialButtonToggleGroup grupoOpciones;

    // Secciones
    private View secPerfil, secFotos, secVideo, secWeb, secBotones;

    // Perfil
    private ImageView imgPlato;
    private TextView txtRegionPlato, txtIngredientes, txtHistoria;
    private TextView txtNombreCocinero, txtRolCocinero, txtEstudios, txtExperiencia;

    // Fotos
    private ImageView imgFotoGrande;
    private TextView txtDescripcionFoto;
    private LinearLayout contenedorMiniaturas;

    // Video
    private VideoView vvPlato;
    private MediaController controlesVideo;

    // Web
    private EditText etUrl;
    private WebView wvReceta;

    // Botones
    private Button btnFavorito;

    public FragmentoDetalle() {
        super(R.layout.fragment_detalle);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtNombrePlato = view.findViewById(R.id.txtNombrePlato);
        imgFavorito = view.findViewById(R.id.imgFavorito);
        grupoOpciones = view.findViewById(R.id.grupoOpciones);

        secPerfil = view.findViewById(R.id.secPerfil);
        secFotos = view.findViewById(R.id.secFotos);
        secVideo = view.findViewById(R.id.secVideo);
        secWeb = view.findViewById(R.id.secWeb);
        secBotones = view.findViewById(R.id.secBotones);

        imgPlato = view.findViewById(R.id.imgPlato);
        txtRegionPlato = view.findViewById(R.id.txtRegionPlato);
        txtIngredientes = view.findViewById(R.id.txtIngredientes);
        txtHistoria = view.findViewById(R.id.txtHistoria);
        txtNombreCocinero = view.findViewById(R.id.txtNombreCocinero);
        txtRolCocinero = view.findViewById(R.id.txtRolCocinero);
        txtEstudios = view.findViewById(R.id.txtEstudios);
        txtExperiencia = view.findViewById(R.id.txtExperiencia);

        imgFotoGrande = view.findViewById(R.id.imgFotoGrande);
        txtDescripcionFoto = view.findViewById(R.id.txtDescripcionFoto);
        contenedorMiniaturas = view.findViewById(R.id.contenedorMiniaturas);

        vvPlato = view.findViewById(R.id.vvPlato);
        controlesVideo = new MediaController(requireContext());
        controlesVideo.setAnchorView(vvPlato);
        vvPlato.setMediaController(controlesVideo);
        vvPlato.setOnPreparedListener(mp -> vvPlato.seekTo(1)); // muestra el primer cuadro
        vvPlato.setOnErrorListener((mp, what, extra) -> {
            Toast.makeText(requireContext(), R.string.video_error, Toast.LENGTH_SHORT).show();
            return true;
        });

        etUrl = view.findViewById(R.id.etUrl);
        Button btnAbrirEnlace = view.findViewById(R.id.btnAbrirEnlace);
        wvReceta = view.findViewById(R.id.wvReceta);
        wvReceta.getSettings().setJavaScriptEnabled(true);
        wvReceta.setWebViewClient(new WebViewClient()); // los enlaces se abren dentro del WebView

        btnFavorito = view.findViewById(R.id.btnFavorito);
        Button btnCompartir = view.findViewById(R.id.btnCompartir);
        Button btnVerReceta = view.findViewById(R.id.btnVerReceta);

        // Eventos
        grupoOpciones.addOnButtonCheckedListener((grupo, checkedId, isChecked) -> {
            if (isChecked) {
                mostrarSeccion(checkedId);
            }
        });
        btnAbrirEnlace.setOnClickListener(v -> mostrarReceta());
        btnFavorito.setOnClickListener(v -> marcarFavorito());
        btnCompartir.setOnClickListener(v -> compartirPlato());
        btnVerReceta.setOnClickListener(v -> grupoOpciones.check(R.id.btnWeb));

        if (platoSeleccionado != null) {
            mostrarPlato(platoSeleccionado);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        vvPlato.pause();
    }

    @Override
    public void onDestroyView() {
        vvPlato.stopPlayback();
        wvReceta.destroy();
        super.onDestroyView();
    }

    // Recibe el plato elegido en el listado y actualiza todas las secciones
    public void mostrarPlato(Plato plato) {
        platoSeleccionado = plato;
        if (getView() == null) {
            return; // la vista aún no existe; se aplica en onViewCreated
        }
        plato.setFavorito(preferenciasFavoritos().getBoolean(plato.getNombre(), false));
        videoCargado = false;
        webCargada = false;
        vvPlato.stopPlayback();

        txtNombrePlato.setText(plato.getNombre());
        etUrl.setText(plato.getUrlReceta());
        mostrarPerfil();
        mostrarGaleria();
        actualizarFavorito();
        mostrarSeccion(grupoOpciones.getCheckedButtonId());
    }

    // Muestra solo la sección elegida y carga video / web cuando se necesitan
    private void mostrarSeccion(int botonId) {
        secPerfil.setVisibility(botonId == R.id.btnPerfil ? View.VISIBLE : View.GONE);
        secFotos.setVisibility(botonId == R.id.btnFotos ? View.VISIBLE : View.GONE);
        secVideo.setVisibility(botonId == R.id.btnVideo ? View.VISIBLE : View.GONE);
        secWeb.setVisibility(botonId == R.id.btnWeb ? View.VISIBLE : View.GONE);
        secBotones.setVisibility(botonId == R.id.btnBotones ? View.VISIBLE : View.GONE);

        if (botonId == R.id.btnVideo) {
            reproducirVideo();
        } else {
            vvPlato.pause();
            controlesVideo.hide();
        }
        if (botonId == R.id.btnWeb && !webCargada) {
            mostrarReceta();
        }
    }

    // Sección Perfil
    private void mostrarPerfil() {
        imgPlato.setImageResource(platoSeleccionado.getImagenResId());
        txtRegionPlato.setText(getString(R.string.region_formato, platoSeleccionado.getOrigen()));
        txtIngredientes.setText(platoSeleccionado.getIngredientes());
        txtHistoria.setText(platoSeleccionado.getHistoria());

        Cocinero cocinero = platoSeleccionado.getCocinero();
        txtNombreCocinero.setText(cocinero.getNombre());
        txtRolCocinero.setText(cocinero.getRol());
        txtEstudios.setText(cocinero.getEstudios());
        txtExperiencia.setText(cocinero.getExperiencia());
    }

    // Sección Fotos: crea las miniaturas y muestra la primera foto
    private void mostrarGaleria() {
        contenedorMiniaturas.removeAllViews();
        for (ImagenGaleria foto : platoSeleccionado.getGaleria()) {
            ImageView miniatura = (ImageView) getLayoutInflater()
                    .inflate(R.layout.item_miniatura, contenedorMiniaturas, false);
            miniatura.setImageResource(foto.getImagenResId());
            miniatura.setOnClickListener(v -> mostrarFoto(foto));
            contenedorMiniaturas.addView(miniatura);
        }
        if (!platoSeleccionado.getGaleria().isEmpty()) {
            mostrarFoto(platoSeleccionado.getGaleria().get(0));
        }
    }

    // Evento: el usuario toca una miniatura
    private void mostrarFoto(ImagenGaleria foto) {
        imgFotoGrande.setImageResource(foto.getImagenResId());
        txtDescripcionFoto.setText(foto.getDescripcion());
    }

    // Sección Video.
    // getIdentifier se usa a propósito: el video local (res/raw/<plato>.mp4) es opcional y no se
    // puede referenciar como R.raw.<plato> si el archivo todavía no existe.
    @SuppressLint("DiscouragedApi")
    private void reproducirVideo() {
        if (!videoCargado) {
            // Si existe el video local res/raw/<nombre_del_plato>.mp4 se usa; si no, el video genérico en línea
            String paquete = requireContext().getPackageName();
            int rawId = getResources().getIdentifier(platoSeleccionado.getNombreRecurso(), "raw", paquete);
            Uri uri = rawId != 0
                    ? Uri.parse("android.resource://" + paquete + "/" + rawId)
                    : Uri.parse(platoSeleccionado.getUrlVideo());
            vvPlato.setVideoURI(uri);
            videoCargado = true;
        }
    }

    // Sección Web: carga en el WebView la URL escrita en la caja de texto
    private void mostrarReceta() {
        String url = etUrl.getText().toString().trim();
        if (url.isEmpty()) {
            Toast.makeText(requireContext(), R.string.url_vacia, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        wvReceta.loadUrl(url);
        webCargada = true;
    }

    // Sección Botones: alterna favorito
    private void marcarFavorito() {
        platoSeleccionado.marcarFavorito();
        preferenciasFavoritos().edit()
                .putBoolean(platoSeleccionado.getNombre(), platoSeleccionado.esFavorito())
                .apply();
        actualizarFavorito();
        Toast.makeText(requireContext(),
                platoSeleccionado.esFavorito() ? R.string.favorito_agregado : R.string.favorito_quitado,
                Toast.LENGTH_SHORT).show();
    }

    private SharedPreferences preferenciasFavoritos() {
        return requireContext().getSharedPreferences(PREFS_FAVORITOS, Context.MODE_PRIVATE);
    }

    private void actualizarFavorito() {
        boolean favorito = platoSeleccionado.esFavorito();
        imgFavorito.setVisibility(favorito ? View.VISIBLE : View.GONE);
        btnFavorito.setText(favorito ? R.string.quitar_favoritos : R.string.agregar_favoritos);
    }

    // Sección Botones: comparte el plato con otras aplicaciones
    private void compartirPlato() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.compartir_texto,
                platoSeleccionado.getNombre(), platoSeleccionado.getUrlReceta()));
        startActivity(Intent.createChooser(intent, getString(R.string.compartir)));
    }
}
