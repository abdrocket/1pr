package test;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import abd.DataAccessor;
import abd.mappers.ActivosMapper;
import abd.mappers.CrucigramaMapper;
import abd.mappers.UsuarioMapper;
import abd.model.Crucigrama;
import abd.model.Usuario;
import abd.observer.UserObserver;

public class CrosswordDAO {

	private DataSource ds;
	private DataAccessor da;
	private ArrayList<UserObserver> obs;

	/**
	 * Aquí se debe inicializar el pool de conexiones, mediante la creación de
	 * un DataSource, que deberá ser asignado a la variable ds.
	 */
	public CrosswordDAO() {

		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setAcquireRetryAttempts(1);
		cpds.setBreakAfterAcquireFailure(true);
		try {
			cpds.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		cpds.setJdbcUrl("jdbc:mysql://localhost/Practica1_606");
		cpds.setUser("UsuarioP1");
		cpds.setPassword("");
		cpds.setAcquireRetryAttempts(1);
		cpds.setAcquireRetryDelay(1);

		this.obs = new ArrayList<UserObserver>();
		
		ds = cpds;
		da = new DataAccessor(ds);
	}

	/**
	 * Devuelve la contraseña del usuario cuyo nick se pasa como parámetro.
	 * Devuelve null si el usuario no existe.
	 */
	public String getPassword(String nick) {
		UsuarioMapper um = new UsuarioMapper(da);
		Usuario u = um.findById(nick);
		if (u != null)
			return u.getPassword();
		else
			return null;
	}

	/**
	 * Modifica la contraseña del usuario pasado como parámetro (String
	 * tableName, String[] fields, Object[] values, Object some_column, Object
	 * some_value)
	 */
	public void modifyPassword(String nick, String newPassword) {
		this.da.updateRows("usuarios", new String[] { "nombre" },
				new String[] { nick }, new String[] { "password" },
				new Object[] { newPassword });

	}

	/**
	 * Devuelve una lista de las claves de aquellos crucigramas cuyo título
	 * contenga str.
	 * 
	 * Si escogisteis una clave numérica para la tabla de crucigramas, se debe
	 * devolver una lista de Integer. Si escogisteis el título como clave, se
	 * debe devolver una lista de String. Si, por el contrario, escogisteis una
	 * clave compuesta, debéis crear una clase para almacenar los atributos de
	 * dicha clave.
	 */
	public List<?> findCrosswordsByTitle(String str) {
		CrucigramaMapper cm = new CrucigramaMapper(da);
		String likeString = "%" + str + "%";// En serio???
		return cm.findCrosswordsByTitle(likeString);
	}

	/**
	 * Devuelve el título del crucigrama cuya clave se pasa como parámetro.
	 */
	public String getCrosswordTitle(Object id) {
		CrucigramaMapper cm = new CrucigramaMapper(da);
		Crucigrama c = cm.findById((Integer) id);
		if (c != null)
			return c.getTitulo();
		else
			return null;
	}

	/**
	 * Añade un crucigrama a la lista de crucigramas activos de un usuario.
	 * 
	 * El crucigrama se especifica mediante su clave
	 */
	public void addCrosswordToUser(String nick, Object object) {
		UsuarioMapper um = new UsuarioMapper(da);
		CrucigramaMapper cm = new CrucigramaMapper(da);
		Usuario u = um.findById(nick);
		Crucigrama c = cm.findById((int) (object));
		if ((u != null) && (c != null)) {
			String[] fields = new String[] { "crucigrama", "usuario" };
			Object[] values = new Object[] { c.getId(), u.getNombre() };
			this.da.insertRow("activos", fields, values);
		}
	}

	/**
	 * Devuelve la lista de identificadores de los crucigramas activos del
	 * usuario pasado como parámetro
	 */
	public List<?> getCrosswordsOf(String nick) {
		ActivosMapper am = new ActivosMapper(da);
		return am.findActivos(nick);
	}

	/**
	 * Crear nuevos métodos sobrecargando este para poder insertar fechas e
	 * imagenes
	 */
	public boolean insertUser(String usr, String pwd) {
		String fields[] = new String[] { "nombre", "password", "fecha_n",
				"imagen" };
		Object values[] = new Object[] { usr, pwd, null, null };
		return da.insertRow("usuarios", fields, values);
	}

	/**
	 * Cierra el dataSource
	 */
	public void close() {
		((ComboPooledDataSource) ds).close();
	}

	public boolean logUser(String usr, String pwd){
		for(UserObserver o : this.obs){
			o.onAccess();
		}
		return false;
	}
	
	public Usuario getUser(String id){
		UsuarioMapper um = new UsuarioMapper(da);
		return um.findById(id);
	}
	
	public void addUserObserver(UserObserver uObserver) {
		// TODO Auto-generated method stub
		this.obs.add(uObserver);
	}

}
