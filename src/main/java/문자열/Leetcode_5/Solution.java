package 문자열.Leetcode_5;

public class Solution {

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        int start = 0;
        int end = 1;

        for (int i = 0; i < s.length(); i++) {
            int len1 = palindrome(s, i, i);
            int len2 = palindrome(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > end) {
                end = len;
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + end);

    }

    private int palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        return r - l - 1;
    }
}
