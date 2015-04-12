package abd.mappers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.mappers.keys.PeticionesKey;
import abd.model.Peticion;

public class PeticionesMapper extends AbstractMapper<Peticion, PeticionesKey> {

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
	protected Peticion buildObject(List<Object> rs) throws SQLException {
		return new Peticion((String)rs.get(0), (String)rs.get(1), (Integer)rs.get(2));
	}

	@Override
	protected Object[] decomposeKey(PeticionesKey key) {
		return new Object[]{key.getUsuario_source(), key.getCrucigrama()};
	}

	public ArrayList<Peticion> getPeticiones(String nombre) {
		return da.getPeticiones(nombre);
	}

	public void deletePeticion(Integer crosswordId, String userOwner) {
		da.deleteRows(this.getTableName(), new String[]{"usuario_source","crucigrama" }, new Object[]{userOwner,crosswordId});
	}

}
