package abd.mappers;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.mappers.keys.HistorialKey;
import abd.model.Historial;

public class HistorialMapper extends AbstractMapper<Historial, HistorialKey> {

	public HistorialMapper(DataAccessor da) {
		super(da);
	}

	@Override
	protected String getTableName() {
		return "Historial";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{"crucigrama","usuario","propietario",
				"respuesta","fecha","hora","correcta","puntuacion"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"crucigrama","usuario"};
	}

	@Override
	protected Historial buildObject(List<Object> rs){
		return new Historial((Integer)rs.get(0), (String)rs.get(1)
				, (String)rs.get(2), (String)rs.get(3), (Integer)rs.get(4)
				, (Date)rs.get(5), (Time)rs.get(6), (Integer)rs.get(7));
	}

	@Override
	protected Object[] decomposeKey(HistorialKey key) {
		return new Object[]{key.getCrucigrama(), key.getUsuario(), key.getPalabra(), key.getHora()};
	}

	public Integer calculateScore(String nick) {
		return da.calculateScore(nick);
	}

	public boolean insertRow(Object[] values) {
		return da.insertRow(getTableName(), getColumnNames(), values);
	}

}
