import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Step 1: Build the adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;

        // Step 2: Iterate through all nodes to find each connected component
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> componentNodes = new ArrayList<>();
                
                // Collect all nodes belonging to this component
                dfs(i, adj, visited, componentNodes);
                
                int totalVertices = componentNodes.size();
                int totalEdgesSum = 0;
                
                // Sum up the degrees of all vertices in this component
                for (int node : componentNodes) {
                    totalEdgesSum += adj.get(node).size();
                }
                
                // In an undirected graph, total unique edges = totalEdgesSum / 2
                int totalEdges = totalEdgesSum / 2;
                
                // Check completeness criteria: E == V * (V - 1) / 2
                if (totalEdges == (totalVertices * (totalVertices - 1)) / 2) {
                    completeComponentsCount++;
                }
            }
        }

        return completeComponentsCount;
    }

    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> componentNodes) {
        visited[node] = true;
        componentNodes.add(node);

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, componentNodes);
            }
        }
    }
}