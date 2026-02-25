package bitmask.BOJ_25691;
import java.io.*;
import java.util.*;

class Main {

    static int n, k, max;
    static List<List<Integer>> tree;
    static boolean[] visited;
    static int[] apple, depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        max = 0;

        tree = new ArrayList<>();
        for(int i=0;i<n;i++) {
            tree.add(new ArrayList<>());
        }

        for(int i=0;i<n-1;i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree.get(p).add(c);
            tree.get(c).add(p);
        }

        apple = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            apple[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[n];
        depth = new int[n];
        cal(0, 0);

        for(int i=1;i<(1<<n);i++) {
            if((i&1) == 0) continue;
            if(!isValid(i)) continue;

            int cnt = Integer.bitCount(i);
            if(cnt > k) continue;

            int sum = 0;
            for(int j=0;j<n;j++) {
                if((i >> j & 1) == 1) sum += apple[j];
            }
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }

    static void cal(int node, int d) {
        visited[node] = true;
        depth[node] = d;
        for(int next : tree.get(node)) {
            if(!visited[next]) cal(next, d+1);
        }
    }

    static boolean isValid(int mask) {
        for(int i=1;i<n;i++) {
            if((mask >> i & 1) == 1) {
                boolean parent = false;
                for(int p : tree.get(i)) {
                    if(depth[p] == depth[i] - 1 && (mask >> p & 1) == 1) {
                        parent = true;
                        break;
                    }
                }
                if(!parent) return false;
            }
        }
        return true;
    }
}