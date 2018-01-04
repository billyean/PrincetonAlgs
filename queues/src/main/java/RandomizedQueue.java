/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue << input.txt
 *
 * A randomized queue is similar to a stack or queue, except that the item
 * removed is chosen uniformly at random from items in the data structure.
 * This queue also provides a random order iterator to traverse on the
 * carrying elements.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    /**
     * DEFAULT CAPACITY, set as 16 to avoid grow for first 16 elements.
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Array hold all items.
     */
    private Item[] elements;

    /**
     * Current queue size.
     */
    private int size;

    /**
     * construct an empty randomized queue.
     */
    public RandomizedQueue() {
        elements = (Item[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * is the randomized queue empty
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the randomized queue.
     * @return return number of items on the randomized queue
     */
    public int size() {
        return size;
    }

    /**
     * add the item. Check if need grow the array if necessary.
     * @param item element added to the queue.
     */
    public void enqueue(Item item) {
        checkGrow();
        elements[size++] = item;
    }

    /**
     * remove and return a random item.
     * @return uniformly picked element in the queue.
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        checkShrink();

        int m = StdRandom.uniform(size);
        int last = size - 1;
        if (m != last) {
            Item t = elements[m];
            elements[m] = elements[last];
            elements[last] = t;
        }
        Item r = elements[size - 1];
        elements[--size] = null;
        return r;
    }

    /**
     * return a random item.
     * @return uniformly picked element in the queue
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[StdRandom.uniform(size)];
    }

    /**
     * Inner class supports random access element.
     * @param <Item>
     */
    private static class RandomizedQueueIterator<Item> implements Iterator<Item> {
        private Item[] copied;

        private int current;

        RandomizedQueueIterator(Item[] elements, int size) {
            copied = (Item[]) new Object[size];
            System.arraycopy(elements, 0, copied, 0, size);
            StdRandom.shuffle(copied);
            current = size;
        }

        @Override
        public boolean hasNext() {
            return current != 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copied[--current];
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    /**
     * return an independent iterator over items in random order.
     * @return
     */
    public Iterator<Item> iterator()  {
        return new RandomizedQueueIterator<>(elements, size);
    }

    public static void main(String[] args) {

    }

    /**
     * Double the size if size has reached array's length
     */
    private void checkGrow() {
        if (size == elements.length) {
            Item[] delements = (Item[]) new Object[elements.length << 1];
            System.arraycopy(elements, 0, delements, 0, size);
            elements = delements;
        }
    }

    /**
     * Double the size if size has reached array's length
     */
    private void checkShrink() {
        if (size <= elements.length >> 2) {
            Item[] delements = (Item[]) new Object[elements.length >> 1];
            System.arraycopy(elements, 0, delements, 0, size);
            elements = delements;
        }
    }
}
