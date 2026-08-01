public class Solution {
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] memo = new int[n][m];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return rec(s, t, 0, 0, memo);
    }

    private boolean rec(String s, String t, int i, int j, int[][] memo) {
        if (i == s.length()) return true;
        if (j == t.length()) return false;
        if (memo[i][j] != -1) return memo[i][j] == 1;
        if (s.charAt(i) == t.charAt(j)) {
            memo[i][j] = rec(s, t, i + 1, j + 1, memo) ? 1 : 0;
        } else {
            memo[i][j] = rec(s, t, i, j + 1, memo) ? 1 : 0;
        }
        return memo[i][j] == 1;
    }
}