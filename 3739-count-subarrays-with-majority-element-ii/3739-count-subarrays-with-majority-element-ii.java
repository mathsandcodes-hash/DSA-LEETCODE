class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        // The possible prefix sums range from -n to n.
        // We shift them by (n + 1) to map them into the strictly positive range [1, 2n + 1]
        // This allows us to use them as valid indices in our Fenwick Tree.
        int offset = n + 1;
        int maxVal = 2 * n + 1;
        int[] bit = new int[maxVal + 1];

        long result = 0;
        int currentPref = 0;

        // Base case: before processing any elements, our prefix sum is 0
        add(bit, currentPref + offset, 1, maxVal);

        for (int num : nums) {
            // Transform array on the fly
            if (num == target) {
                currentPref += 1;
            } else {
                currentPref -= 1;
            }
            
            // Count how many previous prefix sums are strictly less than currentPref
            // i.e., we query the range [1, currentPref + offset - 1]
            result += query(bit, currentPref + offset - 1);

            // Add the current prefix sum to the Fenwick Tree to be used by future elements
            add(bit, currentPref + offset, 1, maxVal);
        }

        return result;
    }

    // Standard Fenwick Tree Add operation
    private void add(int[] bit, int index, int val, int maxVal) {
        for (; index <= maxVal; index += index & -index) {
            bit[index] += val;
        }
    }

    // Standard Fenwick Tree Query operation
    private int query(int[] bit, int index) {
        int sum = 0;
        for (; index > 0; index -= index & -index) {
            sum += bit[index];
        }
        return sum;
    }
}