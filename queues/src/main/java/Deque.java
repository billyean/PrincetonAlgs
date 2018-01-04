/******************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque << input.txt
 *
 * A double-ended queue or deque (pronounced “deck”) is a generalization of a
 * stack and a queue that supports adding and removing items from either the
 * front or the back of the data structure.
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    /**
     * Double linked list data structure.
     * @param <Item> generic type for the list hold.
     */
    private static class Node<Item> {
        /**
         * item value that node carries.
         */
        private Item item;

        /**
         * Previous node in the list.
         */
        private Node<Item> prev;

        /**
         * Next node in the list.
         */
        private Node<Item> next;

        /**
         * Constructor that initiate with given item.
         * @param item
         */
        Node(Item item) {
            this.item = item;
        }
    }

    /**
     * Double linked list head.
     */
    private Node<Item> head;

    /**
     * Double linked list tail.
     */
    private Node<Item> tail;

    /**
     * Number of elements in deque.
     */
    private int size;

    /**
     * An iterator that traverse the double linked list from begin to end.
     * This is not thread-safe.
     * @param <Item>
     */
    private static class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        DequeIterator(Node<Item> head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return current.item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    /**
     * construct an empty deque.
     */
    public Deque() {
        size = 0;
    }

    /**
     * is the deque empty.
     * @return true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * number of items on the deque.
     * @return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * add the item to the front.
     * @param item
     */
    public void addFirst(Item item) {
        if (null == item)   throw new IllegalArgumentException("Can't add null to deque");

        Node<Item> newHead = new Node<>(item);
        if (isEmpty()) {
            head = newHead;
            tail = newHead;
        }
        else {
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }
        size++;
    }

    /**
     * add the item to the end.
     * @param item
     */
    public void addLast(Item item) {
        if (null == item)   throw new IllegalArgumentException("Can't add null to deque");

        Node<Item> newTail = new Node<>(item);
        if (isEmpty()) {
            head = newTail;
            tail = newTail;
        }
        else {
            newTail.prev = tail;
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    /**
     * remove and return the item from the front.
     * @return an element in first position, null if the deque is empty.
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<Item> r = head;
        if (head == tail) {
            head = null;
            tail = null;
        }
        else {
            head = r.next;
            head.prev = null;
            r.next = null;
        }
        size--;
        return r.item;
    }

    /**
     * remove and return the item from the end
     * @return
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<Item> r = tail;
        if (head == tail) {
            head = null;
            tail = null;
        }
        else {
            tail = r.prev;
            tail.next = null;
            r.prev = null;
        }
        size--;
        return r.item;
    }

    /**
     * return an iterator over items in order from front to end.
     * @return
     */
    public Iterator<Item> iterator()  {
        return new DequeIterator<>(head);
    }

    /**
     * unit testing (optional)
     * @param args
     */
    public static void main(String[] args)  {
    }
}
