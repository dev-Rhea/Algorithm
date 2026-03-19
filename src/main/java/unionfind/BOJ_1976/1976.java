package unionfind.BOJ_1976;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[] parent, depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        depth = new int[N+1];
        for(int i=1;i<=N;i++) {
            parent[i] = i;
        }

        StringTokenizer st;
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                if(Integer.parseInt(st.nextToken()) == 1) union(i, j);
            }
        }

        int[] plan = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        boolean possible = true;
        int root = find(plan[0]);
        for(int i=1;i<M;i++) {
            if(find(plan[i]) != root) {
                possible = false;
                break;
            }
        }

        System.out.println(possible ? "YES":"NO");
    }

    static int find(int c) {
        if(parent[c] != c) parent[c] = find(parent[c]);
        return parent[c];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return;
        if(depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        parent[b] = a;
        if(depth[a] == depth[b]) depth[a]++;
    }
}