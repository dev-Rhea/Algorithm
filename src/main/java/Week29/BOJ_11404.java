package Week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11404 {
    static int N, M;
    static int[][] cost;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        cost = new int[N+1][N+1];

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                cost[i][j] = INF;
                if(i == j) {
                    cost[i][j] = 0;
                }
            }
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            cost[a][b] = Math.min(cost[a][b], price);
        }

        for(int k=1;k<=N;k++) {
            for(int i=1;i<=N;i++) {
                for(int j=1;j<=N;j++) {
                    if(cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                    }
                }
            }
        }

        for(int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) {
                if (cost[i][j] == INF) {
                    cost[i][j] = 0;
                }
                System.out.print(cost[i][j] + " ");
            }
            System.out.println();
        }

    }

}
