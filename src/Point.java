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
        //divide by 0
        return (double)(that.y - y) / (that.x - x);
    }

    private static class SlopeComparator implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            return 0;
        }
    }
}
