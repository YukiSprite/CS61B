package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private double[] q;
    private int T;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.T = T;
        q = new double[T];
            for(int i = 0;i < T;i++) {
                Percolation percolation = pf.make(N);
                while(!percolation.percolates()) {
                    int x = StdRandom.uniform(0,N);
                    int y  = StdRandom.uniform(0,N);
                    if(!percolation.isOpen(x, y)) {
                        percolation.open(x,y);
                    }
                }
                q[i] = percolation.numberOfOpenSites();
            }
    }  // perform T independent experiments on an N-by-N grid
    public double mean() {
        double total = 0;
        for(int i=0;i<T;i++) {
            total += q[i];
        }
        return total / T;
    }                                          // sample mean of percolation threshold
    public double stddev() {
//        double stddevTotal = 0;
//        for(int i = 0;i<T;i++) {
//            stddevTotal += Math.pow(q[i]-mean(), 2);
//        }
//        return Math.sqrt(stddevTotal / T);
        return StdStats.stddev(q);
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }                                // high endpoint of 95% confidence interval
}
