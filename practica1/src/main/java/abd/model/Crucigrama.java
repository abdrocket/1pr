package abd.model;


import java.util.Date;

public class Crucigrama {
	
	private Integer id;
	private String titulo;
	private Date fecha;
	
	public Crucigrama(Integer id, String titulo, Date fecha) {
		this.id = id;
		this.titulo = titulo;
		this.fecha = fecha;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
		
	
		
}
