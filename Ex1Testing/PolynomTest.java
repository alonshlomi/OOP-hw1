import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Ex1.*;

class PolynomTest {

	@Test
	void testPolynomString() {
		String p1_str = "3.0x^2-5.0x+6.3";
		String p2_str = "-1.0x^2";
		String p3_str = "0";
		
		Polynom p1 = new Polynom(p1_str);
		Polynom p2 = new Polynom(p2_str);
		Polynom p3 = new Polynom(p3_str);
		
		assertEquals(p1.toString(),p1_str);
		assertEquals(p2.toString(),p2_str);
		assertEquals(p3.toString(),p3_str);
	}

	@Test
	void testF() {
		double x = 5;
		Polynom actual_1 = new Polynom();
		Polynom actual_2 = new Polynom("5x^2+6x-9");
		Polynom actual_3 = new Polynom("-x^5+4x^4-3.5x^3");
		
		double expected_1 = 0;
		double expected_2 = 146;
		double expected_3 = -1062.5;
		
		assertEquals(expected_1, actual_1.f(x));
		assertEquals(expected_2, actual_2.f(x));
		assertEquals(expected_3, actual_3.f(x));
	}

	@Test
	void testAddPolynom_able() {
		Polynom actual = new Polynom("3x^3-5x^2+7x-2");
		Polynom_able p = new Polynom("-10x^4+7x^3-5x^2+3x-8");
		
		actual.add(p);
		
		String expected = "-10.0x^4+10.0x^3-10.0x^2+10.0x-10.0";
		
		assertEquals(expected,actual.toString());
	}

	@Test
	void testAddMonom() {
		Polynom actual = new Polynom("3x^3-5x^2+7x-2");
		String[] monom_s = {"-10x^4","7x^3","-5x^2","3x","-8"};
		String expected = "-10.0x^4+10.0x^3-10.0x^2+10.0x-10.0";
		
		for(int i=0;i<monom_s.length;i++) {
			Monom m = new Monom(monom_s[i]);
			actual.add(m);
		}
		
		assertEquals(expected,actual.toString());
	}

	@Test
	void testSubtractMonom() {
		Polynom actual = new Polynom("3x^3+4x^2-5");
		Monom m = new Monom("2x");
		
		actual.subtract(m);
		String expected = "3.0x^3+4.0x^2-2.0x-5.0";
		
		assertEquals(expected,actual.toString());
	}

	@Test
	void testSubtractPolynom() {
		Polynom p = new Polynom("5x^5+4x^4+3x^3+2x^2+x+1");
		p.substract(p);
		
		assertTrue(p.isZero());
	}

	@Test
	void testMultiplyPolynom_able() {
		Polynom p = new Polynom("5x^2+3");
		Polynom p1 = new Polynom("2x^3+4");
		p.multiply(p1);
		
		String expected = "10.0x^5+6.0x^3+20.0x^2+12.0";

		assertEquals(expected,p.toString());
	}

	@Test
	void testEqualsObject() {
		Monom m = new Monom(3.7,10);
		Polynom p = new Polynom(m.toString());
		
		assertTrue(p.equals(m));
	}

	@Test
	void testIsZero() {
		Polynom p = new Polynom();
		Polynom p1 = new Polynom("0x^3");
		Polynom p2 = new Polynom("0.0001");
		
		assertTrue(p.isZero());
		assertTrue(p1.isZero());
		assertFalse(p2.isZero());
	}

	@Test
	void testRoot() {
		double eps = Monom.EPSILON,x0 = 0, x1 = 2;
		
		Polynom p = new Polynom("x^2-4");
		
		double actual_1 = Math.ceil(p.root(x0, x1, eps));
		double expected_1 = 2;
		
		assertEquals(expected_1, actual_1);
	}

	@Test
	void testCopy() {
		Polynom expected = new Polynom("3x^3-2x^2+x-1");
		Polynom_able actual = expected.copy();
		
		assertEquals(expected, actual);
	}

	@Test
	void testDerivative() {
		Polynom_able actual_1 = new Polynom("3x^2-5x+3").derivative();
		Polynom_able actual_2 = new Polynom("-3x^7+3").derivative();
		Polynom_able actual_3 = new Polynom().derivative();
		
		String expected_1 = "6.0x-5.0";
		String expected_2 = "-21.0x^6";
		String expected_3 = "0";
		
		assertEquals(expected_1, actual_1.toString());
		assertEquals(expected_2, actual_2.toString());
		assertEquals(expected_3, actual_3.toString());
	}

	@Test
	void testArea() {
		double eps = Monom.EPSILON,x0 = 0, x1 = 2;

		double actual_1 = new Polynom("x").area(x0, x1, eps);
		double actual_2 = new Polynom("-x^2+4").area(x0, x1, eps);
		double actual_3 = new Polynom().area(x0, x1, eps);
		
		double expected_1 = 2;
		double expected_2 = 5.3333333333329325;
		double expected_3 = 0;
		
		assertEquals(expected_1,actual_1);
		assertEquals(expected_2,actual_2);
		assertEquals(expected_3,actual_3);
	}

	@Test
	void testMultiplyMonom() {
		Polynom actual = new Polynom("3x^5+2x^2");
		Monom m = new Monom("5x");
		
		actual.multiply(m);
		
		String expected = "15.0x^6+10.0x^3";
		
		assertEquals(expected,actual.toString());
	}

	@Test
	void testToString() {
		String p1_str = "3.0x^3-5.2x^2+6.3";
		String p2_str = "5.01x^3";
		String p3_str = "0";
		
		Polynom p1 = new Polynom(p1_str);
		Polynom p2 = new Polynom(p2_str);
		Polynom p3 = new Polynom(p3_str);
		
		assertEquals(p1_str,p1.toString());
		assertEquals(p2_str,p2.toString());
		assertEquals(p3_str,p3.toString());
	}

	@Test
	void testInitFromString() {
		String p1_str = "3.0x^3-5.2x^2+6.3";
		String p2_str = "5.01x^3";
		String p3_str = "0";
		
		Polynom p1 = new Polynom(p1_str);
		Polynom p2 = new Polynom(p2_str);
		Polynom p3 = new Polynom(p3_str);
		
		function f1 = new Polynom().initFromString(p1_str);
		function f2 = new Polynom().initFromString(p2_str);
		function f3 = new Polynom().initFromString(p3_str);
		
		assertEquals(p1, f1);
		assertEquals(p2, f2);
		assertEquals(p3, f3);
	}

}
