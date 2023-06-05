package n1ejercicio1;

import java.io.File;
import java.util.Arrays;

public class Directorio {
    public static void verificaDirectorio(String ruta) {

            File directorio = new File(ruta);//ingresamos el directorio que el usuario quiera revisar

            //verificamos que el directorio sea válido
            if (!directorio.isDirectory()) {
                System.out.println(ruta + " no es un directorio válido.");
            }

            //creamos una lista del directorio proporcionado y verificamos si tiene elementos en ella
            File[] carpetas = directorio.listFiles();
            if (carpetas == null || carpetas.length == 0) {
                System.out.println("El directorio está vacío.");
            }

            assert carpetas != null;
            Arrays.sort(carpetas);//ordenamos la matriz en orden alfabético

            //recorremos la lista
            System.out.println("Contenido del directorio " + directorio.getAbsolutePath() + ":");
            for (File file : carpetas) {
                System.out.println(file.getName());
            }
    }

    public static void main(String[] args) {
        String directorio = "src"+ File.separator +"Misdocumentos"; // Especifica la ruta del directorio aquí
        verificaDirectorio(directorio);
    }
}
