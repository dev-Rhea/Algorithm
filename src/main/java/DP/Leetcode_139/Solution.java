package DP.Leetcode_139;

import java.util.List;

public class Solution {

    static class TrieNode {

        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            TrieNode now = root;

            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (now.children[idx] == null) {
                    now.children[idx] = new TrieNode();
                }
                now = now.children[idx];
            }
            now.isEnd = true;
        }

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            if (!dp[i]) {
                continue;
            }

            TrieNode now = root;
            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'a';
                if (now.children[idx] == null) {
                    break;
                }
                now = now.children[idx];
                if (now.isEnd) {
                    dp[j + 1] = true;
                }
            }
        }

        return dp[n];
    }
}
