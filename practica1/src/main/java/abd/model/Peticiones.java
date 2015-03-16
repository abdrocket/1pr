package abd.model;

public class Peticiones {
	
	private String usuario_target;
	private String usuario_source;
	private Integer crucigrama;
	public Peticiones(String usuario_target, String usuario_source,
			Integer crucigrama) {
		this.usuario_target = usuario_target;
		this.usuario_source = usuario_source;
		this.crucigrama = crucigrama;
	}
	public String getUsuario_target() {
		return usuario_target;
	}
	public void setUsuario_target(String usuario_target) {
		this.usuario_target = usuario_target;
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
