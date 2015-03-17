package abd.model;

import java.sql.Time;
import java.util.Date;

public class Historial {
	private Integer crucigrama;
	private String usuario;
	private String propietario;
	private String respuesta;
	private Integer palabra;
	private Date fecha;
	private Time hora;
	private Integer correcta;
	private Integer puntuacion;
	
	public Historial(Integer crucigrama, String usuario, String propietario,
			String respuesta, Integer palabra, Date fecha, Time hora, Integer correcta,
			Integer puntuacion) {
		this.crucigrama = crucigrama;
		this.usuario = usuario;
		this.propietario = propietario;
		this.respuesta = respuesta;
		this.palabra = palabra;
		this.fecha = fecha;
		this.hora = hora;
		this.correcta = correcta;
		this.puntuacion = puntuacion;
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

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	public Integer getPalabra() {
		return palabra;
	}

	public void setPalabra(Integer palabra) {
		this.palabra = palabra;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Integer getCorrecta() {
		return correcta;
	}

	public void setCorrecta(Integer correcta) {
		this.correcta = correcta;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	
}
