class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int MOD = 1_000_000_007;
        int m = r - l + 1; // Size of the available number range
        
        // Edge cases
        if (m <= 0) return 0;
        if (n == 1) return m % MOD;
        
        // dpDown[j] stores the number of valid sequences ending in value j
        // where the last operation was a decrease (DOWN).
        int[] dpDown = new int[m + 1];
        
        // dpUp[j] stores the number of valid sequences ending in value j
        // where the last operation was an increase (UP).
        int[] dpUp = new int[m + 1];
        
        // Base case: For a sequence of length 1, we imagine it's ready to
        // either go UP or DOWN next. We initialize both arrays with 1s.
        for (int j = 1; j <= m; j++) {
            dpDown[j] = 1;
            dpUp[j] = 1;
        }
        
        // Build the sequence iteratively up to length n
        for (int i = 2; i <= n; i++) {
            int[] newDpDown = new int[m + 1];
            int[] newDpUp = new int[m + 1];
            
            // To calculate newDpUp[j], we need the sum of all dpDown[k] where k < j.
            // We use a running prefix sum to calculate this in O(1) time per step.
            long sumDown = 0;
            for (int j = 1; j <= m; j++) {
                newDpUp[j] = (int) sumDown;
                sumDown = (sumDown + dpDown[j]) % MOD;
            }
            
            // To calculate newDpDown[j], we need the sum of all dpUp[k] where k > j.
            // We use a running suffix sum by iterating backwards.
            long sumUp = 0;
            for (int j = m; j >= 1; j--) {
                newDpDown[j] = (int) sumUp;
                sumUp = (sumUp + dpUp[j]) % MOD;
            }
            
            // Move to the next sequence length
            dpDown = newDpDown;
            dpUp = newDpUp;
        }
        
        // The total number of valid sequences is the sum of all sequences 
        // ending in any value, regardless of their final direction.
        long total = 0;
        for (int j = 1; j <= m; j++) {
            total = (total + dpDown[j]) % MOD;
            total = (total + dpUp[j]) % MOD;
        }
        
        return (int) total;
    }
}