package abd;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractMapper<T, K> {

	protected DataAccessor da;

	protected abstract String getTableName();

	protected abstract String[] getColumnNames();

	protected abstract String[] getKeyColumnNames();

	protected abstract T buildObject(List<Object> rs) throws SQLException;
	
	protected abstract Object[] decomposeKey(K key);

	public AbstractMapper(DataAccessor da) {
		this.da = da;
	}

	public T findById(K id) {
		
		Operator[] qc= new Operator[getKeyColumnNames().length];
		for(int i=0;i<qc.length;i++){
			qc[i]=Operator.EQ;
		}
		
		Object[] dKey = decomposeKey(id);
		List<Object> rs = da.executeFindById(getTableName(),getColumnNames(),
				getKeyColumnNames(), dKey, qc);
	
		try {
			return buildObject(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		
		
	}
	
}