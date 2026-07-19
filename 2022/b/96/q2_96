public class q2 {

    public static int kAlmostSearch(int [] a, int num) {
        int left = 0, right = a.length - 1, mid;
        int zeroR, zeroL;
        while (left <= right)
        {
            mid = (right + left) / 2;
            if (a[mid] == 0)
            {
                zeroR = mid + 1;
                zeroL = mid - 1;
                while (a[zeroL] == 0 && zeroL >= left || a[zeroR] == 0 && zeroR <= right)
                {
                    if (a[zeroL] == 0 && zeroL != 0)
                        zeroL--;
                    if (a[zeroR] == 0 && zeroR != a.length - 1)
                        zeroR++;
                }
                if (a[zeroL] >= num)
                    mid = zeroL;
                else if (a[zeroR] <= num)
                    mid = zeroR;
                else return -1;
            }

            if (a[mid] < num)
                left = mid + 1;

            else if (a[mid] > num)
                right = mid - 1;
            else
                return mid;
        }
        return -1;
    }


}
