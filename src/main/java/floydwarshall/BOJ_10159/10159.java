package floydwarshall.BOJ_10159;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[][] weight = new boolean[N + 1][N + 1];

        for(int i=0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            weight[a][b] = true;
        }

        for(int k=1;k<=N;k++) {
            for(int i=1;i<=N;i++) {
                if(!weight[i][k]) continue;
                for(int j=1;j<=N;j++) {
                    if(weight[k][j]) weight[i][j] = true;
                }
            }
        }

        for(int i=1;i<=N;i++) {
            int cnt = 0;
            for(int j=1;j<=N;j++) {
                if(i == j) continue;

                if(weight[i][j] || weight[j][i]) cnt++;
            }
            int not = (N - 1) - cnt;
            sb.append(not).append("\n");
        }

        System.out.println(sb);
    }
}