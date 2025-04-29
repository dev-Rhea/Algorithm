package MST.BOJ_1197;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static class Node {
        int node, value;
        Node(int node, int value) {
            this.node = node;
            this.value = value;
        }
    }

    static int V, E;
    static List<List<Node>> graph;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0;i<=V;i++) graph.add(new ArrayList<>());

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        System.out.println(prim());
    }

    static int prim() {
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (o1.value - o2.value));
        boolean[] visited = new boolean[V+1];

        queue.add(new Node(1, 0));

        int sum = 0;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(visited[now.node]) continue;

            visited[now.node] = true;
            sum += now.value;

            for(Node next : graph.get(now.node)) {
                if(!visited[next.node]) queue.add(new Node(next.node, next.value));
            }
        }
        return sum;
    }
}