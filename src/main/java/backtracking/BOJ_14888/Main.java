package backtracking.BOJ_14888;

import java.util.Scanner;

public class Main {
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int[] nums;
    static int[] operators = new int[4];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        nums = new int[N];

        for(int i=0;i<N;i++) {
            nums[i] = sc.nextInt();
        }

        for(int i=0;i<4;i++) {
            operators[i] = sc.nextInt();
        }

        calculate(0, nums[0]);
        System.out.println(max);
        System.out.println(min);
    }

    private static void calculate(int cnt, int temp) {
        if(cnt == nums.length - 1) {
            if(temp > max) max = temp;
            if(temp < min) min = temp;
        }
        else {
            for(int i=0;i<4;i++) {
                if(operators[i] > 0) {
                    operators[i]--; // 연산자 사용 횟수 표시
                    if (i == 0) calculate(cnt + 1, temp + nums[cnt + 1]);
                    else if(i == 1) calculate(cnt + 1, temp - nums[cnt + 1]);
                    else if(i ==2) calculate(cnt + 1, temp * nums[cnt + 1]);
                    else calculate(cnt + 1, temp / nums[cnt + 1]);
                    operators[i]++; // 다른 경우의 수 계산 위해 횟수 복구
                }
            }
        }
    }
}

