import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {
        // Step 1: Count the frequency of each number
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        int maxLen = 1; // Any single element forms a valid subset of length 1
        
        // Step 2: Handle the edge case for 1 separately
        if (map.containsKey(1)) {
            int count = map.get(1);
            // The pattern must have an odd length, so take the largest odd number <= count
            maxLen = Math.max(maxLen, count % 2 == 0 ? count - 1 : count);
        }
        
        // Step 3: Check chains for each unique x > 1
        for (int x : map.keySet()) {
            if (x == 1) continue;
            
            long curr = x;
            int currLen = 0;
            
            // We need at least 2 copies of 'curr' to extend the chain on both sides
            while (map.containsKey((int) curr) && map.get((int) curr) >= 2) {
                currLen += 2;
                curr = curr * curr;
                // Stop if 'curr' exceeds the maximum possible value in nums (10^9)
                if (curr > 1000000000) {
                    break;
                }
            }
            
            // Check if the final 'curr' can act as the single peak element
            if (curr <= 1000000000 && map.containsKey((int) curr)) {
                currLen += 1;
            } else {
                // If it can't be the peak, the previous element must be the peak.
                // Since we already allocated 2 copies for it, we reduce it by 1.
                currLen -= 1;
            }
            
            maxLen = Math.max(maxLen, currLen);
        }
        
        return maxLen;
    }
}