package abd.mappers.keys;

public class ContieneKey {
	private Integer crucigrama;
	private Integer palabra;
	public ContieneKey(Integer crucigrama, Integer palabra) {
		this.crucigrama = crucigrama;
		this.palabra = palabra;
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
	
	
}
