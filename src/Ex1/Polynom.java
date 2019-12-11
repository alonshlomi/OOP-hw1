package Ex1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import Ex1.Monom;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral: / 2. Finding a numerical
 * value between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able {

	private LinkedList<Monom> poly;

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		poly = new LinkedList<Monom>();
	}

	/**
	 * init a Polynom from a String such as: {"x", "3+1.4X^3-34x"};
	 * 
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		poly = new LinkedList<Monom>();
		String[] arr = s.split("(?=-)|(\\+)");

		for (int i = 0; i < arr.length; i++) {
			add(new Monom(arr[i]));
		}
	}

	// ****************** Private Methods and Data *****************

	@Override
	public double f(double x) {
		double sum = 0;

		for (int i = 0; i < poly.size(); i++) {
			sum += poly.get(i).f(x);
		}
		return sum;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> p = p1.iteretor();

		while (p.hasNext()) {
			Monom m = new Monom(p.next());
			this.add(m);
		}
	}

	@Override
	public void add(Monom m1) {
		for (int i = 0; i < this.poly.size(); i++) {
			if (this.poly.get(i).get_power() == m1.get_power()) {
				this.poly.get(i).add(m1);
				return;
			}
		}

		this.poly.add(new Monom(m1));
		this.poly.sort(Monom._Comp);
	}

	public void subtract(Monom m1) {
		for (int i = 0; i < this.poly.size(); i++) {
			if (this.poly.get(i).get_power() == m1.get_power()) {
				this.poly.get(i).subtract(m1);
				return;
			}
		}

		m1.multiply(Monom.MINUS1);
		this.poly.add(new Monom(m1));
		this.poly.sort(Monom._Comp);
	}

	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> p = p1.iteretor();

		while (p.hasNext()) {
			Monom m = new Monom(p.next());
			this.subtract(m);
		}
	}

	@Override
	public void multiply(Polynom_able p1) {
		Polynom tmp = new Polynom();

		for (int i = 0; i < this.poly.size(); i++) {
			Iterator<Monom> p = p1.iteretor();

			while (p.hasNext()) {
				Monom m = new Monom(p.next());
				Monom m1 = new Monom(this.poly.get(i));
				m1.multiply(m);
				tmp.add(new Monom(m1));
			}
		}
		poly = tmp.poly;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || this == null)
			return false;

		Polynom_able p = this.copy();
		Polynom obj_p;

		if (obj instanceof Polynom) {
			obj_p = (Polynom) obj;
			p.substract(obj_p);
			return p.isZero();
		}

		if (obj instanceof Monom) {
			obj_p = new Polynom(obj.toString());
			p.substract(obj_p);
			return p.isZero();
		}

		if (!(obj instanceof Polynom_able))
			return false;

		Polynom_able other = (Polynom_able) obj;
		p.substract(other);
		return p.isZero();
	}

	@Override
	public boolean isZero() {
		for (int i = 0; i < poly.size(); i++) {
			if (!poly.get(i).isZero())
				return false;
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if (this.f(x0) * this.f(x1) > 0) {
			throw new RuntimeException("Invalid input: " + "f(" + x0 + ")*f(" + x1 + ")>0");
		}

		double h = x0;
		while (x1 - x0 >= eps) {
			h = (x0 + x1) / 2.0;
			if (Math.abs(this.f(h)) <= eps)
				return h;
			else if (this.f(h) * this.f(x0) < 0)
				x1 = h;
			else
				x0 = h;
		}
		return h;
	}

	@Override
	public Polynom_able copy() {
		Polynom_able p = new Polynom();

		for (int i = 0; i < poly.size(); i++) {
			p.add(new Monom(poly.get(i)));
		}
		return p;
	}

	@Override
	public Polynom_able derivative() {
		Polynom_able p = new Polynom();

		for (int i = 0; i < poly.size(); i++) {
			p.add(poly.get(i).derivative());
		}
		return p;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		double n = Math.abs((x1 - x0)) / eps;
		double x_i, x_i_minus1, height, sum = 0;

		for (int i = 1; i <= n; i++) {
			x_i = (x0 + eps * i);
			x_i_minus1 = (x0 + eps * (i - 1));
			height = (this.f(x_i) + this.f(x_i_minus1)) / 2.0;
			if (height < 0)
				continue;
			sum += height * eps;
		}
		return sum;
	}

	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		return this.poly.iterator();
	}

	@Override
	public void multiply(Monom m1) {
		for (int i = 0; i < poly.size(); i++) {
			poly.get(i).multiply(m1);
		}
	}

	public String toString() {
		String ans = "";
		if (this.isZero())
			return "0";

		for (int i = 0; i < poly.size(); i++) {
			if (poly.get(i).isZero())
				continue;
			if (i != 0 && poly.get(i).get_coefficient() >= 0)
				ans += "+";
			ans += poly.get(i) + "";
		}
		return ans;
	}

	@Override
	public function initFromString(String s) {
		function ans = new Polynom(s);
		return ans;
	}

}
