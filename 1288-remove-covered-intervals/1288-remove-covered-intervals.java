import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort by start point ascending. 
        // If start points are equal, sort by end point descending.
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        
        int remainingCount = 0;
        int maxEnd = 0;
        
        for (int[] interval : intervals) {
            int end = interval[1];
            
            // If the current interval's end extends past maxEnd,
            // it is not covered by any previous interval.
            if (end > maxEnd) {
                remainingCount++;
                maxEnd = end;
            }
        }
        
        return remainingCount;
    }
}