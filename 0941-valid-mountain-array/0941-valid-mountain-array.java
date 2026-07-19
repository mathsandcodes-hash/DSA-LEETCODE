class Solution {
    public boolean validMountainArray(int[] arr) {
         if (arr.length < 3) return false;
      int start = 0;
      int end = arr.length-1;
      while (start < end) {
        if (arr[start+1] > arr[start]) {
          start++;
        } else if (arr[end-1] > arr[end]) { 
          end--;
        } else {
          break;
        }
      }
      return start != 0 && end != arr.length-1 && start == end;
    }
}