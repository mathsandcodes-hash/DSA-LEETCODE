import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // dist[i][j] will store the minimum health cost to reach cell (i, j)
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        // Deque for 0-1 BFS
        Deque<int[]> deque = new ArrayDeque<>();
        
        // Initialize the starting point
        dist[0][0] = grid.get(0).get(0);
        deque.offerFirst(new int[]{0, 0});
        
        // Direction vectors for moving up, down, left, and right
        int[] dirs = {-1, 0, 1, 0, -1};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            
            // If we reached the bottom-right corner
            if (r == m - 1 && c == n - 1) {
                break;
            }
            
            // Explore all 4 neighbors
            for (int i = 0; i < 4; i++) {
                int nr = r + dirs[i];
                int nc = c + dirs[i + 1];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int weight = grid.get(nr).get(nc);
                    
                    // If a safer path to the neighbor is found
                    if (dist[r][c] + weight < dist[nr][nc]) {
                        dist[nr][nc] = dist[r][c] + weight;
                        
                        // 0-1 BFS optimization
                        if (weight == 0) {
                            deque.offerFirst(new int[]{nr, nc});
                        } else {
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        
        // We need remaining health to be >= 1, meaning health - cost >= 1
        return health - dist[m - 1][n - 1] >= 1;
    }
}