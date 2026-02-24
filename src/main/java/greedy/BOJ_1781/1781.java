package greedy.BOJ_1781;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static class Problem {
        int dead;
        long cnt;
        Problem(int dead, long cnt) {
            this.dead = dead;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Problem problems[] = new Problem[N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            problems[i] = new Problem(d, c);
        }

        Arrays.sort(problems, (o1, o2) -> o1.dead - o2.dead);
        Queue<Long> queue = new PriorityQueue<>();

        for(int i=0;i<N;i++) {
            queue.add(problems[i].cnt);

            if(queue.size() > problems[i].dead) queue.poll();
        }

        long max = 0;
        while(!queue.isEmpty()) {
            max += queue.poll();
        }

        System.out.println(max);
    }
}