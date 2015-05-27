package abd.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.Blob;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.model.Palabra;

public class PalabraMapper extends AbstractMapper<Palabra, Integer> {

	public PalabraMapper(DataAccessor da,DataSource ds) {
		super(da, ds);
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

		return new Palabra(idTabla, palabra, enunciado, null);
	}


	@Override
	protected Object[] decomposeKey(Integer key) {
		return new Object[]{key};
	}

	public Palabra findPalabraById(Integer palabraRef) {
		Palabra p = null;
		String sql = "SELECT * FROM palabras WHERE id = ?";
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);){
			
			pst.setInt(1, palabraRef);
			ResultSet rs = pst.executeQuery();	
			while (rs.next()) {
				Blob b = (Blob) rs.getBlob(4);
				byte[] bytes = null;
				if(b != null){
					bytes = b.getBytes(1, (int)b.length());
				}
				p = new Palabra(rs.getInt(1), rs.getString(2), 
						rs.getString(3),bytes );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

}
