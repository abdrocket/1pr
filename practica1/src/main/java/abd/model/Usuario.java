package abd.model;

import java.sql.Date;
import java.util.List;

public class Usuario {
	private String nombre;
	private Date fechaNac;
	private byte[] imagen;
	private int puntuacion;
	private List<Usuario> amigos;
	private List<Crucigrama> activos;
	
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

}
