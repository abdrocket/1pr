

import java.util.List;

import javax.sql.DataSource;

public class CrosswordDAO {
	private DataSource ds;

	/**
	 * Aquí se debe inicializar el pool de conexiones, mediante
	 * la creación de un DataSource, que deberá ser asignado a
	 * la variable ds.
	 */
	public CrosswordDAO() {
		// this.ds = ...
	}

	
	/**
	 * Devuelve la contraseña del usuario cuyo nick se pasa como
	 * parámetro. Devuelve null si el usuario no existe. 
	 */
	public String getPassword(String nick) {
		return null;
	}
	
	/**
	 * Modifica la contraseña del usuario pasado como parámetro 
	 */
	public void modifyPassword(String nick, String newPassword) {
		
	}

	/**
	 * Devuelve una lista de las claves de aquellos crucigramas
	 * cuyo título contenga str.
	 * 
	 * Si escogisteis una clave numérica para la tabla de crucigramas,
	 * se debe devolver una lista de Integer. Si escogisteis el título
	 * como clave, se debe devolver una lista de String. Si, por el contrario,
	 * escogisteis una clave compuesta, debéis crear una clase para almacenar
	 * los atributos de dicha clave. 
	 */
	public List<Object> findCrosswordsByTitle(String str) {
		return null;
	}

	/**
	 * Devuelve el título del crucigrama cuya clave se pasa como
	 * parámetro.
	 */
	public String getCrosswordTitle(Object id) {
		return null;
	}
	
	/**
	 * Añade un crucigrama a la lista de crucigramas activos de un usuario.
	 * 
	 * El crucigrama se especifica mediante su clave
	 */
	public void addCrosswordToUser(String nick, Object crosswordId) {
		
	}
	
	/**
	 * Devuelve la lista de identificadores de los crucigramas activos
	 * del usuario pasado como parámetro
	 */
	public List<Object> getCrosswordsOf(String nick) {
		return null;
	}

	/**
	 * Cierra el dataSource
	 */
	public void close() {
		// ((ComboPooledDataSource)ds).close();
	}
}
