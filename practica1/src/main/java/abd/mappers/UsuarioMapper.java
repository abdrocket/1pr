package abd.mappers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.Operator;
import abd.model.Usuario;

public class UsuarioMapper extends AbstractMapper<Usuario, String> {

	public UsuarioMapper(DataAccessor da,DataSource ds) {
		super(da, ds);
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
	/**
	 * Can handle blob <--> image
	 * @param tableName
	 * @param columnNames
	 * @param keyColumnNames
	 * @param dKey
	 * @param qc
	 * @return
	 */
	private List<Object> executeFindByIdUser(String tableName, String[] columnNames,
			String[] keyColumnNames, Object[] dKey, Operator[] qc) {
		String sql = generateFindById(tableName, columnNames, keyColumnNames,
				qc);
		List<Object> result = new LinkedList<Object>();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);){
			
			for (int i = 0; i < dKey.length; i++) {
				pst.setObject(i + 1, dKey[i]);
			}
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result.add(rs.getObject("nombre"));
				result.add(rs.getObject("password"));
				result.add(rs.getObject("fecha_n"));
				result.add(rs.getBlob("imagen"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Usuario findById(int id) {
		Operator[] qc = new Operator[getKeyColumnNames().length];
		for (int i = 0; i < qc.length; i++) {
			qc[i] = Operator.EQ;
		}
		Object[] dKey = decomposeKey(id);
		List<Object> rs = executeFindByIdUser(getTableName(),
				getColumnNames(), getKeyColumnNames(), dKey, qc);
		try {
			return buildObject(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String generateFindById(String tableName, String[] columnNames,
			String[] keyColumnNames, Operator[] keyOperator) {

		String[] conditions = new String[keyColumnNames.length];

		for (int i = 0; i < keyColumnNames.length; i++) {
			conditions[i] = keyColumnNames[i] + " " + keyOperator[i] + " ? ";
		}

		return "SELECT " + StringUtils.join(columnNames, ", ") + " FROM "
				+ tableName.toLowerCase() + " WHERE "
				+ StringUtils.join(conditions, " AND ");
	}
}
