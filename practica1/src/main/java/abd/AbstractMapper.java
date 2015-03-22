package abd;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractMapper<T, K> {

	//protected DataSource ds;
	protected DataAccessor da;

	protected abstract String getTableName();

	protected abstract String[] getColumnNames();

	protected abstract String[] getKeyColumnNames();

	protected abstract T buildObject(ResultSet rs) throws SQLException;
	
	protected abstract Object[] decomposeKey(K key);

	public AbstractMapper(DataAccessor da) {
		//this.ds = ds;
		this.da = da;
	}

	public T findById(K id) {
		
		
		String sql = da.generateFindById(getTableName(), getColumnNames(), getKeyColumnNames());
		Object[] dKey = decomposeKey(id);
		
		ResultSet rs = da.executeFindById(sql, dKey);
	
		try {
			return buildObject(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		
		
	}
	
}