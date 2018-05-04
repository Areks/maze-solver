public class Application {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("No file path provided!");
            }
            String filePath = args[0];
            MazeSolver mazeSolverSolver = new MazeSolver(filePath);
            mazeSolverSolver.findPath();
            mazeSolverSolver.saveResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
