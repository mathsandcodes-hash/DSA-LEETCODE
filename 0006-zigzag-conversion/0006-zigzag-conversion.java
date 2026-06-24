class Solution {
    public String convert(String s, int numRows) {
        // Edge case: No zigzag needed
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        int n = s.length();
        StringBuilder result = new StringBuilder(n); // Pre-allocate exact capacity
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                // 1. Add the primary vertical character for this row
                result.append(s.charAt(j + i));
                
                // 2. Add the secondary diagonal character for inner rows
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    result.append(s.charAt(j + cycleLen - i));
                }
            }
        }
        
        return result.toString();
    }
}