public class q1
{
    public static int longestNearlyPal (int[] arr)
    {
        return longestNearlyPal(arr, 0, arr.length - 1);
    }
    // Recursively finds the longest nearly palindrome subarray by checking both sides of the array.
    private static int longestNearlyPal(int[] arr, int left, int right)
    {
        // If the current subarray is a nearly palindrome, return its length
        if ((arr[left] == arr[right]) && isNearlyPalindrome(arr, left, right, true))
            return  (right + 1) - left; // polindrome's length

        // check by removing either the left or right element and return the longest nearly palindrome subarray.
        return Math.max((longestNearlyPal(arr, left + 1, right)), (longestNearlyPal(arr, left, right - 1)));
    }

    // Checks if a given subarray is a nearly palindrome.
    private static boolean isNearlyPalindrome(int[] arr, int left, int right, boolean isSkipped)
    {
        if (right <= left)
            return true;

        // If the current elements are equal, continue checking the inner elements
        if (arr[left] == arr[right])
            return isNearlyPalindrome (arr, left + 1, right - 1, isSkipped);

        // If the elements are not equal and we haven't skipped an element yet,
        // try skipping either the left or the right element and continue checking
        if (isSkipped)
            return isNearlyPalindrome (arr, left + 1, right, false) || isNearlyPalindrome(arr, left, right - 1, false);

        return false; // If we already removed an element and there's still a mismatch, it's not a nearly palindrome
    }
}
