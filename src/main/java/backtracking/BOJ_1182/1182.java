package backtracking.BOJ_1182;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, S;
    static int[] nums;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(0, 0, 0);

        System.out.println(cnt);
    }

    static void backtracking(int idx, int sum, int count) {
        if(idx == N) {
            if(count > 0 && sum == S) cnt++;
            return;
        }

        backtracking(idx+1, sum, count);
        backtracking(idx+1, sum + nums[idx], count+1);
    }
}