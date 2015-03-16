package abd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.mappers.keys.AmigosKey;
import abd.model.Amigos;

public class AmigosMapper extends AbstractMapper<Amigos, AmigosKey> {

	public AmigosMapper(DataSource ds) {
		super(ds);
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
	protected Amigos buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object[] decomposeKey(AmigosKey key) {
		return null;
		// TODO Auto-generated method stub
	//	return new Object[]{key.getUsuarioSource(),key.getUsuarioTarget()};
	}

}
