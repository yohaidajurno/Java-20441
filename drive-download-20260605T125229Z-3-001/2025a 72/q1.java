public class q1 {

    public static int maxFactor(int num)
    {
        return maxFactor(num, (int) Math.sqrt(num));
    }

    private static int maxFactor(int num, int factor)
    {
        if (factor < 2)
        {
            return 0;
        }
        if (isFactor(num, factor, 0, 1, ""))
            return factor;
        return maxFactor(num, factor - 1);
    }

    private static boolean isFactor(int num, int factor, int pow, int currPow, String factorial) {
        if (num < currPow)
            return false;
        if (num == currPow)
        {
            System.out.println(factorial + currPow);
            return true;
        }
        return isFactor(num, factor, pow + 1, (int) Math.pow(factor, pow + 1), factorial) ||
                isFactor(num - currPow, factor, pow + 1, (int) Math.pow(factor, pow + 1), factorial + currPow + "\t");
    }

}
