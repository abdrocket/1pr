package abd.mappers.keys;

public class PeticionesKey {
	
	private String usuario_source;
	private Integer crucigrama;
	
	public PeticionesKey(String usuario_source, Integer crucigrama) {
		this.usuario_source = usuario_source;
		this.crucigrama = crucigrama;
	}

	public String getUsuario_source() {
		return usuario_source;
	}

	public void setUsuario_source(String usuario_source) {
		this.usuario_source = usuario_source;
	}

	public Integer getCrucigrama() {
		return crucigrama;
	}

	public void setCrucigrama(Integer crucigrama) {
		this.crucigrama = crucigrama;
	}

}
