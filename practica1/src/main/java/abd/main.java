package abd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;



import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * Entrega obligatoria
 * -insert Usuario
	-select Usuario
	-update Usuario

	-select Activos
	-insert Activos

	-select Crucigramas
 * */

public class main {

	public main(String[] args) throws Exception {
		
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://localhost/Practica1_606");
		cpds.setUser("root");
		cpds.setPassword("");
		
		cpds.setAcquireRetryAttempts(1);
		cpds.setAcquireRetryDelay(1);
		
		DataSource ds = cpds;
		
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement("SELECT * FROM Usuarios WHERE Nombre = ?")){
			
			try (ResultSet rs = pst.executeQuery()) {
				// Iteramos sobre los resultados
				while (rs.next()) {
					String nombre = rs.getString("nombre");
					String password = rs.getString("password");
					
					System.out.println(nombre + password);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		/*
		String tableName = "Usuarios";
		String[] columnNames = um.getColumnNames();
				String[] keyColumnNames, String[] conditions) {
			for(int i = 0;i < keyColumnNames.length; i++){
				conditions[i] = keyColumnNames[i] + " = ? ";
			}

			String sql = "SELECT " + StringUtils.join(columnNames, ", ") + " FROM "
					+ tableName + " WHERE " + StringUtils.join(conditions, " AND");
			return sql;
		}
		
		public ResultSet executeFindById(String sql, Object[] dKey){
			
			try (Connection con = ds.getConnection();
					PreparedStatement pst = con.prepareStatement(sql)) {

				for(int i = 0; i < dKey.length;i++){
					pst.setObject(i+1, dKey[i]);
				}

				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						return rs;
					} else {
						return null;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		*/
		
		//UsuarioMapper am = new UsuarioMapper(da);
		
		/*
		ContactoMapper contactoMapper = new ContactoMapper(ds);
		Contacto javier = contactoMapper.findById(1);
		System.out.println(javier);
		
		CorreoMapper correoMapper = new CorreoMapper(ds);
		Correo correo = correoMapper.findById("gloria.martinez@atm.es");
		System.out.println(correo);
		*/		
		cpds.close();
	}
}
