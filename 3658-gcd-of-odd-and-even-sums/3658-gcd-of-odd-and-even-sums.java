class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sumeven=n*(n+1);
        int sumodd=n*n;
        int gcd = findGCD(sumeven,sumodd);
        return gcd;
        
    }
     public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % temp;
            a = temp;
        }
        return a;
    }
}