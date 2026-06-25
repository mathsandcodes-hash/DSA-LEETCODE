class Solution {
    private int[] bit;
    private int size;

    // Standard Fenwick Tree update
    private void update(int idx, int val) {
        for (; idx < size; idx += idx & -idx) {
            bit[idx] += val;
        }
    }

    // Standard Fenwick Tree prefix query
    private int query(int idx) {
        int sum = 0;
        for (; idx > 0; idx -= idx & -idx) {
            sum += bit[idx];
        }
        return sum;
    }

    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        // Since prefix sums range from -n to +n, we shift them by an offset
        // to keep them 1-indexed and positive for the Fenwick Tree.
        int offset = n + 1;
        size = 2 * n + 2;
        bit = new int[size];

        long totalSubarrays = 0;
        int prefixSum = 0;

        // Insert the initial prefix sum of 0
        update(0 + offset, 1);

        for (int num : nums) {
            // Update the running prefix sum
            if (num == target) {
                prefixSum += 1;
            } else {
                prefixSum -= 1;
            }

            int currMapped = prefixSum + offset;

            // Count all previous prefix sums that are strictly less than the current one
            totalSubarrays += query(currMapped - 1);

            // Add the current prefix sum into the Fenwick Tree
            update(currMapped, 1);
        }

        return (int) totalSubarrays;
    }
}