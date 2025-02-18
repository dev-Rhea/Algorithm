package etc.MST.BOJ_1774;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int str, dst;
        double cost;

        public Point(int str, int dst, int x, int y) {
            this.str = str;
            this.dst = dst;
            this.cost = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }
    }

    static int N, M;
    static int[] parent;
    static int[][] distance;
    static double answer;
    static PriorityQueue<Point> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        distance = new int[N + 1][2];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            parent[i] = i;
            distance[i][0] = x;
            distance[i][1] = y;
        }

        int str, dst, cnt = 0;
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            str = Integer.parseInt(st.nextToken());
            dst = Integer.parseInt(st.nextToken());
            if(union(str, dst)) cnt++;
        }

        pq = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(o1.cost, o2.cost);
        });

        for(str = 1;str<=N;str++) {
            for(dst = str + 1;dst<=N;dst++) {
                if(find(str) == find(dst)) continue;
                pq.add(new Point(str, dst, Math.abs(distance[str][0] - distance[dst][0]), Math.abs(distance[str][1] - distance[dst][1])));
            }
        }

        answer = 0.0;
        while (!pq.isEmpty()) {
            Point point = pq.poll();
            if(union(point.str, point.dst)) {
                cnt++;
                answer += point.cost;
            }
            if(cnt == N - 1) break;
        }
        System.out.println(String.format("%.2f", answer));
    }

    private static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa == fb) return false;

        parent[fb] = fa;
        return true;
    }

    private static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}
