package pre.Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2512 {

    static int N, M;
    static int[] budget;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        budget = new int[N];

        StringTokenizer st  = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, budget[i]);
        }

        M = Integer.parseInt(br.readLine());

        Arrays.sort(budget);

        System.out.println(binarySearch(budget, M));
    }

    private static int binarySearch(int[] arr, int n) {
        // 탐색
        int start = 0;
        int end = max;
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            int sum = 0;

            for (int a : arr) {
                sum += Math.min(a, mid);
            }

            if (sum <= n) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return result;
    }
}
