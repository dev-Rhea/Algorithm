package unionfind.BOJ_1043;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int[] parent; // 진실 노드 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for(int i=0;i<=N;i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int i=0;i<T;i++) {
            int t = Integer.parseInt(st.nextToken());
            union(0, t);
        }

        List<int[]> party = new ArrayList<>();
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int[] list = new int[cnt];
            for(int j=0;j<cnt;j++) list[j] = Integer.parseInt(st.nextToken());

            party.add(list);

            for(int j=1;j<cnt;j++) {
                union(list[0], list[j]);
            }
        }

        int ans = 0;
        for(int[] list : party) {
            if(find(list[0]) != find(0)) ans++;
        }

        System.out.println(ans);
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if(ra != rb) parent[rb] = ra;
    }
}