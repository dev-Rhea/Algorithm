package pre.Week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16928 {
    static int N, M;
    static int[] map = new int[101];
    static boolean[] visit = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        // 사다리 입력
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            map[start] = end;
        }

        // 뱀 입력
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            map[start] = end;
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0}); // 이동
        visit[1] = true; // 1번 칸 방문 처리

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int position = current[0]; // 현재 위치
            int moves = current[1]; // 이동 위치

            // 주사위 수 만큼
            for (int i = 1; i <= 6; i++) {
                int next = position + i; // 현재 위치 + 주사위수
                if (next > 100) continue; // 100을 넘어가면 안됨
                if (visit[next]) continue; // 이미 방문한 칸이면 안됨
                visit[next] = true; // 방문 처리
                if (map[next] != 0) { // 사다리나 뱀이 있는 경우
                    next = map[next];
                }
                if (next == 100) { // 100에 도착한 경우 종료
                    return moves + 1; // 이동 횟수 반환
                }
                queue.add(new int[]{next, moves + 1});
            }
        }
        return -1;
    }
}