package MST.prim.BOJ_1774;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static class Node {
        long x, y;
        Node(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static Node[] graph;
    static double[] dist;
    static double[][] adj;
    static double sum;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new Node[N+1];
        dist = new double[N+1];
        Arrays.fill(dist, Double.MAX_VALUE);
        adj = new double[N+1][N+1];
        sum = 0;
        visited = new boolean[N+1];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            graph[i] = new Node(x, y);
        }

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(i == j) continue;
                double d = cal(graph[i], graph[j]);
                adj[i][j] = d;
            }
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a][b] = 0;
            adj[b][a] = 0;
        }

        prim();

        System.out.printf("%.2f\n", sum);
    }

    static void prim() {
        dist[1] = 0;
        
        for(int k=0;k<N;k++) {
            int idx = -1;
            double min = Double.MAX_VALUE;
            
            for(int i=1;i<=N;i++) {
                if(!visited[i] && dist[i] < min) {
                    min = dist[i];
                    idx = i;
                }
            }
            
            if(idx == -1) break;
            
            visited[idx] = true;
            sum += min;
            
            for(int i=1;i<=N;i++) {
                if(!visited[i]) {
                    dist[i] = Math.min(dist[i], adj[idx][i]);
                }
            }
        }
    }

    static double cal(Node n1, Node n2) {
        long x = n2.x - n1.x;
        long y = n2.y - n1.y;
        long temp = (x * x) + (y * y);

        return Math.sqrt(temp);
    }
}