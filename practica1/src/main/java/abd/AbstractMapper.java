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

	protected abstract String getKeyColumnName();

	protected abstract T buildObject(ResultSet rs) throws SQLException;

	public AbstractMapper(DataSource ds) {
		this.ds = ds;
	}

	public T findById(K id) {
		String tableName = getTableName();
		String[] columnNames = getColumnNames();
		String keyColumnName = getKeyColumnName();

		String sql = "SELECT " + StringUtils.join(columnNames, ", ") + " FROM "
				+ tableName + " WHERE " + keyColumnName + " = ?";
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setObject(1, id);

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