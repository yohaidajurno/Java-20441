public class q1
{
    public static int lengthPath(char [][] mat, String pattern)
    {
        return lengthPath(mat, pattern, 0, 0);
    }

    private static int lengthPath(char [][] mat, String pattern, int i, int j)
    {
        if(i < 0 || j < 0 || i >= mat.length || j >= mat[0].length || mat[i][j] == '-')
        {
            return Integer.MIN_VALUE;
        }

        char last = mat[i][j];
        mat[i][j] = '-';

        if(pattern.indexOf(last) == -1)
            return 0;
        else
        {
            int up = 1 + lengthPath(mat, pattern, i - 1, j);
            int down = 1 + lengthPath(mat, pattern, i + 1, j);
            int right = 1 + lengthPath(mat, pattern, i, j - 1);
            int  left= 1 + lengthPath(mat, pattern, i, j + 1);

            mat[i][j] = last;
            return Math.max((Math.max(up, down)) , (Math.max(right, left)));
        }
    }

    public static int maxPath(char[][]mat,String pattern)
    {
        return maxPath(mat, pattern, 0, 0, 0);
    }

    private static int maxPath(char [][] mat, String pattern, int i, int j, int currentMax)
    {
        if(i == mat.length)
        {
            return currentMax;
        }
        if(j == mat[0].length)
        {
            return maxPath(mat, pattern, i + 1, 0, currentMax);
        }

        if(pattern.indexOf(mat[i][j]) != -1)
        {
            int currentLength = lengthPath(mat, pattern, i, j);
            if(currentLength > currentMax)
            {
                currentMax = currentLength;
            }
        }
        return maxPath(mat, pattern, i, j + 1, currentMax);
    }
}
