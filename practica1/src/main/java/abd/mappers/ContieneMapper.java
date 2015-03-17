package abd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.mappers.keys.ContieneKey;
import abd.model.Contiene;

public class ContieneMapper extends AbstractMapper<Contiene, ContieneKey> {

	public ContieneMapper(DataAccessor da) {
		super(da);
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
		Integer crucigrama  = rs.getInt("crucigrama");
		Integer palabra     = rs.getInt("palabra");
		Integer orientacion = rs.getInt("orientacion");
		Integer x           = rs.getInt("x");
		Integer y           = rs.getInt("y");
		Integer puntuacion  = rs.getInt("puntuacion");
		return new Contiene(crucigrama, palabra, orientacion, x, y, puntuacion);
	}

	@Override
	protected Object[] decomposeKey(ContieneKey key) {
		return new Object[]{key.getCrucigrama(), key.getPalabra()};
	}
}
