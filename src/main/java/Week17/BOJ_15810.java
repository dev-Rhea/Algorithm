package Week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15810 {
    static int N, M;
    static int[] arr;
    static long sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        arr = new int[N];

        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        long left = 0; // 최소 시간
        long right = 1000000000L * 1000000L; // 최대 시간

        while (left <= right) {
            long mid = (left + right) / 2;
            sum = 0;

            for (int i = 0; i < N; i++) {
                sum += mid / arr[i];
            }

            if (sum >= M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);

    }
}
