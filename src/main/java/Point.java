import java.util.Objects;

/**
 * Point in maze matrix with x, y coordinates
 */
public class Point {

    /**
     * Read string  like "12 4", two digits with space
     * where first digits is x and second is y
     * @param input
     */
    public static Point fromString(String input) {
        String[] coords = input.split(" ");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
        return new Point(x, y);
    }

    private int x;

    private int y;

    public Point(int xVal, int yVal) {
        x = xVal;
        y = yVal;
    }

    public boolean equals(int xVal, int yVal) {
        return (x == xVal) && (y == yVal);
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() { return x; }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
