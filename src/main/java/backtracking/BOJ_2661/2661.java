package backtracking.BOJ_2661;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N;
    static String ans = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        backtrack("");
    }

    static void backtrack(String s) {
        if(ans != null) return;

        if(s.length() == N) {
            ans = s;
            System.out.println(s);
            return;
        }

        for(int i=1;i<=3;i++) {
            String next = s + i;
            if(isValid(next)) backtrack(next);
        }
    }

    static boolean isValid(String s) {
        for(int i=1;i<=s.length()/2;i++) {
            String a = s.substring(s.length() - i * 2, s.length() - i);
            String b = s.substring(s.length() - i, s.length());

            if(a.equals(b)) return false;
        }
        return true;
    }
}