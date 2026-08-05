public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        int num1 = -1, num2 = -1, cnt1 = 0, cnt2 = 0;

        for (int num : nums) {
            if (num == num1) {
                cnt1++;
            } else if (num == num2) {
                cnt2++;
            } else if (cnt1 == 0) {
                cnt1 = 1;
                num1 = num;
            } else if (cnt2 == 0) {
                cnt2 = 1;
                num2 = num;
            } else {
                cnt1--;
                cnt2--;
            }
        }

        cnt1 = cnt2 = 0;
        for (int num : nums) {
            if (num == num1) {
                cnt1++;
            } else if (num == num2) {
                cnt2++;
            }
        }

        List<Integer> res = new ArrayList<>();
        if (cnt1 > n / 3) res.add(num1);
        if (cnt2 > n / 3) res.add(num2);

        return res;
    }
}