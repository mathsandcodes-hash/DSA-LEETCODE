import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        // Map to store the frequency of each valid substring of length minSize
        Map<String, Integer> substringCounts = new HashMap<>();
        
        // Array to track counts of unique characters in the current sliding window
        int[] charCounts = new int[26];
        int uniqueChars = 0;
        int maxOccurrence = 0;
        
        // 1. Initialize the first window of size minSize
        for (int i = 0; i < minSize; i++) {
            char ch = s.charAt(i);
            if (charCounts[ch - 'a'] == 0) {
                uniqueChars++;
            }
            charCounts[ch - 'a']++;
        }
        
        // If the first window is valid, record it
        if (uniqueChars <= maxLetters) {
            String sub = s.substring(0, minSize);
            substringCounts.put(sub, 1);
            maxOccurrence = 1;
        }
        
        // 2. Slide the window across the rest of the string
        for (int i = minSize; i < s.length(); i++) {
            // Add the new character entering the window from the right
            char newChar = s.charAt(i);
            if (charCounts[newChar - 'a'] == 0) {
                uniqueChars++;
            }
            charCounts[newChar - 'a']++;
            
            // Remove the old character leaving the window from the left
            char oldChar = s.charAt(i - minSize);
            charCounts[oldChar - 'a']--;
            if (charCounts[oldChar - 'a'] == 0) {
                uniqueChars--;
            }
            
            // 3. If the current window matches our criteria, process it
            if (uniqueChars <= maxLetters) {
                String sub = s.substring(i - minSize + 1, i + 1);
                int currentCount = substringCounts.getOrDefault(sub, 0) + 1;
                substringCounts.put(sub, currentCount);
                
                // Track the highest frequency seen so far
                maxOccurrence = Math.max(maxOccurrence, currentCount);
            }
        }
        
        return maxOccurrence;
    }
}