package DP.BOJ_13398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] left = new int[n];
        int[] right = new int[n];
        
        left[0] = nums[0];
        int ans = left[0];
        for(int i=1;i<n;i++) {
            left[i] = Math.max(left[i-1] + nums[i], nums[i]);
            ans = Math.max(ans, left[i]);
        }

        right[n-1] = nums[n-1];
        for(int i=n-2;i>=0;i--) {
            right[i] = Math.max(right[i+1] + nums[i], nums[i]);
        }
        for(int i=0;i<n-2;i++) { 
            ans = Math.max(ans, left[i] + right[i+2]);
        }

        System.out.println(ans);
    }
}