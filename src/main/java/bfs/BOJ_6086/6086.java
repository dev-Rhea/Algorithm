package bfs.BOJ_6086;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[][] flow, pipes;
    static boolean[] visited;
    static final int MAX = 52;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        flow = new int[MAX][MAX];
        pipes = new int[MAX][MAX];

        for(int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char p1 = st.nextToken().charAt(0);
            char p2 = st.nextToken().charAt(0);
            int v = Integer.parseInt(st.nextToken());

            int from = 0;
            int to = 0;

            if(p1 >= 'A' && p1 <= 'Z') {
                from = p1 - 'A';
            }
            else {
                from = p1 - 'a' + 26;
            }
            
            if(p2 >= 'A' && p2 <= 'Z') {
                to = p2 - 'A';
            }
            else {
                to = p2 - 'a' + 26;
            }

            pipes[from][to] += v;
            pipes[to][from] += v;
        }

        int ans = calculate(0, 25);
        System.out.println(ans);
    }

    static int calculate(int source, int sink) {
        int total = 0;

        while(true) {
            visited = new boolean[MAX];
            int[] parent = new int[MAX];
            Arrays.fill(parent, -1);

            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);
            visited[source] = true;

            while(!queue.isEmpty() && !visited[sink]) {
                int now = queue.poll();

                for(int next = 0;next<MAX;next++) {
                    if(!visited[next] && pipes[now][next] - flow[now][next] > 0) {
                        queue.add(next);
                        visited[next] = true;
                        parent[next] = now;
                    }
                }
            }

            if(!visited[sink]) break;

            int min = Integer.MAX_VALUE;
            for(int i=sink;i!=source;i=parent[i]) {
                int prev = parent[i];
                min = Math.min(min, pipes[prev][i] - flow[prev][i]);
            }

            for(int i=sink;i!=source;i=parent[i]) {
                int prev = parent[i];
                flow[prev][i] += min;
                flow[i][prev] -= min;
            }
            total+=min;
        }
        return total;
    }
}