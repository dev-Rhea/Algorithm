package DP.BOJ_10653;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static class Point{
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, K;
    static Point[] point;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        point = new Point[N+1];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            point[i] = new Point(a, b);
        }

        dp = new int[N+1][K+1];
        for(int i=0;i<=N;i++) {
            for(int j=0;j<=K;j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(cal(1, 0));
    }

    static int cal(int now, int skip) {
        if(now == N) return 0;

        if(dp[now][skip] != -1) return dp[now][skip];

        int min = Integer.MAX_VALUE;

        int visited = 0;

        int dist = Math.abs(point[now].x - point[now+1].x) + Math.abs(point[now].y - point[now+1].y);
        int next = cal(now+1, skip);
        
        if(next != Integer.MAX_VALUE) min = dist + next;

        if(skip<K && now+1<N) {
            for(int i=1;i<=K-skip&&now+i+1<=N;i++) {
                dist = Math.abs(point[now].x - point[now+1+i].x) + Math.abs(point[now].y - point[now+1+i].y);
                next = cal(now+1+i, skip+i);

                if(next != Integer.MAX_VALUE) min = Math.min(min, dist+next);
            }
        }
        return dp[now][skip] = min;
    }
}