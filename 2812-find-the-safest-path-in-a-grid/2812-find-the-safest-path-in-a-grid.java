import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // If the start or end cell has a thief, the safeness factor is 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        int[][] distToThief = new int[n][n];
        for (int[] row : distToThief) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Multi-source BFS setup to calculate minimum distance to any thief
        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    q.offer(new int[]{r, c});
                    distToThief[r][c] = 0;
                }
            }
        }

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        // Compute Manhattan distance to the nearest thief for all cells
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && distToThief[nr][nc] == Integer.MAX_VALUE) {
                    distToThief[nr][nc] = distToThief[r][c] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        // Priority Queue to find the path maximizing the safeness factor (Max-Heap)
        // Store elements as: {safeness_factor, row, col}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.offer(new int[]{distToThief[0][0], 0, 0});
        
        int[][] maxSafeness = new int[n][n];
        for (int[] row : maxSafeness) {
            Arrays.fill(row, -1);
        }
        maxSafeness[0][0] = distToThief[0][0];

        // Modified Dijkstra to find the maximum safeness path
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currentSafeness = curr[0];
            int r = curr[1], c = curr[2];

            // Reached the destination
            if (r == n - 1 && c == n - 1) {
                return currentSafeness;
            }

            if (currentSafeness < maxSafeness[r][c]) {
                continue;
            }

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    // The safeness of the path to the neighbor is limited by the minimum along the route
                    int nextSafeness = Math.min(currentSafeness, distToThief[nr][nc]);
                    if (nextSafeness > maxSafeness[nr][nc]) {
                        maxSafeness[nr][nc] = nextSafeness;
                        pq.offer(new int[]{nextSafeness, nr, nc});
                    }
                }
            }
        }

        return 0;
    }
}