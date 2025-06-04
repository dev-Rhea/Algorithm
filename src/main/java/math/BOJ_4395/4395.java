package math.BOJ_4395;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        while(n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());

            long dist = y - x;

            if(dist == 0) {
                System.out.println(0);
                continue;
            }

            long steps = minimum(dist);
            System.out.println(steps);
        }
    }

    static long minimum(long dist) {
        if(dist == 1) return 1;
        if(dist == 2) return 2;

        long k = (long) Math.sqrt(dist);

        if(dist == (k * k)) return 2 * k - 1;
        else if(dist <= (k * k) + k) return 2 * k;
        else return 2 * k + 1;
    }
}