package math.BOJ_31247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        long[] pow = new long[64]; // 2의 거듭 제곱 배열
        pow[0] = 1;

        for(int i=1;i<=63;i++) {
            pow[i] = pow[i-1] * 2;
        }

        for(int i=0;i<T;i++) {
            st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            long K = Long.parseLong(st.nextToken());

            if(K >= 63) {
                sb.append(0).append("\n");
            }
            else {
                int tmp = (int) K;
                long ans = (N/pow[tmp]);
                if(ans % 2 == 0) {
                    sb.append(ans / 2).append("\n");
                }
                else {
                    sb.append(ans - (ans - 1) / 2).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
