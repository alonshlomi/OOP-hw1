import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Ex1.*;

class MonomTest {

	@Test
	void testDerivative() {
		Monom m1 = new Monom("-3x^2");
		Monom m2 = new Monom("5x");
		Monom m3 = new Monom("0");
		
		Monom m1_d = m1.derivative();
		Monom m2_d = m2.derivative();
		Monom m3_d = m3.derivative();
		
		String m1_d_str = "-6.0x";
		String m2_d_str = "5.0";
		String m3_d_str = "0";
		
		assertEquals(m1_d.toString(), m1_d_str);
		assertEquals(m2_d.toString(), m2_d_str);
		assertEquals(m3_d.toString(), m3_d_str);
	}

	@Test
	void testF() {
		double x = 2;
		Monom actual_1 = new Monom("5x^3");
		Monom actual_2 = new Monom("-4x");
		Monom actual_3 = new Monom("10");
		
		double expected_1 = 40;
		double expected_2 = -8;
		double expected_3 = 10;
		
		assertEquals(expected_1, actual_1.f(x));
		assertEquals(expected_2, actual_2.f(x));
		assertEquals(expected_3, actual_3.f(x));
	}

	@Test
	void testIsZero() {
		Monom m1 = new Monom("0x^33");
		Monom m2 = new Monom("-3x^0");
		
		assertTrue(m1.isZero());
		assertFalse(m2.isZero());
	}

	@Test
	void testMonomString() {
		Monom expected_1 = new Monom("5x^3");
		Monom expected_2 = new Monom(10.3,56);
		Monom expected_3 = new Monom(expected_1);

		Monom actual_1 = new Monom(expected_1.toString());
		Monom actual_2 = new Monom(expected_2.toString());
		Monom actual_3 = new Monom(expected_3.toString());
		
		assertEquals(expected_1,actual_1);
		assertEquals(expected_2,actual_2);
		assertEquals(expected_3,actual_3);
	}

	@Test
	void testAdd() {
		Monom m1 = new Monom(5.3,2);
		Monom m2 = new Monom(3,2);
		m1.add(m2);
		
		String expected = "8.3x^2";
		
		assertEquals(expected, m1.toString());
	}

	@Test
	void testMultiply() {
		Monom m1 = new Monom(5,3);
		Monom m2 = new Monom(3,2);
		m1.multiply(m2);
		
		String expected = "15.0x^5";
		
		assertEquals(expected, m1.toString());
		}

	@Test
	void testToString() {
		String expected_1 = "3.0x^5";
		String expected_2 = "0";
		String expected_3 = "-1.0x^5";
		
		Monom actual_1 = new Monom(expected_1);
		Monom actual_2 = new Monom(expected_2);
		Monom actual_3 = new Monom(expected_3);
		
		assertEquals(expected_1, actual_1.toString());
		assertEquals(expected_2, actual_2.toString());
		assertEquals(expected_3, actual_3.toString());
	}

	@Test
	void testEqualsObject() {
		Monom m1 = new Monom("3x^2");
		Monom m2 = new Monom(3,2);
		Monom m3 = new Monom("5x");
		Monom m4 = new Monom("-5x");
		
		assertTrue(m1.equals(m2));
		assertFalse(m3.equals(m4));
	}

	@Test
	void testSubtract() {
		Monom m1 = new Monom(5.3,2);
		Monom m2 = new Monom(3,2);
		m1.subtract(m2);
		
		String expected = "2.3x^2";
		
		assertEquals(expected, m1.toString());
		}

	@Test
	void testInitFromString() {
		String str = "-3.5x^2";
		Monom m = new Monom(str);
		function f = m.initFromString(str);
		
		assertTrue(m.equals(f));
	}

	@Test
	void testCopy() {
		Monom expected = new Monom("33.7x^7");
		function actual = expected.copy();
		
		assertEquals(expected,actual);
	}

}
