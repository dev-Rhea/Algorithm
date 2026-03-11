package implementation.BOJ_20055;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int ans = 0;
        int len = 2 * N;
        int[] durb = new int[len];
        boolean[] robot = new boolean[len];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++) {
            durb[i] = Integer.parseInt(st.nextToken());
        }

        int head = 0;
        int cnt = 0;
        for (int d : durb) {
            if(d == 0) cnt++;
        }

        while(cnt < K) {
            ans++;

            head = (head + len - 1) % len;
            robot[(head + N - 1) % len] = false;

            for(int i=N-1;i>=1;i--) {
                int now = (head + i) % len;
                int next = (head + i + 1) % len;

                if(robot[now] && !robot[next] && durb[next] > 0) {
                    robot[now] = false;
                    robot[next] = true;

                    if(--durb[next] == 0) cnt++;
                }
            }
            robot[(head + N - 1) % len] = false;

            if(!robot[head] && durb[head] > 0) {
                robot[head] = true;
                if(--durb[head] == 0) cnt++;
            }
        }

        System.out.println(ans);
    }
}