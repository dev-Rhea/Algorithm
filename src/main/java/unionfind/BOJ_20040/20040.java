package unionfind.BOJ_20040;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[] parent, check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[6];

        parent = new int[n];
        check = new int[n];

        for(int i=0;i<n;i++) {
            parent[i] = i;
        }

        for(int i=1;i<=m;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(find(u) == find(v)) {
                System.out.println(i);
                return;
            }

            union(u, v);
        }

        System.out.println(0);
    }

    static void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if(pu == pv) return;
        
        if(check[pu] < check[pv]) parent[pu] = pv;
        else if(check[pu] > check[pv]) parent[pv] = pu;
        else {
            parent[pv] = pu;
            check[pu]++;
        }
    }

    static int find(int n) {
        if(parent[n] != n) parent[n] = find(parent[n]);
        return parent[n];
    }
}