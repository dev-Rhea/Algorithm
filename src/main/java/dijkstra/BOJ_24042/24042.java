package dijkstra.BOJ_24042;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[M];
        int[] B = new int[M];

        List<List<int[]>> signal = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            signal.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());

            signal.get(A[i]).add(new int[]{i, B[i]});
            signal.get(B[i]).add(new int[]{i, A[i]});
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        Queue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[]{0, 1});

        while (!pq.isEmpty()) {
            long[] now = pq.poll();
            long t = now[0];
            int node = (int) now[1];

            if (dist[node] != t) continue;
            if (node == N) {
                System.out.println(t);
                return;
            }

            long cycle = t / M;
            int mod = (int)(t % M);

            for (int[] next : signal.get(node)) {
                int idx = next[0];
                int nd = next[1];

                // idx >= mod: 이번 주기에서 탈 수 있음
                // idx <  mod: 이번 주기는 이미 지났으므로 다음 주기
                long nt = (idx >= mod ? cycle : cycle + 1) * M + idx + 1;

                if (dist[nd] > nt) {
                    dist[nd] = nt;
                    pq.add(new long[]{nt, nd});
                }
            }
        }
    }
}