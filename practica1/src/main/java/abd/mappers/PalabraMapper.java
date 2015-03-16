package abd.mappers;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.model.Palabra;

public class PalabraMapper extends AbstractMapper<Palabra, Integer> {

	public PalabraMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		return "Palabras";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[] {"id", "palabra",
				"enunciado","imagen"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"id"};
	}

	@Override
	protected Palabra buildObject(ResultSet rs) throws SQLException {
		Integer idTabla = rs.getInt("id");
		String palabra = rs.getString("palabra");
		String enunciado = rs.getString("enunciado");
		Blob imagen = rs.getBlob("imagen");
		
		byte[] imagenBytes = null;
		if (imagen != null) {
			imagenBytes = imagen.getBytes(1, (int)imagen.length());
		}
		//Falta arreglar Palabra
		return new Palabra(idTabla, palabra, enunciado, imagenBytes);
	}


	@Override
	protected Object[] decomposeKey(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

}
