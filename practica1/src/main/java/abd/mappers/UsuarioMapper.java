package abd.mappers;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.model.Usuario;

public class UsuarioMapper extends AbstractMapper<Usuario, String>{

	public UsuarioMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getColumnNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getKeyColumnName() {
		return "nombre";
	}

	@Override
	protected Usuario buildObject(ResultSet rs) throws SQLException {
		//Integer idTabla = rs.getInt(getKeyColumnName());//??
		String nombre = rs.getString("nombre");//??
		String password = rs.getString("password");
		java.sql.Date fecha = rs.getDate("fecha_n");
		Blob imagen = rs.getBlob("imagen");
		
		byte[] imagenBytes = null;
		if (imagen != null) {
			imagenBytes = imagen.getBytes(1, (int)imagen.length());
		}
		
		return new Usuario(nombre, password, fecha, imagenBytes);//String nombre, String password, Date fechaNac, byte[] imagen
	}

		
}
