package abd.mappers.keys;

public class EtiquetasKey {
	
	private String etiqueta;
	private Integer palabra;
	
	public EtiquetasKey(String etiqueta, Integer palabra) {
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
