public class q1
{
    public static int alternateSorted(int[] a, int[] b)
    {
        int [] length = {0, 0};
        String [] printPath = {path(a, b, length, 0, 0, 0, 0), path(b, a, length, 0, 0, 1, 0)};

        System.out.println(length[0] > length[1] ? printPath[0] : printPath[1]);
        return Math.max(length[0], length[1]);
    }

    private static String path(int [] curr, int[] next, int[] length, int i, int j, int current, int size1)
    {
        if (i >= curr.length)
        {
            length[current] = size1;
            return "}";
        }
        return (i == 0 || curr[i] > next[j - 1]) ? (j == 0 ? "{" : ",") + curr[i] +
                path(next, curr, length, j, i + 1, current, size1 + 1):
                path(curr, next, length, i + 1, j, current, size1 + 1);
    }
}
