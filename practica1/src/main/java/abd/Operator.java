package abd;

public enum Operator {
	EQ("="),
	LEQ("<="),
	GEQ(">="),
	LT("<"),
	GT(">"),
	NEQ("<>"),
	LIKE("LIKE")
	;
	
	private String value;
	
	public String toString(){
		return this.value;
	}
	
	Operator(String s) {
		this.value = s;
	}
}
