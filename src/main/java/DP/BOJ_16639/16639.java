package DP.BOJ_16639;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static class Value {
        long min, max;
        Value(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }

    static int N;
    static int[] nums;
    static char[] ops;
    static Value[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        int cnt = (N+1)/2;
        nums = new int[cnt];
        ops = new char[cnt-1];
        
        for(int i=0;i<N;i++) {
            if(i % 2 == 0) nums[i/2] = input.charAt(i) - '0';
            else ops[i/2] = input.charAt(i);
        }

        dp = new Value[cnt][cnt];
        for(int i=0;i<cnt;i++) {
            dp[i][i] = new Value(nums[i], nums[i]);
        }

        for(int i=2;i<=cnt;i++) {
            for(int j=0;j<=cnt-i;j++) {
                int temp = j+i-1;
                cal(j, temp);
            }
        }

        System.out.println(dp[0][cnt-1].max);
    }

    static void cal(int a, int b) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for(int i=a;i<b;i++) {
            Value left = dp[a][i];
            Value right = dp[i+1][b];
            char op = ops[i];

            switch(op) {
                case '*':
                    long[] prd = {
                        left.max * right.max,
                        left.max * right.min,
                        left.min * right.max,
                        left.min * right.min
                    };

                    for(long p : prd) {
                        max = Math.max(max, p);
                        min = Math.min(min, p);
                    }
                    break;
                case '+':
                    max = Math.max(max, left.max + right.max);
                    min = Math.min(min, left.min + right.min);
                    break;
                case '-':
                    max = Math.max(max, left.max - right.min);
                    min = Math.min(min, left.min - right.max);
                    break;
            }
        }
        
        dp[a][b] = new Value(min, max);
    }
}