package abd.model;

public class Etiquetas {
	
	private String etiqueta;
	private Integer palabra;
	
	public Etiquetas(String etiqueta, Integer palabra) {
		this.etiqueta = etiqueta;
		this.palabra = palabra;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public Integer getPalabra() {
		return palabra;
	}
	public void setPalabra(Integer palabra) {
		this.palabra = palabra;
	}
	
	
}
