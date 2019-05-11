/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] th; // threshold
    private int T;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        T = trials;
        if (n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException("n and trials must be larger than 0");

        th = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p1 = new Percolation(n);
            for (int j = 0; j <= n * n; j++) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                while (p1.isOpen(row, col)) {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                }
                p1.open(row, col);
                if (p1.percolates()) {
                    th[i] = (double) p1.numberOfOpenSites() / (n * n);
                    break;
                }
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(th);
    }

    // sample standard deviation of percolation interval
    public double stddev() {
        return StdStats.stddev(th);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        System.out.println("mean    =" + ps.mean());
        System.out.println("stddev    =" + ps.stddev());
        System.out.println(
                "95% confidence interval    =[" + ps.confidenceLo() + "," + ps.confidenceHi()
                        + "]");
    }

}
