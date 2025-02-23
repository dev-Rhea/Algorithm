package sort.BOJ_1766;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] degree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        degree = new int[N + 1];

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b); // 노드간 연결관계 추가
            degree[b]++; // 진입 차수 증가
        }

        Queue<Integer> pq = new PriorityQueue();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) { // 진입 차수가 0인 노드를 우선순위 큐에 추가
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now).append(" ");

            for (int next : graph.get(now)) {
                degree[next]--;
                if (degree[next] == 0) {
                    pq.add(next);
                }
            }
        }
        System.out.println(sb);
    }
}
