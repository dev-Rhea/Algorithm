package backtracking.BOJ_2023;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        find(0, N);

        System.out.println(sb);
    }

    static void find(int num, int n) {
        if(n == 0) {
            if(isPrime(num)) sb.append(num).append("\n");
            return;
        }
        for(int i=0;i<10;i++) {
            int next = num * 10 + i;
            if(isPrime(next)) find(next, n-1);
        }
    }

    static boolean isPrime(int num) {
        if(num < 2) return false;

        for(int i=2;i<=Math.sqrt(num);i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}