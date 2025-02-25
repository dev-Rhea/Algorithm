package implementation.BOJ_20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] belt;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[2 * N + 1];
        robot = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 1;

        while (true) {
            int temp = belt[2 * N];

            // 벨트 이동
            for (int i = N * 2; i > 1; i--) {
                belt[i] = belt[i - 1];
            }
            belt[1] = temp;

            // 로봇 이동
            for (int i = N; i > 1; i--) {
                robot[i] = robot[i - 1];
            }
            robot[1] = false;

            // 내리는 위치에 로봇이 있는 경우, 내린다.
            if (robot[N]) {
                robot[N] = false;
            }

            // 이동 가능한 로봇 이동 && 옆 칸에 로봇이 없고 내구도가 1 이상
            for (int i = N - 1; i > 0; i--) {
                if (robot[i] && !robot[i + 1] && belt[i + 1] >= 1) {
                    belt[i + 1]--;
                    robot[i] = false;
                    robot[i + 1] = true;
                }
            }

            // 로봇 내리기
            if (robot[N]) {
                robot[N] = false;
            }

            // 내구도가 0이 아니면 로봇 올리기
            if (belt[1] > 0) {
                robot[1] = true;
                belt[1]--;
            }

            // 내구도가 0인 칸의 개수가 K개 이상이면 종료, 아니면 과정 반복
            int cnt = 0;
            for (int i = 1; i <= N * 2; i++) {
                if (belt[i] == 0) {
                    cnt++;
                }

                if (cnt >= K) {
                    System.out.println(ans);
                    return;
                }
            }
            ans++;
        }
    }
}
