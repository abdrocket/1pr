package abd.mappers;

import java.sql.SQLException;
import java.util.List;
import abd.AbstractMapper;
import abd.DataAccessor;
import abd.mappers.keys.AmigosKey;
import abd.model.Amigos;

public class AmigosMapper extends AbstractMapper<Amigos, AmigosKey> {

	public AmigosMapper(DataAccessor da) {
		super(da);
	}

	@Override
	protected String getTableName() {
		return "Amigos";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{"usuario_target","usuario_source"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"usuario_target","usuario_source"};
	}

	@Override
	protected Amigos buildObject(List<Object> rs) throws SQLException {
		return new Amigos((String)rs.get(0), (String)rs.get(1));
	}

	@Override
	protected Object[] decomposeKey(AmigosKey key) {
		return new Object[]{key.getUsuario_source(), key.getUsuario_target()};
	}

}
