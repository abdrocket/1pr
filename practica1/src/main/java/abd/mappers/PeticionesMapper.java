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
import abd.mappers.keys.PeticionesKey;
import abd.model.Peticion;

public class PeticionesMapper extends AbstractMapper<Peticion, PeticionesKey> {

	public PeticionesMapper(DataAccessor da,DataSource ds) {
		super(da,ds);
	}
	
	@Override
	protected String getTableName() {
		return "Peticiones";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{"usuario_target","usuario_source","crucigrama"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"usuario_source","crucigrama"};
	}

	@Override
	protected Peticion buildObject(List<Object> rs) throws SQLException {
		return new Peticion((String)rs.get(0), (String)rs.get(1), (Integer)rs.get(2));
	}

	@Override
	protected Object[] decomposeKey(PeticionesKey key) {
		return new Object[]{key.getUsuario_source(), key.getCrucigrama()};
	}

	public ArrayList<Peticion> getPeticiones(String nombre) {
		ArrayList<Peticion> pets= new ArrayList<Peticion>();
		String sql = "SELECT * FROM peticiones WHERE usuario_target = ?";
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);){
			
			pst.setString(1, nombre);
			ResultSet rs = pst.executeQuery();	
			while (rs.next()) {
				pets.add(new Peticion(rs.getString(1),rs.getString(2)
						,rs.getInt(3)));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pets;
	}

	public boolean estaEnPeticion(String userOwner, Integer crosswordId) {
		boolean esta = false;
		String sql = "SELECT * FROM peticiones WHERE usuario_source = ?";
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);){
			
			pst.setString(1, userOwner);
			ResultSet rs = pst.executeQuery();	
			while (rs.next()) {
				if(rs.getInt("crucigrama") == crosswordId)
					esta = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return esta;
	}
	public void deletePeticion(Integer crosswordId, String userOwner) {
		da.deleteRows(this.getTableName(), new String[]{"usuario_source","crucigrama" }, new Object[]{userOwner,crosswordId});
	}

}
