package abd.model;

import es.ucm.abd.crossword.WordModel;

public class Word implements WordModel {
	private int x;
	private int y;
	private String word;
	private boolean horizontal;
	private Integer puntuacion;
	private Integer palabraRef;
	private String crucigramaPropietario;
	

	public Word(int x, int y, String word, boolean horizontal, 
			Integer palabraRef, Integer puntuacion, String crucigramaPropietario) {
		this.x = x;
		this.y = y;
		this.word = word;
		this.horizontal = horizontal;
		this.palabraRef = palabraRef;
		this.crucigramaPropietario = crucigramaPropietario;
		this.puntuacion = puntuacion;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public String getWord() {
		return this.word;
	}

	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	public Integer getPalabraRef() {
		return palabraRef;
	}

	public String getCrucigramaPropietario() {
		return crucigramaPropietario;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (horizontal ? 1231 : 1237);
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (horizontal != other.horizontal)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	

}
