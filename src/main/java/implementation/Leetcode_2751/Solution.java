package implementation.Leetcode_2751;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Solution {

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (a, b) -> positions[a] - positions[b]);

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i : idx) {
            if (directions.charAt(i) == 'R') {
                stack.push(i);
            } else {
                while (!stack.isEmpty()) {
                    int r = stack.peek();
                    if (healths[r] > healths[i]) {
                        healths[r]--;
                        healths[i] = 0;
                        break;
                    } else if (healths[r] < healths[i]) {
                        healths[i]--;
                        healths[r] = 0;
                        stack.pop();
                    } else {
                        healths[r] = 0;
                        healths[i] = 0;
                        stack.pop();
                        break;
                    }
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (healths[i] > 0) {
                ans.add(healths[i]);
            }
        }
        return ans;
    }
}
