package abd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.mappers.keys.PeticionesKey;
import abd.model.Peticiones;

public class PeticionesMapper extends AbstractMapper<Peticiones, PeticionesKey> {

	public PeticionesMapper(DataSource ds) {
		super(ds);
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
		// TODO Auto-generated method stub
		return null;
	}

}
