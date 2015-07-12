/**
 * Created by aarondesouza on 20/06/2015.
 */
public class Percolation {

    private static final int VIRTUAL_TOP = 0;
    private final int VIRTUAL_BOTTOM;

    private final int n;

    private final WeightedQuickUnionUF uf;
    private final int[] openSites;


    public Percolation(final int n)
    {
        if (n <= 0)
            throw new IllegalArgumentException();

        this.n = n;
        VIRTUAL_BOTTOM = n*n + 1;
        openSites = new int[n*n + 1]; //ignore the first element

        uf = new WeightedQuickUnionUF(n*n + 2); //first site is the virtual top last site is the virtual bottom

        for (int i = 1; i < n + 1; i++) {
            uf.union(VIRTUAL_TOP, i);
            uf.union((n* (n -1)) + i, VIRTUAL_BOTTOM);
        }
    }

    public void open(int i, int j)
    {
        if (i <= 0 || j <= 0 || i > n || j > n)
            throw new IndexOutOfBoundsException();

        final int index = coordsToIndex(i, j);
        openSites[index] = 1;
        connectBottomIndex(i, j);
        connectLeftIndex(i, j);
        connectRightIndex(i, j);
        connectTopIndex(i, j);
    }

    public boolean isOpen(int i, int j)
    {
        if (i <= 0 || j <= 0 || i > n || j > n)
            throw new IndexOutOfBoundsException();

        final int index = coordsToIndex(i, j);
        return openSites[index] == 1;
    }

    public boolean isFull(int i, int j)
    {
        if (i <= 0 || j <= 0 || i > n || j > n)
            throw new IndexOutOfBoundsException();

        for (int k = 1; k < openSites.length; k++) {
            if (openSites[k] == 0)
                    return false;
        }
        return true;
        //final int index = coordsToIndex(i, j);
        //return uf.connected(index, VIRTUAL_TOP);
    }

    public boolean percolates()
    {
        return uf.connected(VIRTUAL_BOTTOM, VIRTUAL_TOP);
    }

    private int coordsToIndex(int i, int j)
    {
        final int rowStart = n * (j - 1);
        return rowStart + i;
    }

    private void connectLeftIndex(int i, int j)
    {
        if (i != 1)
        {
            if (isOpen(i - 1, j))
                uf.union(coordsToIndex(i, j), coordsToIndex(i - 1, j));
        }
    }

    private void connectRightIndex(int i, int j)
    {
        if (i != n)
        {
            if (isOpen(i + 1, j))
                uf.union(coordsToIndex(i, j), coordsToIndex(i + 1, j));
        }
    }

    private void connectTopIndex(int i, int j)
    {
        if (j != 1)
        {
            if (isOpen(i, j - 1))
                uf.union(coordsToIndex(i, j), coordsToIndex(i, j - 1));
        }
    }

    private void connectBottomIndex(int i, int j)
    {
        if (j != n)
        {
            if (isOpen(i, j + 1))
                uf.union(coordsToIndex(i, j), coordsToIndex(i, j + 1));
        }
    }

}
