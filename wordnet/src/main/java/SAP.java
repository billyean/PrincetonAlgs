import edu.princeton.cs.algs4.Digraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/******************************************************************************
 *  Compilation:  javac SAP.java
 *  Execution:    java SAP filename
 *  Dependencies: PercolationVisualizer.java Percolation.java
 *                StdDraw.java StdOut.java
 *
 ******************************************************************************/

public class SAP {
    private Digraph g;


    private Map<Integer, Integer>[] ancestors;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.g = G;


    }

    private void bfs() {
        int e = g.E();

        boolean[] visited = new boolean[e];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < e; i++) {
            if (!visited[i]) {
                visited[i] = 
            }
        }
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        Map<Integer, Integer> va = ancestors[v];
        Map<Integer, Integer> wa = ancestors[w];

        int len = ancestors.length + 1;
        for (Map.Entry<Integer, Integer> e : va.entrySet()) {
            int an = e.getKey();
            int level = e.getValue();
            if (wa.containsKey(an)) {
                len = Math.min(len, level + wa.get(an)) ;
            }
        }

        return len == ancestors.length + 1 ? -1 : len;
    }

    // a common ancestor of v and w that participates in a shortest ancestral
    // path; -1 if no such path
    public int ancestor(int v, int w) {
        Map<Integer, Integer> va = ancestors[v];
        Map<Integer, Integer> wa = ancestors[w];

        int len = ancestors.length + 1;
        int ancestor = -1;
        for (Map.Entry<Integer, Integer> e : va.entrySet()) {
            int an = e.getKey();
            int level = e.getValue();
            if (wa.containsKey(an)) {
                int nlevel = level + wa.get(an);
                if (nlevel < len) {
                    len = nlevel;
                    ancestor = e.getKey();
                }
            }
        }
        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        Map<Integer, Integer> vas = new HashMap<>();
        Map<Integer, Integer> was = new HashMap<>();

        for (int i: v) {
            Map<Integer, Integer> va = ancestors[i];
            for (Map.Entry<Integer, Integer> e : va.entrySet()) {
                int an = e.getKey();
                int level = e.getValue();
                if (vas.containsKey(an)) {
                    int m = vas.get(an);
                    vas.put(e.getKey(), Math.min(level, m));
                }
            }
        }
        for (int i: w) {
            Map<Integer, Integer> wa = ancestors[i];
            for (Map.Entry<Integer, Integer> e : wa.entrySet()) {
                int an = e.getKey();
                int level = e.getValue();
                if (was.containsKey(an)) {
                    int m = was.get(an);
                    was.put(e.getKey(), Math.min(level, m));
                }
            }
        }

        int len = ancestors.length + 1;
        for (Map.Entry<Integer, Integer> e : vas.entrySet()) {
            if (was.containsKey(e.getKey())) {
                len = Math.min(len, e.getValue() + was.get(e.getKey())) ;
            }
        }

        return len == ancestors.length + 1 ? -1 : len;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        Map<Integer, Integer> vas = new HashMap<>();
        Map<Integer, Integer> was = new HashMap<>();

        for (int i: v) {
            Map<Integer, Integer> va = ancestors[i];
            for (Map.Entry<Integer, Integer> e : va.entrySet()) {
                int an = e.getKey();
                int level = e.getValue();
                if (vas.containsKey(an)) {
                    int m = vas.get(an);
                    vas.put(e.getKey(), Math.min(level, m));
                }
            }
        }
        for (int i: w) {
            Map<Integer, Integer> wa = ancestors[i];
            for (Map.Entry<Integer, Integer> e : wa.entrySet()) {
                int an = e.getKey();
                int level = e.getValue();
                if (was.containsKey(an)) {
                    int m = was.get(an);
                    was.put(e.getKey(), Math.min(level, m));
                }
            }
        }

        int len = ancestors.length + 1;
        int ancestor = -1;
        for (Map.Entry<Integer, Integer> e : vas.entrySet()) {
            int an = e.getKey();
            int level = e.getValue();
            if (was.containsKey(an)) {
                int nlevel = level + was.get(an);
                if (nlevel < len) {
                    len = nlevel;
                    ancestor = e.getKey();
                }
            }
        }

        return ancestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
