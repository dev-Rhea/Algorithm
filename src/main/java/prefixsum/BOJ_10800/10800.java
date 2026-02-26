package prefixsum.BOJ_10800;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] color = new int[N];
        int[] size = new int[N];
        Integer[] idx = new Integer[N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            color[i] = Integer.parseInt(st.nextToken());
            size[i] = Integer.parseInt(st.nextToken());

            idx[i] = i;
        }

        Arrays.sort(idx, (a, b) -> size[a] - size[b]);

        long sum = 0;
        long[] colorSum = new long[N+1];
        long[] ans = new long[N];

        int i = 0;
        while(i < N) {
            int j = i;
            while(j < N && size[idx[j]] == size[idx[i]]) j++;

            for(int k=i;k<j;k++) {
                int ball = idx[k];
                ans[ball] = sum - colorSum[color[ball]];
            }

            for(int k=i;k<j;k++) {
                int ball = idx[k];
                sum += size[ball];
                colorSum[color[ball]] += size[ball];
            }
            i = j;
        }

        StringBuilder sb = new StringBuilder();
        for (int k=0;k<N;k++) {
            sb.append(ans[k]).append('\n');
        }
        System.out.print(sb);
    }
}