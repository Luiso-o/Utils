package n3ejercicio1;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.security.*;

public class Encriptado {

    public static void encriptar(String rutaArchivo , String rutaDestino, String key) throws Exception {
        File ruta = new File(rutaArchivo );
        FileInputStream archivoEntrada = new FileInputStream(ruta);
        FileOutputStream archivoSalida = new FileOutputStream(rutaDestino);

        byte[] llave = new byte[(int) ruta.length()];//verifico la longitud
        archivoEntrada.read(llave);

        // Generar un vector de inicialización (IV) aleatorio
        SecureRandom random = new SecureRandom();
        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");//Advanced Encryption Standard
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//(Advanced Encryption Standard/Cipher Block Chaining/metodo de llenado(Padding))
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        byte[] encryptedBytes = cipher.doFinal(llave);

        // Escribir el IV al principio del archivo encriptado
        archivoSalida.write(ivBytes);
        archivoSalida.write(encryptedBytes);

        archivoEntrada.close();
        archivoSalida.close();

    }

}
