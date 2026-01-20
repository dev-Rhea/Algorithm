package 문자열.BOJ_1013;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        String vega =  "(100+1+|01)+";

        for(int t=0;t<T;t++) {
            String input = br.readLine();

            if(input.matches(vega)) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}