package Ex1;

import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * This class represents a simple (naive) tester for the Polynom class,
 * Expected output: 
 ************ Test 1 ************

1.9999999999999996
0

 ************ Test 2 ************

p1: -4.7x^2-x+6.0
p2: 1.7000000000000002x^2+1.7x+2.0
p1+p2: -3.0x^2+0.7x+8.0
(p1+p2)*p2: -5.1000000000000005x^4-3.9099999999999997x^3+8.790000000000001x^2+15.0x+16.0
from string: -5.1000000000000005x^4-3.9099999999999997x^3+8.790000000000001x^2+15.0x+16.0

 ************ Test 3 ************

Invalid input, cannot init 5a
0.) The Polynom isn't created!

1.) 3.0x^5-1.0x^2+3.0 : 
	Equals to copy: true, f(1) = 5.0, isZero: false
	Derivative: 15.0x^4-2.0x
	(Copy + p): 6.0x^5-2.0x^2+6.0
	Root in [-2,2]: -0.9336465001106262, Area in [0,2]: 3.166667749999748
2.) 0 : 
	Equals to copy: true, f(2) = 0.0, isZero: true
	Derivative: 0
	(Copy + p): 0
	Root in [-2,2]: 0.0, Area in [0,2]: 0.0
3.) 3.0x^3+5.0x-2.0 : 
	Equals to copy: true, f(3) = 94.0, isZero: false
	Derivative: 9.0x^2+5.0
	(Copy + p): 6.0x^3+10.0x-4.0
	Root in [-2,2]: 0.3696855902671814, Area in [0,2]: 1.2500007499999994
 */

public class PolynomTest {
	public static void main(String[] args) {
		//test1();
		//test2();
		//test3();
		Polynom p = new Polynom("3.2x^2x");
		System.out.println(p);
		
	}

	public static void test1() {
		System.out.println("************ Test 1 ************\n");

		Polynom p1 = new Polynom();
		String[] monoms = { "1", "x", "x^2", "0.5x^2" };
		for (int i = 0; i < monoms.length; i++) {
			Monom m = new Monom(monoms[1]);
			p1.add(m);
		}
		double aa = p1.area(0, 1, 0.0001);
		System.out.println(aa);
		p1.substract(p1);
		System.out.println(p1);

		System.out.println();
	}

	public static void test2() {
		System.out.println("************ Test 2 ************\n");

		Polynom p1 = new Polynom(), p2 = new Polynom();
		String[] monoms1 = { "2", "-x", "-3.2x^2", "4", "-1.5x^2" };
		String[] monoms2 = { "5", "1.7x", "3.2x^2", "-3", "-1.5x^2" };
		for (int i = 0; i < monoms1.length; i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for (int i = 0; i < monoms2.length; i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: " + p1);
		System.out.println("p2: " + p2);
		p1.add(p2);
		System.out.println("p1+p2: " + p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: " + p1);
		String s1 = p1.toString();
		Polynom_able pp1 = new Polynom(s1);
		System.out.println("from string: "+pp1);
		System.out.println();
	}

	public static void test3() {
		System.out.println("************ Test 3 ************\n");
		String[] polynoms = { "3x^4+5a", "-x^2+0x^5+3x^5+3", "0x^2+0", "5x+3x^3-2" };
		for (int i = 0; i < polynoms.length; i++) {
			Polynom p = new Polynom(polynoms[i]);

			Polynom_able p_copy, p_deri;
			p_copy = p.copy();
			p_deri = p.derivative();
			System.out.println(i + ".) " + p + " : \n\t" + "Equals to copy: " + p.equals(p_copy) + ", f(" + i + ") = "
					+ p.f(i) + ", isZero: " + p.isZero());
			System.out.println("\tDerivative: " + p_deri);
			p_copy.add(p);
			System.out.println("\t(Copy + p): " + p_copy);
			System.out.println(
					"\tRoot in [-2,2]: " + p.root(-2, 2, Monom.EPSILON) + ", Area in [0,2]: " + p.area(0, 1, 0.001));
		}
		System.out.println();
	}
}
