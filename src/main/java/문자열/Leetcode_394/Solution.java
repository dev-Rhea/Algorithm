package 문자열.Leetcode_394;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public String decodeString(String s) {
        Deque<Integer> count = new ArrayDeque<>();
        Deque<StringBuilder> str = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();
        int k = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                count.push(k);
                str.push(sb);
                k = 0;
                sb = new StringBuilder();
            } else if (c == ']') {
                int repeat = count.pop();
                StringBuilder prev = str.pop();

                for (int j = 0; j < repeat; j++) {
                    prev.append(sb);
                }
                sb = prev;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
