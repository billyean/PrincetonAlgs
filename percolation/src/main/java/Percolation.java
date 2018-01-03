/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    java Percolation n
 *
 * A composite system that quick calculate if the system is percolated.
 * This class is using Weighted Quick Union Find algorithm to look up if two
 * sites are connected. For quick find if the system is percolated. one special
 * site is introduced as first site above of all sites.
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /**
     * Moves array makes calculate up, bottom, left, right site easier.
     */
    private static final int[][] MOVES = new int[][]{
            {-1, 0},
            {0, -1},
            {1,  0},
            {0,  1},
    };

    /**
     * Instance for Weighted Quick Union Find.
     */
    private final WeightedQuickUnionUF uf1;


    /**
     * Instance for Weighted Quick Union Find.
     */
    private final WeightedQuickUnionUF uf2;

    /**
     * Grid size.
     */
    private final int dimension;

    /**
     * Array records if the site is open.
     */
    private final boolean[] open;

    /**
     * Total open sites number.
     */
    private int openSites = 0;

    /**
     * A cache flag records if system percolated.
     * When percolated calls, if this flag is false, will check if system
     * percolated again. Otherwise will return this flag.
     */
    private boolean percolate = false;

    /**
     * create n-by-n grid, with all sites blocked.
     * @param n grid number.
     */
    public Percolation(final int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n should be a positive number");
        this.dimension = n;
        uf1 = new WeightedQuickUnionUF(n * n + 1);
        uf2 = new WeightedQuickUnionUF(n * n + 2);
        open = new boolean[n * n];
    }

    /**
     * open site (row, col) if it is not open already.
     * @param row site row number.
     * @param col site column number.
     */
    public void open(final int row, final int col)  {
        if (row < 1 || row > dimension)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (col < 1 || col > dimension)
            throw new IndexOutOfBoundsException("col index i out of bounds");
        int r = row - 1, c = col - 1;
        int p = r * dimension + c;

        if (!open[p]) {
            open[p] = true;
            for (int[] move : MOVES) {
                int mr = r + move[0], mc = c + move[1];
                int mp = mr * dimension + mc;
                int up = p + 1;
                if (mr >= 0 && mr < dimension && mc >= 0 && mc < dimension) {
                    if (open[mp]) {
                        uf1.union(up, mp + 1);
                        uf2.union(up, mp + 1);
                    }
                }
                else {
                    if (mr == -1) {
                        uf1.union(up, 0);
                        uf2.union(up, 0);
                    }
                    else if (mr == dimension) {
                        uf2.union(up, dimension * dimension + 1);
                    }
                }
            }
            openSites++;
        }
    }

    /**
     * Check is site (row, col) open.
     * @param row row number that's checked if the site is open
     * @param col column number that's checked if the site is open
     * @return true if site is open by given row and column
     */
    public boolean isOpen(final int row, final int col) {
        if (row < 1 || row > dimension)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (col < 1 || col > dimension)
            throw new IndexOutOfBoundsException("col index i out of bounds");
        int r = row - 1, c = col - 1;
        int p = r * dimension + c;

        return open[p];
    }

    /**
     * is site (row, col) full?
     * @param row row number that's checked if the site is full
     * @param col column number that's checked if the site is full
     * @return true if site is full by given row and column
     */
    public boolean isFull(final int row, final int col)  {
        if (row < 1 || row > dimension)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (col < 1 || col > dimension)
            throw new IndexOutOfBoundsException("col index i out of bounds");
        int r = row - 1, c = col - 1;
        int p = r * dimension + c;

        return uf1.connected(p + 1, 0);
    }

    /**
     * Calculate number of open sites.
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * does the system percolate.
     * @return true if sites are system percolate, otherwise return false.
     */
    public boolean percolates() {
        if (!percolate) {
            percolate = uf2.connected(0, dimension * dimension + 1);
        }
        return percolate;
    }
}
