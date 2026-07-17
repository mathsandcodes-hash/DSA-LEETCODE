class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 50000;
        int[] freq = new int[maxVal + 1];
        for (int x : nums) freq[x]++;
        
        long[] g = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long count = 0;
            for (int j = i; j <= maxVal; j += i) {
                count += freq[j];
            }
            g[i] = count * (count - 1) / 2;
            for (int j = 2 * i; j <= maxVal; j += i) {
                g[i] -= g[j];
            }
        }
        
        // Prefix sum to use with binary search
        long[] prefixSum = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSum[i] = prefixSum[i - 1] + g[i];
        }
        
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            // Find first index where prefixSum[idx] > queries[i]
            int low = 1, high = maxVal, ans = maxVal;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSum[mid] > queries[i]) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            result[i] = ans;
        }
        return result;
    }
}