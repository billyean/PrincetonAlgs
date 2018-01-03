/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats n trails
 *
 * A percolation statistics class.
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    /**
     * Default percolation system grid number.
     */
    private static final int DEFAULT_N = 100;

    /**
     * Default trails number.
     */
    private static final int DEFAULT_TRIALS = 200;


    private static final double CONFIDENT_PERCENTAGE = 1.96;
    /**
     * Percolation thresholds array for n times of run.
     */
    private final double[] times;

    /** perform trials independent experiments on an n-by-n grid.
     *
     * @param n number of sites
     * @param trials round number that percolation system will be run.
     */
    public PercolationStats(int n, int trials)  {
        times = new double[trials];

        for (int i = 0; i < trials; i++) {
            int threshold = timesForPercolation(n);
            times[i] = threshold / (double) (n * n);
        }
    }

    /**
     * One run for percolation by given site number.
     * @param n grid row and columns
     * @return open times on system before it gets percolated.
     */
    private int timesForPercolation(final int n) {
        Percolation percolation = new Percolation(n);
        int n2 = n * n;
        int[] sites = new int[n2];
        for (int i = 0; i < n2; i++) {
            sites[i] = i;
        }

        int nt = 0;

        while (!percolation.percolates()) {
            int v = StdRandom.uniform(n2 - nt);
            int site = sites[v];
            int r = site / n + 1, c = site % n + 1;
            percolation.open(r, c);
            int last = n2 - nt - 1;
            if (v != last) {
                int t = sites[v];
                sites[v] = sites[last];
                sites[last] = t;
            }

            nt++;
        }

        return nt;
    }

    /**
     * sample mean of percolation threshold.
     *
     * @return sample mean of percolation threshold.
     */
    public double mean() {
        return StdStats.mean(times);
    }

    /**
     * sample standard deviation of percolation threshold.
     *
     * @return sample standard deviation of percolation threshold.
     */
    public double stddev() {
        return StdStats.stddev(times);
    }

    /**
     * low  endpoint of 95% confidence interval.
     * @return low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        double x = mean(), s = stddev();
        return x - CONFIDENT_PERCENTAGE * s / Math.sqrt(times.length);
    }

    /**
     * high endpoint of 95% confidence interval.
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        double x = mean(), s = stddev();
        return x + CONFIDENT_PERCENTAGE * s / Math.sqrt(times.length);
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = DEFAULT_N, t = DEFAULT_TRIALS;

        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        if (args.length > 1) {
            t = Integer.parseInt(args[1]);
        }

        Stopwatch sw = new Stopwatch();
        PercolationStats ps = new PercolationStats(n, t);
        System.out.printf("Elapsed time = %f\n", sw.elapsedTime());
        System.out.printf("mean                    = %f\n", ps.mean());
        System.out.printf("stddev                  = %f\n", ps.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());

    }
}
