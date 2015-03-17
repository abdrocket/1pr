package abd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractMapper<T, K> {

	protected DataSource ds;
	protected DataAccessor da;

	protected abstract String getTableName();

	protected abstract String[] getColumnNames();

	protected abstract String[] getKeyColumnNames();

	protected abstract T buildObject(ResultSet rs) throws SQLException;
	
	protected abstract Object[] decomposeKey(K key);

	public AbstractMapper(DataSource ds,DataAccessor da) {
		this.ds = ds;
		this.da = da;
	}

	public T findById(K id) {
		String tableName = getTableName();
		String[] columnNames = getColumnNames();
		String[] keyColumnNames = getKeyColumnNames();
		String[] conditions = new String[keyColumnNames.length];
		
		String sql = da.generateFindById(tableName, columnNames, keyColumnNames, conditions);
		Object[] dKey = decomposeKey(id);
		
		ResultSet rs = da.executeFindById(sql, dKey);
		
		if(rs != null)
			try {
				return buildObject(rs);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		else 
			return null;
		
		
	}
	
}