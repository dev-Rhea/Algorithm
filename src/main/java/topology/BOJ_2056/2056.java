import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] times = new int[N+1];
        List<List<Integer>> works = new ArrayList<>();
        int[] degree = new int[N+1];

        for(int i=0;i<=N;i++) {
            works.add(new ArrayList<>());
        }

        for(int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            for(int j=0;j<cnt;j++) {
                int pre = Integer.parseInt(st.nextToken());
                works.get(pre).add(i);
                degree[i]++;
            }
        }

        int sum = 0;
        int[] start = new int[N+1];

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=N;i++) {
            if(degree[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int next : works.get(now)) {
                start[next] = Math.max(start[next], start[now]+times[now]);
                degree[next]--;
                if(degree[next] == 0) queue.add(next);
            }
        }

        for(int i=1;i<=N;i++) {
            sum = Math.max(sum, start[i] + times[i]);
        }

        System.out.println(sum);
    }
}