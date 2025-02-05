package pre.Week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read N
        N = Integer.parseInt(br.readLine());

        // Initialize array
        arr = new int[N];

        // Read array elements
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        // Read M (number of queries)
        M = Integer.parseInt(br.readLine());

        // Process each query
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            int s = Arrays.binarySearch(arr, num);
            if (s < 0) {
                sb.append(0).append("\n");
            } else {
                sb.append(1).append("\n");
            }
        }
        System.out.println(sb);
    }
}
