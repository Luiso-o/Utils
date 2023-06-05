package n1ejercicio2;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

//recursividad
public class Directorio {
    public static void verificaDirectorio(String ruta) {
        File directorio = new File(ruta);

        // Verificamos que el directorio sea válido
        if (!directorio.isDirectory()) {
            System.out.println(ruta + " no es un directorio válido.");
        }

        listarDirectorioRecursivamente(directorio, 0);
    }

    private static void listarDirectorioRecursivamente(File directorio, int nivel) {
        File[] elementos = Objects.requireNonNull(directorio.listFiles());//Objects.requireNonNull metodo de la clase Object que verifica si un objeto es nulo

        Arrays.sort(elementos); // Ordenamos los elementos en orden alfabético

        for (File elemento : elementos) { //imprimo los elementos del directorio
            imprimirElemento(elemento, nivel); //imprimo los elementos con su formato D

            //el metodo se llama así mismo
            if (elemento.isDirectory()) {
                listarDirectorioRecursivamente(elemento, nivel + 1); // Llamada recursiva para listar los elementos del subdirectorio
            }
        }
    }

    private static void imprimirElemento(File elemento, int nivel) {

        String tipo = elemento.isDirectory() ? "D" : "F"; //if D : else F
        String nombre = elemento.getName();//imprimimos el nombre del elemento
        String fechaModificacion = obtenerFechaModificacion(elemento);//lamamos al metodo obtenerFechaModificacion

        System.out.println("  ".repeat(Math.max(0, nivel)) // Espacios para la indentación según el nivel
                + tipo + " - " + nombre + " - " + fechaModificacion);
    }

    private static String obtenerFechaModificacion(File elemento) {
        Date fechaModificacion = new Date(elemento.lastModified());//Instancio un objeto
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//creo el formato
        return sdf.format(fechaModificacion);//devuelvo los datos con fecha y hora
    }

    public static void main(String[] args) {
        String directorio = "src" + File.separator +"Misdocumentos"; // Especifica la ruta del directorio aquí
        verificaDirectorio(directorio);
    }
}
