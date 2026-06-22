import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfBalloons(String s) {
        Map<Character, Integer> mp = new HashMap<>();
        Map<Character, Integer> mp2 = new HashMap<>();
        
        // Count frequencies of characters in the input string
        for (char ch : s.toCharArray()) {
            mp.put(ch, mp.getOrDefault(ch, 0) + 1);
        }
        
        int ans = Integer.MAX_VALUE;
        String target = "balloon";
        
        // Count frequencies of characters in the target string
        for (char ch : target.toCharArray()) {
            mp2.put(ch, mp2.getOrDefault(ch, 0) + 1);
        }
        
        // Calculate the maximum number of times "balloon" can be formed
        for (Map.Entry<Character, Integer> it : mp2.entrySet()) {
            char ch = it.getKey();
            int ct = it.getValue();
            
            if (!mp.containsKey(ch) || mp.get(ch) < ct) {
                return 0; 
            } else {
                ans = Math.min(ans, mp.get(ch) / ct);
            }
        }
        
        return ans; 
    }
}