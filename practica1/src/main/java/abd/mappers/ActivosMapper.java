package abd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import abd.AbstractMapper;
import abd.DataAccessor;
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
	protected Activos buildObject(ResultSet rs) throws SQLException {
		return new Activos(rs.getInt("crucigrama"),rs.getString("usuario"));
	}

	@Override
	protected Object[] decomposeKey(ActivosKey key) {
		return new Object[]{key.getCrucigrama(), key.getUsuario()};
	}

	public List<Integer> findActivos(String nick) {
		List<Integer> crucigramasActivos = new LinkedList<Integer>();
		String sql = da.generateFindById(getTableName(), new String[]{"crucigrama"}
		, new String[]{"usuario"});
		ResultSet rs = da.executeFindById(sql, new Object[]{nick});
		
		try {
			while(rs.next()){
				crucigramasActivos.add(rs.getInt("crucigrama"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return crucigramasActivos;
	}

}
