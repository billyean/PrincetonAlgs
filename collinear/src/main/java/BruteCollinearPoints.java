/******************************************************************************
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java BruteCollinearPoints input
 *
 *  A brute-force algorithm to calculate all collinear points. It examines
 *  4 points at a time and checks whether they all lie on the same line
 *  segment, returning all such line segments. To check whether the 4 points
 *  p, q, r, and s are collinear, check whether the three slopes between p
 *  and q, between p and r, and between p and s are all equal..
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BruteCollinearPoints {
    /**
     * List of 4 points line segment.
     */
    private final List<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length - 3; i++) {
            Point p = points[i];
            for (int j = i + 1; j < points.length - 2; j++) {
                Point q = points[j];
                for (int k = j + 1; k < points.length - 1; k++) {
                    Point r = points[k];
                    for (int m = k + 1; m < points.length; m++) {
                        Point s = points[m];
                        Comparator<Point> comp = p.slopeOrder();
                        if (comp.compare(q, r) == 0
                                && comp.compare(q, s) == 0) {
                            segments.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
    }

    /**
     * Number of 4 points line segment.
     * @return
     */
    public int numberOfSegments() {
        return segments.size();
    }

    /**
     * Instance of all 4 points line segment.
     * @return all 4 points line segment.
     */
    public LineSegment[] segments() {
        return segments.stream().toArray(LineSegment[]::new);
    }

    /**
     * Test Client
     * @param args
     */
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        Stopwatch sw = new Stopwatch();
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.printf("Brute Force Collinear implementation takes %f seconds\n", sw.elapsedTime());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
