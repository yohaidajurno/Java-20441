public class q2
{
    public static int longestSubarray(int[] a) {
        int start = 0;
        int end = 1;
        int max = 1;
        int maxStart = 0;
        int maxEnd = 0;
        if (a.length == 1)
        {
            System.out.println("Starting index = " + maxStart + " maxEnd = " + maxEnd);
            return 1;
        }
        while (end < a.length){
            if (a[end] * a[end - 1] < 0) {
                if (end - start + 1 > max) {
                    max = end - start + 1;
                    maxStart = start;
                    maxEnd = end;
                }
                end ++;
            }
            else {
                start ++;
                end++;
            }
        }
        System.out.println("Starting index = " + maxStart + " maxEnd = " + maxEnd);
        return max;
    }
}
