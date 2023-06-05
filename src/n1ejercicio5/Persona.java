package n1ejercicio5;

import java.io.*;

// Clase de ejemplo para la serialización
record Persona(String nombre, int edad) implements Serializable {
}

/*
interfaz Serializable, indica que la clase que la implementa es susceptible a ser
trasformada a Bytes.
*/
