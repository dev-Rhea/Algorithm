package greedy.Leetcode_309;

public class Solution {

    public int maxProfit(int[] prices) {
        int hold = -prices[0];
        int sold = 0;
        int rest = 0;

        for (int i = 1; i < prices.length; i++) {
            int nh = Math.max(hold, rest - prices[i]);
            int ns = hold + prices[i];
            int nr = Math.max(rest, sold);

            hold = nh;
            sold = ns;
            rest = nr;
        }

        return Math.max(sold, rest);
    }
}
