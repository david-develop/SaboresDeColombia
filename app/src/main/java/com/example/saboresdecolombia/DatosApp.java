package com.example.saboresdecolombia;

import java.util.ArrayList;
import java.util.List;

// Datos de ejemplo de la app. En una entrega posterior se reemplazan por datos reales.
public class DatosApp {

    // Video genérico (CC0, MDN) para todos los platos mientras se consiguen los reales
    private static final String VIDEO_EJEMPLO =
            "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4";
    private static final String WIKI = "https://es.wikipedia.org/wiki/";
    // Artículo sobre la gastronomía de cada región (para platos sin página propia en Wikipedia)
    private static final String COLOMBIA_CO =
            "https://colombia.co/cultura-colombiana/gastronomia/gastronomia-colombiana-conoce-las-comidas-de-cada-region-del-pais";

    private static List<Region> regiones;

    // Personas ficticias (uso académico) que representan la cocina de cada región
    private static Cocinero cocineroDe(String region) {
        switch (region) {
            case "Caribe":
                return new Cocinero("Yolanda Barrios Pérez", "Cocinera tradicional · Cartagena",
                        "Tecnóloga en Gastronomía. Curso de cocina de pescados y mariscos del Caribe.",
                        "18 años en cocinas de la Ciudad Amurallada y en festivales gastronómicos de la costa.",
                        R.drawable.ic_avatar_caribe);
            case "Pacífica":
                return new Cocinero("Maritza Angulo Caicedo", "Cocinera tradicional · Buenaventura",
                        "Formación en cocina del Pacífico en talleres comunitarios. Curso de manipulación de alimentos.",
                        "22 años preparando encocados y arroces en su restaurante familiar y en encuentros de saberes afrocolombianos.",
                        R.drawable.ic_avatar_pacifica);
            case "Orinoquía":
                return new Cocinero("Wilmer Torres Rincón", "Cocinero llanero · Villavicencio",
                        "Técnico en Cocina Colombiana. Diplomado en cocina de fogón y carnes a la brasa.",
                        "20 años como asador en fincas y festivales de la música llanera. Instructor de cocina de campo.",
                        R.drawable.ic_avatar_orinoquia);
            case "Amazonía":
                return new Cocinero("Luz Marina Cabrera", "Cocinera tradicional · Leticia",
                        "Curso de aprovechamiento de productos del río y de la yuca. Taller de cocina sostenible.",
                        "15 años cocinando con productos de la chagra y del río. Participante en ferias gastronómicas amazónicas.",
                        R.drawable.ic_avatar_amazonia);
            default:
                return new Cocinero("Rosa Elvira Cárdenas", "Cocinera tradicional · Bogotá",
                        "Técnica en Cocina Colombiana. Diplomado en patrimonio culinario de la región andina.",
                        "25 años preparando platos andinos en su restaurante familiar de La Candelaria. Instructora de talleres de cocina tradicional.",
                        R.drawable.ic_avatar_andina);
        }
    }

    public static synchronized List<Region> obtenerRegiones() {
        if (regiones == null) {
            regiones = new ArrayList<>();

            Region andina = new Region("Andina", R.color.region_andina);
            andina.agregarPlato(crearPlato("Ajiaco", "Andina",
                    "Pollo, papa, mazorca, guascas, crema de leche, alcaparras",
                    "Plato tradicional de Bogotá, con origen indígena y gran importancia cultural en la región andina.",
                    "Ajiaco",
                    R.drawable.ajiaco_1, R.drawable.ajiaco_2, R.drawable.ajiaco_3));
            andina.agregarPlato(crearPlato("Bandeja paisa", "Andina",
                    "Frijoles, arroz, chicharrón, carne molida, chorizo, huevo, plátano maduro, arepa, aguacate",
                    "Plato insignia de Antioquia, nacido como comida abundante para los arrieros y campesinos.",
                    "Bandeja_paisa",
                    R.drawable.bandeja_paisa_1, R.drawable.bandeja_paisa_2, R.drawable.bandeja_paisa_3));
            andina.agregarPlato(crearPlato("Tamal", "Andina",
                    "Masa de maíz, cerdo, pollo, arroz, arveja, zanahoria, hoja de plátano",
                    "Preparación envuelta en hoja de plátano, presente en celebraciones familiares de toda la región.",
                    "Tamal",
                    R.drawable.tamal_1));
            regiones.add(andina);

            Region caribe = new Region("Caribe", R.color.region_caribe);
            caribe.agregarPlato(crearPlato("Arroz con coco", "Caribe",
                    "Arroz, coco, azúcar, pasas",
                    "Plato típico de la costa Caribe colombiana, acompañante clásico del pescado frito.",
                    "Arroz_con_coco",
                    R.drawable.arroz_con_coco_1));
            caribe.agregarPlato(crearPlato("Sancocho de pescado", "Caribe",
                    "Pescado, yuca, plátano, ñame, cilantro",
                    "Sopa costeña de influencia africana e indígena, muy común en los pueblos de pescadores.",
                    "Sancocho",
                    R.drawable.sancocho_pescado_1));
            regiones.add(caribe);

            Region pacifica = new Region("Pacífica", R.color.region_pacifica);
            pacifica.agregarPlato(crearPlato("Encocado de pescado", "Pacífica",
                    "Pescado, leche de coco, plátano, cilantro",
                    "Preparación típica del Pacífico colombiano, con fuerte influencia afrocolombiana.",
                    COLOMBIA_CO,
                    R.drawable.encocado_1));
            pacifica.agregarPlato(crearPlato("Arroz atollado", "Pacífica",
                    "Arroz, cerdo, pollo, chorizo, papa, hogao",
                    "Arroz caldoso tradicional del Valle del Cauca, servido en reuniones familiares.",
                    "Arroz_atollado",
                    R.drawable.arroz_atollado_1));
            regiones.add(pacifica);

            Region orinoquia = new Region("Orinoquía", R.color.region_orinoquia);
            orinoquia.agregarPlato(crearPlato("Carne a la llanera", "Orinoquía",
                    "Carne de res, sal, yuca, plátano, guiso criollo",
                    "Carne asada a las brasas, símbolo de la cultura llanera y de las fiestas de los Llanos Orientales.",
                    "Carne_a_la_llanera",
                    R.drawable.carne_llanera_1));
            orinoquia.agregarPlato(crearPlato("Mojojoy", "Orinoquía",
                    "Larva de mojojoy, sal",
                    "Delicia tradicional indígena de los Llanos Orientales, rica en proteína.",
                    "Mojojoy",
                    R.drawable.mojojoy_1));
            regiones.add(orinoquia);

            Region amazonia = new Region("Amazonía", R.color.region_amazonia);
            amazonia.agregarPlato(crearPlato("Pescado amazónico", "Amazonía",
                    "Pescado de río, yuca, plátano",
                    "Plato representativo de las comunidades indígenas del Amazonas colombiano.",
                    COLOMBIA_CO,
                    R.drawable.pescado_amazonico_1));
            amazonia.agregarPlato(crearPlato("Casabe", "Amazonía",
                    "Yuca brava, agua",
                    "Torta delgada de yuca, alimento base de los pueblos indígenas amazónicos.",
                    "Casabe",
                    R.drawable.casabe_1, R.drawable.casabe_2, R.drawable.casabe_3));
            regiones.add(amazonia);
        }
        return regiones;
    }

    // fotos: recursos de imagen del plato; la primera es la foto principal.
    // Si no se indican, se usan imágenes de ejemplo hasta tener las reales.
    private static Plato crearPlato(String nombre, String origen, String ingredientes,
                                    String historia, String enlace, int... fotos) {
        List<ImagenGaleria> galeria = new ArrayList<>();
        if (fotos.length == 0) {
            galeria.add(new ImagenGaleria(R.drawable.fondo_cartagena, nombre + " (foto de ejemplo)"));
            galeria.add(new ImagenGaleria(R.drawable.logo_sabores, nombre + " (foto de ejemplo)"));
        } else {
            for (int i = 0; i < fotos.length; i++) {
                galeria.add(new ImagenGaleria(fotos[i], nombre + " - foto " + (i + 1)));
            }
        }
        // El enlace puede ser una URL completa o solo el nombre de una página de Wikipedia
        String urlReceta = enlace.startsWith("http") ? enlace : WIKI + enlace;
        return new Plato(nombre, origen, ingredientes, historia,
                VIDEO_EJEMPLO, urlReceta, galeria.get(0).getImagenResId(), galeria, cocineroDe(origen));
    }
}
