// capacity Nをきめないといけないのが欠点

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

    // Nをdecrementしているだけで、s[N-1]というitemが本当に配列から削除されたわけではない。(loitering)
    // public String pop() {
    // return s[--N];
    // }

    // loiteringの改善ver
    public String pop() {
        String item = s[--N];
        S[N] = null;
        return item;
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