package Ex1;

import java.io.IOException;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		
		function f1 = new ComplexFunction().initFromString("mul(plus(3x^2,3x),mul(5x,3x))");
		function f2 = new ComplexFunction().initFromString("mul(mul(3x,5x),plus(3x,3x^2))");
		System.out.println(f1.equals(f2));
	}
}
