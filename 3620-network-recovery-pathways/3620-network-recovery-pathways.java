import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // 1. Build adjacency list and calculate in-degrees for topological sort
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        int maxEdgeCost = -1;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            adj[u].add(new int[]{v, cost});
            maxEdgeCost = Math.max(maxEdgeCost, cost);
        }
        
        // 2. Compute a global topological order of the DAG
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            topoOrder.add(curr);
            for (int[] next : adj[curr]) {
                int neighbor = next[0];
                if (--inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // 3. Binary Search for the maximum possible minimum-edge cost
        int low = 0, high = maxEdgeCost;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (isValidPathPossible(mid, n, adj, topoOrder, online, k)) {
                ans = mid;       // Try to find a larger minimum edge cost
                low = mid + 1;
            } else {
                high = mid - 1;  // Look for a smaller constraint
            }
        }
        
        return ans;
    }
    
    private boolean isValidPathPossible(int minCostLimit, int n, List<int[]>[] adj, 
                                        List<Integer> topoOrder, boolean[] online, long k) {
        // dist[i] stores the minimum path cost from node 0 to node i
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        
        // Traverse nodes in topological order to compute DP transitions
        for (int u : topoOrder) {
            if (dist[u] == Long.MAX_VALUE) continue;
            // If the current node is offline (and it's not the start/end node), we cannot route through it
            if (!online[u] && u != 0 && u != n - 1) continue;
            
            for (int[] edge : adj[u]) {
                int v = edge[0];
                int cost = edge[1]; // Fixed: cost is at index 1 in our adjacency storage
                
                // Only traverse edges that meet our binary search threshold
                if (cost >= minCostLimit) {
                    if (dist[u] + cost < dist[v]) {
                        dist[v] = dist[u] + cost;
                    }
                }
            }
        }
        
        return dist[n - 1] <= k;
    }
}