package practica1;

import java.beans.PropertyVetoException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class main {

	public static void main(String[] args) throws Exception {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://localhost/Practica1_606");
		cpds.setUser("root");
		cpds.setPassword("");
		
		cpds.setAcquireRetryAttempts(1);
		cpds.setAcquireRetryDelay(1);
		
		DataSource ds = cpds;
		
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
