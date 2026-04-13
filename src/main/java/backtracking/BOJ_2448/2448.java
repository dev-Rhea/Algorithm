package backtracking.BOJ_2448;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static String[] star;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        star = new String[N];
        star[0] = "  *  ";
        star[1] = " * * ";
        star[2] = "*****";

        for(int i=1;3*Math.pow(2, i)<=N;i++) {
            draw(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            sb.append(star[i]).append('\n');
        }

        System.out.print(sb);
    }

    static void draw(int i) {
        int bottom = (int) (3 * Math.pow(2, i));
        int mid = bottom/2;

        for(int j=mid;j<bottom;j++) {
            star[j] = star[j-mid] + " " + star[j-mid];
        }
        String blank = " ".repeat(mid);

        for(int j=0;j<mid;j++) {
            star[j] = blank + star[j] + blank;
        }
    }
}