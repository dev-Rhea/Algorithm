package bruteforce.BOJ_2422;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] comb = new boolean[N+1][N+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            comb[a][b] = true;
            comb[b][a] = true;
        }

        int ans = 0;
        for(int i=1;i<=N;i++) {
            for(int j=i+1;j<=N;j++) {
                for(int k = j+1;k<=N;k++) {
                    if(comb[i][j] || comb[j][k] || comb[k][i]) continue;
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}