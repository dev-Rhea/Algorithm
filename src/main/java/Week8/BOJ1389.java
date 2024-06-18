package Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1389 {
    static int N, M;
    static int INF = 1000000; // Integer.MAX_VALUE 대신 충분히 큰 값 사용
    static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        distance = new int[N+1][N+1];

        // distance 배열 초기화
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i != j) {
                    distance[i][j] = INF;
                }
            }
        }

        // 엣지 읽기, distance 배열 업데이트
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            distance[from][to] = distance[to][from] = 1;
        }

        // 플로이드-워셜 알고리즘
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        int minIndex = 0;
        int minSum = INF;

        // 케빈 베이컨 수가 가장 작은 사람 찾기
        for(int i = 1; i <= N; i++) {
            int sum = 0;
            for(int j = 1; j <= N; j++) {
                sum += distance[i][j];
            }
            if(sum < minSum) {
                minSum = sum;
                minIndex = i;
            }
        }

        System.out.println(minIndex);
    }
}
