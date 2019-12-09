# Ex1

Ex1 is an extension of Ex0, in this project we implemented complex function, save and load from file and represent them on graphical window.

### Monom Class

This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative).

### Polynom Class

This class represents a general Polynom: f(x) = a_1X^b_1 + a_2*X^b_2 ... a_n*Xb_n,  
where: a_1, a_2 ... a_n are real numbers and b_1<b_2..<b_n are none negative integers (naturals).  
Also supports add and multiply functionality and compute Reimann's Integral, Root and derivate Polynom's.  

### ComplexFunction Class

This class represents a complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions (or complex functions),  
y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).  


### Functions_GUI Class

This interface represents a collection of mathematical functions,  
which can be presented on a GUI window and can be saved (and load) to file.  


##### Libraries

In this project we used Gson library for reading a json file.  
[Gson](https://github.com/google/gson)
