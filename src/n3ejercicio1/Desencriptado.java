package n3ejercicio1;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.security.*;

public class Desencriptado {

    public static void desencriptar(String rutaArchivo , String rutaDestino, String key) throws Exception {
        File ruta = new File(rutaArchivo);
        FileInputStream archivoEntrada = new FileInputStream(ruta);
        FileOutputStream archivoSalida = new FileOutputStream(rutaDestino);

        byte[] fileBytes = new byte[(int) ruta.length()];
        archivoEntrada.read(fileBytes);

        // Extraer el vector de inicialización (IV) del archivo encriptado
        byte[] ivBytes = new byte[16];
        System.arraycopy(fileBytes, 0, ivBytes, 0, 16);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] decryptedBytes = cipher.doFinal(fileBytes, 16, fileBytes.length - 16);
        archivoSalida.write(decryptedBytes);

        archivoEntrada.close();
        archivoSalida.close();

    }
}
