package abd.model;

public class Activos {
	private Integer crucigrama;
	private String usuario;
	
	public Activos(Integer id, String string2) {
		this.crucigrama = id;
		this.usuario = string2;
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
