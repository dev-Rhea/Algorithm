package dijkstra.BOJ_9370;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static class Node {
        int node, d;
        Node(int node, int d) {
            this.node = node;
            this.d = d;
        }
    }

    static int ghDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for(int test=0;test<T;test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            List<Node>[] map = new ArrayList[n+1];
            List<Integer> end = new ArrayList<>();

            for(int i=1;i<=n;i++) {
                map[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for(int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                map[a].add(new Node(b, d));
                map[b].add(new Node(a, d));

                if(a == g && b == h || a == h && b == g) ghDist = d;
            }

            int[] fromS = find(s, map, n);
            int[] fromG = find(g, map, n);
            int[] fromH = find(h, map, n);
            
            for(int i=0;i<t;i++) {
                int e = Integer.parseInt(br.readLine());
                
                int min = fromS[e];

                int gh = fromS[g] + ghDist + fromH[e];
                
                // s -> h -> g -> e 경로 길이
                int hg = fromS[h] + ghDist + fromG[e];
                
                // g-h 도로를 지나는 경로가 최단 경로와 같다면 추가
                if(min == gh || min == hg) {
                    end.add(e);
                }
            }
            Collections.sort(end);
            for(int c : end) {
                sb.append(c).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int[] find(int s, List<Node>[] map, int n) {
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (o1.d - o2.d));
        queue.add(new Node(s, 0));
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(dist[now.node] < now.d) continue;

            for(Node next : map[now.node]) {
                if(now.d + next.d < dist[next.node]) {
                    dist[next.node] = next.d + now.d;
                    queue.add(new Node(next.node, dist[next.node]));
                }
                
            }
        }
        return dist;
    }
}