package MST.prim.BOJ_10021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Tree {
        int idx;
        long cost;
        Tree(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(x, y);
        }

        boolean[] visited = new boolean[N];
        long[] min = new long[N];

        Arrays.fill(min, Long.MAX_VALUE);

        Queue<Tree> pq = new PriorityQueue<>((o1, o2) -> (int)(o1.cost - o2.cost));
        min[0] = 0;
        pq.offer(new Tree(0, 0));

        long sum = 0;
        int connection = 0;

        while(!pq.isEmpty()) {
            Tree now = pq.poll();

            if(visited[now.idx]) continue;

            visited[now.idx] = true;
            sum += now.cost;
            connection++;

            for(int i=0;i<N;i++) {
                if(!visited[i]) {
                    long dist = calculate(nodes[now.idx], nodes[i]);
                    if(dist >= C && dist < min[i]) {
                        min[i] = dist;
                        pq.offer(new Tree(i, dist));
                    }
                }
            }
        }
        if(connection != N) System.out.println(-1);
        else System.out.println(sum);
    }

    static long calculate(Node a, Node b) {
        long dx = a.x - b.x;
        long dy = a.y - b.y;
        return dx*dx + dy*dy;
    }
    
}
