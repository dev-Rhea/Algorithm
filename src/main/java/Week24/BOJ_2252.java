package Week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252 {
    static int N, M;
    static ArrayList<Integer>[] heights;
    static int[] degree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        heights = new ArrayList[N+1];

        // 키 정보 입력
        for(int i=0;i<=N;i++) {
            heights[i] = new ArrayList<>();
        }

        degree = new int[N+1];
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            heights[start].add(end);
            degree[end]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[N+1];
        for(int i=1;i<degree.length;i++) {
            if(degree[i] == 0) {
                queue.add(i);
                visited[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for(int h : heights[now]) {
                degree[h]--;
            }

            for(int i=1;i<degree.length;i++) {
                if(!visited[i] && degree[i] == 0) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
            sb.append(now).append(" ");
        }
        System.out.println(sb);
    }
}
