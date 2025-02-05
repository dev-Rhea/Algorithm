package pre.Week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11723 {
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());

        int S = 0;

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String operator = st.nextToken();
            int n;

            switch (operator) {
                case "add" :
                    n = Integer.parseInt(st.nextToken()) - 1;
                    S = S | (1 << n);
                    break;
                case "remove" :
                    n = Integer.parseInt(st.nextToken()) - 1;
                    S = S & ~(1 << n);
                    break;
                case "check" :
                    n = Integer.parseInt(st.nextToken()) - 1;
                    int temp = S & (1 << n);
                    sb.append(((temp == 0) ? 0 : 1)).append("\n");
                    break;
                case "toggle" :
                    n = Integer.parseInt(st.nextToken()) - 1;
                    S = S ^ (1 << n);
                    break;
                case "all" :
                    S = (1 << 21) - 1;
                    break;
                case "empty" :
                    S = 0;
                    break;
            }
        }
        System.out.println(sb);
    }
}
