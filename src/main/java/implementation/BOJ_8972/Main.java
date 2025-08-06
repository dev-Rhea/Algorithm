package implementation.BOJ_8972;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int R; 
    static int C;
    static int[][] arr; 
    static String move; 

    static int jx;
    static int jy;
    static int moveCnt;

    static int[] dx = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
    static int[] dy = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char input = line.charAt(j);
                if (input == '.') continue;
                else if (input == 'I') {
                    arr[i][j] = 1;
                    jx = i; jy = j;
                }
                else if (input == 'R') arr[i][j] = 2;
            }       
        }

        move = br.readLine();

        start();

        if (moveCnt < move.length()) {
            sb.append("kraj ").append(moveCnt + 1);
        }
        else {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (arr[i][j] == 1) sb.append("I");
                    else if (arr[i][j] == 2) sb.append("R");
                    else sb.append(".");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }


    static void start() {
        for (moveCnt = 0; moveCnt < move.length(); moveCnt++) {
            int[][] moveArr = new int[R][C];

            int dir = move.charAt(moveCnt) - '0';
            jx += dx[dir];
            jy += dy[dir];

            if (arr[jx][jy] == 2) return; 
            moveArr[jx][jy] = 1;
            
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (arr[i][j] != 2) continue;

                    int mx = i;
                    int my = j;

                    if (mx > jx) mx--;
                    else if (mx < jx) mx++;
                    if (my > jy) my--;
                    else if (my < jy) my++;
                    
                    if (moveArr[mx][my] == 1) return;
                    else if (moveArr[mx][my] >= 2) moveArr[mx][my] = 3;
                    else moveArr[mx][my] = 2;
                }
            }

            arr = moveArr;
        }
    }
}