class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer,Integer> duplicate = new HashMap<>();
        for(int i =0;i<nums.length;i++){
            duplicate.put(nums[i],duplicate.getOrDefault(nums[i],0)+1);
        }
        for(int x: nums){
            if(duplicate.get(x)>=2){
                return true;
            }
        }
        return false;
    }
}