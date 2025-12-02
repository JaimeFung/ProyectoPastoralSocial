package edu.ulatina.util;

public class StringUtils {
    
    /**
     * Repite un string N veces (compatible con Java 8)
     * Equivalente a String.repeat() de Java 11+
     */
    public static String repeat(String str, int count) {
        if (count <= 0) {
            return "";
        }
        StringBuilder result = new StringBuilder(str.length() * count);
        for (int i = 0; i < count; i++) {
            result.append(str);
        }
        return result.toString();
    }
    
    /**
     * Crea una línea horizontal
     */
    public static String linea(int longitud) {
        return repeat("─", longitud);
    }
    
    /**
     * Crea una línea de igual signs
     */
    public static String lineaIgual(int longitud) {
        return repeat("═", longitud);
    }
}