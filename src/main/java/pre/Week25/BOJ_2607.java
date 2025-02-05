package pre.Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2607 {
    static int N;
    static int[] alpha = new int[26];
    static int result = 0;
    static String str1, str2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        str1 = br.readLine();

        for (int i = 0; i < N - 1; i++) {
            str2 = br.readLine();
            int cnt = 0;

            for(int j=0;j<str1.length();j++) {
                alpha[str1.charAt(j) - 'A']++;
            }

            for(int j=0;j<str2.length();j++) {
                if(alpha[str2.charAt(j) - 'A'] > 0) {
                    cnt++;
                    alpha[str2.charAt(j) - 'A']--;
                }
            }

            if(str1.length() == str2.length() && (str1.length() == cnt || str1.length() - 1 == cnt)) result++;
            else if (str1.length() == str2.length() - 1 && str1.length() - 1 == cnt) result++;
            else if(str1.length() == str2.length() + 1 && str1.length() == cnt) result++;
        }
        System.out.println(result);
    }
}
