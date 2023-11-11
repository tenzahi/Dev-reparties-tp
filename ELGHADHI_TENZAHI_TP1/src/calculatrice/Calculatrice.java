package calculatrice;
import java.io.Serializable;

public class Calculatrice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int a ;
	private int b ;
	private String op ;
	
	public Calculatrice(int a,String op , int b) {
		this.a = a ;
		this.op = op;
		this.b = b;
	}

	@Override
	public String toString() {
		return "Calculatrice [a=" + a + ", b=" + b + ", op=" + op + "]";
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}}
	
	
	 
	
