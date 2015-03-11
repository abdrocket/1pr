package practica1;

public class QueryCondition {
	
	private String ColumnName;
	private Operator op;
	private Object value;
	
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
