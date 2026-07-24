class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] present = new boolean[2048];
        int[] unique = new int[2048];
        int size = 0;
        
        for (int num : nums) {
            if (!present[num]) {
                present[num] = true;
                unique[size++] = num;
            }
        }
        
        boolean[] s2 = new boolean[2048];
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                s2[unique[i] ^ unique[j]] = true;
            }
        }
        
        boolean[] s3 = new boolean[2048];
        int count = 0;
        for (int i = 0; i < 2048; i++) {
            if (s2[i]) {
                for (int j = 0; j < size; j++) {
                    int xor = i ^ unique[j];
                    if (!s3[xor]) {
                        s3[xor] = true;
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
}