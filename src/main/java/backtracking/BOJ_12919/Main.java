package backtracking.BOJ_12919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String S, T;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        game(T);

        System.out.println(answer);
    }

    private static void game(String t) {
        int lenT = t.length();

        if (lenT == S.length()) {
            if (S.equals(t)) {
                answer = 1;
            }
            return;
        }

        if (t.endsWith("A")) {
            game(t.substring(0, lenT - 1));
        }
        if (t.startsWith("B")) {
            game(new StringBuilder(t.substring(1)).reverse().toString());
        }
    }
}
