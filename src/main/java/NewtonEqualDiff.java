public class NewtonEqualDiff {

    private double[] x;
    private double[] y;
    private double x_curr;
    private int n;
    static int begin;

    public NewtonEqualDiff(double[] x, double[] y, double x_curr) { // равностоящие узлы
        this.x = x;
        this.y = y;
        this.n = x.length;
        this.x_curr = x_curr;
    }

    public String solve() {
        double res;
        double t;
        if (x_curr < x[0]) {
            t = (x_curr - x[0]) / (x[1] - x[0]);
            res = backward(t);
        } else if (x_curr > x[n - 1]) {
            t = (x_curr - x[n - 1]) / (x[1] - x[0]);
            res = forward(t);
        } else if (x_curr <= x[n / 2]) {
            begin = 0;
            while (x_curr > x[begin])
                begin++;
            begin--;

            t = (x_curr - x[begin]) / (x[1] - x[0]);
            res = forward(t);
        } else {
            begin = 0;
            while (x_curr > x[begin])
                begin++;
            begin--;

            t = (x_curr - x[begin]) / (x[1] - x[0]);
            res = backward(t);
        }
        return String.valueOf(res);
    }

    private double forward(double t) {
        double res = y[begin] + t * delta(1);
        double ti = t;
        int iterator = 0;

        for (int i = begin + 1; i < n - 1; i++) {
            iterator++;
            ti *= (t - iterator);
            res += ti / fact(iterator + 1) * delta(iterator + 1);
        }
        return res;
    }

    private double backward(double t) {
        double res = y[begin] + t * delta(1, begin - 1);
        double ti = t;

        for (int i = 1; i < begin; i++) {
            ti *= (t + i);
            res += ti / fact(i + 1) * delta(i + 1, begin - i - 1);
        }
        return res;
    }


    private double delta(int q) {
        return calcCurrentDelta(q)[begin];
    }

    private double delta(int q, int p) {
        return calcCurrentDelta(q)[p];
    }

    private double[] calcCurrentDelta(int q) {
        double[] res = new double[n];
        if (q == 1) {
            res = new double[n];
            for (int i = 0; i < n - 1; i++) {
                res[i] = y[1 + i] - y[i];
            }
        } else {
            for (int i = 0; i < n - 1; i++) {
                double a = calcCurrentDelta(q - 1)[i + 1];
                double b = calcCurrentDelta(q - 1)[i];
                res[i] = a - b;
            }
        }
        return res;
    }

    int fact(int n) {
        if (n <= 1)
            return 1;
        else
            return n * fact(n - 1);
    }
}
