package n2ejercicio1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Directorio {

    public void verificaDirectorio() {
        Properties properties = cargarConfiguracion();

        String rutaDirectorio = properties.getProperty("rutaDirectorio");
        String rutaArchivoTxt = properties.getProperty("rutaArchivoTxt");

        if (rutaDirectorio == null || rutaArchivoTxt == null) {
            System.out.println("Error: Configuración incompleta.");
            return;
        }

        File directorio = new File(rutaDirectorio);

        if (!directorio.isDirectory()) {
            System.out.println(rutaDirectorio + " no es un directorio válido.");
            return;
        }

        crearArchivoTxt(directorio, rutaArchivoTxt);
    }

    private void crearArchivoTxt(File directorio, String rutaArchivoTxt) {
        File archivo = new File(directorio, rutaArchivoTxt);

        try (PrintStream imprimir = new PrintStream(new FileOutputStream(archivo))) {
            listarDirectorioRecursivamente(imprimir, directorio, 0);
            System.out.println("Archivo TXT creado exitosamente.");

        } catch (IOException e) {
            System.out.println("Error al crear el archivo TXT: " + e.getMessage());
        }
    }

    private void listarDirectorioRecursivamente(PrintStream printStream, File directorio, int nivel) {
        File[] elementos = Objects.requireNonNull(directorio.listFiles());

        Arrays.sort(elementos);

        for (File elemento : elementos) {
            imprimirElemento(printStream, elemento, nivel);

            if (elemento.isDirectory()) {
                listarDirectorioRecursivamente(printStream, elemento, nivel + 1);
            }
        }
    }

    private void imprimirElemento(PrintStream printStream, File elemento, int nivel) {
        String tipo = elemento.isDirectory() ? "D" : "F";
        String nombre = elemento.getName();
        String fechaModificacion = obtenerFechaModificacion(elemento);

        printStream.println("  ".repeat(Math.max(0, nivel)) + tipo + " - " + nombre + " - " + fechaModificacion);
    }

    private String obtenerFechaModificacion(File elemento) {
        Date fechaModificacion = new Date(elemento.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(fechaModificacion);
    }

    private Properties cargarConfiguracion() {
        Properties properties = new Properties();

        try (InputStream inputStream = new FileInputStream("src/n2ejercicio1/miCarpeta/miArchivo.properties")) {
            properties.load(inputStream);

        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de configuración: " + e.getMessage());
        }

        return properties;
    }
}
