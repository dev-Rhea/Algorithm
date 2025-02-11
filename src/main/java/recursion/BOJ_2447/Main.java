package recursion.BOJ_2447;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[][] star;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        star = new char[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                star[i][j] = ' ';
            }
        }
        printStar(0, 0, N);

        for(int i=0;i<N;i++) {
            System.out.println(star[i]);
        }
    }

    static void printStar(int x, int y,int N) {
        if(N == 1) {
            star[x][y] = '*';
            return;
        }

        // 영역 3*3 분할
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if (i == j && i ==1) continue; // 가운데 공백
                printStar(x + i * N/3, y + j * N/3, N/3); // 새로운 시작좌표
            }
        }
    }

}
