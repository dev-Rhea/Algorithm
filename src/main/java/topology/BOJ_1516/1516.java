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
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            list.add(new ArrayList<>());
        }

        int[] degree = new int[N+1];
        int[] time = new int[N+1];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int n = Integer.parseInt(st.nextToken());
                if(n == -1) break;
                else {
                    list.get(n).add(i);
                    degree[i]++;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=N;i++) {
            if(degree[i] == 0) queue.add(i);
        }

        int[] ans = new int[N+1];
        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int i=0;i<list.get(now).size();i++) {
                int next = list.get(now).get(i);
                degree[next]--;

                ans[next] = Math.max(ans[next], ans[now] + time[now]);
                if(degree[next] == 0) queue.add(next);
            }
        }

        for(int i=1;i<=N;i++) {
            System.out.println(ans[i] + time[i]);
        }
    }
}