package sort.topological.BOJ_1005;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int[] time, child, sum;
    static List<List<Integer>> game;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            time = new int[N+1];
            child = new int[N+1];
            sum = new int[N+1];

            game = new ArrayList<>();
            for(int i=0;i<=N;i++) {
                game.add(new ArrayList<>());
            }

            for(int i=1;i<=N;i++) {
                child[i] = 0;
                sum[i] = 0;
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++){
                time[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=0;i<K;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                child[b]++; 
                game.get(a).add(b);
            }

            int w = Integer.parseInt(br.readLine());

            Queue<Integer> queue = new LinkedList<>();
            
            for(int i=1;i<=N;i++) {
                sum[i] = time[i];
                if(child[i] == 0) queue.add(i); 
            }

            while(!queue.isEmpty()) {
                int now = queue.poll();

                for(int next : game.get(now)) {
                    sum[next] = Math.max(sum[next], sum[now]+time[next]);
                    child[next]--;

                    if(child[next] == 0) queue.add(next);
                }
            }
            System.out.println(sum[w]);
        }
    }
}