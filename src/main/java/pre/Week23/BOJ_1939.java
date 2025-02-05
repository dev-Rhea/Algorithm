package pre.Week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1939 {
    static int N, M, ans;
    static List<Node>[] node;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        node = new ArrayList[N + 1];

        for(int i=1;i<=N;i++) {
            node[i] = new ArrayList<>();
        }

        int start = 0;
        int finish = 0;

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            node[A].add(new Node(B, W));
            node[B].add(new Node(A, W));
            finish = Math.max(finish, W);
        }

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        while (start <= finish) {
            int mid = (start + finish) / 2;
            visit = new boolean[N + 1];
            ans = -1;
            DFS(from, to, mid);

            if(ans != -1) {
                start = mid + 1;
            } else {
                finish = mid - 1;
            }
        }
        System.out.println(finish);
    }

    static void DFS(int s, int e, int limit) {
        if(s == e) {
            ans = s;
            return;
        }
        visit[s] = true;
        for(Node n : node[s]) {
            if(!visit[n.end] && limit <= n.weight) {
                DFS(n.end, e, limit);
            }
        }
    }

}

class Node {
    int end;
    int weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}