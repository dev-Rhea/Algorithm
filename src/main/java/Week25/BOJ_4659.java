package Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4659 {
    static String password;
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean flag;
        char prev;
        int cnt;

        while (!(password = br.readLine()).equals("end")) {
            arr = password.toCharArray();

            prev = '.';
            cnt = 0;
            flag = false;

            for(char p : arr) {
                if(isVowel(p)) {
                    flag = true;
                }

                if(isVowel(p) != isVowel(prev)) cnt = 1;
                else cnt++;

                if(cnt > 2 || (prev == p && (p != 'e' && p != 'o'))) {
                    flag = false;
                    break;
                }

                prev = p;
            }

            if(flag) sb.append('<').append(password).append("> is acceptable.\n");
            else sb.append('<').append(password).append("> is not acceptable.\n");

        }
        System.out.println(sb);
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
