import java.util.*;

class Solution {
    public int fib(int n) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        return helper(n, mp);
    }
    private int helper(int n, HashMap<Integer, Integer> mp) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (mp.containsKey(n)) {
            return mp.get(n);
        }
        int a1 = helper(n - 1, mp);
        int a2 = helper(n - 2, mp);
        int ans = a1 + a2;
        mp.put(n, ans);
        
        return ans;
    }
}