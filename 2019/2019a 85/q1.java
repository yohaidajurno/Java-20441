public static int longestPalindrome(int[] arr) {
    if (arr == null || arr.length == 0) {
        return 0;
    }

    return longestPalindrome(arr, 0, arr.length - 1, arr.length);
}

private static int longestPalindrome(int[] arr, int start, int end, int length) {
    if (start == end || start > end) {
        return length;
    }

    if (start > arr.length - 1) {
        return 1;
    }

    if (arr[start] != arr[end]) {
        return Math.max(longestPalindrome(arr, start + 1, end, length - 1),
                longestPalindrome(arr, start, end - 1, length - 1));
    }
    return longestPalindrome(arr, start + 1, end - 1, length);
}

public static void main(String[] args) {

    int[] arr = { 1, 3, 2, 3, 10, 10, 3, 2, 4 };
    int[] arr2 = { 1, 2, 3, 4 };

    // expected 6
    System.out.println("answer = " + longestPalindrome(arr));
    System.out.println("answer = " + longestPalindrome(arr2));
}
