package implementation.BOJ_2290;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {

    static int s;
    static char[][] lcd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        s = Integer.parseInt(st.nextToken());
        String n = st.nextToken();
        lcd = new char[2 * s + 3][(s + 3) * n.length() - 1];

        @SuppressWarnings("unchecked")
        Set<Integer>[] number = new HashSet[7];
        number[0] = new HashSet<>(Arrays.asList(2, 3, 5, 6, 7, 8, 9, 0));
        number[1] = new HashSet<>(Arrays.asList(4, 5, 6, 8, 9, 0));
        number[2] = new HashSet<>(Arrays.asList(1, 2, 3, 4, 7, 8, 9, 0));
        number[3] = new HashSet<>(Arrays.asList(2, 3, 4, 5, 6, 8, 9));
        number[4] = new HashSet<>(Arrays.asList(2, 6, 8, 0));
        number[5] = new HashSet<>(Arrays.asList(1, 3, 4, 5, 6, 7, 8, 9, 0));
        number[6] = new HashSet<>(Arrays.asList(2, 3, 5, 6, 8, 9, 0));

        for(int i=0;i<n.length();i++) {
            int num = n.charAt(i) - '0';

            for(int j=0;j<number.length;j++) {
                if(number[j].contains(num)) fill(i, j);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<lcd.length;i++) {
            for(int j=0;j<lcd[0].length;j++) {
                char c = lcd[i][j] == '\u0000' ? ' ': lcd[i][j];
                sb.append(c);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void fill(int idx, int num) {
        if(num == 0) {
            for(int i=0;i<s;i++) {
                lcd[0][(s+3)*idx+1+i] = '-';
            }
        }

        if(num == 3) {
            for(int i=0;i<s;i++) {
                lcd[s+1][(s+3)*idx+1+i] = '-';
            }
        }

        if(num == 6) {
            for(int i=0;i<s;i++) {
                lcd[2*s+2][(s+3)*idx+1+i] = '-';
            }
        }

        if(num == 1) {
            for(int i=0;i<s;i++) {
                lcd[i+1][(s+3)*idx] = '|';
            }
        }

        if(num == 4) {
            for(int i=0;i<s;i++) {
                lcd[s+1+i+1][(s+3)*idx] = '|';
            }
        }

        if(num == 2) {
            for(int i=0;i<s;i++) {
                lcd[i+1][(s+3)*idx+s+1] = '|';
            }
        }

        if(num == 5) {
            for(int i=0;i<s;i++) {
                lcd[s+1+i+1][(s+3)*idx+s+1] = '|';
            }
        }
    }
}