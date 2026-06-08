public class q1
{
    public static int where (int[] vec)
    {
        return where(vec, 0);
    }

    private static int where(int[] vec, int p)
    {
        if(p >= vec.length)
            return -1;

        if(sum(vec, 0, p - 1) == sum(vec, p, vec.length - 1))
            return p;

        return where(vec, p + 1);
    }


    public static boolean isBalanced(int[] vec, int k)
    {
        return isBalanced(vec, 0, vec.length - 1, 0, k);
    }

    private static boolean isBalanced(int[] vec, int left, int right, int p, int k)
    {
        if (k == 0)
        {
            return true;
        }
        if (p > right)
        {
            return false;
        }

        if (sum(vec, left, p - 1) == sum(vec, p, right) &&
                isBalanced(vec, left, p - 1, left, k - 1) && isBalanced(vec, p, right, p, k - 1))
            return true;

        return isBalanced(vec, left, right, p + 1, k);
    }

    private static int sum(int[] v, int lo, int hi) {
        int s = 0;
        for (int i = lo; i <= hi; i++) s += v[i];
        return s;
    }
}
