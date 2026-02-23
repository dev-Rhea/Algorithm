package greedy.BOJ_8980;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Box {
        int from, cnt;
        Box(int from, int cnt) {
            this.from = from;
            this.cnt = cnt;
        }
    }

    static int N, C, max, sum;
    static List<Box>[] list;
    static Queue<Box> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        max = 0;
        sum = 0;

        queue = new PriorityQueue<>((a, b) -> b.from - a.from);

        list = new ArrayList[N+1];
        for(int i=0;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());

            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[f].add(new Box(t, c));
        }

        for(int i=1;i<=N;i++) {
           list[i].sort((a, b) -> (a.from - b.from));
        }

        load(1);

        System.out.println(max);
    }

    static void load(int area) {
        for (Box b : list[area]) {
            queue.add(new Box(b.from, b.cnt));
            sum += b.cnt;
        }

        while (sum > C) {
            Box top = queue.poll();
            int over = sum - C;
            if (top.cnt <= over) {
                sum -= top.cnt;
            } else {
                top.cnt -= over;
                sum = C;
                queue.add(top);
            }
        }

        delivery(area+1);
    }

    static void delivery(int area) {
        List<Box> temp = new ArrayList<>(queue);
        queue.clear();
        sum = 0;
        for (Box b : temp) {
            if (b.from == area) {
                max += b.cnt;
            } else {
                queue.add(b);
                sum += b.cnt;
            }
        }

        if(area == N) return;

        load(area);
    }
}