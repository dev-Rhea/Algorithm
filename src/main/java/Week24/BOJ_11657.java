package Week24;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11657 {
    static int N, M;
    static long[] time;
    static final int INF = Integer.MAX_VALUE;
    static StringTokenizer st;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        time = new long[N+1];
        Arrays.fill(time, INF);
        time[1] = 0;

        boolean isCycle = bellmanFord(time);

        if(isCycle) {
            System.out.println(-1);
        }
        else {
            for(int i=2;i<time.length;i++) {
                if(time[i] == INF) {
                    System.out.println(-1);
                } else {
                    System.out.println(time[i]);
                }
            }
        }
    }

    private static boolean bellmanFord(long[] t) throws IOException {
        boolean isMinusCycle = false;

        Edge[] edge = new Edge[M];

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long time = Long.parseLong(st.nextToken());

            edge[i] = new Edge(from, to, time);
        }

        for(int i=0;i<M;i++) {
            for(int j=0;j<M;j++) {
                Edge cur = edge[j];

                if(time[cur.from] == INF) {
                    continue;
                }

                if(time[cur.to] > time[cur.from] + cur.time) {
                    time[cur.to] = time[cur.from] + cur.time;

                    if(i == N) {
                        isMinusCycle = true;
                        break;
                    }
                }
            }
        }
        return isMinusCycle;
    }
}

class Edge {
    int from;
    int to;
    long time;

    Edge(int from, int to, long time) {
        this.from = from;
        this.to = to;
        this.time = time;
    }
}
