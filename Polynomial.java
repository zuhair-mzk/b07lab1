import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Polynomial{
    double[] coefficients;
    int[] exponents; 

    public Polynomial(){
        coefficients = new double[1];
        exponents = new int[1];
    }    

    public Polynomial(double[] given_coeff, int[] given_exp) {
        coefficients = given_coeff;
        exponents = given_exp;
    }

    public Polynomial(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();

        String[] split_terms = line.split("(?=[+-])");

        coefficients = new double[split_terms.length];
        exponents = new int[split_terms.length];

        for (int i = 0; i < split_terms.length; i++) {
            String term = split_terms[i].trim();

            if (term.charAt(0) == '-') {
                coefficients[i] = -1;
                term = term.substring(1);
            } else if (term.charAt(0) == '+') {
                coefficients[i] = 1;
                term = term.substring(1);
            } else {
                coefficients[i] = 1;
            }

            String[] part = term.split("x");
            coefficients[i] *= Double.parseDouble(part[0]);

            if (part.length == 2) {
                exponents[i] = Integer.parseInt(part[1]);
            } else {
                exponents[i] = 0;
            }
        }
    }

    public Polynomial add(Polynomial p3) {
        if (coefficients == null || p3.coefficients == null) {
            System.out.println("error, Polynomial cannot be added");
            return new Polynomial();
        }
        
        int maxlength = coefficients.length + p3.coefficients.length;
        double[] new_coeff = new double[maxlength];
        int[] new_exp = new int[maxlength];
        int result_index = 0;
    
        for (int i = 0; i < coefficients.length; i++) {
            new_coeff[result_index] = coefficients[i];
            new_exp[result_index] = exponents[i];
            result_index++;
        }
    
        for (int i = 0; i < p3.coefficients.length; i++) {
            boolean match = false;
            for (int j = 0; j < result_index; j++) {
                if (new_exp[j] == p3.exponents[i]) {
                    new_coeff[j] += p3.coefficients[i];
                    match = true;
                    break;
                }
            }
            if (!match) {
                new_exp[result_index] = p3.exponents[i];
                new_coeff[result_index] = p3.coefficients[i];
                result_index++;
            }
        }
    
        double[] trimmed_coeff = Arrays.copyOf(new_coeff, result_index);
        int[] trimmed_exp = Arrays.copyOf(new_exp, result_index);
    
        return new Polynomial(trimmed_coeff, trimmed_exp);
    }

    public Polynomial multiply(Polynomial p4){
        if (coefficients == null || p4.coefficients == null) {
            System.out.println("error, Polynomial cannot be Multiplied");
            return new Polynomial();
        } 
    
        int maxlength = coefficients.length * p4.coefficients.length;
        double[] new_coeff = new double[maxlength];
        int[] new_exp = new int[maxlength];
        int result_index = 0;
    
        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < p4.coefficients.length; j++) {
                double multiply_coeff = coefficients[i] * p4.coefficients[j];
                int multiply_exp = exponents[i] + p4.exponents[j];
                boolean found = false;
    
                for (int k = 0; k < result_index; k++) {
                    if (new_exp[k] == multiply_exp) {
                        new_coeff[k] += multiply_coeff;
                        found = true;
                        break;
                    }
                }
    
                if (!found) {
                    new_coeff[result_index] = multiply_coeff;
                    new_exp[result_index] = multiply_exp;
                    result_index++;
                }
            }
        }
    
        double[] trimmed_coeff = Arrays.copyOf(new_coeff, result_index);
        int[] trimmed_exp = Arrays.copyOf(new_exp, result_index);
    
        return new Polynomial(trimmed_coeff, trimmed_exp);
    }

    public void saveToFile(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);

        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] >= 0 && i > 0) {
                writer.print("+");
            }
            writer.print(coefficients[i]);
            if (exponents[i] != 0) {
                writer.print("x");
                writer.print(exponents[i]);
            }
        }

        writer.close();
    }
    
    

    public double evaluate(double x){
        double value = 0;
        for (int i = 0; i < coefficients.length; i++){
            value += Math.pow(x, exponents[i]) * coefficients[i];
        }
        return value;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }

    public double[] getCoefficients() {
        return coefficients;
    }
    
    public int[] getExponents() {
        return exponents;
    }
}