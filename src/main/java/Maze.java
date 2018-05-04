import java.util.Arrays;

/**
 * Class for storing maze matrix and allow to easily manipulate it
 */
public class Maze implements Cloneable{

    private int[][] mazeMatrix;

    private int xSize;

    private int ySize;

    private Maze(int[][] matrix, int xSizeVal, int ySizeVal) {
        xSize = xSizeVal;
        ySize = ySizeVal;
        mazeMatrix = matrix;
    }

    public Maze(int xSizeVal, int ySizeVal) {
        xSize = xSizeVal;
        ySize = ySizeVal;
        mazeMatrix = new int[ySize][xSize];
    }

    public final void setLine(int y, int[] val) {
        mazeMatrix[y] = val;
    }

    public final void set(int x, int y, int val) {
        mazeMatrix[y][x] = val;
    }

    public final void set(Point point, int val) {
        mazeMatrix[point.getY()][point.getX()] = val;
    }

    public final int get(int x, int y) {
        return mazeMatrix[y][x];
    }

    public final int get(Point point) {
        return mazeMatrix[point.getY()][point.getX()];
    }

    public final int getXSize() {
        return xSize;
    }

    public final int getYSize() {
        return ySize;
    }

    @Override
    public Maze clone() {
        int[][] dst = new int[mazeMatrix.length][];
        for (int i = 0; i < mazeMatrix.length; i++) {
            dst[i] = Arrays.copyOf(mazeMatrix[i], mazeMatrix[i].length);
        }
        return new Maze(dst, xSize, ySize);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (mazeMatrix[y][x] < 0) {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
