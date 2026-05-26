package binarysearch.Leetcode_300;

public class Solution {

    public int lengthOfLIS(int[] nums) {
        int[] mins = new int[nums.length];
        int size = 0;

        for (int n : nums) {
            int l = 0;
            int h = size;

            while (l < h) {
                int mid = (l + h) / 2;
                if (mins[mid] < n) {
                    l = mid + 1;
                } else {
                    h = mid;
                }
            }
            mins[l] = n;
            if (l == size) {
                size++;
            }
        }
        return size;
    }
}
