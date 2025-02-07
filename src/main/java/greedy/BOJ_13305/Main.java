package greedy.BOJ_13305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] dist;
    static long[] cost;
    static long min = Long.MAX_VALUE;
    static long sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dist = new long[N-1];
        cost = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N-1;i++) {
            dist[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            cost[i] = Long.parseLong(st.nextToken());
        }

        long sum = 0;
        long min = 1000000001;
        for(int i=0;i<N-1;i++) {
            if(cost[i] < min) {
                min = cost[i];
            }
            sum += min * dist[i];
        }
        System.out.println(sum);
    }

}
