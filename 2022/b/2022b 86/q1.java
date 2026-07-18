public class q1 {
    public static int maxPath(int[][]mat)
    {
        return maxPath(mat, 0, 0);
    }

    private static int maxPath(int [][] mat, int i, int j)
    {
        if(i > mat.length - 1 || j > mat[0].length - 1)
        {
            return Integer.MIN_VALUE;
        }
        if(i == mat.length - 1 && j == mat[0].length - 1)
        {
            return mat[i][j];
        }
        return Math.max(maxPath(mat, i + (mat[i][j] % 10), j + (mat[i][j] / 10)) + mat[i][j],
                        maxPath(mat, i + (mat[i][j] / 10), j + (mat[i][j] % 10))+ mat[i][j]);

    }
}
