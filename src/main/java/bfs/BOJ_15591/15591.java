package bfs.BOJ_15591;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int v;
        int u;

        Node(int v, int u) {
            this.v = v;
            this.u = u;
        }
    }

    static List<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for(int i=0;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<N - 1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        for(int i=0;i<Q;i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(bfs(k, v, N)).append("\n");
        }
        System.out.println(sb);
    }

    static int bfs(int k, int video,  int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        visited[video] = true;
        queue.add(video);

        int cnt = 0;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(Node next : list[now]) {
                if(!visited[next.v] && next.u >= k) {
                    visited[next.v] = true;
                    queue.add(next.v);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}