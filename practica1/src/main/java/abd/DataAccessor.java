package abd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
			con.commit();
			return (numRows == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String generateInsertStatement(String tableName, String[] fields) {
		String fieldList = StringUtils.join(fields, ",");//??
		String[] marks = new String[fields.length];
		Arrays.fill(marks, "?");
		String markList = StringUtils.join(marks, ",");
		return "INSERT INTO " + tableName + " (" + fieldList + ") VALUES (" +
				markList + ")"; 
	}
	
	//SELECT STATEMENTS		
	public String generateFindById(String tableName, String[] columnNames,
			String[] keyColumnNames,Operator[] keyOperator) {
		
		String[] conditions = new String[keyColumnNames.length];
		
		for(int i = 0;i < keyColumnNames.length; i++){
			conditions[i] = keyColumnNames[i] + " "+keyOperator[i]+ " ";
		}

		return "SELECT " + StringUtils.join(columnNames, ", ") + " FROM "
				+ tableName.toLowerCase() + " WHERE " + StringUtils.join(conditions, " AND");
	}
	
	public List<Object> executeFindById(String tableName, String[] columnNames,
			String[] keyColumnNames, Object[] dKey,Operator[] qc){
		
		String sql = generateFindById(tableName, columnNames, keyColumnNames,qc);
		List<Object> result = new LinkedList<Object>();
		
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {

			for(int i = 0; i < dKey.length;i++){
				pst.setObject(i+1, dKey[i]);
			}
			try (ResultSet rs = pst.executeQuery()) {
				
				while(rs.next()){
					for(int i=0;i<columnNames.length;i++){
						result.add(rs.getObject(columnNames[i]));
					}
				}
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//----UPDATE STATEMENTS----
	public boolean updateRows(String tableName, String[] columns, Object[] values, String[] kColumns, Object[] kValues) {
		String sql = generateUpdateStatement(tableName, columns, kColumns);
		try(Connection con = ds.getConnection();
			PreparedStatement pst = con.prepareStatement(sql)) {
			for (int i = 0; i < values.length; i++) {
				pst.setObject(i + 1, values[i]);
			}
			for (int i = values.length; i < values.length+kValues.length; i++) {
				pst.setObject(i + 1, kValues[i]);
			}
			int numRows = pst.executeUpdate();
			con.commit();
			return (numRows == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * UPDATE table_name
	 * SET column1=value1,column2=value2,column(n)=value(n)
	 * WHERE col_key1=key_value1
	 * 	AND	 col_key2=key_value2
	 * 	AND	 col_key(n)=key_value(n);
	 */
	public String generateUpdateStatement(String tableName, 
			String[] columns, String[] kColumns) {
		
		String[] setColumns = new String[columns.length];
		for(int i = 0; i < columns.length; i++){
			setColumns[i] = columns[i] + "= ? ";
		}
		String[] whereColumns = new String[kColumns.length];
		for(int i = 0; i < kColumns.length; i++){
			whereColumns[i] = kColumns[i] + "= ? ";
		}
		
		return "UPDATE " + tableName + " SET " + StringUtils.join(setColumns, ",") 
				+ " WHERE " + StringUtils.join(whereColumns, "AND");
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
			con.commit();
			return (numRows >= 0);
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
		return "DELETE FROM " + tableName + " WHERE " + fieldList + Operator.EQ +
				markList; 
	}

	

	
}
