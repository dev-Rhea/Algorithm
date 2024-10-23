package Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_8979 {

    static int N, K;
    static HashMap<Integer, Integer[]> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][4];
        int idx = 0;
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            if (arr[i][0] == K) {
                idx = i; // K번 국가의 인덱스
            }
        }

        for (int i = 0; i < N; i++) {
            if (idx == i) {
                continue;
            }

            for (int j = 1; j < 4; j++) { // 메달 수 비교
                if (arr[i][j] > arr[idx][j]) {
                    cnt++;
                    break;
                } else if (arr[i][j] < arr[idx][j]) {
                    break;
                }
            }
        }
        System.out.println(cnt + 1); // K 번 국가 등수
    }
}
