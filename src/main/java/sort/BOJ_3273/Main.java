package sort.BOJ_3273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());
        int cnt = 0;

        for(int num : nums) {
            int target = x - num;
            if(target <= 0) continue;

            for(int i=0;i<n;i++) {
                if(nums[i] == target) {
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt/2);
    }

}
