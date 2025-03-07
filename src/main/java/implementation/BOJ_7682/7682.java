package implementation.BOJ_7682;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static char[][] game;
    static int X, O;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = "";

        while(!(input = br.readLine()).equals("end")) {
            game = new char[3][3];
            X = 0; O = 0;

            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++) {
                    game[i][j] = input.charAt(3*i+j); // 1차원 배열을 2차원 배열로 변환 (3x3)

                    if(game[i][j] == 'X') X++;
                    else if(game[i][j] == 'O') O++;
                }
            }

            if(O + X == 9 && X - O == 1) { // 모든 칸이 주어진 조건대로 채워진 경우
                if(tictactoe('X') && tictactoe('O')) sb.append("invalid\n"); // 동시 승리
                else if(tictactoe('O')) sb.append("invalid\n"); // O 가 승리했는데 X 가 더 많은 경우
                else sb.append("valid\n"); // 위의 두 조건을 만족하지 않으면 유효함
            }
            else { // 아직 게임이 끝나지 않은 경우 
                if(tictactoe('X') && tictactoe('O')) sb.append("invalid\n"); // 동시 승리 
                else if(tictactoe('O') && X == O) sb.append("valid\n"); // O 가 승리했는데 X 와 O 의 개수가 같은 경우
                else if(tictactoe('X') && X - O == 1) sb.append("valid\n"); // X 가 승리했는데 X 가 O 보다 1개 더 많은 경우
                else sb.append("invalid\n"); // 위의 조건을 만족하지 않으면 유효하지 않음 
            }
        }
        System.out.println(sb);
    }

    static boolean tictactoe(char c) {
        // 행 체크 
        for(int i=0;i<3;i++) {
            int cnt = 0;
            for(int j=0;j<3;j++) {
                if(game[i][j] == c) cnt++;
            }
            if(cnt == 3) return true; // 한 행이 같은 문자면 승리 
        }

        // 열 체크 
        for(int i=0;i<3;i++) {
            int cnt = 0;
            for(int j=0;j<3;j++) {
                if(game[j][i] == c) cnt++;
            }
            if(cnt == 3) return true; // 한 열이 같은 문자면 승리 
        }

        // 대각선 체크 
        if(game[0][0] == c && game[1][1] == c && game[2][2] == c) return true;
        if(game[0][2] == c && game[1][1] == c && game[2][0] == c) return true;

        return false;
    }
}