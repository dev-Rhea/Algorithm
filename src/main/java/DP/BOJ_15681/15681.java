package DP.BOJ_15681;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int N, R;
    static List<List<Integer>> tree;
    static int[] sub;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        sub = new int[N+1];

        tree = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            tree.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dfs(R);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<Q;i++) {
            int q = Integer.parseInt(br.readLine());

            sb.append(sub[q]).append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int root) {
        Deque<int[]> stack = new ArrayDeque<>();
        int[] parent = new int[N+1];
        int[] order = new int[N];
        int idx = 0;

        Arrays.fill(parent, -1);
        stack.push(new int[]{root, -1});

        while(!stack.isEmpty()) {
            int[] now = stack.pop();
            parent[now[0]] = now[1];
            order[idx++] = now[0];

            for(int next : tree.get(now[0])) {
                if(next != now[1]) stack.push(new int[]{next, now[0]});
            }
        }

        for(int i=idx-1;i>=0;i--) {
            int v = order[i];
            sub[v] = 1;
            
            for(int next : tree.get(v)) {
                if(next != parent[v]) sub[v] += sub[next];
            }
        }
    }
}