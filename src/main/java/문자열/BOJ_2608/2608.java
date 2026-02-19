package 문자열.BOJ_2608;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        int arab = conv(str1) + conv(str2);
        String rome = trans(arab);

        System.out.println(arab);
        System.out.println(rome);
    }

    static int conv(char[] c) {
        int sum = 0;
        int cnt = 0;

        for (int i = 0; i < c.length; i++) {
            if (cnt > 0) {
                cnt--;
                continue;
            }

            char next = (i + 1 < c.length) ? c[i + 1] : 0;

            switch (c[i]) {
                case 'I':
                    if      (next == 'V') { sum += 4;   cnt = 1; }
                    else if (next == 'X') { sum += 9;   cnt = 1; }
                    else                    sum += 1;
                    break;
                case 'V':
                    sum += 5;
                    break;
                case 'X':
                    if      (next == 'L') { sum += 40;  cnt = 1; }
                    else if (next == 'C') { sum += 90;  cnt = 1; }
                    else                    sum += 10;
                    break;
                case 'L':
                    sum += 50;
                    break;
                case 'C':
                    if      (next == 'D') { sum += 400; cnt = 1; }
                    else if (next == 'M') { sum += 900; cnt = 1; }
                    else                    sum += 100;
                    break;
                case 'D':
                    sum += 500;
                    break;
                case 'M':
                    sum += 1000;
                    break;
            }
        }
        return sum;
    }

    static String trans(int n) {
        int[]    vals  = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] syms  = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();
        int t = 1;
        int len = vals.length;

        for (int i = 0; i < len; i++) {
            t = vals[i];
            while (n >= t) {
                sb.append(syms[i]);
                n -= t;
            }
        }
        return sb.toString();
    }
}