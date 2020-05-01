import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static double[] x = new double[7];
    static double[] y = new double[7];

    public static void main(String[] args) throws IOException {

        String answer;
        double x_curr;
        Scanner scanner = new Scanner(System.in);

        readFile("data_1.txt");

        System.out.println("Введите значение аргумента для м.Лагранжа: ");
        x_curr = scanner.nextDouble();
        answer = Lagrange.solve(x, y, x_curr);
        System.out.println("Ответ: " + answer);


        readFile("data_2.txt");

        System.out.println("Введите значение аргумента для м.Ньютона для равностоящих узлов: ");
        x_curr = scanner.nextDouble();
        NewtonEqualDiff newtonEqualDiff = new NewtonEqualDiff(x, y, x_curr);
        answer = newtonEqualDiff.solve();
        System.out.println("Ответ: " +answer);


        readFile("data_1.txt");

        System.out.println("Введите значение аргумента для м.Ньютона для НЕравностоящих узлов: ");
        x_curr = scanner.nextDouble();
        NewtonNotEqualDiff newtonNotEqualDiff = new NewtonNotEqualDiff(x, y, x_curr);
        answer = newtonNotEqualDiff.solve();
        System.out.println("Ответ: " +answer);
    }

    public static void readFile(String filename) throws IOException {
        String row;
        String[] data;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        int i = 0;
        while ((row = reader.readLine()) != null) {
            data = row.split(" ");
            x[i] = Double.parseDouble(data[0]);
            y[i] = Double.parseDouble(data[1]);
            i++;
        }
    }
}
