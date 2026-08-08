package DP.Leetcode_2467;

import java.util.Arrays;

public class Solution {

    static int n, m;
    static int[] head, next, to;

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        n = amount.length;
        m = edges.length;

        head = new int[n];
        Arrays.fill(head, -1);
        next = new int[2 * m];
        to = new int[2 * m];

        int[] depth = new int[n];

        int idx = 0;
        for (int[] e : edges) {
            to[idx] = e[1];
            next[idx] = head[e[0]];
            head[e[0]] = idx++;

            to[idx] = e[0];
            next[idx] = head[e[1]];
            head[e[1]] = idx++;

            depth[e[0]]++;
            depth[e[1]]++;
        }

        int qh = 0, qt = 0;
        int[] parent = new int[n];
        int[] dep = new int[n];
        int[] order = new int[n];
        Arrays.fill(parent, -1);

        parent[0] = 0;
        dep[0] = 0;
        order[qt++] = 0;

        while (qh < qt) {
            int now = order[qh++];

            for (int i = head[now]; i != -1; i = next[i]) {
                int nxt = to[i];
                if (parent[nxt] != -1) {
                    continue;
                }

                parent[nxt] = now;
                dep[nxt] = dep[now] + 1;
                order[qt++] = nxt;
            }
        }

        int[] bd = new int[n];
        Arrays.fill(bd, Integer.MAX_VALUE);

        int t = 0;
        int now = bob;
        while (true) {
            bd[now] = t++;
            if (now == 0) {
                break;
            }
            now = parent[now];
        }

        int[] ad = new int[n];
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int nw = order[i];
            if (nw == 0) {
                ad[nw] = gain(amount, bd, nw, dep[nw]);
            } else {
                ad[nw] = ad[parent[nw]] + gain(amount, bd, nw, dep[nw]);
            }

            if (nw != 0 && depth[nw] == 1) {
                ans = Math.max(ans, ad[nw]);
            }
        }
        return ans;
    }

    private int gain(int[] am, int[] bd, int node, int time) {
        if (time < bd[node]) {
            return am[node];
        }
        if (time == bd[node]) {
            return am[node] / 2;
        }

        return 0;
    }
}
