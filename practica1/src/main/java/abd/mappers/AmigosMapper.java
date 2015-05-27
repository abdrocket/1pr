package abd.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.mappers.keys.AmigosKey;
import abd.model.Amigos;

public class AmigosMapper extends AbstractMapper<Amigos, AmigosKey> {

	public AmigosMapper(DataAccessor da,DataSource ds) {
		super(da, ds);
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

	public boolean addFriend(String nombre, String friend) {
		da.insertRow("amigos", new String[]{"usuario_source","usuario_target"}
		, new Object[]{friend,nombre});
		return da.insertRow("amigos", new String[]{"usuario_source","usuario_target"}
		, new Object[]{nombre,friend});
	}

	
	public ArrayList<String> getAmigos(String nombre) {
		ArrayList<String> amigos = new ArrayList<String>();
		String sql = "SELECT usuario_target FROM amigos WHERE amigos.usuario_source = ?";
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);){
			
			pst.setString(1, nombre);
			ResultSet rs = pst.executeQuery();	
			while (rs.next()) {
				amigos.add(rs.getString("usuario_target"));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return amigos;
	}

	public void deleteFriend(String nombre, String friendToDelete) {
		da.deleteRows("amigos", new String[]{"usuario_source","usuario_target"}
		, new Object[]{friendToDelete,nombre});
		da.deleteRows("amigos", new String[]{"usuario_source","usuario_target"}
		, new Object[]{nombre,friendToDelete});
		
	}

}
