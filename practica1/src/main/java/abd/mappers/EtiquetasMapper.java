package abd.mappers;

import java.util.List;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.mappers.keys.EtiquetasKey;
import abd.model.Etiquetas;

public class EtiquetasMapper extends AbstractMapper<Etiquetas, EtiquetasKey> {

	public EtiquetasMapper(DataAccessor da,DataSource ds) {
		super(da, ds);
	}

	@Override
	protected String getTableName() {
		return "Etiquetas";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{"etiqueta","palabra"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"etiqueta","palabra"};
	}

	@Override
	protected Etiquetas buildObject(List<Object> rs){
		return new Etiquetas((String)rs.get(0), (Integer)rs.get(1));
	}

	@Override
	protected Object[] decomposeKey(EtiquetasKey key) {
		return new Object[]{key.getEtiqueta(), key.getPalabra()};
	}

}
