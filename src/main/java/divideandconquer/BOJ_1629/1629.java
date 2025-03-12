package divideandconquer.BOJ_1629;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static long C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long A = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(calculate(A, B));
    }

    static long calculate(long A, long B) {
        if(B == 1) return A % C;

        long ans = calculate(A, B / 2); // 거듭제곱을 절반으로 줄이면 시간복잡도 줄어듦

        if(B % 2 == 1) ans = (ans * A) % C; // 홀수인 경우
        
        return ans;
    }
}