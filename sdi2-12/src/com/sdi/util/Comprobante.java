package com.sdi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sdi.model.Waypoint;


public class Comprobante {
	
	/**
	 * Comprueba si los parametros introducidos son null o vacios
	 * @param args
	 * @return True si son correctos
	 * @return False si estan vacios o es null.
	 */
	public static boolean comprobarDatos(String... args){
		for (String string : args) {
			if(string == null || string.isEmpty())
				return false;
		}
		return true;
	}
	
	/**
	 * Comprueba que el String introducido es de tipo int
	 * @param arg
	 * @return El valor en entero si es correcto
	 * @return -1 en caso contrario
	 */
	public static int comprobarInt(String arg){
		int num = -1;
		try{
			num = Integer.parseInt(arg);
		}catch(Exception e){
		}
		return num;
	}

	/**
	 * Coprueba que el String introducido es de tipo Date
	 * @param arg
	 * @return True si es una fecha
	 * @return False en caso contrario
	 */
	public static Date comprobarFecha(String arg) {
		Date fecha = null;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			fecha = formatter.parse(arg);
			
		}catch(Exception e){
			return fecha;
		}
		return fecha;
	}

	public static int[] comprobarHora(String arg) {
		int[] hora = new int[2];
		
		try {
			String[] array = arg.split(":");
			hora[0] = Integer.parseInt(array[0]);
			hora[1] = Integer.parseInt(array[1]);
			
		} catch (Exception e) {
			return hora;
		}
		return hora;
	}

	public static Waypoint comprobarPunto(String arg) {
		Waypoint punto = null;
		
		try {
			String[] array = arg.split(",");
			punto = new Waypoint(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
			
		} catch (Exception e) {
			return punto;
		}
		return punto;
		
	}

}
