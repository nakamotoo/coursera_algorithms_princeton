import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;git

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;  // 最初と最後のノードを記録
    private Node last = null;
    private int N = 0; // size of Deque

    private class Node {
        Item item;
        Node pre;
        Node next;
    }

    // construcst an empty deque
    public Deque() {

    }

    // is the deque empty ?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item should not be null");
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.pre = null;
        if (N == 0) last = first;
        else if (oldfirst != null) oldfirst.pre = first;
        N++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item should not be null");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.pre = oldlast;
        N++;
        if (isEmpty()) first = last;
        else if (oldlast != null) oldlast.next = last;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Deque is empty!!");
        }
        Item item = first.item;
        // firstを一個進める
        first = first.next;
        first.pre = null;
        N--;
        if (isEmpty()) last = null;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Deque is empty!!");
        }
        Item item = last.item;
        if (N == 1) {
            last = null;
            first = null;
            N--;
            return item;
        }

        last = last.pre;
        last.next = null;
        N--;
        if (N == 1) first = last;
        return item;
    }

    // return an iterator over items in order form front to end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("remove() is unsupported...");
        }

        public Item next() {
            if (current.next == null) {
                throw new java.util.NoSuchElementException("no more items to return...");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        System.out.print(d.isEmpty() + "\n");
        d.addFirst("a");
        d.addFirst("b");
        d.addLast("c");
        System.out.print(d.isEmpty() + "\n");
        System.out.print(d.size() + "\n");
        System.out.print(d.last.item + "\n");
        for (String s : d) StdOut.println(s);
//        System.out.print(d.removeFirst() + "\n");
//        System.out.print(d.removeLast() + "\n");
//        System.out.print(d.removeLast() + "\n");
//        System.out.print(d.isEmpty() + "\n");
//        System.out.print(d.size());

    }
}
