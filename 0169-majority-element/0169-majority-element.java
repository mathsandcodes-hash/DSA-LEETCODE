class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<=nums.length-1;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        for(int x: nums){
            if(map.get(x)>n/2){
                return x;
            }
        }
        return 0;
    }
}