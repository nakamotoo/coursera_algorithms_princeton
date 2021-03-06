// last in, first out

public class LinkedStackOfStrings {
    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

    public static void main(String[] args) {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();
        System.out.print(stack.isEmpty() + "\n");
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.print(stack.isEmpty() + "\n");
        System.out.print(stack.pop() + "\n");
        System.out.print(stack.pop() + "\n");
        System.out.print(stack.pop() + "\n");
        System.out.print(stack.isEmpty() + "\n");
    }
}
