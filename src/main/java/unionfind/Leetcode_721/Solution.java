package unionfind.Leetcode_721;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    static int[] parent, rank;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        List<List<String>> ans = new ArrayList<>();
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Map<String, Integer> email = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String e = accounts.get(i).get(j);
                if (email.containsKey(e)) {
                    union(i, email.get(e));
                } else {
                    email.put(e, i);
                }
            }
        }

        Map<Integer, List<String>> merged = new HashMap<>();
        for (Map.Entry<String, Integer> entry : email.entrySet()) {
            int root = find(entry.getValue());
            merged.computeIfAbsent(root, k -> new ArrayList<>()).add(entry.getKey());
        }

        for (Map.Entry<Integer, List<String>> entry : merged.entrySet()) {
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            List<String> account = new ArrayList<>();
            account.add(accounts.get(entry.getKey()).get(0));
            account.addAll(emails);
            ans.add(account);
        }

        return ans;
    }

    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return;
        }

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }
    }

    private int find(int p) {
        if (parent[p] != p) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
}
