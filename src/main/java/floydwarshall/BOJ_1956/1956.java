package floydwarshall.BOJ_1956;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] dist = new int[V+1][V+1];

        for(int i=1;i<=V;i++) {
            Arrays.fill(dist[i], INF);
        }

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = c;
        }

        for(int i=1;i<=V;i++) {
            for(int j=1;j<=V;j++) {
                for(int k=1;k<=V;k++) {
                    if(dist[j][i] != INF && dist[i][k] != INF) {
                        dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                    }
                }
            }
        }

        int ans = INF;

        for(int i=1;i<=V;i++) {
            for(int j=1;j<=V;j++) {
                if(i != j && dist[i][j] != INF && dist[j][i] != INF) {
                    ans = Math.min(ans, dist[i][j] + dist[j][i]);
                }
            }
        }
        System.out.println(ans == INF ? -1 : ans);
    }
}