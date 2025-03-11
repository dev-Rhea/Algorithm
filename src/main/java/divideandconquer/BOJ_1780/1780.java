package divideandconquer.BOJ_1780;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int minus1, zero, one;
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        minus1 = 0;
        zero = 0;
        one = 0;

        cutting(0, 0, N);
        
        System.out.println(minus1);
        System.out.println(zero);
        System.out.println(one);
    }

    static void cutting(int row, int col, int n) {
        if(check(row, col, n)) {
            if(paper[row][col] == -1) minus1++;
            else if(paper[row][col] == 0 ) zero++;
            else if(paper[row][col] == 1) one++;
        }
        else {
            int size = n / 3;

            for(int i=0;i<n;i+=size) {
                for(int j=0;j<n;j+=size) {
                    cutting(row + i, col + j, size);                
                }
            }
        }
    }

    static boolean check(int row, int col, int n) {
        for(int i=row;i<row+n;i++) {
            for(int j=col;j<col+n;j++) {
                if(paper[i][j] != paper[row][col]) return false;
            }
        }
        return true;
    }
} 