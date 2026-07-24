class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        
        // dp[i][state] where 'i' is the house index and 'state' is 0 or 1.
        // We use -1 to indicate that a state hasn't been calculated yet.
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
        
        // Start at the 0th house with "free will" (state = 1)
        return solve(nums, 0, 1, dp);
    }
    
    private int solve(int[] nums, int i, int canRob, int[][] dp) {
        // Base case: If we've run out of houses, there's no more money to rob.
        if (i >= nums.length) {
            return 0;
        }
        
        // Memoization: If we already calculated this exact scenario, return it.
        if (dp[i][canRob] != -1) {
            return dp[i][canRob];
        }
        
        int maxProfit = 0;
        
        if (canRob == 1) {
            // Choice 1: Rob this house. We gain nums[i], but lose free will for the next house (pass 0).
            int robCurrent = nums[i] + solve(nums, i + 1, 0, dp);
            
            // Choice 2: Skip this house. We gain nothing, but keep free will for the next house (pass 1).
            int skipCurrent = solve(nums, i + 1, 1, dp);
            
            // The best option is the maximum of the two choices.
            maxProfit = Math.max(robCurrent, skipCurrent);
        } else {
            // No free will. We MUST skip this house and pass free will (1) to the next house.
            maxProfit = solve(nums, i + 1, 1, dp);
        }
        
        // Store the result in our DP table before returning.
        dp[i][canRob] = maxProfit;
        return maxProfit;
    }
}