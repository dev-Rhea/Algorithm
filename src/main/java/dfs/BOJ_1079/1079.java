package dfs.BOJ_1079;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, max = 0;
    static int[] guilty;
    static int[][] R;
    static boolean[] isDead;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        guilty = new int[N];
        isDead = new boolean[N];
        R = new int[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            guilty[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        M = Integer.parseInt(br.readLine());

        dfs(N, 0);
        System.out.println(max);
    }

    static void dfs(int remain, int night) {
        if (isDead[M] || remain == 1) {
            max = Math.max(max, night);
            return;
        }

        if (remain % 2 == 0) {
            for (int i = 0; i < N; i++) {
                if (isDead[i] || i == M) continue;

                isDead[i] = true;
                for (int j = 0; j < N; j++) {
                    if (!isDead[j]) guilty[j] += R[i][j];
                }

                dfs(remain - 1, night + 1);

                for (int j = 0; j < N; j++) {
                    if (!isDead[j]) guilty[j] -= R[i][j];
                }
                isDead[i] = false;
            }
        } else {
            int target = -1;
            int maxGuilty = -1000000;

            for (int i = 0; i < N; i++) {
                if (!isDead[i]) {
                    if (guilty[i] > maxGuilty) {
                        maxGuilty = guilty[i];
                        target = i;
                    }
                }
            }

            isDead[target] = true;
            dfs(remain - 1, night);
            isDead[target] = false;
        }
    }
}