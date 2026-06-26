import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        
        // A valid IP address string length must be between 4 and 12
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }
        
        backtrack(result, new ArrayList<>(), s, 0);
        return result;
    }

    private void backtrack(List<String> result, List<String> current, String s, int start) {
        // Base case: we found 4 parts
        if (current.size() == 4) {
            // If we also reached the end of the string, it's a valid IP
            if (start == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }

        // Try picking 1, 2, or 3 characters for the next segment
        for (int i = 1; i <= 3; i++) {
            // Prevent out-of-bounds
            if (start + i > s.length()) {
                break;
            }

            String segment = s.substring(start, start + i);
            
            if (isValid(segment)) {
                current.add(segment);                      // Choose
                backtrack(result, current, s, start + i); // Explore
                current.remove(current.size() - 1);       // Un-choose (Backtrack)
            }
        }
    }

    private boolean isValid(String segment) {
        // Check for leading zero in a multi-digit segment
        if (segment.length() > 1 && segment.startsWith("0")) {
            return false;
        }
        // Check if the integer value is between 0 and 255
        int val = Integer.parseInt(segment);
        return val >= 0 && val <= 255;
    }
}