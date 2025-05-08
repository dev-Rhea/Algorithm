package implementation.BOJ_4347;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static char[][] map = new char[3][3];
    static int[] dy = {1, 0, -1, 0, 1, 1, -1, -1};
    static int[] dx = {0, 1, 0, -1, -1, 1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            int cntX = 0;
            int cntO = 0;
            String input = br.readLine();
            if(input != null && input.isEmpty()) input = br.readLine();
            for(int i=0;i<3;i++) {
                while(input == null || input.isEmpty()) input = br.readLine();
                map[i] = input.toCharArray();

                for(char c : map[i]) {
                    if(c == 'X') cntX++;
                    else if(c == 'O') cntO++;
                }
                if(i < 2) input = br.readLine();
            }

            System.out.println(valid(cntX, cntO) ? "yes" : "no");
        }
    }

    static boolean valid(int x, int o) {
        if(!(x == o || x == o + 1)) return false;

        boolean winX = isWin('X');
        boolean winO = isWin('O');

        if(winX && winO) return false;
        if(winX) return x == o + 1;
        if(winO) return x == o;

        return true;
    }

    static boolean isWin(char c) {
        for(int i=0;i<3;i++) {
            if(map[i][0] == c && map[i][1] == c && map[i][2] == c) return true;
        }

        for(int j=0;j<3;j++) {
            if(map[0][j] == c && map[1][j] == c && map[2][j] == c) return true;
        }

        if(map[0][0] == c && map[1][1] == c && map[2][2] == c) return true;

        if(map[0][2] == c && map[1][1] == c && map[2][0] == c) return true;

        return false;
    }
}