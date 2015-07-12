/**
 * Created by aarondesouza on 20/06/2015.
 */
public class PercolationStats {

    private final double[] results;
    private final int noOfExperiments;

    public PercolationStats(int gridSize, int noOfExperiments)  throws Exception    // perform T independent experiments on an N-by-N grid
    {
        if (gridSize <= 0 || noOfExperiments <= 0)
            throw new IllegalArgumentException();
        this.noOfExperiments = noOfExperiments;
        results = new double[noOfExperiments];

        for (int i = 0; i < noOfExperiments; i++)
        {
            int openSites = 0;
            Percolation p = new Percolation(gridSize);

            do
            {
                int row, col;
                row = StdRandom.uniform(1, gridSize + 1);
                col = StdRandom.uniform(1, gridSize + 1);
                while (p.isOpen(row, col))
                {
                    row = StdRandom.uniform(1, gridSize + 1);
                    col = StdRandom.uniform(1, gridSize + 1);
                }
//                System.out.println("opening row " + row + ", col " + col);
                p.open(row, col);
                openSites++;

            }
            while (!p.percolates());

            results[i] = (double) openSites / (gridSize * gridSize);
        }
    }

    public double mean()                      // sample mean of percolation threshold
    {
        return StdStats.mean(results);
    }

    public double stddev()                    // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(results);
    }

    public double confidenceLo()              // low  endpoint of 95% confidence interval
    {
        return mean() + ((1.96 * stddev()) / Math.sqrt(noOfExperiments));
    }

    public double confidenceHi()              // high endpoint of 95% confidence interval
    {
        return mean() - ((1.96 * stddev()) / Math.sqrt(noOfExperiments));
    }

    public static void main(String[] args) throws Exception   // test client (described below)
    {
        PercolationStats ps = new PercolationStats(200, 100);
        System.out.println("Mean is " + ps.mean());
        System.out.println("Standard deviation is " + ps.stddev());
        System.out.println("95% confidence interval is " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
