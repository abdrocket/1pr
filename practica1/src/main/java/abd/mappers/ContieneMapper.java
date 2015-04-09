package abd.mappers;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.mappers.keys.ContieneKey;
import abd.model.Contiene;
import abd.model.Word;

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
	protected Contiene buildObject(List<Object> rs) throws SQLException {
		return new Contiene((Integer)rs.get(0), (Integer)rs.get(1), (Integer)rs.get(2)
				, (Integer)rs.get(3), (Integer)rs.get(4), (Integer)rs.get(5));
	}

	@Override
	protected Object[] decomposeKey(ContieneKey key) {
		return new Object[]{key.getCrucigrama(), key.getPalabra()};
	}

	public List<Word> getCrosswordInfo(Integer crosswordId, String nick) {
		return da.findCrosswordInfo(crosswordId, nick);
	}
}
