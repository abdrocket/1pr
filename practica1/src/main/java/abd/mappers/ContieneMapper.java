package abd.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.mappers.keys.ContieneKey;
import abd.model.Contiene;
import abd.model.Word;

public class ContieneMapper extends AbstractMapper<Contiene, ContieneKey> {

	public ContieneMapper(DataAccessor da,DataSource ds) {
		super(da, ds);
	}

	@Override
	protected String getTableName() {
		return "Contiene";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{"crucigrama","palabra","orientacion",
				"x","y","puntuacion"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"crucigrama","palabra"};
	}

	@Override
	protected Contiene buildObject(List<Object> rs) throws SQLException {
		return new Contiene((Integer)rs.get(0), (Integer)rs.get(1), (Integer)rs.get(2)
				, (Integer)rs.get(3), (Integer)rs.get(4), (Integer)rs.get(5));
	}

	@Override
	protected Object[] decomposeKey(ContieneKey key) {
		return new Object[]{key.getCrucigrama(), key.getPalabra()};
	}

	public List<Word> getCrosswordInfo(Integer crosswordId, String nick) {

		List<Word> cInfo = new LinkedList<Word>();
		String sql = "SELECT contiene.x, contiene.y, palabras.palabra, contiene.orientacion"
				+ ", contiene.puntuacion, contiene.palabra FROM contiene, palabras WHERE "
				+ "contiene.palabra = palabras.id AND contiene.crucigrama = "
				+ crosswordId;

		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);){
			
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				cInfo.add(new Word(rs.getInt("x"), rs.getInt("y"), rs
						.getString(3), (rs.getInt("orientacion") == 0), rs
						.getInt(6), rs.getInt("puntuacion"),
						nick));

				cInfo.add(new Word(rs.getInt("x"),rs.getInt("y"),
						rs.getString(3),(rs.getInt("orientacion")==0),rs.getInt(6),
						rs.getInt("puntuacion"),nick));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cInfo;
	}
	
}
