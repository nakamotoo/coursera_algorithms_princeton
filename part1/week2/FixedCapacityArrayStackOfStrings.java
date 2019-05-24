public class FixedCapacityArrayStackOfStrings {

    private String[] s;
    private int N = 0;

    public FixedCapacityArrayStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        s[N++] = item;
    }

    // Nをdecrementしているだけで、itemが本当に配列から削除されたわけではない。
    public String pop() {
        return s[--N];
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        FixedCapacityArrayStackOfStrings stack = new FixedCapacityArrayStackOfStrings(n);
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