package bfs.BOJ_4179;
import java.io.*;
import java.util.*;

public class Main {
    static int R, C, answer = Integer.MAX_VALUE;
    static char[][] graph;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> jihoon = new LinkedList<>(); // 지훈이의 좌표가 담긴 큐
    static Queue<int[]> fire = new LinkedList<>(); // 불의 좌표가 담긴 큐
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = input.charAt(j);
                if (graph[i][j] == 'J') {
                    jihoon.offer(new int[]{i, j});
                }
                if (graph[i][j] == 'F') {
                    fire.offer(new int[]{i, j});
                }
            }
        }
        br.close();

        int answer = 0;
        while (true) {
            answer++; // while 반복문을 1회 시행할 때 마다 1분이 지난것이므로 1씩 증가
            int fireSize = fire.size(); // 불의 좌표가 담긴 큐의 크기 == 불의 갯수
            while (fireSize > 0) { // 번져야 할 불의 개수만큼만 반복, 이미 번진 불은 번지는것을 반복하지 않음
                fireSize--;
                int[] tmp = fire.poll();
                int x = tmp[0];
                int y = tmp[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                        if (graph[nx][ny] == '.' || graph[nx][ny] == 'J') { // 불이 번진 위치가 벽이 아니라면
                            fire.offer(new int[]{nx, ny}); // 새로운 불의 좌표를 큐에 담는다
                            graph[nx][ny] = 'F'; // 번진만큼 지도를 갱신
                        }
                    }
                }
            }

            int jihoonSize = jihoon.size();
            while (jihoonSize > 0) { // 지훈이의 좌표가 담긴 큐가 비어있지 않을 경우
                jihoonSize--; // 새로운 지훈이의 좌표갯수만큼만 반복, 이미 이동한 지훈이는 이동하는것을 또 반복하지 않음
                int[] tmp = jihoon.poll();
                int x = tmp[0];
                int y = tmp[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) { // 탈출을 했을 경우
                        System.out.println(answer);
                        return;
                    }
                    if (graph[nx][ny] == '.') { // 이동가능한 자리일 경우
                        jihoon.offer(new int[]{nx, ny}); // 이동한 위치의 좌표를 큐에 담는다
                        graph[nx][ny] = 'J'; // 이동한 위치를 J로 갱신 (사실 이 코드는 필요하지는 않음)
                    }
                }
            }
            if (jihoon.isEmpty()) { // 새로이동할 때마다 지훈이의 좌표가 담긴 큐 jihoon 이 늘어나는데, 이동이 불가능하면 큐가 비게 됨
                System.out.println("IMPOSSIBLE");
                return;
            }
        }
    }
}