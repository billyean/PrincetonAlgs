/******************************************************************************
 *  Compilation:  javac Permutation.java
 *  Execution:    java Permutation number << input.txt
 *
 * A test client that takes an integer k as a command-line argument; reads in a
 * sequence of strings from standard input using StdIn.readString(); and prints
 * exactly k of them, uniformly at random. Print each item from the sequence at
 * most once.
 *
 * This test client is using
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
    /**
     * Main method to run the program.
     * @param args
     */
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        Iterator<String> iter = rq.iterator();
        int i = 0;
        while (iter.hasNext() && i++ < k) {
            StdOut.println(iter.next());
        }
    }
}
