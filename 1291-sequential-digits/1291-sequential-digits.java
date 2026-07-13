import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";
        
        // Loop through all possible lengths of sequential numbers (from 2 to 9 digits)
        for (int length = 2; length <= 9; length++) {
            // Slide a window of the current 'length' across the digits string
            for (int start = 0; start <= 9 - length; start++) {
                String sub = digits.substring(start, start + length);
                int num = Integer.parseInt(sub);
                
                // If the generated number falls within the range, add it to the list
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}