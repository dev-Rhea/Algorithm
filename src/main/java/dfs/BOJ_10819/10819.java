package dfs.BOJ_10819;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int max = Integer.MIN_VALUE;
    static int[] nums;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       N = Integer.parseInt(br.readLine());
       nums = new int[N];
       visited = new boolean[N];

       StringTokenizer st = new StringTokenizer(br.readLine());
       for(int i = 0; i < N; i++) {
           nums[i] = Integer.parseInt(st.nextToken());
       }

       dfs(0, 0, 0);

       System.out.println(max);
    }

    static void dfs(int idx, int sum, int pre) {
        if(idx == N) {
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                
                if(idx == 0) dfs(1, 0, nums[i]);
                else dfs(idx + 1, sum + Math.abs(pre-nums[i]), nums[i]);

                visited[i] = false;
            }
        }
    }
}
