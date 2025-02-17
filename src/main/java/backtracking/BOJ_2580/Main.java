package backtracking.BOJ_2580;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[9][9];
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<9;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<9;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sudoku(0);
    }

    static void sudoku(int x) {
        if(flag == true) return;

        // 모든 칸을 다 채운 경우
        if(x == 81) {
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<9;i++) {
                for(int j=0;j<9;j++) {
                    sb.append(board[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb);
            flag = true;
            return;
        }

        // x로 행, 열 위치 계산
        int row = x/9;
        int col = x%9;
        // 이미 채워진 경우 다음칸으로
        if(board[row][col] != 0) {
            sudoku(x + 1);
            return;
        }

        // 1 - 9 숫자 넣으면서 확인
        for(int i=1;i<10;i++) {
            if(check(row, col, i) == true) {
                board[row][col] = i;
                sudoku(x + 1);
                board[row][col] = 0; // 새로운 탐색을 진행하기 위해 reset
            }
        }
    }

    static boolean check(int row, int col, int x) {
        for(int i=0;i<9;i++) {
            if(board[row][i] == x) return false;
            if(board[i][col] == x) return false;
            if(board[(row/3*3) + i/3][(col/3*3) + i%3] == x) return false;
        }
        return true;
    }
}
