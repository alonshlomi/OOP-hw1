import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.*;

class ComplexFunctionTest {
	
	@Test
	void testF() {
		String f1_str = "div(plus(3x^2,3x),5x)";
		String f2_str = "2x^2+3x-5";
		String f3_str = "max(mul(2x,5),div(2x^2,2))";
		
		function f1 = new ComplexFunction().initFromString(f1_str);		
		double f1_f = f1.f(5);
		
		function f2 = new ComplexFunction().initFromString(f2_str);
		double f2_f = f2.f(2);
		
		function f3 = new ComplexFunction().initFromString(f3_str);
		double f3_f = f3.f(3);
		
		assertEquals(3.6, f1_f);
		assertEquals(9, f2_f);
		assertEquals(30, f3_f);

	}

	@Test
	void testInitFromString() {
		function f1 = new Polynom("3x^2");
		function f2 = new Polynom("5x^3+10");
		String actual_str = "mul(3x^2,5x^3+10)";
		
		ComplexFunction expected = new ComplexFunction("mul", f1, f2);
		function actual = expected.initFromString(actual_str);
		
		assertEquals(expected, actual);
	
	}

	@Test
	void testCopy() {
		function f1 = new ComplexFunction().initFromString("plus(mul(3x^2,5x),div(5x^2,x^2))");
		function f2 = f1.copy();
		
		assertEquals(f1, f2);
	}

	@Test
	void testPlus() {
		ComplexFunction actual = new ComplexFunction("mul", new Polynom("3x^2"), new Monom("5x"));
		function f1 = new ComplexFunction().initFromString("5x^2");
		String expected = "plus(mul(3.0x^2,5.0x),5.0x^2)";
		actual.plus(f1);
		
		assertEquals(actual.toString(), expected);
	}

	@Test
	void testMul() {
		ComplexFunction actual = new ComplexFunction("mul", new Polynom("3x^2"), new Monom("5x"));
		function f1 = new ComplexFunction().initFromString("5x^2");
		String expected = "mul(mul(3.0x^2,5.0x),5.0x^2)";
		actual.mul(f1);
		
		assertEquals(actual.toString(), expected);	}

	@Test
	void testDiv() {
		ComplexFunction actual = new ComplexFunction("mul", new Polynom("3x^2"), new Monom("5x"));
		function f1 = new ComplexFunction().initFromString("5x^2");
		String expected = "div(mul(3.0x^2,5.0x),5.0x^2)";
		actual.div(f1);
		
		assertEquals(actual.toString(), expected);	}

	@Test
	void testMax() {
		ComplexFunction actual = new ComplexFunction("mul", new Polynom("3x^2"), new Monom("5x"));
		function f1 = new ComplexFunction().initFromString("5x^2");
		String expected = "max(mul(3.0x^2,5.0x),5.0x^2)";
		actual.max(f1);
		
		assertEquals(actual.toString(), expected);	}

	@Test
	void testMin() {
		ComplexFunction actual = new ComplexFunction("mul", new Polynom("3x^2"), new Monom("5x"));
		function f1 = new ComplexFunction().initFromString("5x^2");
		String expected = "min(mul(3.0x^2,5.0x),5.0x^2)";
		actual.min(f1);
		
		assertEquals(actual.toString(), expected);	}

	@Test
	void testComp() {
		ComplexFunction actual = new ComplexFunction("mul", new Polynom("3x^2"), new Monom("5x"));
		function f1 = new ComplexFunction().initFromString("5x^2");
		String expected = "comp(mul(3.0x^2,5.0x),5.0x^2)";
		actual.comp(f1);
		
		assertEquals(actual.toString(), expected);	}

	@Test
	void testEqualsObject() {
		String f1_str = "mul(plus(3x^2,3x),mul(5x,3x))";
		String f2_str = "mul(mul(3x,5x),plus(3x,3x^2))";
		function f1 = new ComplexFunction().initFromString(f1_str);
		function f2 = new ComplexFunction().initFromString(f2_str);
		
		assertTrue(f1.equals(f2));
		}

	@Test
	void testToString() {
		ComplexFunction actual = new ComplexFunction("mul", new Polynom("3x^2"), new Monom("5x"));

		String expected = "mul(3.0x^2,5.0x)";
		
		assertEquals(actual.toString(), expected);	}

}
