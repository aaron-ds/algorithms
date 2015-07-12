import java.util.Comparator;

public class Point implements Comparable<Point> {


    public final Comparator<Point> SLOPE_ORDER = new SlopeComparator();       // compare points by slope to this point
    private final int x, y;

    public Point(int x, int y) {                      // construct the point (x, y)
        this.x = x;
        this.y = y;
    }

    public void draw() {                              // draw this point
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {                  // draw the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {                        // string representation
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {                // is this point lexicographically smaller than that point?
//        y0 < y1 or if y0 = y1 and x0 < x1
        if (y < that.y) return -1;
        if (y > that.y) return 1;
        if (x < that.x) return -1;
        if (x > that.x) return 1;
        return 0;
    }

    public double slopeTo(Point that) {               // the slope between this point and that point
        if (compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        if (y == that.y) return 0.0;
        if (x == that.x) return Double.POSITIVE_INFINITY;
        return (double)(that.y - y) / (that.x - x);
    }

    private class SlopeComparator implements Comparator<Point> {

        /*
         * The SLOPE_ORDER comparator should compare points by the slopes they make with the invoking point (x0, y0).
         * Formally, the point (x1, y1) is less than the point (x2, y2) if and only if the slope (y1 − y0) / (x1 − x0)
         * is less than the slope (y2 − y0) / (x2 − x0). Treat horizontal, vertical, and degenerate line segments as in
         * the slopeTo() method.
         */

        @Override
        public int compare(Point p1, Point p2) {

            if (compareTo(p1) < compareTo(p2)) return -1;
            return 1;
        }
    }
}
