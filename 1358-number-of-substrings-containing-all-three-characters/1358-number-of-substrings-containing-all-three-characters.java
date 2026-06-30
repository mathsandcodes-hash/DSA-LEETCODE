class Solution {
    public int numberOfSubstrings(String s) {
        // Track the last seen positions of 'a', 'b', and 'c'
        int[] lastPos = {-1, -1, -1};
        int count = 0;
        
        for (int right = 0; right < s.length(); right++) {
            // Update the last seen index for the current character
            lastPos[s.charAt(right) - 'a'] = right;
            
            // If all three characters have been seen at least once
            if (lastPos[0] != -1 && lastPos[1] != -1 && lastPos[2] != -1) {
                // The smallest index among the three determines how many 
                // valid substrings can start before or at that index.
                int minPos = Math.min(lastPos[0], Math.min(lastPos[1], lastPos[2]));
                count += minPos + 1;
            }
        }
        
        return count;
    }
}