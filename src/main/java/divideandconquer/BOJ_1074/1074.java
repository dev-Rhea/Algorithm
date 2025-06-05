package divideandconquer.BOJ_1074;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int x  = 0;
    static int y = 0;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int ans = Zmap((int)Math.pow(2, N), r, c);

        System.out.println(ans);
    }

    static int Zmap(int n, int r, int c) {
        n /= 2;

        if(r < x + n && c < y + n) cnt += (n * n * 0); // 1사분면 
        else if(r < x + n && c >= y + n) { // 2사분면
            cnt += (n * n * 1);
            y += n;
        }
        else if(c < y + n) { // 3사분면 
            cnt += (n * n * 2);
            x += n;
        }
        else { // 4사분면 
            cnt += (n * n * 3);
            x += n;
            y += n;
        }

        if(n == 1) return cnt;
        return Zmap(n, r, c);
    }
}