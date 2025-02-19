package dijkstra.BOJ_10282;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, d, c;
    static int[] distance; // 도달 최소 시간 저장
    static boolean[] visited;
    static List<int[]>[] graph; // 인접 노드 정보
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int i=0;i<T;i++) {
            int av = 0, am = 0; // 감연 컴퓨터 수, 총 감염 시간
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph = new List[n+1];
            distance = new int[n+1];
            visited = new boolean[n+1];

            for(int j=1;j<=n;j++) {
                graph[j] = new ArrayList<>();
                distance[j] = INF;
            }
            distance[c] = 0; // 시작 노드 거리 0

            for(int k=0;k<d;k++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph[b].add(new int[] {a, s}); // b -> a 가는 시간 s
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
            pq.offer(new int[] {c, 0}); // 시작 노드, 거리

            while(!pq.isEmpty()) {
                int[] time = pq.poll(); // 가장 적은 시간 노드
                if(visited[time[0]]) continue;
                visited[time[0]] = true;
                av += 1;

                for(int[] j : graph[time[0]]) { // 인접 노드 순회
                    if(!visited[j[0]] && distance[j[0]] > distance[time[0]] + j[1]) { // 인접 노드 미방문 + 최단 시간 갱신
                        distance[j[0]] = distance[time[0]] + j[1];
                        pq.offer(new int[] {j[0], distance[j[0]]});
                    }
                }
            }

            // 감염시간 중 최댓값 찾기
            for(int j=1;j<=n;j++) {
                if(distance[j] != INF) {
                    am = Math.max(am, distance[j]);
                }
            }
            System.out.println(av + " " + am);
        }
    }
}
