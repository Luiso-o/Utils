package n3ejercicio1;

public class Encriptacion {
    public static void main(String[] args) {

        String rutaArchivo = "C:\\untitled\\Utils\\src\\n3ejercicio1\\encriptado.enc";//ruta del archivo
        String rutaDestino = "C:\\untitled\\Utils\\src\\n3ejercicio1\\desencriptado.enc";//ruta donde se guardará el documento encriptado
        String key = "1234567890123456";//clave secreta de encriptado (16 bytes)

        //encriptado del archivo
        try {
            n3ejercicio1.Encriptado.encriptar(rutaArchivo, rutaDestino , key);
            System.out.println("Archivo encriptado exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al encriptar el archivo: " + e.getMessage());
        }

        //desencriptado del archivo
        try {
            n3ejercicio1.Desencriptado.desencriptar(rutaArchivo, rutaDestino, key);
            System.out.println("Archivo desencriptado exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al desencriptar el archivo: " + e.getMessage());
        }

    }

}
