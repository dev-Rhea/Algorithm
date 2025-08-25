package bruteforce.BOJ_17089;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    
    static int N, M;
    static int ans = Integer.MAX_VALUE;
    static boolean[][] friends;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        friends = new boolean[N+1][N+1];
        cnt = new int[N+1];

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friends[a][b] = true;
            friends[b][a] = true;

            cnt[a]++;
            cnt[b]++;
        }

        find();

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void find() {
        for(int i=1;i<=N;i++) {
            for(int j=i+1;j<=N;j++) {
                if(friends[i][j]) {
                    for(int k=j+1;k<=N;k++) {
                        if(friends[i][k] && friends[j][k]) ans = Math.min(ans, cnt[i]+ cnt[j] + cnt[k] - 6);
                    }
                }
            }
        }
    }
}