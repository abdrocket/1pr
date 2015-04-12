package abd.mappers;

import java.util.Date;
import java.util.List;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.model.Historial;

public class HistorialMapper extends AbstractMapper<Historial, Integer> {

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
				"respuesta","palabra","fecha","correcta","id"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"crucigrama","usuario"};
	}
	
	@Override
	protected Object[] decomposeKey(Integer key) {
		return new Object[]{key};
	}

	@Override
	protected Historial buildObject(List<Object> rs){
		return new Historial((Integer)rs.get(0), (String)rs.get(1)
				, (String)rs.get(2), (String)rs.get(3), (Integer)rs.get(4)
				, (Date)rs.get(5), (Integer)rs.get(6), (Integer)rs.get(7));
	}

	

	public Integer calculateScore(String nick) {
		return da.calculateScore(nick);
	}

	public boolean insertRow(Object[] values) {
		return da.insertRow(getTableName(), getColumnNames(), values);
	}





}
