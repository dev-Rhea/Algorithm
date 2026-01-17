package math.BOJ_1722;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int N;
    static long[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        nums = new long[N+1];
        nums[0] = 1;
        for(int i=1;i<=N;i++) {
            nums[i] = nums[i-1] * i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int type = Integer.parseInt(st.nextToken());
        if(type == 1) {
            long k = Long.parseLong(st.nextToken());
            int[] ans = findSeq(k);
            for(int n : ans) {
                sb.append(n).append(" ");
            }
        }
        else {
            int[] seq = new int[N];
            for(int i=0;i<N;i++) {
                seq[i] = Integer.parseInt(st.nextToken());
            }
            long ans = findOrder(seq);
            sb.append(ans);
        }
        System.out.println(sb);
    }

    static int[] findSeq(long k) {
        int[] num = new int[N];
        List<Integer> list = new ArrayList<>();
        for(int i=1;i<=N;i++) {
            list.add(i);
        }
        k--;

        for(int i=0;i<N;i++) {
            long cnt = nums[N-1-i];
            int idx = (int) (k/cnt);

            num[i] = list.get(idx);
            list.remove(idx);

            k %= cnt;
        }
        return num;
    }

    static long findOrder(int[] seq) {
        long n = 1;

        List<Integer> list = new ArrayList<>();
        for(int i=1;i<=N;i++) {
            list.add(i);
        }

        for(int i=0;i<N;i++) {
            int now = seq[i];
            int idx = list.indexOf(now);

            n += idx * nums[N-1-i];
            list.remove(idx);
        }
        return n;
    }
}