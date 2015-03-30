package abd.mappers;
 	
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import abd.AbstractMapper;
import abd.DataAccessor;
import abd.Operator;
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
		return new String[] {"fecha","id","titulo" };
	}

	@Override
	protected String[] getKeyColumnNames() {
		return new String[]{"id"};
	}

	@Override
	protected Crucigrama buildObject(List<Object> rs){
		return new Crucigrama((Integer)rs.get(1), (String)rs.get(2), (Date)rs.get(0));
	}

	@Override
	protected Object[] decomposeKey(Integer key) {
		return new Object[]{key};
	}

	public List<Integer> findCrosswordsByTitle(String title) {
		
		List<Integer> crosswords = new LinkedList<Integer>();
		List<Object> rs = da.executeFindById(getTableName()
		,new String[]{"id"}
		,new String[]{"titulo"}, new Object[]{title}
		,new Operator[]{Operator.LIKE});
		
		//System.out.println(rs.size() + " " + title);
		
		for(int i = 0; i<rs.size();i++){
			crosswords.add((Integer)rs.get(i));
		}
		
		return crosswords;
	}
	
}
