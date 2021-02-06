package com.wattyla.springbootmongodb.repositorios.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

public class URL {
	public static String decodificaParametro(String texto) {
		try {
			return URLDecoder.decode(texto, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static Instant converteData(String textoData, Instant valorPadrao) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textoData).toInstant();
		} catch (ParseException e) {
			return valorPadrao;
		}
	}
}
