package greedy.BOJ_1339;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] value = new int[26];

        for(int i=0;i<N;i++) {
            String input = br.readLine();
            for(int j=input.length()-1;j>=0;j--) {
                value[input.charAt(j) - 'A'] += (int) Math.pow(10, input.length()-j-1);
            }
        }
        Arrays.sort(value);

        int ans = 0;
        for(int i=0;i<10;i++) {
            ans += value[25-i] * (9 - i);
        }

        System.out.println(ans);
    }
}
