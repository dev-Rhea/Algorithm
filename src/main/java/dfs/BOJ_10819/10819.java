package dfs.BOJ_10819;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[] nums;
    static int max = Integer.MIN_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            visited[i] = true;
            dfs(1, nums[i], 0);
            visited[i] = false;
        }

        System.out.println(max);
    }

    static void dfs(int depth, int prev, int sum) {
        if(depth == N) {
            max = Math.max(max, sum);
            return;
        }

        for(int i=0;i<N;i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, nums[i], sum + Math.abs(prev - nums[i]));
                visited[i] = false;
            }
        }
    }
}