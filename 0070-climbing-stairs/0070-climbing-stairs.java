class Solution {
    public int climbStairs(int n) {
        int []memo = new int[n+1];
        return climbstairs(0,n,memo);
    }
    private int climbstairs(int current , int n , int []memo){
       if (current == n) {
            return 1;
        }
        if (current > n) {
            return 0;
        }
      if (memo[current] != 0) {
            return memo[current];
        }
        int takeOneStep = climbstairs(current + 1, n, memo);
        int takeTwoSteps = climbstairs(current + 2, n, memo);

        memo[current] = takeOneStep + takeTwoSteps;
        
        return memo[current];
    }
}