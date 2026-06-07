package binarysearch.Leetcode_1923;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 998_244_353L;
    private static final long BASE1 = 100003L;
    private static final long BASE2 = 200003L;

    public int longestCommonSubpath(int n, int[][] paths) {
        int l = 0;
        int h = Integer.MAX_VALUE;
        for (int[] p : paths) {
            h = Math.min(h, p.length);
        }

        while (l < h) {
            int mid = (l + h + 1) / 2;
            if (check(mid, paths)) {
                l = mid;
            } else {
                h = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int len, int[][] paths) {
        Set<Long> common = null;

        for (int[] path : paths) {
            Set<Long> now = getCommon(path, len);

            if (common == null) {
                common = now;
            } else {
                common.retainAll(now);
            }

            if (common.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    private Set<Long> getCommon(int[] path, int len) {
        Set<Long> set = new HashSet<>();
        long h1 = 0;
        long h2 = 0;
        long pow1 = 1;
        long pow2 = 1;

        for (int i = 0; i < len - 1; i++) {
            pow1 = pow1 * BASE1 % MOD1;
            pow2 = pow2 * BASE2 % MOD2;
        }

        for (int i = 0; i < path.length; i++) {
            long val = path[i] + 1;
            h1 = (h1 * BASE1 + val) % MOD1;
            h2 = (h2 * BASE2 + val) % MOD2;

            if (i >= len) {
                long rem = path[i - len] + 1;
                h1 = (h1 - rem * pow1 % MOD1 * BASE1 % MOD1 + MOD1 * 2) % MOD1;
                h2 = (h2 - rem * pow2 % MOD2 * BASE2 % MOD2 + MOD2 * 2) % MOD2;
            }

            if (i >= len - 1) {
                set.add(h1 * MOD2 + h2);
            }
        }
        return set;
    }
}
