public class q1_another_solution
{
    public static int longestNearlyPal(int[] arr)
    {
        return arr.length == 0 ? 0 : longestNearlyPalindrome(arr, 0, 0, true);
    }

    private static int longestNearlyPalindrome(int[] arr, int left, int right, boolean skip)
    {
        if (left < 0 || right >= arr.length) {
            return 0; // Base case: Out of bounds
        }

        if (left == right)
            return Math.max(1 + longestNearlyPalindrome(arr, left - 1, right + 1, true), // Expand symmetrically outward
                    Math.max(longestNearlyPalindrome(arr, left, right + 1, true), // Expand only the right side
                            longestNearlyPalindrome(arr, left + 1, right + 1, true))); // Skip the current left index

        if (!skip && arr[left] == arr[right])
            return 2 + longestNearlyPalindrome(arr, left - 1, right + 1, skip);
        else
        {
            // Try removing either the left or the right element
            int removeLeft = 0, removeRight = 0;
            if (left > 0 && arr[left - 1] == arr[right])  // remove left
                removeLeft = 3 + longestNearlyPalindrome(arr, left - 2, right + 1, false);

            if (right + 1 < arr.length && arr[left] == arr[right + 1]) // remove right
                removeRight = 3 + longestNearlyPalindrome(arr, left - 1, right + 2, false);

            if (arr[left] == arr[right]) // Case: Elements at the current indices are equal
                return Math.max(Math.max(removeLeft, removeRight), 2 + longestNearlyPalindrome(arr, left - 1, right + 1, skip));

            else
                return Math.max(removeLeft, removeRight);
        }
    }
}
