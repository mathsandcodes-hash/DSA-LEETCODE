import java.util.Arrays;

class Solution {
    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        int mod = 1_000_000_007;
        
        // Step 1: Sort the array
        Arrays.sort(nums);
        
        // Step 2: Precompute powers of 2 modulo 10^9 + 7
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % mod;
        }
        
        int left = 0;
        int right = n - 1;
        int result = 0;
        
        // Step 3: Two-pointer traversal
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                // Add the number of valid subsequences
                result = (result + pow2[right - left]) % mod;
                left++; // Move left pointer to check the next minimum
            } else {
                // The current max is too large, decrease it
                right--;
            }
        }
        
        return result;
    }
}