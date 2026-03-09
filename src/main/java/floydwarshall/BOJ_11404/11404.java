package floydwarshall.BOJ_11404;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] cost = new int[n+1][n+1];
        int INF = Integer.MAX_VALUE/2;
        for(int i=1;i<=n;i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;
        }

        for(int i=0;i<m;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            cost[a][b] = Math.min(cost[a][b], c);
        }        

        for(int k=1;k<=n;k++) {
            for(int i=1;i<=n;i++) {
                for(int j=1;j<=n;j++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                sb.append(cost[i][j] == INF ? 0 : cost[i][j]);
                if (j < n) sb.append(' ');
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}