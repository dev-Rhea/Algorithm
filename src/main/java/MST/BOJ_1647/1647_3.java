package MST.BOJ_1647;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        for(int i=1;i<=N;i++) {
            parents[i] = i;
        }

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            queue.add(new int[]{A, B, C});
        }

        long max = 0;
        long sum = 0;

        while(!queue.isEmpty()) {
            int[] edge = queue.poll();

            int a = edge[0];
            int b = edge[1];

            int c = edge[2];

            if(find(a) != find(b)) {
                union(a, b);
                sum += c;
                max = Math.max(max, c);
            }
        }

        System.out.println(sum - max);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) parents[pb] = pa;
    }

    static int find(int h) {
        if(parents[h] != h) parents[h] = find(parents[h]);

        return parents[h];
    }
}