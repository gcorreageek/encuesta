package com.encuesta.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.el.ELProcessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Utiles {
	private final static Log log = LogFactory.getLog("Utiles"); 
//	public static void main(String[] args) {
//		Utiles u = new Utiles(); 
//		 
//	}
	
	
	public static Date stringToDate(String dia,String mes,String annio) { 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return format.parse(new StringBuilder().append(annio).append("-").append(mes).append("-").append(dia).append(" 00:00:00").toString());
		} catch (ParseException e) { 
			log.error("",e);
		}
		return null;
	}
	public static Date stringToDate(String date) { 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return format.parse(date);
		} catch (ParseException e) { 
			log.error("",e);
		}
		return null;
	}
	public static String dateToString(Date date) { 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(date);
	}
	 
	
	public static java.sql.Date fechaDateSql(String fecString){ 
		java.sql.Date fecFormatoDate = null;
		try {													   
		      SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
		      fecFormatoDate = new java.sql.Date(sdf.parse(fecString).getTime()); 
		} catch (Exception ex) {
		      System.out.println("Error al obtener el formato de la fecha/hora: " + ex.getMessage());
		} 
		return fecFormatoDate;
	}
	public static String getResultado(String formula){
		ELProcessor elp = new ELProcessor();
//		String campoDB= formula;
//		Object ret = elp.eval(campoDB);
		return elp.eval(formula).toString();
	}
	
}
