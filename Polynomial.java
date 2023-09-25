public class Polynomial{
    double[] coefficients;
    int [] exponents; 

    public Polynomial(){
        coefficients = new double[1];
        exponents = new int[1];
    }    

    public Polynomial(double[] given_coeff, int[] given_exp) {
        coefficients = given_coeff;
        exponents = given_exp;
    }

    public Polynomial add(Polynomial p3) {
        int maxlength = Math.max(coefficients.length, p3.coefficients.length);
        double[] result = new double[maxlength];

        for ( int i = 0; i < coefficients.length; i++){
            result[i] += coefficients[i];
        }

        for (int j = 0; j < p3.coefficients.length; j++){
            result[j] += p3.coefficients[j];
        }
        return new Polynomial(result);
    }

    public double evaluate(double x){
        double value = 0;
        for (int i = 0; i < coefficients.length; i++){
            value += Math.pow(x, i) * coefficients[i];
        }
        return value;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}