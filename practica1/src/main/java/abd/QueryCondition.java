package abd;



public class QueryCondition {
	
	private String ColumnName;
	private Operator op;
	private Object value;
	
	public QueryCondition(String columname, Operator op, Object value){
		this.ColumnName = columname;
		this.op = op;
		this.value = value;
	}
	
	public String getColumnName(){
		return this.ColumnName;
	}
	
	public Object getValue() {
		return value;
	}

	public Operator getOperator(){
		return this.op;
	}
	
	public String toString(){
		return this.ColumnName + " " + this.op + " " + this.value;
	}
	
}
