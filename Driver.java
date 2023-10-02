import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.File;

public class Driver {
    public static void main(String [] args) throws FileNotFoundException {
        // testing the polynomial cosntructor:
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));

        // creating three differing polynomials:
        double[] c1 = {6, 2};
        int[] d1 = {0, 1};
        Polynomial p1 = new Polynomial(c1, d1);

        double[] c2 = { 2, -2, 0, 0, -9 };
        int[] d2 = { 0, 1, 2, 3, 4 };
        Polynomial p2 = new Polynomial(c2, d2);

        double[] c3 = { 2, -3, 1};
        int[] d3 = { 0, 1, 2};
        Polynomial p3 = new Polynomial(c3, d3);

        // Testing the add function on the Polynomials:
        Polynomial s = p1.add(p2);
        Polynomial s2 = s.add(p3);
        System.out.println("Coefficient for s: " + Arrays.toString(s.getCoefficients()));
        System.out.println("Exponents for s: " + Arrays.toString(s.getExponents()));
        System.out.println("coefficients for s2: " + Arrays.toString(s2.getCoefficients()));
        System.out.println("Exponents for s2: "  + Arrays.toString(s2.getExponents()));

        // Checkign the evaluation function:
        System.out.println("s(1) = " + s.evaluate(1));
        System.out.println("s(2) = " + s.evaluate(2));
        System.out.println("p3(2) = " + p3.evaluate(2));


        // Checking the hasRoot method:
        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        
        if (p3.hasRoot(2))
            System.out.println("2 is a root of s");
        else
            System.out.println("2 is not a root of s"); 

        // Multiply function:
        Polynomial product = p1.multiply(p3);
        System.out.println("Coefficient for product: " + Arrays.toString(product.getCoefficients()));
        System.out.println("Exponents for product: " + Arrays.toString(product.getExponents()));

        // Testing saveToFile function and file constructor
        String fileName = "Polynomial.txt";
        s.saveToFile(fileName);
        
        // Creating a new polynomial from the file
        File file = new File("Polynomial.txt");
        Polynomial fromFile = new Polynomial(file);
        System.out.println("Coefficient from file: " + Arrays.toString(fromFile.getCoefficients()));
        System.out.println("Exponents from file: " + Arrays.toString(fromFile.getExponents()));
    }
}
