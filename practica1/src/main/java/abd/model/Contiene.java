package abd.model;

public class Contiene {
	private Integer crucigrama;
	private Integer palabra;
	private Integer orientacion;
	private Integer x;
	private Integer y;
	private Integer puntuacion;
	
	public Contiene(Integer crucigrama, Integer palabra, Integer orientacion,
			Integer x, Integer y, Integer puntuacion) {
		this.crucigrama = crucigrama;
		this.palabra = palabra;
		this.orientacion = orientacion;
		this.x = x;
		this.y = y;
		this.puntuacion = puntuacion;
	}

	public Integer getCrucigrama() {
		return crucigrama;
	}

	public void setCrucigrama(Integer crucigrama) {
		this.crucigrama = crucigrama;
	}

	public Integer getPalabra() {
		return palabra;
	}

	public void setPalabra(Integer palabra) {
		this.palabra = palabra;
	}

	public Integer getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Integer orientacion) {
		this.orientacion = orientacion;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
}
