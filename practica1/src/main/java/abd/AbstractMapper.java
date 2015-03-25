package abd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractMapper<T, K> {

	//protected DataSource ds;
	protected DataAccessor da;

	protected abstract String getTableName();

	protected abstract String[] getColumnNames();

	protected abstract String[] getKeyColumnNames();

	protected abstract T buildObject(List<Object> rs) throws SQLException;
	
	protected abstract Object[] decomposeKey(K key);

	public AbstractMapper(DataAccessor da) {
		//this.ds = ds;
		this.da = da;
	}

	public T findById(K id) {
		
		Object[] dKey = decomposeKey(id);
		List<Object> rs = da.executeFindById(getTableName(),getColumnNames(),getKeyColumnNames(), dKey);
	
		try {
			return buildObject(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		
		
	}
	
}