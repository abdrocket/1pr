package abd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import abd.AbstractMapper;
import abd.DataAccessor;
import abd.mappers.keys.PeticionesKey;
import abd.model.Peticiones;

public class PeticionesMapper extends AbstractMapper<Peticiones, PeticionesKey> {

	public PeticionesMapper(DataAccessor da) {
		super(da);
	}

	@Override
	protected String getTableName() {
		return "Peticiones";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{"usuario_target","usuario_source","crucigrama"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"usuario_source","crucigrama"};
	}

	@Override
	protected Peticiones buildObject(ResultSet rs) throws SQLException {
		return new Peticiones(rs.getString("usuario_target"), rs.getString("usuario_source"), rs.getInt("crucigrama"));
	}

	@Override
	protected Object[] decomposeKey(PeticionesKey key) {
		return new Object[]{key.getUsuario_source(), key.getCrucigrama()};
	}

}
