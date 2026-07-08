class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        int mod = 1_000_000_007;
        
        // Count non-zero digits
        int k = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                k++;
            }
        }
        
        int[] digits = new int[k];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                digits[idx++] = s.charAt(i) - '0';
            }
        }
        
        // Precompute next_nonzero and prev_nonzero mapping arrays
        int[] next_nonzero = new int[m];
        int cur_next = k;
        for (int i = m - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                cur_next--;
            }
            next_nonzero[i] = cur_next;
        }
        
        int[] prev_nonzero = new int[m];
        int cur_prev = -1;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                cur_prev++;
            }
            prev_nonzero[i] = cur_prev;
        }
        
        // Precompute prefix sum, suffix mod configurations, and powers of 10
        long[] pref_sum = new long[k + 1];
        long[] P = new long[k + 1];
        long[] pow10 = new long[k + 1];
        pow10[0] = 1;
        
        for (int i = 0; i < k; i++) {
            pref_sum[i + 1] = pref_sum[i] + digits[i];
            P[i + 1] = (P[i] * 10 + digits[i]) % mod;
            pow10[i + 1] = (pow10[i] * 10) % mod;
        }
        
        int numQueries = queries.length;
        int[] answer = new int[numQueries];
        
        for (int i = 0; i < numQueries; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            
            // Map original boundaries to compressed array boundaries
            int L = next_nonzero[l];
            int R = prev_nonzero[r];
            
            // Check if there are no non-zero elements in the range
            if (L > R || L >= k || R < 0) {
                answer[i] = 0;
            } else {
                long sum = pref_sum[R + 1] - pref_sum[L];
                long x = (P[R + 1] - (P[L] * pow10[R - L + 1]) % mod + mod) % mod;
                answer[i] = (int) ((x * sum) % mod);
            }
        }
        
        return answer;
    }
}