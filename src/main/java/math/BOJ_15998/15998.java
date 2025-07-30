package math.BOJ_15998;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long N = Long.parseLong(br.readLine());
        long a, b;

        long minB = (long) 1e18;
        long balance = 0, M = 0;

        boolean flag = true;

        StringTokenizer st;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());

            if(balance + a < 0) {
                long temp = b - a - balance;

                if(b != 0) minB = Math.min(minB, b);

                if(M == 0) M = temp;
                else {
                    M = GCD(M, temp);

                    if(M <= minB && minB != (long) 1e18) {
                        flag = false;
                        break;
                    }
                }
            }
            else {
                if (balance + a != b) {
                    flag = false;
                    break;
                }
            }

            balance = b;
        }

        if(flag && M != 0) System.out.println(M);
        else if(flag && M == 0) System.out.println(1);
        else System.out.println(-1);
    }

    static long GCD(long a, long b) {
        while(b > 0) {
            long temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}