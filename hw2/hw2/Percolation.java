package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
    private boolean[][] isPercolated;
    private int size;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF uf;
    public Percolation(int N) {
        if(N <= 0) throw new java.lang.IllegalArgumentException();
        isPercolated = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                isPercolated[i][j] = false;
            }
        }
        numberOfOpenSites = 0;
        size = N;
        uf = new WeightedQuickUnionUF(N*N+2);
    }               // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isPercolated[row][col]) return;

        isPercolated[row][col] = true;
        numberOfOpenSites++;

        int current = row * size + col;

        // Connect to the virtual top site if it's the top row
        if (row == 0) {
            uf.union(current, size * size);  // connect to virtual top
        }

        // Connect to the virtual bottom site if it's the bottom row
        if (row == size - 1) {
            uf.union(current, size * size + 1);  // connect to virtual bottom
        }

        // Connect to adjacent sites if they are open
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(current, row * size + col - 1);
        }
        if (col < size - 1 && isOpen(row, col + 1)) {
            uf.union(current, row * size + col + 1);
        }
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(current, (row - 1) * size + col);
        }
        if (row < size - 1 && isOpen(row + 1, col)) {
            uf.union(current, (row + 1) * size + col);
        }
    }      // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        if(row < 0||row > size-1||col < 0|| col > size-1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return isPercolated[row][col];
    } // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        if(row < 0||row > size-1||col < 0|| col > size-1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return !(isPercolated[row][col]);
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }          // number of open sites
    public boolean percolates() {
        return uf.connected(size * size, size * size + 1);
    }             // does the system percolate?
    public static void main(String[] args){

    }   // use for unit testing (not required)
}
