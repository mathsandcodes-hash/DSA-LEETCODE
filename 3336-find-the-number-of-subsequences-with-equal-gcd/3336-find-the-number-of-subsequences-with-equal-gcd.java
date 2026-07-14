class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        
        // Find the maximum value in nums to bound our GCD state dimensions
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // dp[g1][g2] stores the number of valid subsequence pairs with current GCDs g1 and g2
        // We use a 2D array and iterate through nums to optimize space from O(N * Max* Max) to O(Max * Max)
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        
        // Base case: 1 way to have both subsequences empty (represented by GCD = 0)
        dp[0][0] = 1;

        // Precompute GCD matrix to avoid repetitive calculations during transitions
        int[][] gcdMatrix = new int[maxVal + 1][maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            for (int j = 0; j <= maxVal; j++) {
                gcdMatrix[i][j] = gcd(i, j);
            }
        }

        // Iterate through each number in the input array
        for (int num : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];

            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    long count = dp[g1][g2];

                    // Option 1: Skip the current number (keep existing state)
                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + count) % MOD);

                    // Option 2: Add the current number to seq1
                    int nextG1 = gcdMatrix[g1][num];
                    nextDp[nextG1][g2] = (int) ((nextDp[nextG1][g2] + count) % MOD);

                    // Option 3: Add the current number to seq2
                    int nextG2 = gcdMatrix[g2][num];
                    nextDp[g1][nextG2] = (int) ((nextDp[g1][nextG2] + count) % MOD);
                }
            }
            dp = nextDp;
        }

        // Sum up all pairs where g1 == g2, excluding the case where both are empty (g1 = 0, g2 = 0)
        long totalPairs = 0;
        for (int g = 1; g <= maxVal; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    // Helper method to calculate Greatest Common Divisor
    private int gcd(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}