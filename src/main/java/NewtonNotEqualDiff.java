public class NewtonNotEqualDiff {

    private double x_curr;
    private double[] x;
    private double[] y;

    private double[] node_x;
    private double[] node_y;
    private double res;
    private int size;

    public NewtonNotEqualDiff(double[] x, double[] y, double x_curr) {
        this.x = x;
        this.y = y;
        this.size = x.length;
        this.x_curr = x_curr;
    }

    public String solve() {
        for (int i = 0; i < size; i++) {
            if (x_curr < x[size - 1] && x_curr > x[size - 1 - 1] || x_curr > x[i] && (size - 1 - i) < 3) {
                for (int j = 1; j >= 0; j--) {
                    node_x = new double[]{x[size - 1 - 2 - j], x[size - 1 - 1 - j], x[size - 1 - j]};
                    node_y = new double[]{y[size - 1 - 2 - j], y[size - 1 - 1 - j], y[size - 1 - j]};
                    res += calculate();
                }
                break;
            } else if (x_curr > x[0] && x_curr < x[1] || x_curr > x[i] && (size - 1 - i) >= 3) {
                for (int j = 0; j <= 1; j++) {
                    node_x = new double[]{x[i + j], x[i + j + 1], x[i + j + 2]};
                    node_y = new double[]{y[i + j], y[i + j + 1], y[i + j + 2]};
                    res += calculate();
                }
                break;
            }
        }
        return String.valueOf(res / 2);
    }

    private double calculate() {
        return node_y[0] + f2(0, 1) * (x_curr - node_x[0]) + f3(0, 1, 2) * (x_curr - node_x[0]) * (x_curr - node_x[1]);
    }

    private double f2(int a, int b) {
        return (node_y[b] - node_y[a]) / (node_x[b] - node_x[a]);
    }

    private double f3(int a, int b, int c) {
        return (f2(b, c) - f2(a, b)) / (node_x[c] - node_x[a]);
    }
}