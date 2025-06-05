package divideandconquer.BOJ_1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(Zmap(N, r, c));
    }

    static int Zmap(int n, int r, int c) {
        int ans = 0;
        int size = 1 << (n - 1);

        while(size > 0) {
            int q = 0;
            if(r >= size) {
                q += 2;
                r -= size;
            }
            if(c >= size) {
                q += 1;
                c -= size;
            }

            ans += q * size * size;
            size >>= 1;
        }
        return ans;
    }
}