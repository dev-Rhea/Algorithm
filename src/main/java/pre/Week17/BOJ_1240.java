package pre.Week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1240 {
    static int N, M;
    static List<int[]>[] adjList;
    static int[][] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 노드
        adjList = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 거리 정보
        distances = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(distances[i], -1);
        }

        // 인접 리스트 생성
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            adjList[a].add(new int[]{b, d});
            adjList[b].add(new int[]{a, d});
        }

        // 모든 노드에서 다른 노드까지의 거리 계산
        for (int i = 1; i <= N; i++) {
            dfs(i, i, 0);
        }

        // 노드 간 거리 출력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            System.out.println(distances[u][v]);
        }
    }

    // DFS로 모든 노드에서 다른 노드까지의 거리 계산
    static void dfs(int start, int node, int dist) {
        distances[start][node] = dist; // 시작 노드에서 현재 노드까지의 거리 저장
        for (int[] neighbor : adjList[node]) { // 연결된 노드들 탐색
            int nextNode = neighbor[0]; // 다음노드
            int nextDist = neighbor[1]; // 다음노드까지의 거리
            if (distances[start][nextNode] == -1) { // 방문하지 않은 노드라면
                dfs(start, nextNode, dist + nextDist);
            }
        }
    }
}