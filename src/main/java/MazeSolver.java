import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeSolver {

    private List<Point> path = new ArrayList<>();

    private Maze mazeMatrix;

    private Point start;

    private Point end;

    private Point size;

    private String fileName;

    public MazeSolver(String fileName) throws Exception {
        this.fileName = fileName;
        parseFile(fileName);
    }

    public final void findPath() {
        try {
            LeePathFinder pathFinder = new LeePathFinder();
            path = pathFinder.findPath(mazeMatrix, start, end, size);
        } catch (MatrixHasNoSolution e) {
            System.out.print("Matrix has no solution");
            System.exit(100);
        }

    }

    /**
     * Build visual matrix from solution
     * @throws Exception
     */
    public final void saveResult() throws Exception {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < mazeMatrix.getYSize(); y++) {
            for (int x = 0; x < mazeMatrix.getXSize(); x++) {
                if (isStartPathCoordinate(x, y)) {
                    result.append("S");
                } else if (isEndPathCoordinate(x, y)) {
                    result.append("E");
                } else if (isPathCoordinate(x, y)) {
                    result.append("x");
                } else if (mazeMatrix.get(x, y) == -1) {
                    result.append("#");
                } else if (mazeMatrix.get(x, y) == 0) {
                    result.append(" ");
                }
            }
            result.append("\n");
        }
        System.out.print(result.toString());
        try (PrintWriter out = new PrintWriter(fileName + ".out.txt")) {
            out.println(result.toString());
        }
    }

    /**
     * Point with x and y contains in solution
     * @param x
     * @param y
     * @return
     */
    private boolean isPathCoordinate(int x, int y) {
        return path.stream()
            .anyMatch(point -> point.equals(x, y));
    }

    /**
     * Point with x and y is start of path
     * @param x
     * @param y
     * @return
     */
    private boolean isStartPathCoordinate(int x, int y) {
        return path.get(0).equals(x, y);
    }

    /**
     * Point with x and y is end of path
     * @param x
     * @param y
     * @return
     */
    private boolean isEndPathCoordinate(int x, int y) {
        return path.get(path.size() - 1).equals(x, y);
    }

    /**
     * Parse input file
     * @param file
     * @throws Exception
     */
    private void parseFile(String file) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                switch (lineNumber) {
                    case 0:
                        // First line is matrix size
                        size = Point.fromString(line);
                        mazeMatrix = new Maze(size.getX(), size.getY());
                        break;
                    case 1:
                        // second line is start point
                        start = Point.fromString(line);
                        break;
                    case 2:
                        // third line is end point
                        end = Point.fromString(line);
                        break;
                    default:
                        // parse file line by line until matrix y size ned
                        if (lineNumber - 3 >= size.getY()) {
                            break;
                        }
                        fillMatrixLine(line, lineNumber - 3);
                        break;
                }
                lineNumber++;
            }
        }
    }

    /**
     * Parse string from file to matrix line
     * replase all tiels with 1 to -1
     * @param line
     * @param lineNumber
     */
    private void fillMatrixLine(String line, int lineNumber) {
        String[] values = line.split(" ");
        int[] valuesLine = Arrays.stream(values)
            .map(Integer::parseInt)
            .map(val -> val == 1 ? -1 : 0)
            .mapToInt(x -> x)
            .toArray();
        mazeMatrix.setLine(lineNumber, valuesLine);
    }

}
