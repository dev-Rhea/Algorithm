package prefixsum.BOJ_2042;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] nums = new long[N+1];
        tree = new long[N+1];
        for(int i=1;i<=N;i++) {
            nums[i] = Long.parseLong(br.readLine());
            update(i, nums[i]);
        }

        for(int i=0;i<(M+K);i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1) {
                long diff = c - nums[b];
                nums[b] = c;
                update(b, diff);
            }
            else if(a == 2) {
                sb.append(sum((int) c) - sum(b-1)).append('\n');
            }
        }

        System.out.print(sb);
    }

    static void update(int i, long diff) {
        for(;i<=N;i+=i&(-i)) {
            tree[i] += diff;
        }
    }

    static long sum(int i) {
        long sum = 0;
        for(;i>0;i-=i&(-i)) {
            sum += tree[i];
        }
        return sum;
    }
}