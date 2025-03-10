package divideandconquer.BOJ_2630;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int white;
    static int blue = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        cutting(0, 0, N);

        System.out.println(white);
        System.out.println(blue);
    }

    static void cutting(int row, int col, int n) {
        
        if(check(row, col, n)) {
            if(map[row][col] == 0) white++;
            else blue++;
            return;
        }

        int newN = n / 2;

        cutting(row, col, newN);
        cutting(row, col+newN, newN);
        cutting(row+newN, col, newN);
        cutting(row+newN, col+newN, newN);
    }

    static boolean check(int row, int col, int n) {
        int color = map[row][col];

        for(int i=row;i<row+n;i++) {
            for(int j=col;j<col+n;j++) {
                if(map[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}