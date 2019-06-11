import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int N = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    //return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // resize array
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("argument is null!!");
        }
        if (N == s.length)
            resize(2 * s.length);
        s[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("queue is empty...");
        }
        int n = StdRandom.uniform(N);
        Item[] new_s = (Item[]) new Object[s.length];
        for (int i = 0; i < n; i++) {
            new_s[i] = s[i];
        }
        if (n < s.length - 1) {
            for (int j = n + 1; j < N; j++) {
                new_s[j - 1] = s[j];
            }
        }
        Item rtn = s[n];
        s = new_s;
        N--;
        return rtn;
    }

    // return a random item(but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("queue is empty...");
        }
        int n = StdRandom.uniform(N);
        return s[n];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = N;

        StdRandom.shuffle(s);

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("remove() is unsupported...");
        }

        public Item next() {
            if (i == 0) {
                throw new java.util.NoSuchElementException("no more items to return...");
            }
            return s[--i];
        }
    }

    public static void main(String[] args) {

    }
}
