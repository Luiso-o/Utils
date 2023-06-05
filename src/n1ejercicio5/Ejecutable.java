package n1ejercicio5;

import java.io.File;

public class Ejecutable {
    public static void main(String[] args) {

        Directorio miDirectorio = new Directorio();

        String ruta = "src" + File.separator + "Misdocumentos";
       miDirectorio.verificaDirectorio(ruta);

    }
}
