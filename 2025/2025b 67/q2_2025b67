public class q2 {
    public static int findTriplet(int[] arr) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, min3 = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min1) {
                min3 = min2;
                min2 = min1;
                min1 = arr[i];
            } else if (arr[i] < min2) {
                min3 = min2;
                min2 = arr[i];
            } else if (arr[i] < min3)
                min3 = arr[i];

            if (arr[i] > max1) {
                max2 = max1;
                max1 = arr[i];
            } else if (arr[i] > max2)
                max2 = arr[i];
        }
        if (max1 * max2 * min1 < min1 * min2 * min3) {
            System.out.println(max1 + "\t" + max2 + "\t" + min1);
            return max1 * max2 * min1;
        }
        System.out.println(min1 + "\t" + min2 + "\t" + min3);
        return min1 * min2 * min3;
    }
}
