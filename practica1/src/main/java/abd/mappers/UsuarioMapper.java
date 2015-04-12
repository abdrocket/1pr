package abd.mappers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.Operator;
import abd.model.Usuario;

public class UsuarioMapper extends AbstractMapper<Usuario, String> {

	public UsuarioMapper(DataAccessor da) {
		super(da);
	}

	@Override
	protected String getTableName() {
		return "Usuarios";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[] { "nombre", "password", "fecha_n", "imagen" };
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[] { "nombre" };
	}

	@Override
	protected Usuario buildObject(List<Object> rs) throws SQLException {
		String nombre = null;
		String password = null;
		Date fecha = null;
		byte[] imagen = null;
		if (rs.size() == 4) {
			nombre = (String) rs.get(0);
			password = (String) rs.get(1);
			fecha = (Date) rs.get(2);
			imagen = (byte[]) rs.get(3);
		}
		if (nombre == null || password == null) {
			return null;
		}
		return new Usuario(nombre, password, fecha, imagen);
	}

	@Override
	protected Object[] decomposeKey(String key) {
		return new Object[] { key };
	}

	protected Object[] decomposeKey(int key) {
		return new Object[] { key };
	}

	public Usuario findById(int id) {
		Operator[] qc = new Operator[getKeyColumnNames().length];
		for (int i = 0; i < qc.length; i++) {
			qc[i] = Operator.EQ;
		}
		Object[] dKey = decomposeKey(id);
		List<Object> rs = da.executeFindByIdUser(getTableName(),
				getColumnNames(), getKeyColumnNames(), dKey, qc);
		try {
			return buildObject(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
