package DP.BOJ_2533;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> tree = new ArrayList<>();
        
        for(int i=0;i<=N;i++) {
            tree.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        int[] nodaptor = new int[N+1];
        int[] adaptor = new int[N+1];
        Arrays.fill(adaptor, 1);

        int[] parent = new int[N+1];
        int[] order = new int[N+1];
        boolean[] visited = new boolean[N+1];

        int idx = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        parent[1] = -1;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            order[idx++] = now;

            for(int next : tree.get(now)) {
                if(!visited[next]) {
                    visited[next] = true;
                    parent[next] = now;
                    queue.add(next);
                }
            }
        }

        // 리프 -> 루트 
        for(int i=idx-1;i>=0;i--) {
            int v = order[i];
            for(int child : tree.get(v)) {
                if(child == parent[v]) continue;
                nodaptor[v] += adaptor[child];
                adaptor[v] += Math.min(nodaptor[child], adaptor[child]);
            }
        }

        System.out.println(Math.min(adaptor[1], nodaptor[1]));
    }
}