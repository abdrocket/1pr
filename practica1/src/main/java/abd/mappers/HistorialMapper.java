package abd.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.model.Historial;

public class HistorialMapper extends AbstractMapper<Historial, Integer> {

	public HistorialMapper(DataAccessor da,DataSource ds) {
		super(da, ds);
	}

	@Override
	protected String getTableName() {
		return "Historial";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{"crucigrama","usuario","propietario",
				"respuesta","palabra","fecha","correcta","id"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"crucigrama","usuario"};
	}
	
	@Override
	protected Object[] decomposeKey(Integer key) {
		return new Object[]{key};
	}

	@Override
	protected Historial buildObject(List<Object> rs){
		return new Historial((Integer)rs.get(0), (String)rs.get(1)
				, (String)rs.get(2), (String)rs.get(3), (Integer)rs.get(4)
				, (Date)rs.get(5), (Integer)rs.get(6), (Integer)rs.get(7));
	}

	

	public Integer calculateScore(String nick) {
		Integer score = 0;
		// Sql para sacar la puntuacion de un usuario que el ha respondido
		String sql = "SELECT usuario, propietario, puntuacion, correcta FROM contiene, historial "
				+ "WHERE contiene.crucigrama = historial.crucigrama AND "
				+ "contiene.palabra = historial.palabra AND "
				+ "historial.usuario = ? ";
		// Sql para sacar la puntuacion de un usuario que corresponde a la mitad
		// que han respondido sus amigos en los prestados.
		String sqlex = "SELECT usuario, propietario, puntuacion, correcta FROM contiene, historial "
				+ "WHERE contiene.crucigrama = historial.crucigrama AND "
				+ "contiene.palabra = historial.palabra AND "
				+ "historial.usuario <> historial.propietario AND "
				+ "historial.propietario = ? ";

		try(Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);){
			

			pst.setObject(1, nick);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				if (rs.getInt("correcta") == 1) {
					if (rs.getString("usuario").equalsIgnoreCase(rs.getString("propietario"))) {
						score += rs.getInt("puntuacion");
					} else {
						score += rs.getInt("puntuacion") / 2;
					}
				} else {
					score -= 10;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sqlex);){
			

			pst.setObject(1, nick);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				if (rs.getInt("correcta") == 1) {
					score += rs.getInt("puntuacion") / 2;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return score;
	}

	public List<Historial> getResueltas(Integer crosswordId, String userOwner) {
		String sql = "SELECT * FROM historial WHERE crucigrama = ? AND propietario = ?"
				+ " AND correcta = ?";
		List<Historial> h = new LinkedList<Historial>();
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);){
			
			int correcta = 1;
			pst.setInt(1, crosswordId);
			pst.setString(2, userOwner);
			pst.setInt(3, correcta);
			ResultSet rs = pst.executeQuery();	
			while (rs.next()) {
				h.add(new Historial(rs.getInt(1),rs.getString(2)
						,rs.getString(3),rs.getString(4),rs.getInt(5),
						rs.getDate(6),rs.getInt(7),rs.getInt(8)));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return h;
	}
	public boolean insertRow(Object[] values) {
		return da.insertRow(getTableName(), getColumnNames(), values);
	}





}
