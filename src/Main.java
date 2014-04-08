/**
 * Created by minardus on 1-4-14.
 */
public class Main {

    private static int[][] testData = {
            {1, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
    };

    public static void main(String[] args) {
        Perceptron p = new Perceptron();
        p.train(p.iMatrix[0], true);
    }

}
