package 문자열.Leetcode_880;

public class Solution {

    public String decodeAtIndex(String s, int k) {
        char[] str = s.toCharArray();
        int n = str.length;

        long size = 0;
        long kk = k;

        int i = 0;
        for (; i < n; i++) {
            char c = str[i];
            if (c >= '0' && c <= '9') {
                int d = c - '0';
                size *= d;
            } else {
                size++;
            }
            if (size >= kk) {
                break;
            }
        }

        char ans = 0;
        for (; i >= 0; i--) {
            char c = str[i];

            kk %= size;

            if (kk == 0 && !(c >= '0' && c <= '9')) {
                ans = c;
                break;
            }

            if (c >= '0' && c <= '9') {
                int d = c - '0';
                size /= d;
            } else {
                size--;
            }
        }

        return String.valueOf(ans);
    }
}
