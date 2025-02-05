package DP.BOJ_1949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        int x; // 주민 수
        ArrayList<Integer> map = new ArrayList<>(); // 인접 마을 리스트
        public Node(int x) {
            this.x = x;
        }
    }
    static int N;
    static Node[] graph;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new Node[N + 1];
        dp = new int[N+1][2];

        graph[0] = new Node(0); // 가상의 루트 노드 지정
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++) {
            graph[i] = new Node(Integer.parseInt(st.nextToken()));
        }
        graph[0].map.add(1); // 가상의 루트노드와 1번 노드 연결
        graph[1].map.add(0);

        // 연결 관계 저장
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].map.add(b);
            graph[b].map.add(a);
        }

        DFS(0, -1);
        System.out.println(dp[0][0]);
    }

    private static void DFS(int node, int pre) {

        for(int i : graph[node].map) {
            if(i != pre) {
                DFS(i, node);
                dp[node][1] += dp[i][0];
                dp[node][0] += Math.max(dp[i][0], dp[i][1]);
            }
        }
        dp[node][1] += graph[node].x;
    }
}
