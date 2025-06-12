package MST.BOJ_1647;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static class Node {
        int A, B, cost;

        Node(int A, int B, int cost) {
            this.A = A;
            this.B = B;
            this.cost = cost;
        }
    }

    static Queue<Node> map;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new PriorityQueue<>((o1, o2) -> (o1.cost - o2.cost));
        parent = new int[N+1];

        for(int i=0;i<N;i++) {
            parent[i] = i;
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken()); 
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map.add(new Node(a, b, c));
        }
        
        int sum = 0;
        int max = -1;

        while(!map.isEmpty()) {
            Node node = map.poll();
            if(find(node.A) != find(node.B)) {
                union(node.A, node.B);
                sum += node.cost;
                max = Math.max(node.cost, max);
            }
        }

        sum -= max;
        System.out.println(sum); 
    }

    static void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);

        if(p1 <= p2) parent[p2] = parent[p1];
        else parent[p1] = parent[p2];
    }

    static int find(int p) {
        if(parent[p] != p) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
}