package ejercicioFinal;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Exepciones {
	    public static boolean esEntero(String cadena) {
	        try {
	            Integer.parseInt(cadena);
	            return true;
	        	} catch (NumberFormatException error) {
	            return false;
	        	}
	    }
	    
	    public static boolean esFloat(String numeroString) {
	        try {
	            Float.parseFloat(numeroString);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	    
	    public static boolean verificarFecha(int dia, int mes, int anio) {
	        try {
	            LocalDate.of(anio, mes, dia);
	            return true;
	        } catch (DateTimeException e) {
	            return false;
	        }
	    }



}

