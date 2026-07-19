public static int longestSequence(int[] a, int k) {
    // i is the beginning of the sliding window
    // j is the end of the sliding window
    int i = 0;
    int j = 0;

    while (j < a.length) {
        // A zero uses one of the k allowed replacements
        if (a[j] == 0) {
            k--;
        }

        // Too many zeros are inside the current window
        if (k < 0) {
            // If the element leaving the window is zero,
            // restore one available replacement
            if (a[i] == 0) {
                k++;
            }

            // Move the beginning of the window forward
            i++;
        }

        // Expand the window
        j++;
    }

    // Length of the longest sequence containing at most
    // the original number of allowed zeros
    return j - i;
}
