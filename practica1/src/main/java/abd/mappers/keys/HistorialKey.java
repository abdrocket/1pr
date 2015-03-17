package abd.mappers.keys;

import java.sql.Time;

public class HistorialKey {
	
	private Integer crucigrama;
	private String usuario;
	private Integer palabra;
	private Time hora;
	
	public HistorialKey(Integer crucigrama, String usuario, Integer palabra,
			Time hora) {
		this.crucigrama = crucigrama;
		this.usuario = usuario;
		this.palabra = palabra;
		this.hora = hora;
	}
	
	public Integer getCrucigrama() {
		return crucigrama;
	}

	public void setCrucigrama(Integer crucigrama) {
		this.crucigrama = crucigrama;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getPalabra() {
		return palabra;
	}

	public void setPalabra(Integer palabra) {
		this.palabra = palabra;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}
	
	
}
