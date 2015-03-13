package abd.model;

import java.util.HashMap;

public class Palabra {
	
	private int id;
	private String palabra;
	private String enunciado;
	private byte[] imagen;
	private int orientacion;
	private int x;
	private int y;
	private int puntuacion;
	private HashMap<String,String> etiquetas;
	
	public int getId() {
		return id;
	}
	public String getPalabra() {
		return palabra;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public int getOrientacion() {
		return orientacion;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public HashMap<String,String> getEtiquetas() {
		return etiquetas;
	}
	
}
