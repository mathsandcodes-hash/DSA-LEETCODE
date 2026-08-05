class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        // Instead of a whole grid, we just track the state of the "next day" (i + 1)
        int aheadBuy = 0;  // equivalent to dp[i+1][2]
        int aheadSell = 0; // equivalent to dp[i+1][1]

        // Loop backwards exactly like your tabulation
        for (int i = n - 1; i >= 0; i--) {
            // Calculate the current day based strictly on the 'ahead' day
            int currentBuy = Math.max(aheadSell - prices[i], aheadBuy);
            int currentSell = Math.max(aheadBuy + prices[i], aheadSell);

            // Move our 'ahead' pointers back by one day for the next iteration
            aheadBuy = currentBuy;
            aheadSell = currentSell;
        }

        // The answer bubbles up to the 'buy' state on day 0
        return aheadBuy;
    }
}