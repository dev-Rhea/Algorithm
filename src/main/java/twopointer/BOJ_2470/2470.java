package twopointer.BOJ_2470;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;

        int start = 0;
        int end = N - 1;
        int ans1 = 0;
        int ans2 = 0;

        while(start < end) {
            int sum = nums[start] + nums[end];

            
            if(Math.abs(sum) < min) {
                min = Math.abs(sum);
                ans1 = nums[start];
                ans2 = nums[end];
            }
            if(sum > 0) end--;
            else start++;
        }
        System.out.print(ans1 + " " + ans2);
    }
}