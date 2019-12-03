package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;

public class Functions_GUI implements functions {

	private LinkedList<function> functions;
	private static File f;
	private static FileReader in;
	private static FileWriter fw;
	private static BufferedReader br;
	private static Color[] Colors = { Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN,
			Color.PINK };

	public Functions_GUI() {
		functions = new LinkedList<function>();
	}
	
	public Functions_GUI(Collection<? extends function> c) {
		functions = new LinkedList<function>();
		addAll(c);
	}
	
	@Override
	public int size() {
		return functions.size();
	}

	@Override
	public boolean isEmpty() {
		return functions.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return functions.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return functions.iterator();
	}

	@Override
	public Object[] toArray() {
		return functions.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return functions.toArray(a);
	}

	@Override
	public boolean add(function e) {
		return functions.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return functions.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return functions.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return functions.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return functions.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return functions.retainAll(c);
	}

	@Override
	public void clear() {
		functions.clear();
	}

	@Override
	public void initFromFile(String file) throws IOException {
		f = new File(file);
		in = new FileReader(f);
		br = new BufferedReader(in);
		function cf = new ComplexFunction();
		String read_line = br.readLine();
		while (read_line != null) {
			function tmp = cf.initFromString(read_line);
			functions.add(tmp);
			read_line = br.readLine();
		}
		br.close();
	}

	@Override
	public void saveToFile(String file) throws IOException {
		f = new File(file);
		fw = new FileWriter(f, false);
		for (int i = 0; i < functions.size(); i++) {
			fw.write(functions.get(i).toString() + "\r\n");
		}
		fw.close();
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

		StdDraw.setCanvasSize(width, height);

		double minX = rx.get_min(), maxX = rx.get_max();
		double minY = ry.get_min(), maxY = ry.get_max();

		// Scales
		StdDraw.setXscale(minX, maxX);
		StdDraw.setYscale(minY, maxY);
		Font font = new Font("Arial", 0 ,15);
		StdDraw.setFont(font);
		
		StdDraw.setPenRadius(0.001);
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for(int i = (int) rx.get_min();i<rx.get_max();i++) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
		
		// x axis
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor();
		StdDraw.line(minX, 0, maxX, 0);
		for (int i = (int) minX; i <= maxX; i++) {
			StdDraw.text(i, 0, "|");
			StdDraw.text(i, -0.6, Integer.toString(i));
		}
		// y axis
		StdDraw.line(0, minY, 0, maxY);
		for (int i = (int) minY; i <= maxY; i++) {
			StdDraw.text(0, i, "-");
			StdDraw.text(-0.4, i, Integer.toString(i));
		}
		StdDraw.setPenRadius(0.003);
		// draw functions
		for (int i = 0; i < this.functions.size(); i++) {
			function curr_f = this.functions.get(i);
			Color curr_c = Colors[i%7];
			double steps = (maxX - minX) / resolution;

			StdDraw.setPenColor(curr_c);

			for (double j = minX; j < maxX; j += steps) {

				double x0 = j, y0 = curr_f.f(x0);
				double x1 = j + steps, y1 = curr_f.f(x1);

				StdDraw.line(x0, y0, x1, y1);

			}
			System.out.println(i+1+") "+curr_c+"\tf(x) = "+curr_f);

		}
	}

	@Override
	public void drawFunctions(String json_file) {
		// default values
		int width = 500, height = 500, resolution = 1000;
		Range rx = new Range(-10, 10);
		Range ry = new Range(-10, 10);

		try {
			f = new File(json_file);
			in = new FileReader(f);
			br = new BufferedReader(in);
			String json_str = "", line;

			while ((line = br.readLine()) != null)
				json_str += line;

			Gson g = new Gson();
			JsonParam jp = g.fromJson(json_str, JsonParam.class);

			width = jp.getWidth();
			height = jp.getHeight();
			rx = jp.getRX();
			ry = jp.getRY();
			resolution = jp.getReso();

		} catch (Exception e) {
			System.out.println("Invalid input, draw with default parameters");
		}

		drawFunctions(width, height, rx, ry, resolution);

	}

	public static void main(String[] args) throws IOException {
		Range rx=new Range(-10,10);
		Range ry=new Range(-10,10);
		Functions_GUI f= new Functions_GUI();
//		f.initFromFile("test.txt");
//		f.saveToFile("mosh.txt");

		function f1=new Polynom("2x^2");
		function f2=new Polynom("5x^3+5x-5");
		function f3=new Polynom("5x");
		function f4=new Polynom("-2x^2-4");
		function cf=new ComplexFunction();
		cf=cf.initFromString("div(plus(2.0x^4-3.0x,5.0x),mul(7.0x^3,3.0x))");
		f.functions.add(f1);
		f.functions.add(f2);
		f.functions.add(f3);
		f.functions.add(f4);
		f.functions.add(cf);
		f.drawFunctions(700,700,rx,ry,1000);
	}

	private class JsonParam {
		int width, height, resolution;
		Range rx, ry;

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public int getReso() {
			return resolution;
		}

		public Range getRX() {
			return rx;
		}

		public Range getRY() {
			return ry;
		}

	}
}
