package greedy.BOJ_1339;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {

    static int max = Integer.MIN_VALUE;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] alpha = new long[26];
        List<String> words = new ArrayList<>(N);

        for(int i=0;i<N;i++) {
            String input = br.readLine().trim();
            words.add(input);
            long now = 1;// 자릿수

            for(int j=input.length()-1;j>=0;j--) {
                int c = input.charAt(j) - 'A';
                alpha[c] += now;
                now *= 10;
            }
        }

        List<Long> isTrue = new ArrayList<>();
        for(long t : alpha) {
            if(t > 0) isTrue.add(t);
        }
        Collections.sort(isTrue, Collections.reverseOrder());

        int digit = 9;
        long ans = 0;
        for(long t : isTrue) {
            ans += t * digit;
            digit--;
        }

        System.out.println(ans);
    }
}