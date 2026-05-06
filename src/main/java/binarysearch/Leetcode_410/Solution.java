package binarysearch.Leetcode_410;

public class Solution {

    public int splitArray(int[] nums, int k) {
        long start = 0;
        long end = 0;
        for (int n : nums) {
            start = Math.max(start, n);
            end += n;
        }

        long ans = end;
        while (start <= end) {
            long mid = (start + end) / 2;
            int right = 0;
            int left = 0;

            if (isPossible(nums, k, mid)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return (int) ans;
    }

    private boolean isPossible(int[] nums, int k, long end) {
        int cnt = 1;
        long sum = 0;

        for (int n : nums) {
            if (sum + n > end) {
                cnt++;
                sum = n;
                if (cnt > k) {
                    return false;
                }
            } else {
                sum += n;
            }
        }
        return true;
    }
}
