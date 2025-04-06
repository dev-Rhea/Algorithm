package DP.BOJ_1943;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int coin;
        int n;

        Node(int coin, int n) {
            this.coin = coin;
            this.n = n;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i=0;i<3;i++) {
            int N = Integer.parseInt(br.readLine());
            List<Node> coins = new ArrayList<>();

            for(int j=0;j<N;j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
    
                coins.add(new Node(a, b));
            }

            int sum = 0;
            for(Node node : coins) {
                sum += node.coin * node.n;
            }

            if(sum % 2 == 0) System.out.println(calculate(coins, sum / 2) ? 1 : 0);
            else System.out.println(0);
        }
    }

    static boolean calculate(List<Node> coins, int sum) {
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for(Node node : coins) {
            int coin = node.coin;
            int cnt = node.n;

            for(int i=sum;i>=0;i--) {
                if(!dp[i]) continue;

                for(int j=1;j<=cnt;j++) {
                    int next = i + coin * j;
                    if(next > sum) break;
                    dp[next] = true;
                }
            }
        }
        return dp[sum];
    }
}