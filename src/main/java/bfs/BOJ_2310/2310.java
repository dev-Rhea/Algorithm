package bfs.BOJ_2310;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static char[] room;
    static int[] cost;
    static List<Integer>[] next;
    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            room = new char[n + 1];
            cost = new int[n + 1];
            next = new ArrayList[n + 1];
            for(int i=1;i<=n;i++) {
                next[i] = new ArrayList<>();
            }

            for(int i=1;i<=n;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                room[i] = st.nextToken().charAt(0);
                cost[i] = Integer.parseInt(st.nextToken());

                while(true) {
                    int v = Integer.parseInt(st.nextToken());
                    if(v == 0) break;
                    next[i].add(v);
                }
            }
            System.out.println(reach() ? "Yes" : "No");
        }
    }

    static boolean reach() {
        int[] max = new int[n+1];
        Arrays.fill(max, -1);

        Queue<Integer> queue = new LinkedList<>();
        max[1] = 0;
        queue.add(1);

        while(!queue.isEmpty()) {
            int m = queue.poll();
            int now = max[m];

            for(int a : next[m]) {
                int b = now;

                if(room[a] == 'L') {
                    if(b < cost[a]) b = cost[a];
                }
                else if(room[a] =='T') {
                    if(b < cost[a]) continue;
                    b -= cost[a];
                }

                if(b > max[a]) {
                    max[a] = b;
                    queue.add(a);
                }
            }
        }
        return max[n] >= 0;
    }
}