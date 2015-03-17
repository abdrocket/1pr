package abd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.mappers.keys.HistorialKey;
import abd.model.Historial;

public class HistorialMapper extends AbstractMapper<Historial, HistorialKey> {

	public HistorialMapper(DataSource ds) {
		super(ds);
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
	protected Historial buildObject(ResultSet rs) throws SQLException {
		Integer crucigrama   = rs.getInt("crucigrama");
		String usuario       = rs.getString("usuario");	 //FK, no presente en vista de phpmyadmin
		String propietario   = rs.getString("propietario");//FK, no presente en vista de phpmyadmin
		String respuesta     = rs.getString("palabra");//esto contendria la palabra correcta, no la contestada!!!
		Date fecha           = rs.getDate("fecha");
		Time hora            = rs.getTime("hora");
		Integer correcta     = rs.getInt("correcta");
		Integer puntuacion   = rs.getInt("puntuacion");
		return new Historial(crucigrama, usuario, propietario, respuesta, fecha, hora, correcta, puntuacion);
	}

	@Override
	protected Object[] decomposeKey(HistorialKey key) {
		// TODO Auto-generated method stub
		return null;
	}

}
