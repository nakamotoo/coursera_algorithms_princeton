import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String a = StdIn.readString();
            q.enqueue(a);
        }
        for (int i = 0; i < k; i++) {
            String rtn = q.dequeue();
            System.out.print(rtn + "\n");
        }
    }
}
