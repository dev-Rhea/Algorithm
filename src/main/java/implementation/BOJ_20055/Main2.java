package implementation.BOJ_20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] belt = new int[2 * N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int first = 0, end = N - 1, step = 0;
        boolean[] robot = new boolean[N * 2];

        while (K > 0) {
            step++; // 회전 수 증가

            // 벨트 회전 시, 한칸씩 이동
            first = (2 * N + first - 1) % (2 * N);
            end = (first + N - 1) % (2 * N);

            // 내리는 위치에 로봇있으면, 내림
            if (robot[end]) {
                robot[end] = false;
            }

            for (int i = 1; i < N; i++) {
                int now = (first + N - 1 - i) % (2 * N); // 현재 로봇 위치
                int next = (now + 1) % (2 * N); // 이동하려는 위치

                // 내구도 남아 있고, 현재 위치에 로봇이 있으며 이동할 위치에 로봇이 없는 경우
                if (belt[next] > 0 && robot[now] && !robot[next]) {
                    robot[now] = false;
                    // 이동할 위치가 내리는 위치가 아니면 로봇 이동
                    if (next != end) {
                        robot[next] = true;
                    }
                    // 이동 후, 내구도 감소 & K 감소
                    if (--belt[next] == 0 && --K == 0) {
                        break;
                    }
                }
            }

            // 새로운 로봇 올림
            if (belt[first] > 0) {
                // 로봇 올리고 내구도 감소
                if (--belt[first] == 0 && --K == 0) {
                    break;
                }
                robot[first] = true;
            }
        }
        System.out.println(step);
    }
}
