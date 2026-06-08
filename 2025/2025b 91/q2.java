public class q2
{
    public static void printKClosest (int[] arr, int num, int k)
    {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int i;
        int j;
        boolean b = false;
        if(arr.length <= k)
        {
            for(int h = 0; h < arr.length; h++)
            {
                System.out.print(arr[h]);
            }
        }
        while(low <= high)
        {
            mid = (low + high) / 2;
            if(arr[mid] == num)
            {
                b = true;
                low = high + 1;
            }
            if(arr[mid] < num)
            {
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }
        i = high;
        j = low;
        if(b == true)
        {
            i = mid - 1;
            j = mid + 1;
        }
        while(k > 0 && j <= arr.length - 1 && i >= 0)
        {
            if((num - arr[i]) <= (arr[j] - num))
            {
                System.out.print(arr[i] + "\t");
                i--;
                k--;
            }
            else
            {
                System.out.print(arr[j] + "\t");
                j++;
                k--;
            }

        }
        while(k > 0 && i >= 0)
        {
            System.out.print(arr[i] + "\t");
            i--;
            k--;
        }
        while(k > 0 && j <= arr.length - 1)
        {
            System.out.print(arr[j] + "\t");
            j++;
            k--;
        }
    }
}
