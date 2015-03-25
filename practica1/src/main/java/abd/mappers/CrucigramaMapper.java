package abd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.model.Crucigrama;

public class CrucigramaMapper extends AbstractMapper<Crucigrama, Integer>{

	public CrucigramaMapper(DataAccessor da) {
		super(da);
	}

	@Override
	protected String getTableName() {
		return "Crucigramas";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[] {"fecha", "usuario_source",
				"usuario_target","id","titulo" };
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"id"};
	}

	@Override
	protected Crucigrama buildObject(ResultSet rs) throws SQLException {
		Date date = rs.getDate("fecha");
		Integer idTabla = rs.getInt("id");
		String titulo = rs.getString("titulo");
		return new Crucigrama(idTabla, titulo, date);
	}

	@Override
	protected Object[] decomposeKey(Integer key) {
		return new Object[]{key};
	}

	public List<Integer> findCrosswordsByTitle(String title) {
		
		List<Integer> crosswords = new LinkedList<Integer>();
		ResultSet rs = da.findTitleLike(getTableName(), new String[]{"id"}
		,"titulo", title);
		
		try {
			while(rs.next()){
				crosswords.add(rs.getInt("id"));
			};
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return crosswords;
	}
	
}
