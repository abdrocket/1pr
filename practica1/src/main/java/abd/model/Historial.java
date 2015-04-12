package abd.model;

import java.util.Date;

public class Historial {
	private Integer crucigrama;
	private String usuario;
	private String propietario;
	private String respuesta;
	private Integer palabra;
	private Date fecha;
	private Integer id;
	private Integer correcta;
	
	public Historial(Integer crucigrama, String usuario, String propietario,
			String respuesta, Integer palabra, Date fecha, Integer correcta, Integer id) {
		this.crucigrama = crucigrama;
		this.usuario = usuario;
		this.propietario = propietario;
		this.respuesta = respuesta;
		this.palabra = palabra;
		this.fecha = fecha;
		this.id = correcta;
		this.correcta = correcta;
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCorrecta() {
		return correcta;
	}

	public void setCorrecta(Integer correcta) {
		this.correcta = correcta;
	}
}
