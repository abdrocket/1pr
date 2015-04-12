package abd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import abd.model.Word;

public class DataAccessor {
	private DataSource ds;

	public DataAccessor(DataSource ds) {
		this.ds = ds;
	}

	// INSERT STATEMENTS
	public boolean insertRow(String tableName, String[] fields, Object[] values) {
		String sql = generateInsertStatement(tableName, fields);
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			for (int i = 0; i < values.length; i++) {
				pst.setObject(i + 1, values[i]);
			}
			int numRows = pst.executeUpdate();
			return (numRows == 1);
		} catch (SQLException e) {
			System.err.println(Constants.INSERT_MISTAKE);
			e.printStackTrace();
			return false;
		}
	}

	private String generateInsertStatement(String tableName, String[] fields) {
		String fieldList = StringUtils.join(fields, ",");
		String[] marks = new String[fields.length];
		Arrays.fill(marks, "?");
		String markList = StringUtils.join(marks, ",");
		return "INSERT INTO " + tableName + " (" + fieldList + ") VALUES ("
				+ markList + ")";
	}

	// SELECT STATEMENTS
	public String generateFindById(String tableName, String[] columnNames,
			String[] keyColumnNames, Operator[] keyOperator) {

		String[] conditions = new String[keyColumnNames.length];

		for (int i = 0; i < keyColumnNames.length; i++) {
			conditions[i] = keyColumnNames[i] + " " + keyOperator[i] + " ? ";
		}

		return "SELECT " + StringUtils.join(columnNames, ", ") + " FROM "
				+ tableName.toLowerCase() + " WHERE "
				+ StringUtils.join(conditions, " AND");
	}

	public List<Object> executeFindById(String tableName, String[] columnNames,
			String[] keyColumnNames, Object[] dKey, Operator[] qc) {
		String sql = generateFindById(tableName, columnNames, keyColumnNames,
				qc);
		List<Object> result = new LinkedList<Object>();
		try {
			Connection con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			for (int i = 0; i < dKey.length; i++) {
				pst.setObject(i + 1, dKey[i]);
			}
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				for (int i = 0; i < columnNames.length; i++) {
					result.add(rs.getObject(columnNames[i]));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
	public List<Object> executeFindByIdUser(String tableName, String[] columnNames,
			String[] keyColumnNames, Object[] dKey, Operator[] qc) {
		String sql = generateFindById(tableName, columnNames, keyColumnNames,
				qc);
		List<Object> result = new LinkedList<Object>();
		try {
			Connection con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
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

	public Integer calculateScore(String nick) {
		Integer score = 0;
		// Sql para sacar la puntuacion de un usuario que el ha respondido
		String sql = "SELECT usuario, propietario, puntuacion, correcta FROM contiene, crucigramas, historial "
				+ "WHERE contiene.crucigrama = crucigramas.id AND "
				+ "crucigramas.id = historial.crucigrama AND "
				+ "historial.usuario = ? ";
		// Sql para sacar la puntuacion de un usuario que corresponde a la mitad
		// que han respondido sus amigos en los prestados.
		String sqlex = "SELECT usuario, propietario, puntuacion, correcta FROM contiene, crucigramas, historial "
				+ "WHERE contiene.crucigrama = crucigramas.id AND "
				+ "crucigramas.id = historial.crucigrama AND "
				+ "historial.usuario <> historial.propietario AND "
				+ "historial.propietario = ? ";

		try {
			Connection con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setObject(1, nick);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				if (rs.getInt("correcta") == 1) {
					if (rs.getString("usuario").equalsIgnoreCase("propietario")) {
						score += rs.getInt("puntuacion");
					} else {
						score += rs.getInt("puntuacion") / 2;
					}
				} else {
					score -= 10;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			Connection con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(sqlex);

			pst.setObject(1, nick);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				if (rs.getInt("correcta") == 1) {
					score += rs.getInt("puntuacion") / 2;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return score;
	}

	public List<Word> findCrosswordInfo(Integer crosswordId,
			String crucigramaPropietario) {
		List<Word> cInfo = new LinkedList<Word>();
		String sql = "SELECT contiene.x, contiene.y, palabras.palabra, contiene.orientacion"
				+ ", contiene.puntuacion, contiene.palabra FROM contiene, palabras WHERE "
				+ "contiene.palabra = palabras.id AND contiene.crucigrama = "
				+ crosswordId;

		try {
			Connection con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				cInfo.add(new Word(rs.getInt("x"), rs.getInt("y"), rs
						.getString(3), (rs.getInt("orientacion") == 0), rs
						.getInt(6), rs.getInt("puntuacion"),
						crucigramaPropietario));

				System.out.println("x: " + rs.getInt("x"));
				System.out.println("y: " + rs.getInt("y"));
				System.out.println("palabra: " + rs.getString(3));
				System.out.println("orien: " + (rs.getInt("orientacion") == 0));
				System.out.println("punt: " + rs.getInt("puntuacion"));
				System.out.println("Ref: " + rs.getInt(6));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cInfo;
	}

	// ----UPDATE STATEMENTS----
	public boolean updateRows(String tableName, String[] columns,
			Object[] values, String[] kColumns, Object[] kValues) {
		String sql = generateUpdateStatement(tableName, columns, kColumns);
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			for (int i = 0; i < kValues.length; i++) {
				pst.setObject(i + 1, kValues[i]);
			}
			for (int i = kValues.length; i < values.length + kValues.length; i++) {
				pst.setObject(i + 1, values[i - kValues.length]);
			}
			System.out.println(pst.toString());
			int numRows = pst.executeUpdate();////////////////////////////////////////////////////////////////////////////////////
			System.out.println(numRows);
			return (numRows == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * UPDATE table_name SET column1=value1,column2=value2,column(n)=value(n)
	 * WHERE col_key1=key_value1 AND col_key2=key_value2 AND
	 * col_key(n)=key_value(n);
	 */
	private String generateUpdateStatement(String tableName, String[] columns,
			String[] kColumns) {

		String[] whereColumns = new String[columns.length];
		for (int i = 0; i < columns.length; i++) {
			whereColumns[i] = columns[i] + "= ? ";
		}
		String[] setColumns = new String[kColumns.length];
		for (int i = 0; i < kColumns.length; i++) {
			setColumns[i] = kColumns[i] + "= ? ";
		}

		return "UPDATE " + tableName + " SET "
				+ StringUtils.join(setColumns, ",") + " WHERE "
				+ StringUtils.join(whereColumns, "AND");
	}

	// DELETE STATEMENTS
	public boolean deleteRows(String tableName, String[] fields, Object[] values) {
		String sql = generateDeleteStatement(tableName, fields);
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			for (int i = 0; i < values.length; i++) {
				pst.setObject(i + 1, values[i]);
			}
			int numRows = pst.executeUpdate();
			return (numRows >= 0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * DELETE FROM tablename WHERE col(1) = value(1) AND col(2) = value(2) AND
	 * ... AND col(n) = value(n)
	 */
	private String generateDeleteStatement(String tableName, String[] fields) {
		String[] conditionsWithMarks = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			conditionsWithMarks[i] = fields[i] + " = ? ";
		}
		return "DELETE FROM " + tableName + " WHERE "
				+ StringUtils.join(conditionsWithMarks, "AND");
	}

}
