package abd.mappers;

import java.sql.SQLException;
import java.util.List;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.model.Palabra;

public class PalabraMapper extends AbstractMapper<Palabra, Integer> {

	public PalabraMapper(DataAccessor da) {
		super(da);
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
	protected Palabra buildObject(List<Object> rs) throws SQLException{
		Integer idTabla = (Integer)rs.get(0);
		String palabra = (String)rs.get(1);
		String enunciado = (String)rs.get(2);
//		Blob imagen = (Blob)rs.get(3);
//		
//		byte[] imagenBytes = null;
//		if (imagen != null) {
//			
//				imagenBytes = imagen.getBytes(1, (int)imagen.length());
//
//		}
		//Esto lanzaba excepcion
		//Para hacer findById de palabra llamar a da.findPalabraById
		return new Palabra(idTabla, palabra, enunciado, null);
	}


	@Override
	protected Object[] decomposeKey(Integer key) {
		return new Object[]{key};
	}

	public Palabra findPalabraById(Integer palabraRef) {
		return da.findPalabraById(palabraRef);
	}

}
