package n1ejercicio3;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

//PrintStream
public class Directorio {
    public static void verificaDirectorio(String ruta) {
        File directorio = new File(ruta);

        if (!directorio.isDirectory()) {
            System.out.println(ruta + " no es un directorio válido.");
        }

        crearArchivoTxt(directorio);
    }

    private static void crearArchivoTxt(File directorio) {
        File archivo = new File(directorio, "contenido_directorio.txt");

        try (PrintStream imprimir = new PrintStream(new FileOutputStream(archivo))) {
            listarDirectorioRecursivamente(imprimir, directorio, 0);
            System.out.println("Archivo TXT creado exitosamente.");

        } catch (IOException e) {
            System.out.println("Error al crear el archivo TXT: " + e.getMessage());
        }
    }


    private static void listarDirectorioRecursivamente(PrintStream printStream, File directorio, int nivel) {
        File[] elementos = Objects.requireNonNull(directorio.listFiles());

        Arrays.sort(elementos);

        for (File elemento : elementos) {
            imprimirElemento(printStream, elemento, nivel);

            if (elemento.isDirectory()) {
                listarDirectorioRecursivamente(printStream, elemento, nivel + 1);
            }
        }
    }

    private static void imprimirElemento(PrintStream printStream, File elemento, int nivel) {
        String tipo = elemento.isDirectory() ? "D" : "F";
        String nombre = elemento.getName();
        String fechaModificacion = obtenerFechaModificacion(elemento);

        printStream.println("  ".repeat(Math.max(0, nivel)) + tipo + " - " + nombre + " - " + fechaModificacion);
    }

    private static String obtenerFechaModificacion(File elemento) {
        Date fechaModificacion = new Date(elemento.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(fechaModificacion);
    }

    public static void main(String[] args) {
        String directorio = "src" + File.separator +"Misdocumentos";
        verificaDirectorio(directorio);
    }
}
