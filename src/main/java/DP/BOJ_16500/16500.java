package DP.BOJ_16500;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        int N = Integer.parseInt(br.readLine());

        Set<String> words = new HashSet<>();
        for(int i = 0; i < N; i++) {
            words.add(br.readLine());
        }

        boolean[] dp = new boolean[S.length() + 1];
        dp[0] = true;

        for(int i=0;i<=S.length();i++) {
            if(!dp[i]) continue;
            
            for(String word : words) {
                if(S.startsWith(word, i)) {
                    dp[i + word.length()] = true;
                }
            }
        }

        System.out.println(dp[S.length()] ? 1 : 0);
    }
}