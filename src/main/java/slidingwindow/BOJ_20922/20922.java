package slidingwindow.BOJ_20922;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        int[] dp = new int[100001];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int max = 1;

        while(end < N) {
            int cnt = dp[nums[end]] + 1;

            if(K < cnt) {
                // 시작점을 오른쪽으로 이동시키며, 등장횟수 감소 시킴 
                while(nums[start] != nums[end]) dp[nums[start++]]--;

                // 중복 된 수 갯수 감소 후, 시작점 이동 -> K개 이하 만족 
                dp[nums[start++]]--;
            }
            else {
                max = Math.max(max, end - start + 1); // 윈도우 길이 계산해서 max 갱신 
                dp[nums[end++]] = cnt; // 윈도우 확장 
            }
        }
        System.out.println(max);
    }
} 