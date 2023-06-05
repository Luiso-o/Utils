package n1ejercicio5;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Directorio {
    public static void verificaDirectorio(String ruta) {
        File directorio = new File(ruta);

        if (!directorio.isDirectory()) {
            System.out.println(ruta + " no es un directorio válido.");
        }

        crearArchivoTxt(directorio);

        Persona persona = new Persona("Juan", 25);
        String archivoSerializacion = "persona.ser";

        serializarObjeto(persona, archivoSerializacion);
        deserializarObjeto(archivoSerializacion);

    }

    private static void crearArchivoTxt(File directorio) {
        File archivo = new File(directorio, "contenido_directorio.txt");

        try (PrintStream printStream = new PrintStream(new FileOutputStream(archivo))) {
            listarDirectorioRecursivamente(printStream, directorio, 0);
            System.out.println("Archivo TXT creado exitosamente.");

        } catch (IOException e) {
            System.out.println("Error al crear el archivo TXT: " + e.getMessage());
        }
    }

    private static void listarDirectorioRecursivamente(PrintStream imprime, File directorio, int nivel) {
        File[] elementos = Objects.requireNonNull(directorio.listFiles());

        Arrays.sort(elementos);

        for (File elemento : elementos) {
            imprimirElemento(imprime, elemento, nivel);

            if (elemento.isDirectory()) {
                listarDirectorioRecursivamente(imprime, elemento, nivel + 1);
            }
        }
    }

    private static void serializarObjeto(Persona persona, String archivoSerializacion) {

        try (ObjectOutputStream serializador = new ObjectOutputStream(new FileOutputStream(archivoSerializacion))) {
            serializador.writeObject(persona);
            System.out.println("Objeto serializado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al serializar el objeto: " + e.getMessage());
        }
    }

    private static void deserializarObjeto(String archivoSerializacion) {
        try (ObjectInputStream deserializacion = new ObjectInputStream(new FileInputStream(archivoSerializacion))) {
            Persona personaDeserializada = (Persona) deserializacion.readObject();
            System.out.println("Objeto deserializado correctamente.");
            System.out.println("Nombre: " + personaDeserializada.nombre());
            System.out.println("Edad: " + personaDeserializada.edad());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar el objeto: " + e.getMessage());
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

}

