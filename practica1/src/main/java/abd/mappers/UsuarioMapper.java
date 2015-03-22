package abd.mappers;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import abd.AbstractMapper;
import abd.DataAccessor;
import abd.model.Usuario;

public class UsuarioMapper extends AbstractMapper<Usuario, String>{

	public UsuarioMapper(DataAccessor da) {
		super(da);
	}

	@Override
	protected String getTableName() {
		return "Usuarios";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[] {"nombre", "password",
				"fecha_n","imagen"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"nombre"};
	}

	@Override
	protected Usuario buildObject(ResultSet rs) throws SQLException {

		String nombre = rs.getString("nombre");//??
		String password = rs.getString("password");
		java.sql.Date fecha = rs.getDate("fecha_n");
		Blob imagen = rs.getBlob("imagen");
		
		byte[] imagenBytes = null;
		if (imagen != null) {
			imagenBytes = imagen.getBytes(1, (int)imagen.length());
		}
		
		return new Usuario(nombre, password, fecha, imagenBytes);
	}

	@Override
	protected Object[] decomposeKey(String key) {
		return new Object[]{key};
	}

		
}
