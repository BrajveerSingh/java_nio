//import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//
//public class Percolation {
//    private WeightedQuickUnionUF weightedQuickUnionUF;
//    private int[][] sites;
//    // creates n-by-n grid, with all sites initially blocked
//    public Percolation(int n){
//        if(n <= 0){
//            throw new IllegalArgumentException("Invalid value n=" + n);
//        }
//        weightedQuickUnionUF = new WeightedQuickUnionUF(n);
//        sites = new int[n][n];
//        for(int i = 0; i < n; i++){
//            for (int j = 0; j < n; j++){
//                sites[i][j] = 0; // 0 mean block site
//            }
//        }
//    }
//
//    // opens the site (row, col) if it is not open already
//    public void open(int row, int col){
//
//    }
//
//    // is the site (row, col) open?
//    public boolean isOpen(int row, int col){
//        return sites[row][col] == 1;   // i means open site
//    }
//
//    // is the site (row, col) full?
//    public boolean isFull(int row, int col){
//
//    }
//
//    // returns the number of open sites
//    public int numberOfOpenSites() {
//
//    }
//
//    // does the system percolate?
//    public boolean percolates(){
//
//    }
//
//    // test client (optional)
//    public static void main(String[] args){
//
//    }
//}