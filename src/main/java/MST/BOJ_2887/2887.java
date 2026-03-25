package MST.BOJ_2887;
import java.io.*;
import java.util.*;

class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] planet = new int[N][4];
        parent = new int[N];
        
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            planet[i][0] = Integer.parseInt(st.nextToken());
            planet[i][1] = Integer.parseInt(st.nextToken());
            planet[i][2] = Integer.parseInt(st.nextToken());
            planet[i][3] = i;
            parent[i] = i;
        }

        List<int[]> edges = new ArrayList<>();

        for(int i=0;i<3;i++) {
            int ax = i;
            Arrays.sort(planet, (a, b) -> (a[ax] - b[ax]));
            
            for(int j=0;j<N-1;j++) {
                int cost = Math.abs(planet[j][ax] - planet[j+1][ax]);
                edges.add(new int[]{cost, planet[j][3], planet[j+1][3]});
            }
        }

        edges.sort((a, b) -> Integer.compare(a[0], b[0]));

        long total = 0;
        int cnt = 0;

        for(int[] e : edges) {
            int cost = e[0];
            int a = e[1];
            int b = e[2];

            if(find(a) != find(b)) {
                union(a, b);
                total += cost;
                if(++cnt == N-1) break;
            } 
        }

        System.out.println(total);

    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa != pb) parent[pb] = pa;
    }

    static int find(int n) {
        if(parent[n] != n) parent[n] = find(parent[n]);

        return parent[n];
    }
}