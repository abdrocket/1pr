package abd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import abd.AbstractMapper;
import abd.DataAccessor;
import abd.mappers.keys.EtiquetasKey;
import abd.model.Etiquetas;

public class EtiquetasMapper extends AbstractMapper<Etiquetas, EtiquetasKey> {

	public EtiquetasMapper(DataAccessor da) {
		super(da);
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
	protected Etiquetas buildObject(ResultSet rs) throws SQLException {
		return new Etiquetas(rs.getString("etiqueta"), rs.getInt("palabra"));
	}

	@Override
	protected Object[] decomposeKey(EtiquetasKey key) {
		return new Object[]{key.getEtiqueta(), key.getPalabra()};
	}

}
