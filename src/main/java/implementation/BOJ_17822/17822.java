package implementation.BOJ_17822;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M, T;
    static int[][] circle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        circle = new int[N+1][M];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<T;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            rotation(x, d, k);
            check();
        }

        int sum = 0;
        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                if(circle[i][j] == 0) continue;
                sum += circle[i][j];
            }
        }

        System.out.println(sum);
    }

    static void rotation(int x, int d, int k) {
        for(int i=x;i<=N;i+=x) {
            int[] temp = new int[M];

            if(d == 0) {
                for(int j=0;j<M;j++) {
                    temp[(j+k)%M] = circle[i][j];
                }
            }
            else {
                for(int j=0;j<M;j++) {
                    temp[(j-k+M)%M] = circle[i][j];
                }
            }
            circle[i] = temp;
        }
    }

    static void check() {
        boolean[][] same = new boolean[N+1][M];
        boolean found = false;

        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                if(circle[i][j] == 0) continue;

                int next = (j+1) % M;
                if(circle[i][j] == circle[i][next] && circle[i][next] != 0) {
                    same[i][j] = true;
                    same[i][next] = true;
                    found = true;
                }
            }
        }

        for(int i=1;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(circle[i][j] == 0 || circle[i+1][j] == 0) continue;

                if(circle[i][j] == circle[i+1][j]) {
                    same[i][j] = true;
                    same[i+1][j] = true;
                    found = true;
                }
            }
        }

        if(found) {
            for(int i=1;i<=N;i++) {
                for(int j=0;j<M;j++) {
                    if(same[i][j]) circle[i][j] = 0;
                }
            }
        }
        else average();
    }

    static void average() {
        int sum = 0;
        int cnt = 0;

        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                if(circle[i][j] > 0) {
                    sum += circle[i][j];
                    cnt++;
                }
            }
        }

        if(cnt == 0) return;

        double avg = (double) sum / cnt;

        for(int i=1;i<=N;i++) {
            for(int j=0;j<M;j++) {
                if(circle[i][j] > 0) {
                    if(circle[i][j] > avg) circle[i][j]--;
                    else if(circle[i][j] < avg) circle[i][j]++;
                }
            }
        }
    }
}