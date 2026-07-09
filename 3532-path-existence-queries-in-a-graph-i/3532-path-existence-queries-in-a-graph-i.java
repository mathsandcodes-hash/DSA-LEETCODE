class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] componentId = new int[n];
        int currentId = 0;
        componentId[0] = currentId;
        
        // Assign component IDs based on the maxDiff condition
        for (int i = 0; i < n - 1; i++) {
            if (nums[i+1] - nums[i] > maxDiff) {
                currentId++;
            }
            componentId[i+1] = currentId;
        }
        
        // Answer queries
        boolean[] results = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            results[i] = componentId[queries[i][0]] == componentId[queries[i][1]];
        }
        
        return results;
    }
}