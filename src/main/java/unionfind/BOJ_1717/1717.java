package unionfind.BOJ_1717;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for(int i=0;i<=n;i++) parent[i] = i;

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // if(t == 0) {
            //     union(a, b);
            // }
            // else if(t == 1) {
            //     if(find(a) == find(b)) sb.append("YES\n");
            //     else sb.append("NO\n");
            // }

            switch(t) {
                case 0:
                    union(a, b);
                    break;
                case 1:
                    if(find(a) == find(b)) sb.append("YES\n");
                    else sb.append("NO\n");
                    break;
            }
        }

        System.out.println(sb);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return;
        parent[b] = a;
    }

    static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}