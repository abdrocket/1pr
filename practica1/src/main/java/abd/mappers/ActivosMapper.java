package abd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.mappers.keys.ActivosKey;
import abd.model.Activos;

public class ActivosMapper extends AbstractMapper<Activos, ActivosKey> {

	public ActivosMapper(DataSource ds) {
		super(ds);
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
		// TODO Auto-generated method stub
//		return new Object[]{key.getIdCrucigrama(),key.getUsuario()};
		return null;
	}

}