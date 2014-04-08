import java.util.Random;

/**
 * Created by minardus on 1-4-14.
 */
public class Perceptron {

    public int[][][] iMatrix = {
            {
                    {1, 0, 0, 0},
                    {0, 0, 0, 0},
                    {1, 0, 0, 0},
                    {1, 0, 0, 0},
                    {1, 0, 0, 0},
                    {1, 0, 0, 0},
            },
            {
                    {0, 1, 0, 0},
                    {0, 0, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
            },
            {
                    {0, 0, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
            },
            {
                    {0, 0, 0, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 1},
                    {0, 0, 0, 1},
                    {0, 0, 0, 1},
                    {0, 0, 0, 1},
            }
    };

    public int[][] cMatrix = {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {1, 0, 0, 1},
            {0, 1, 1, 0},
    };

    private double[][] weights;
    private double learningRate = 0.01f;
    private int learningIterations = 100;

    public Perceptron() {
        initWeights();
    }

    /* initialize weights random between 0 and 1, seems to be good practice */
    private void initWeights() {
        weights = new double[6][4];
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 4; j++)
                weights[i][j] = 0;
    }

    /*
    private int sign(int[][] matrix, int i, int j) {
        return (matrix[i][j] * weights[i][j]) > 0 ? 1 : 0;
    }
    */

    private int sign(int[][] matrix) {
        double y = 0.0;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 4; j++) {
                y += matrix[i][j] * weights[i][j];
            }
        }
        y--;
        System.out.println("sum: " + y);
        return y > 0 ? 1 : 0;
    }

    public void train(int[][] matrix, boolean type) {
        int expected = type ? 2 : 0;
        int counter = 0;
        for(int h = 0; h < learningIterations; h++) {
            int signed = sign(matrix);
            if(expected-1 == signed)
                break;
            for(int i = 0; i < 6; i++) {
                for(int j = 0; j < 4; j++) {
                    int x = matrix[i][j];
                    int error = expected - signed;
                    System.out.println("error: " + error + "\tsigned: " + signed);
                    weights[i][j] += learningRate * error * x;
                }
            }
            counter++;
        }
        System.out.println("counter: " + counter);
        print();
    }

    private void print() {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(String.format("%.2f " + (j == 3 ? "\n" : " "), weights[i][j]));
            }
        }
    }

}
