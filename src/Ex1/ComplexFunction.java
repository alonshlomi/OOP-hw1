package Ex1;

import java.util.Stack;

public class ComplexFunction implements complex_function {
	
	private function f1;
	private function f2;
	private Operation op;
	

	
	//Constructors:
	public ComplexFunction() {
		this.f1 = new Polynom();
		this.f2 = null;
		this.op = Operation.None;
	}
	
	public ComplexFunction(function f1) {
		if(f1 == null) throw new RuntimeException("Cannot init null function!");
		this.f1 = f1;
		this.f2 = null;
		this.op = Operation.None;
	}
	
	public ComplexFunction(Operation op, function f1,function f2) {
		if(f1 == null) throw new RuntimeException("Cannot init null function!");
		this.f1 = f1;
		this.f2 = f2;
		this.op = (op==null) ? Operation.None : op;
	}
	
	
	public ComplexFunction(String op_str, function f1, function f2) {
		if(f1 == null) throw new RuntimeException("Cannot init null function!");
		Operation op = Operation.None;
		switch (op_str) {
		case "plus":
			op = Operation.Plus;
			break;
		case "mul":
			op = Operation.Times;
			break;
		case "div":
			op = Operation.Divid;
			break;
		case "max":
			op = Operation.Max;
			break;
		case "min":
			op = Operation.Min;
			break;
		case "comp":
			op = Operation.Comp;
			break;
		default:
			throw new RuntimeException("Invalid operation: "+op_str);
		}
		this.f1 = f1;
		this.f2 = f2;
		this.op = op;
	}

	//Methods:
	@Override
	public double f(double x) {
		switch (this.op) {
		case Plus:
			return f1.f(x) + f2.f(x);		
		case Times:
			return f1.f(x) * f2.f(x);
		case Comp:
			return f1.f(f2.f(x));
		case Divid:
			if(f2.f(x)==0) throw new ArithmeticException("Divide by 0");
			return f1.f(x) / f2.f(x);
		case Max:
			return Math.max(f1.f(x), f2.f(x));
		case Min:
			return Math.min(f1.f(x), f2.f(x));
		case None:
			if(f2 == null) return f1.f(x);
			throw new RuntimeException("No operation between "+f1+" and "+f2);
		case Error:
			throw new RuntimeException("Unknown Operation!");
		default:
			return this.f(x);
		}
	}

	@Override
	public function initFromString(String s) {
		try {
		Stack<Character> st = new Stack<Character>();
		int left = s.indexOf('(');
		int right = s.lastIndexOf(')');
		
		if(left == -1 || right == -1) {
			return new Polynom(s);
		}
		
		String op_str = s.substring(0, left);
		String next_func = s.substring(left+1, right);
		int i;
		for(i = 0;i<next_func.length();i++) {
			if(st.isEmpty() && next_func.charAt(i) == ',') {
				i--;
				break;
			}
			if(next_func.charAt(i) == '(')  {
				st.push(next_func.charAt(i));
				continue;
			}
			if(next_func.charAt(i) == ')') {
				st.pop();
				if(st.isEmpty()) break;
			}
		}
		
		String f1_str = next_func.substring(0,i+1);
		String f2_str = next_func.substring(i+2,next_func.length());
		
		function f1 = this.initFromString(f1_str);
		function f2 = this.initFromString(f2_str);
		
		return new ComplexFunction(op_str, f1, f2);
		} catch (Exception e) {
			throw new RuntimeException("Invalid input!");
		}
		
	}

	@Override
	public function copy() {
		function copy = new ComplexFunction(getOp(),left(),right());
		return copy;
	}

	@Override
	public void plus(function f1) {
		this.f1 = (this.f2 != null) ? this.copy() : this.f1;
		this.f2 = f1;
		this.op = Operation.Plus;
	}

	@Override
	public void mul(function f1) {
		this.f1 = (this.f2 != null) ? this.copy() : this.f1;
		this.f2 = f1;
		this.op = Operation.Times;		
	}

	@Override
	public void div(function f1) {
		this.f1 = (this.f2 != null) ? this.copy() : this.f1;
		this.f2 = f1;
		this.op = Operation.Divid;
	}

	@Override
	public void max(function f1) {
		this.f1 = (this.f2 != null) ? this.copy() : this.f1;
		this.f2 = f1;
		this.op = Operation.Max;	
	}

	@Override
	public void min(function f1) {
		this.f1 = (this.f2 != null) ? this.copy() : this.f1;
		this.f2 = f1;
		this.op = Operation.Min;	}

	@Override
	public void comp(function f1) {
		this.f1 = (this.f2 != null) ? this.copy() : this.f1;
		this.f2 = f1;
		this.op = Operation.Comp;	
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ComplexFunction)) return false;
		
		ComplexFunction other = (ComplexFunction) obj;
		
		if(this.f2==null&&other.f2==null) return this.f1.equals(other.f1);
		if(this.f1==null || this.f2==null) return false;
		
		return (this.f1.equals(other.f1)) && (this.f2.equals(other.f2)) && (this.op.equals(other.op));
	}
	
	//Getters:
	@Override
	public function left() {
		return f1;
	}

	@Override
	public function right() {
		return f2;
	}

	@Override
	public Operation getOp() {
		return op;
	}
	
	//toString:
	public String toString() {
		if(this.f2 == null) return this.f1.toString();
		String ans = "("+f1.toString()+","+f2.toString()+")";
		switch (this.op) {
		case Plus:
			return "plus"+ans;
		case Times:
			return "mul"+ans;
		case Divid:
			return "div"+ans;
		case Comp:
			return "comp"+ans;
		case Max:
			return "max"+ans;
		case Min:
			return "min"+ans;
		case None:
			return ans;
		default:
			return "Error!";
		}
	}
}
