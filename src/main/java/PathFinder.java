import java.util.List;

public interface PathFinder {

    public List<Point> findPath(Maze matrix, Point start, Point end, Point size) throws MatrixHasNoSolution;

}
