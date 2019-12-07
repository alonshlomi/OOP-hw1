import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Ex1.*;

class Functions_GUITest {

	@Test
	void FileTest() {
		LinkedList<function> funcs = new LinkedList<function>();
		function f1 = new ComplexFunction().initFromString("5x^2+5x+2");
		function f2 = new ComplexFunction().initFromString("mul(5x^2,5x+2)");
		function f3 = new ComplexFunction().initFromString("div(mul(5x^2,5x+2),5x^2+5x+2)");
		
		funcs.add(f1);
		funcs.add(f2);
		funcs.add(f3);
		
		Functions_GUI save_f = new Functions_GUI(funcs);
		Functions_GUI init_f = new Functions_GUI();
		try {
			save_f.saveToFile("funcs_test.txt");
			init_f.initFromFile("funcs_test.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertTrue(init_f.containsAll(funcs));
		
	}

}
