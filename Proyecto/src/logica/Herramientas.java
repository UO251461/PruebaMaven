package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Herramientas {
	
	public static boolean stringNoVacio(String cadena) {
		return cadena.isEmpty() ? false : true;
	}

	public static boolean esNumericoYNoVacio(String cadena) {
		if (!cadena.isEmpty()) {
			for (int i = 0; i < cadena.length(); ++i) {
				char caracter = cadena.charAt(i);
				if (Character.isDigit(caracter)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static java.sql.Date convertirStringFecha(String cadena) {
		SimpleDateFormat format = new SimpleDateFormat("d/MM/yyyy");
		Date parsed = null;
		try {
			parsed = format.parse(cadena);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (parsed != null) {
			java.sql.Date sql = new java.sql.Date(parsed.getTime());
			return sql;
		}
		return null;
	}
	
	


}
