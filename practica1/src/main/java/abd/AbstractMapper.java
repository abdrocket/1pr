package abd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractMapper<T, K> {

	protected DataSource ds;

	protected abstract String getTableName();

	protected abstract String[] getColumnNames();

	protected abstract String[] getKeyColumnNames();

	protected abstract T buildObject(ResultSet rs) throws SQLException;
	
	protected abstract Object[] decomposeKey(K key);

	public AbstractMapper(DataSource ds) {
		this.ds = ds;
	}

	public T findById(K id) {
		String tableName = getTableName();
		String[] columnNames = getColumnNames();
		String[] keyColumnNames = getKeyColumnNames();
		String[] conditions = new String[keyColumnNames.length];
		
		for(int i = 0;i < keyColumnNames.length; i++){
			conditions[i] = keyColumnNames[i] + " = ? ";
		}

		String sql = "SELECT " + StringUtils.join(columnNames, ", ") + " FROM "
				+ tableName + " WHERE " + StringUtils.join(conditions, " AND");
		
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {

			Object[] dKey = decomposeKey(id);
			for(int i = 1; i <= keyColumnNames.length;i++){
				pst.setObject(i, dKey[i]);
			}

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return buildObject(rs);
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


}