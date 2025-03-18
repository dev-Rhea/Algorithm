package twopointer.BOJ_1806;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken()); 
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        int left = 0, right = 0, sum = 0;

        while (right < N) {
            sum += nums[right++];  // 현재 right 값을 더함

            while (sum >= S) {  // sum이 S 이상일 때 최소 길이 갱신
                min = Math.min(min, right - left);
                sum -= nums[left++];  // left를 이동하여 sum 줄이기
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}
