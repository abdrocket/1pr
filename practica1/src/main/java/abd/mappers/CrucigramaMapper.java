package abd.mappers;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.model.Crucigrama;
import abd.model.Palabra;
import abd.model.Usuario;

public class CrucigramaMapper extends AbstractMapper<Crucigrama, Integer>{

	public CrucigramaMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		return "Crucigramas";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[] {"fecha", "usuario_source",
				"usuario_target","id","titulo" };
	}

	@Override
	protected String getKeyColumnName() {
		return "id";
	}

	@Override
	protected Crucigrama buildObject(ResultSet rs) throws SQLException {
		Date date = rs.getDate("fecha");
		String usuario_source = rs.getString("usuario_source");
		String usuario_target = rs.getString("usuario_target");
		Integer idTabla = rs.getInt(getKeyColumnName());
		String titulo = rs.getString("titulo");
		//Falta arreglar crucigrama
		return new Crucigrama(date, usuario_source, usuario_target, idTabla,titulo);
	}
	
}
