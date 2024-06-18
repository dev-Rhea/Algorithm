package Week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912 {
    static int n;
    static int[] num; // 임의의 수열
    static Integer[] sum; // 합한 값 저장
    static int max; // 최댓값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        num = new int[n];
        sum = new Integer[n];

        // 임의의 수열 생성
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        // dp 초기 값은 자기 자신
        sum[0] = num[0];
        max = num[0];


        for(int i=1;i<n;i++) {
            // 연속된 값의 합과, 비교해가며 큰 값 저장
            sum[i] = Math.max(sum[i-1] + num[i], num[i]);
            // 최댓값 저장
            max = Math.max(max, sum[i]);
        }
        System.out.println(max);

    }
}
