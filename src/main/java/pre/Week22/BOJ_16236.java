package pre.Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16236 {
    static int N, ans;
    static int[][] map;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        Queue<Node> queue = new LinkedList<>();

        // 초기 아기 상어 위치
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    queue.add(new Node(i, j, 0));
                    map[i][j] = 0; // 상어 초기 위치를 빈 칸으로 설정
                }
            }
        }

        int eat = 0;
        ans = 0;
        int size = 2;

        while (true) {
            LinkedList<Node> fish = new LinkedList<>();
            int[][] distance = new int[N][N];
            boolean[][] visited = new boolean[N][N];  // 방문 여부 체크 배열 초기화
            Queue<Node> tempQueue = new LinkedList<>(queue);  // 큐를 복사해서 사용

            while (!tempQueue.isEmpty()) {
                Node current = tempQueue.poll();
                int x = current.x;
                int y = current.y;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && map[nx][ny] <= size) {
                        visited[nx][ny] = true;
                        distance[nx][ny] = distance[x][y] + 1;
                        tempQueue.add(new Node(nx, ny, distance[nx][ny]));

                        if (map[nx][ny] != 0 && map[nx][ny] < size) {
                            fish.add(new Node(nx, ny, distance[nx][ny]));
                        }
                    }
                }
            }

            if (fish.isEmpty()) {
                System.out.println(ans);
                return;
            }

            // 가장 가까운 물고기 선택 (우선순위: 거리 -> 위쪽 -> 왼쪽)
            Node currentFish = fish.get(0);
            for (int i = 1; i < fish.size(); i++) {
                Node newFish = fish.get(i);
                if (currentFish.distance > newFish.distance) {
                    currentFish = newFish;
                } else if (currentFish.distance == newFish.distance) {
                    if (currentFish.x > newFish.x) {
                        currentFish = newFish;
                    } else if (currentFish.x == newFish.x && currentFish.y > newFish.y) {
                        currentFish = newFish;
                    }
                }
            }

            // 물고기 먹기
            ans += currentFish.distance;
            eat++;
            map[currentFish.x][currentFish.y] = 0;

            // 상어 크기 증가 조건
            if (eat == size) {
                size++;
                eat = 0;
            }

            // 새로운 상어 위치에서 탐색 재시작
            queue.clear();
            queue.add(new Node(currentFish.x, currentFish.y, 0));
        }
    }

    public static class Node {
        int x, y, distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
