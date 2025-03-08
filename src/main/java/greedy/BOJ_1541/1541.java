package greedy.BOJ_1541;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split("-");
        int ans = 0;

        for(int i=0;i<input.length;i++) {
            int temp = 0;

            String[] plus = input[i].split("\\+");

            for(int j=0;j<plus.length;j++) {
                temp += Integer.parseInt(plus[j]);
            }

            ans = (i == 0) ? temp : ans - temp;
        }

        System.out.println(ans);

    }
}