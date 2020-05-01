public class Lagrange {

    public static String solve(double[] x, double[] y, double x_curr) { // Лагранж
        double answer = 0, numerator, denominator;
        int size = x.length;
        for (int i = 0; i < size; i++) {
            numerator = 1;
            denominator = 1;
            for (int j = 0; j < size; j++) {
                if (j != i) {
                    numerator *= (x_curr - x[j]);
                    denominator *= (x[i] - x[j]);
                }
            }
            answer += (numerator / denominator * y[i]);
        }

        return String.valueOf(answer);
    }
}
