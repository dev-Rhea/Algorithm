package sparsetable.BOJ_2357;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N+1];
        for(int i=1;i<=N;i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        int LOG = 1;
        while((1<<LOG) <= N) LOG++;

        int[][] max = new int[LOG][N+1];
        int[][] min = new int[LOG][N+1];
        for(int i=1;i<=N;i++) {
            max[0][i] = nums[i];
            min[0][i] = nums[i];
        }

        max[1][1] = nums[1];
        min[1][1] = nums[1];

        for(int k=1;k<LOG;k++) {
            for(int i=1;i+(1<<k)-1<=N;i++) {
                max[k][i] = Math.max(max[k-1][i], max[k-1][i + (1<<(k-1))]);
                min[k][i] = Math.min(min[k-1][i], min[k-1][i + (1<<(k-1))]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int len = b-a+1;
            int k = 31-Integer.numberOfLeadingZeros(len);

            int tmax = Math.max(max[k][a], max[k][b-(1<<k)+1]);
            int tmin = Math.min(min[k][a], min[k][b-(1<<k)+1]);

            sb.append(tmin).append(' ').append(tmax).append('\n');
        }

        System.out.print(sb);
    }
}