package abd.mappers;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
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
	protected Usuario buildObject(List<Object> rs) throws SQLException {

		String nombre = (String)rs.get(0);
		String password = (String)rs.get(1);
		Date fecha = (Date) rs.get(2);
		Blob imagen = (Blob) rs.get(3);
		
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
