package abd.model;

public class Palabra {
	
	private Integer id;
	private String palabra;
	private String enunciado;
	private byte[] imagen;

	
	public Palabra(Integer id, String palabra, String enunciado, byte[] imagen) {
		this.id = id;
		this.palabra = palabra;
		this.enunciado = enunciado;
		this.imagen = imagen;
	}
	
	public Integer getId() {
		return id;
	}
	public String getPalabra() {
		return palabra;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public byte[] getImagen() {
		return imagen;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	
	
}
