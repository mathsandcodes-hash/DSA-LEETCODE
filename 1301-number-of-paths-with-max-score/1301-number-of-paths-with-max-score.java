import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
        
        // dpScore[r][c] holds the max score from 'S' to (r, c)
        int[][] dpScore = new int[n][n];
        
        // dpPaths[r][c] holds the number of valid paths to get the max score to (r, c)
        int[][] dpPaths = new int[n][n];
        
        // Base case: Start at bottom-right
        dpPaths[n - 1][n - 1] = 1;
        
        // Directions we can arrive FROM: Down, Right, Down-Right
        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
        
        // Traverse from bottom-right to top-left
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                char currentCell = board.get(r).charAt(c);
                
                // Skip obstacles and the starting square itself
                if (currentCell == 'X' || currentCell == 'S') {
                    continue;
                }
                
                int maxPrevScore = -1;
                int ways = 0;
                
                // Check where we could have come from
                for (int[] dir : dirs) {
                    int prevR = r + dir[0];
                    int prevC = c + dir[1];
                    
                    // If within bounds and the previous cell is reachable
                    if (prevR < n && prevC < n && dpPaths[prevR][prevC] > 0) {
                        if (dpScore[prevR][prevC] > maxPrevScore) {
                            maxPrevScore = dpScore[prevR][prevC];
                            ways = dpPaths[prevR][prevC];
                        } else if (dpScore[prevR][prevC] == maxPrevScore) {
                            ways = (ways + dpPaths[prevR][prevC]) % MOD;
                        }
                    }
                }
                
                // If the current cell is reachable, calculate its score and paths
                if (ways > 0) {
                    dpPaths[r][c] = ways;
                    int cellValue = (currentCell == 'E') ? 0 : currentCell - '0';
                    dpScore[r][c] = maxPrevScore + cellValue;
                }
            }
        }
        
        // Return max score and number of paths to the top-left cell 'E'
        return new int[]{dpScore[0][0], dpPaths[0][0]};
    }
}