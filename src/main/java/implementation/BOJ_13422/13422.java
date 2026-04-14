package implementation.BOJ_13422;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] money = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                money[i] = Integer.parseInt(st.nextToken());
            }

            if(M == N) {
                long sum = 0;
                for(int m : money) {
                    sum += m;
                }

                sb.append(sum < K ? 1 : 0).append('\n');
                continue;
            }

            long sum = 0;
            for(int i=0;i<M;i++) {
                sum += money[i];
            }

            int cnt = 0;
            for(int i=0;i<N;i++) {
                if(sum < K) cnt++;

                sum += money[(i+M)%N];
                sum -= money[i];
            }

            sb.append(cnt).append('\n');
        }

        System.out.print(sb);
    }
}