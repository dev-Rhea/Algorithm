package divideandconquer.BOJ_1992;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[][] video;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N =  Integer.parseInt(br.readLine());
        video = new int[N][N];

        for(int i=0;i<N;i++) {
            String input = br.readLine();
            for(int j=0;j<N;j++) {
                video[i][j] = input.charAt(j) - '0';
            }
        }
        br.close();

        compress(0, 0, N);
        System.out.println(sb);
    }

    static void compress(int row, int col, int n) {
        if(check(row, col, n)) {
            sb.append(video[row][col]);
            return;
        }

        int newN = n / 2;
        sb.append('(');

        compress(row, col, newN);
        compress(row, col + newN, newN);
        compress(row + newN, col, newN);
        compress(row + newN, col + newN, newN);

        sb.append(')');
    }

    static boolean check(int row, int col, int n) {
        for(int i=row;i<row+n;i++) {
            for(int j=col;j<col+n;j++) {
                if(video[i][j] != video[row][col]) return false;
            }
        }
        return true;
    }
}