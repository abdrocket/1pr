package abd.model;

import java.util.Date;

public class Usuario {
	private String nombre;
	private String password;
	private Date fechaNac;
	private byte[] imagen;
	
	
	public Usuario(String nombre, String password, Date fechaNac, byte[] imagen){
		this.nombre = nombre;
		this.password = password;
		this.fechaNac = fechaNac;
		this.imagen = imagen;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getPassword() {
		return password;
	}
	public Date getFechaNac() {
		return fechaNac;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

}
