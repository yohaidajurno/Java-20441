public class q1
{
    public static int minPoints(int[][] m)
    {
        return minPoints(m, m.length - 1, m[0].length - 1, 1, 1);
    }

    private static int minPoints(int[][] mat, int i, int j, int sum, int currentNum)
    {
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE;

        sum -= mat[i][j];

        if (sum <= 0)
        {
            currentNum += 1 - sum;
            sum = 1;
        }

        if (i == 0 && j == 0)
                return currentNum;

        return Math.min(minPoints(mat, i - 1, j, sum, currentNum), minPoints(mat, i, j - 1, sum, currentNum));
    }
}