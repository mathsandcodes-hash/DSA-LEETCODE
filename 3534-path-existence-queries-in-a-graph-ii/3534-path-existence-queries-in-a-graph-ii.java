import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Pair up the values with their original indices and sort them
        int[][] sortedNodes = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedNodes[i][0] = nums[i]; // value
            sortedNodes[i][1] = i;       // original index
        }
        Arrays.sort(sortedNodes, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Map original index to its sorted position for quick lookup
        int[] originalToSorted = new int[n];
        for (int i = 0; i < n; i++) {
            originalToSorted[sortedNodes[i][1]] = i;
        }
        
        // Step 2: Use two pointers to find the furthest reachable right neighbor for each node
        int[] nextR = new int[n];
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n && sortedNodes[r][0] - sortedNodes[l][0] <= maxDiff) {
                r++;
            }
            nextR[l] = r - 1; 
        }
        
        // Step 3: Setup Binary Lifting Sparse Table
        int LOG = 18;
        int[][] up = new int[n][LOG];
        
        for (int i = 0; i < n; i++) {
            up[i][0] = nextR[i];
        }
        
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                // FIXED: 2^j steps from i is equal to 2^(j-1) steps from the node reached after 2^(j-1) steps
                up[i][j] = up[up[i][j - 1]][j - 1]; 
            }
        }
        
        // Step 4: Process Queries
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int uOriginal = queries[q][0];
            int vOriginal = queries[q][1];
            
            if (uOriginal == vOriginal) {
                ans[q] = 0;
                continue;
            }
            
            int u = originalToSorted[uOriginal];
            int v = originalToSorted[vOriginal];
            
            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }
            
            if (Math.abs(nums[uOriginal] - nums[vOriginal]) <= maxDiff) {
                ans[q] = 1;
                continue;
            }
            
            int steps = 0;
            int current = u;
            
            for (int j = LOG - 1; j >= 0; j--) {
                if (up[current][j] < v) {
                    current = up[current][j];
                    steps += (1 << j);
                }
            }
            
            if (up[current][0] >= v) {
                ans[q] = steps + 1;
            } else {
                ans[q] = -1; 
            }
        }
        
        return ans;
    }
}