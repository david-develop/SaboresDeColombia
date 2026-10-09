# Sabores de Colombia – Ruta gastronómica (Grupo 14)

Aplicación Android (Java) para explorar los platos típicos de las regiones de Colombia.
La pantalla principal muestra dos fragmentos a la vez: a la izquierda las regiones y sus platos
(`FragmentoListado`) y a la derecha la información del plato con cinco opciones:
Perfil, Fotos, Video, Web y Botones (`FragmentoDetalle`).

## Cómo abrir y ejecutar
1. Android Studio → File → Open → seleccionar la carpeta `SaboresDeColombia`.
   La ruta de la carpeta no debe tener tildes ni caracteres especiales.
2. Esperar la sincronización de Gradle.
3. Crear un emulador (p. ej. Pixel 9, API 35) o conectar un celular y ejecutar la app.
   El video y las páginas web requieren conexión a Internet.

## Estructura
- `MainActivity`: pantalla de bienvenida.
- `RutasGastronomicasActivity`: aloja los dos fragmentos y los comunica.
- `FragmentoListado` (regiones y platos) y `FragmentoDetalle` (cinco secciones).
- `DatosApp`, `Region`, `Plato`, `ImagenGaleria`: datos de ejemplo y modelo.

## Contenido (fotos y videos)
- **Fotos:** están en `app/src/main/res/drawable/` (JPG de máx. 1000 px) y se asignan a cada plato
  en `DatosApp.java`.
- **Videos:** si existe el archivo `app/src/main/res/raw/<nombre_del_plato>.mp4`, el plato usa ese
  video; si no, usa el video genérico en línea (`VIDEO_EJEMPLO` en `DatosApp.java`).
  Nombres esperados (minúsculas, sin tildes): `ajiaco`, `bandeja_paisa`, `tamal`, `arroz_con_coco`,
  `sancocho_de_pescado`, `encocado_de_pescado`, `arroz_atollado`, `carne_a_la_llanera`, `mojojoy`,
  `pescado_amazonico`, `casabe`. Se recomienda que cada video pese menos de 10 MB.
