import java.util.*;
import java.util.stream.Collectors;

public class LeePathFinder implements PathFinder {

    private Set<Point> wavePoints;

    private int waveNumber;

    private Maze matrix;

    private int maxX = 0;

    private int maxY = 0;

    /**
     * Find path for maze matrix using Lee algorithm
     * @param matrixVal
     * @param start
     * @param end
     * @param size
     * @return
     * @throws MatrixHasNoSolution
     */
    @Override
    public List<Point> findPath(Maze matrixVal, Point start, Point end, Point size) throws MatrixHasNoSolution {
        maxY = size.getY() - 1;
        maxX = size.getX() - 1;
        wavePoints = Collections.singleton(start);
        waveNumber = 1;
        matrix = matrixVal.clone();

        // Set initial cost
        matrix.set(start, waveNumber);

        //While we not mark END point we continye to spread waves
        while (!wavePoints.contains(end)) {
            // If no Points in next wave maze haven't got solution
            if (wavePoints.isEmpty()) {
                throw new MatrixHasNoSolution();
            }
            // generate next wave
            updateWave(getNextWave());
        }

        // After filling matrix till the end point, build result path going from the end point to start by tiles number
        return buildPath(end);
    }

    /**
     * After we mark END Point, we build path collections points by their wave number from END to START
     * @param end
     * @return
     */
    private List<Point>  buildPath(Point end) {
        LinkedList<Point> path = new LinkedList<>();
        int nextPointCost = matrix.get(end) - 1;
        path.add(end);

        while (nextPointCost != 0) {
            path.add(getNeighbour(path.getLast(), nextPointCost).iterator().next());
            nextPointCost--;
        }

        Collections.reverse(path);

        return path;
    }

    private void updateWave(Set<Point> nextWave) {
        wavePoints = nextWave;
        waveNumber++;
        nextWave.stream().forEach(point -> {
            matrix.set(point, waveNumber);
        });
    }

    /**
     * Get next wave (collecting all neighbour of previous wave's points which avalable for moving)
     * @return
     */
    private Set<Point> getNextWave() {
        return wavePoints.stream()
            .flatMap(point -> getNeighbour(point, 0).stream())
            .collect(Collectors.toSet());
    }

    /**
     * Get all neighbours for point which available for moving.
     * Check left, top, right, bottom direction
     * @param target
     * @param pointMarker - flag of target point
     * @return
     */
    private Set<Point> getNeighbour(Point target, int pointMarker) {
        Set<Point> result = new HashSet<>();

        Point leftPoint = new Point(target.getX() - 1, target.getY());
        if (leftPoint.getX() >= 0 && matrix.get(leftPoint) == pointMarker) {
            result.add(leftPoint);
        }

        Point topPoint = new Point(target.getX(), target.getY() - 1);
        if (topPoint.getY() >= 0 && matrix.get(topPoint) == pointMarker) {
            result.add(topPoint);
        }

        Point rightPoint = new Point(target.getX() + 1, target.getY());
        if (rightPoint.getX() < maxX && matrix.get(rightPoint) == pointMarker) {
            result.add(rightPoint);
        }

        Point bottomPoint = new Point(target.getX(), target.getY() + 1);
        if (bottomPoint.getY() < maxY && matrix.get(bottomPoint) == pointMarker) {
            result.add(bottomPoint);
        }

        return result;
    }


}
