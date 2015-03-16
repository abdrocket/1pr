package abd.mappers.keys;

public class HistorialKey {
	
	private Integer crucigrama;
	private String usuario;
	
	public HistorialKey(Integer crucigrama, String usuario) {
		this.crucigrama = crucigrama;
		this.usuario = usuario;
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
	
	
}
