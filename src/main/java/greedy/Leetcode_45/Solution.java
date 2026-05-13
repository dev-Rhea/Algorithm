package greedy.Leetcode_45;

public class Solution {

    public int jump(int[] nums) {
        int cnt = 0;
        int now = 0;
        int next = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            next = Math.max(next, i + nums[i]);

            if (i == now) {
                cnt++;
                now = next;
            }
        }

        return cnt;
    }
}
