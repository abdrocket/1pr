package abd.mappers;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.Operator;
import abd.mappers.keys.ActivosKey;
import abd.model.Activos;

public class ActivosMapper extends AbstractMapper<Activos, ActivosKey> {

	public ActivosMapper(DataAccessor da) {
		super(da);
	}

	@Override
	protected String getTableName() {
		return "Activos";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{"crucigrama","usuario"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"crucigrama","usuario"};
	}

	@Override
	protected Activos buildObject(List<Object> rs) throws SQLException {
		return new Activos((Integer)rs.get(0),(String)rs.get(1));
	}

	@Override
	protected Object[] decomposeKey(ActivosKey key) {
		return new Object[]{key.getCrucigrama(), key.getUsuario()};
	}

	public List<Integer> findActivos(String nick) {
		
		List<Integer> crucigramasActivos = new LinkedList<Integer>();
		List<Object> rs = da.executeFindById(getTableName(),new String[]{"crucigrama"}
		, new String[]{"usuario"}, new Object[]{nick},new Operator[]{Operator.EQ});
		
		if(rs.isEmpty())
			return null;
		
		for(int i = 0;i < rs.size();i++){
			crucigramasActivos.add((Integer)rs.get(i));
		}
		
		return crucigramasActivos;
	}

}
