// FixedCapacityArrayStackOfStringsの改善版

public class ResizingArrayStackOfStrings {

    private String[] s;
    private int N = 0;

    public ResizingArrayStackOfStrings() {
        s = new String[1]; // capacityの指定がいらなくなって、最初はサイズ１の配列を作ればよい！
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public void push(String item) {
        // 配列が満タンになったらサイズを２倍に拡張する
        if (N == s.length)
            resize(2 * s.length);
        s[N++] = item;
    }

    public String pop() {
        String item = s[--N];
        s[N] = null;
        return item;
    }

    public static void main(String[] args) {
      
        ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();
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
