package math.BOJ_1669;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        long dist = Y - X;

        if(dist == 0) {
            System.out.println(0);
            return;
        }

        long k = (long) Math.ceil(Math.sqrt(dist));
        
        if(k * k == dist) System.out.println(2 * k - 1);
        else if(dist <= k * k - k) System.out.println(2 * k - 2);
        else System.out.println(2 * k - 1);
    }
}