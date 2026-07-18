public class q2 {

    public static void printTriplets(int[] a, int num) {
        for (int i = 0; i < a.length - 2; i++) {
            int low = i + 1;
            int high = a.length - 1;

            while (low < high) {
                int triplet = a[i] * a[low] * a[high];
                if (triplet == num) {
                    System.out.println(a[i] + "\t" + a[low] + "\t" + a[high]);
                    low++;
                    high--;
                }
                else if (triplet < num) {
                    low++;
                }
                else {
                    high--;
                }
            }
        }
    }

}
