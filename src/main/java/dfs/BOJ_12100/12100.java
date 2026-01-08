package dfs.BOJ_12100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(board, 0);
        System.out.println(max);
    }

    static void dfs(int[][] board, int depth) {
        if(depth == 5) {
            findMax(board);
            return;
        }

        for(int dir = 0; dir < 4; dir++) {
            int[][] newBoard = move(board, dir);
            dfs(newBoard, depth + 1);
        }
    }

    static int[][] move(int[][] board, int dir) {
        int[][] newBoard = copyBoard(board);

        if(dir == 0) moveUp(newBoard);
        else if(dir == 1) moveDown(newBoard);
        else if(dir == 2) moveLeft(newBoard);
        else if(dir == 3) moveRight(newBoard);

        return newBoard;
    }

    // 위로 이동
    static void moveUp(int[][] board) {
        for(int j = 0; j < N; j++) {
            int index = 0;
            int prev = 0;
            
            for(int i = 0; i < N; i++) {
                if(board[i][j] == 0) continue;

                int current = board[i][j];
                board[i][j] = 0;

                if(prev == current) {
                    board[index - 1][j] = current * 2;
                    prev = 0;
                } else {
                    board[index][j] = current;
                    prev = current;
                    index++;
                }
            }
        }
    }

    static void moveDown(int[][] board) {
        for(int j = 0; j < N; j++) {
            int index = N - 1;
            int prev = 0;
            
            for(int i = N - 1; i >= 0; i--) {
                if(board[i][j] == 0) continue;

                int current = board[i][j];
                board[i][j] = 0;

                if(prev == current) {
                    board[index + 1][j] = current * 2;
                    prev = 0;
                } else {
                    board[index][j] = current;
                    prev = current;
                    index--;
                }
            }
        }
    }

    static void moveLeft(int[][] board) {
        for(int i = 0; i < N; i++) {
            int index = 0;
            int prev = 0;
            
            for(int j = 0; j < N; j++) {
                if(board[i][j] == 0) continue;

                int current = board[i][j];
                board[i][j] = 0;

                if(prev == current) {
                    board[i][index - 1] = current * 2;
                    prev = 0;
                } else {
                    board[i][index] = current;
                    prev = current;
                    index++;
                }
            }
        }
    }

    static void moveRight(int[][] board) {
        for(int i = 0; i < N; i++) {
            int index = N - 1;
            int prev = 0;
            
            for(int j = N - 1; j >= 0; j--) {
                if(board[i][j] == 0) continue;

                int current = board[i][j];
                board[i][j] = 0;

                if(prev == current) {
                    board[i][index + 1] = current * 2;
                    prev = 0;
                } else {
                    board[i][index] = current;
                    prev = current;
                    index--;
                }
            }
        }
    }

    static void findMax(int[][] board) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
    }

    // 보드 복사
    static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }
}