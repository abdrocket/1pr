package abd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.mappers.keys.ContieneKey;
import abd.model.Contiene;

public class ContieneMapper extends AbstractMapper<Contiene, ContieneKey> {

	public ContieneMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		return "Contiene";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{"crucigrama","palabra","orientacion",
				"x","y","puntuacion"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"crucigrama","palabra"};
	}

	@Override
	protected Contiene buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object[] decomposeKey(ContieneKey key) {
		// TODO Auto-generated method stub
		return null;
	}
}
