import java.util.*;

class Solution {
    // Function to set entire row and column to 0 if an element in the matrix is 0
    public void setZeroes(int[][] matrix) {
        // Get number of rows
        int m = matrix.length;
        // Get number of columns
        int n = matrix[0].length;

        // Create row marker array
        boolean[] row = new boolean[m];
        // Create column marker array
        boolean[] col = new boolean[n];

        // First pass: mark rows and columns that need to be zeroed
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If element is zero, mark its row and column
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        // Second pass: set cells to zero based on markers
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the row or column is marked, set cell to zero
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
