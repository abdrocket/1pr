package abd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

public class DataAccessor {
	private DataSource ds;
	
	public DataAccessor(DataSource ds) {
		this.ds = ds;
	}
	
	//INSERT STATEMENTS
	public boolean insertRow(String tableName, String[] fields, Object[] values) {
		String sql = generateInsertStatement(tableName, fields);
		try(Connection con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(sql)) {
			for (int i = 0; i < values.length; i++) {
				pst.setObject(i + 1, values[i]);
			}
			int numRows = pst.executeUpdate();
			return (numRows == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String generateInsertStatement(String tableName, String[] fields) {
		String fieldList = StringUtils.join(fields, ",");
		String[] marks = new String[fields.length];
		Arrays.fill(marks, "?");
		String markList = StringUtils.join(marks, ",");
		return "INSERT INTO " + tableName + " (" + fieldList + ") VALUES (" +
				markList + ")"; 
	}
	
	//SELECT STATEMENTS
	public boolean selectRows(String tableName, String[] fields, Object[] values) {
		String sql = generateInsertStatement(tableName, fields);
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(sql)) {
			for (int i = 0; i < values.length; i++) {
				pst.setObject(i + 1, values[i]);
			}
			rs = pst.executeQuery();
			return (numRows == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String generateSelectStatement(String tableName, String[] fields) {
		String fieldList = StringUtils.join(fields, ",");
		String[] marks = new String[fields.length];
		Arrays.fill(marks, "?");
		String markList = StringUtils.join(marks, ",");
		return "SELECT FROM " + tableName + " (" + fieldList + ") WHERE (" +
				markList + ")"; 
	}
	
	//UPDATE STATEMENTS
	public boolean updateRows(String tableName, String[] fields, Object[] values) {
		String sql = generateInsertStatement(tableName, fields);
		try(Connection con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(sql)) {
			for (int i = 0; i < values.length; i++) {
				pst.setObject(i + 1, values[i]);
			}
			int numRows = pst.executeUpdate();
			return (numRows == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String generateUpdateStatement(String tableName, String[] fields) {
		String fieldList = StringUtils.join(fields, ",");
		String[] marks = new String[fields.length];
		Arrays.fill(marks, "?");
		String markList = StringUtils.join(marks, ",");
		return "INSERT INTO " + tableName + " (" + fieldList + ") VALUES (" +
				markList + ")"; 
	}
	
	//DELETE STATEMENTS
	public boolean deleteRows(String tableName, String[] fields, Object[] values) {
		String sql = generateInsertStatement(tableName, fields);
		try(Connection con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(sql)) {
			for (int i = 0; i < values.length; i++) {
				pst.setObject(i + 1, values[i]);
			}
			int numRows = pst.executeUpdate();
			return (numRows == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String generateDeleteStatement(String tableName, String[] fields) {
		String fieldList = StringUtils.join(fields, ",");
		String[] marks = new String[fields.length];
		Arrays.fill(marks, "?");
		String markList = StringUtils.join(marks, ",");
		return "INSERT INTO " + tableName + " (" + fieldList + ") VALUES (" +
				markList + ")"; 
	}
}
