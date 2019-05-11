/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {
    private int[] id;
    private int[] sz;
    private boolean[] op;
    private boolean[] full;
    private int N;

    // create n-by-n grid with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new java.lang.IllegalArgumentException("n must be larger than 0");
        id = new int[n * n + 1];
        sz = new int[n * n + 1];
        op = new boolean[n * n + 1];
        N = n;

        if (n == 1) {
            id[0] = 0;
            id[1] = 0;
            op[1] = false;
        }
        else {
            for (int i = 0; i <= n; i++) {
                id[i] = 0;
                sz[i] = n + 1;
                op[i] = false;
            }
            for (int i = n + 1; i <= n * n; i++) {
                id[i] = i;
                sz[i] = 1;
                op[i] = false;
            }
        }
    }

    private int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    private void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (i * j == 0) {
            id[i + j] = 0;
            sz[0] += sz[i + j];
        }
        else if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    // open site(row, col) if it is not open already
    public void open(int row, int col) {
        int num = N * (row - 1) + col;
        if (row < 0 || col < 0 || row > N || col > N) {
            throw new java.lang.IllegalArgumentException("Argument of open() is out of range.");
        }
        else {
            int left = num - 1;
            int right = num + 1;
            int above = N * (row - 2) + col;
            int below = N * row + col;
            op[num] = true;
            if (left > 0 && left <= N * N && op[left] && col != 1) union(num, left);
            if (right > 0 && right <= N * N && op[right] && col != N) union(num, right);
            if (above > 0 && above <= N * N && op[above] && row != 1) union(num, above);
            if (below > 0 && below <= N * N && op[below] && row != N) union(num, below);
        }
    }

    // is site(row, col) open?
    public boolean isOpen(int row, int col) {
        int num = N * (row - 1) + col;
        if (row < 0 || col < 0 || row > N || col > N) {
            throw new java.lang.IllegalArgumentException("Argument of isOpen() is out of range.");
        }
        else {
            return op[num];
        }
    }

    // is site(row, col) full?
    public boolean isFull(int row, int col) {
        int num = N * (row - 1) + col;
        if (row < 0 || col < 0 || row > N || col > N) {
            throw new java.lang.IllegalArgumentException("Argument of isFull() is out of range.");
        }
        else {
            return op[num] && this.root(num) == 0;
        }
    }

    // number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 1; i <= op.length - 1; i++) {
            if (op[i]) count++;
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        if (N == 1) {
            return op[1];
        }
        else {
            // search bottom row nodes' id
            boolean p = false;
            for (int i = N * (N - 1) + 1; i <= N * N; i++) {
                if (root(i) == 0) {
                    p = true;
                    break;
                }
            }
            return p;
        }
    }

    // debug
    // protected void printMatrix() {
    //     System.out.println("--------------------- mat ---------------------");
    //     for (int i = 1; i <= N; i++) {
    //         for (int j = 1; j <= N; j++) {
    //             String str = !isOpen(i, j) ? "X" : (isFull(i, j) ? "F" : "O");
    //             System.out.print(str);
    //         }
    //         System.out.println("");
    //     }
    //
    //     System.out.println("-----------------------------------------------");
    // }

    public static void main(String[] args) {
        // int n = Integer.parseInt(args[0]);
        // Percolation p1 = new Percolation(n);
        // System.out.println(p1.percolates());

        //     int size = 3;
        //     Percolation perc = new Percolation(size);
        //
        //     java.util.Scanner sc = new java.util.Scanner(System.in);
        //
        //     for (int i = 0; i < size * size; i++) {
        //         System.out.printf("Percolated?: %s\n", perc.percolates() ? "yes" : "no");
        //         perc.printMatrix();
        //         // perc.printWeq();
        //         System.out.print("Type coord to open: ");
        //         String[] coord = sc.nextLine().split(" ", 3);
        //         int row = Integer.parseInt(coord[0]);
        //         int col = Integer.parseInt(coord[1]);
        //
        //         perc.open(row, col);
        //     }
        //     sc.close();
    }
}
