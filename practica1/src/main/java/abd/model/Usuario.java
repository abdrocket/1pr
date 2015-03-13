package abd.model;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
	private String nombre;
	private String password;
	private Date fechaNac;
	private byte[] imagen;
	private int puntuacion;
	private ArrayList<Usuario> amigos;
	private ArrayList<Crucigrama> activos;
	
	public Usuario(String nombre, String password, Date fechaNac, byte[] imagen){
		this.nombre = nombre;
		this.password = password;
		this.fechaNac = fechaNac;
		this.imagen = imagen;
		this.puntuacion = 0;
		this.amigos  = new ArrayList<Usuario>();
		this.activos = new ArrayList<Crucigrama>();
	}
	
	public String getNombre() {
		return nombre;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public int getPuntuacion() {
		return puntuacion;
	}
	
	public String toString(){
		return this.nombre + " " + this.puntuacion;
	}

}
