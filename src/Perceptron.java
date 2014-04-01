import java.util.Random;

/**
 * Created by minardus on 1-4-14.
 */
public class Perceptron {

    private int[][][] iMatrix = {
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

    private int[][] cMatrix = {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {1, 0, 0, 1},
            {0, 1, 1, 0},
    };

    private double[][] weights;
    private double learningRate = 0.2;
    private int learningIterations = 100;

    public Perceptron() {
        initWeights();
        train();
        print();
    }

    private void initWeights() {
        weights = new double[6][4];
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 4; j++) {
                weights[i][j] = Math.random();
                //System.out.println(String.format("w[%d][%d] == %f", i, j, weights[i][j]));
            }
        }
    }

    private void train() {
        for(int i = 0; i < learningIterations; i++) {
            int a = new Random().nextInt(iMatrix.length);
            int b = new Random().nextInt(iMatrix[0].length);
            int[] x = iMatrix[a][b];
            int[] expected = cMatrix[b];
            double result = 0;
            // dot
            int j;
            for(j = 0; j < 4; j++) {
                result += weights[a][j] * x[j];
            }
            for(j = 0; j < 4; j++) {
                double error = expected[j] - unit_step(result);
                weights[a][j] += learningRate * error * x[j];
            }
        }
    }

    private int unit_step(double n) {
        return n > 0 ? 1 : 0;
    }

    private void print() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 6; j++) {
                for(int k = 0; k < 4; k++) {
                    String output = String.format("[%d %d %d %d]: %f -> %d", iMatrix[i][j][0], iMatrix[i][j][1],
                            iMatrix[i][j][2], iMatrix[i][j][3], weights[j][k], cMatrix[j][k]);
                    System.out.println(output);
                }
            }
        }
    }

}
