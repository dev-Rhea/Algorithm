package 문자열.BOJ_14369;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        String[] num =  {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
        int[] order = {0, 2, 4, 6, 8, 1, 3, 5, 7, 9};
        char[] str = {'Z', 'W', 'U', 'X', 'G', 'O', 'H', 'F', 'S', 'I'};


        for(int i=1;i<=T;i++) {
            sb.append("Case #").append(i).append(": ");
            String S = br.readLine();
            int[] words = new int[26];

            for(char c : S.toCharArray()) {
                words[c - 'A']++;
            }
            int[] ans = new int[10]; // 숫자 개수 

            for(int j=0;j<10;j++) {
                int digit = order[j];
                char key = str[j];

                int cnt = words[key - 'A'];
                ans[digit] = cnt;

                for(char c : num[digit].toCharArray()) {
                    words[c-'A'] -= cnt;
                }
            }

            for(int j=0;j<10;j++) {
                for(int k=0;k<ans[j];k++) {
                    sb.append(j);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}