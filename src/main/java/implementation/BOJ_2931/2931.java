package implementation.BOJ_2931;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int R, C;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};  // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R+1][C+1];

        for(int i=1;i<=R;i++) {
            String input = br.readLine();
            for(int j=1;j<=C;j++) {
                map[i][j] = input.charAt(j-1);
            }
        }

        for(int i=1;i<=R;i++) {
            for(int j=1;j<=C;j++) {
                if(map[i][j] == '.') {
                    boolean[] connect = new boolean[4];

                    for(int d=0;d<4;d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if(nr < 1 || nr > R || nc < 1 || nc > C) continue;

                        char block = map[nr][nc];
                        int oppositeDir = getOpposite(d);
                        if(canConnect(block, oppositeDir)) {
                            connect[d] = true;
                        }
                    }

                    int cnt = 0;
                    for(boolean t : connect) {
                        if(t) cnt++;
                    }

                    if(cnt >= 2) {
                        System.out.print(i + " " + j + " ");
                        System.out.println(find(connect));
                        return;
                    }
                }
            }
        }
    }

    static int getOpposite(int d) {
        if(d == 0) return 1;
        if(d == 1) return 0;
        if(d == 2) return 3;
        return 2;
    }

    static boolean canConnect(char block, int d) {
        switch(block) {
            case '|': return d == 0 || d == 1;
            case '-': return d == 2 || d == 3;
            case '+': return true;
            case '1': return d == 1 || d == 3;
            case '2': return d == 0 || d == 3;
            case '3': return d == 0 || d == 2;
            case '4': return d == 1 || d == 2;
            case 'M': case 'Z': return true;
            default: return false;
        }
    }

    static char find(boolean[] connect) {
        boolean up = connect[0];
        boolean down = connect[1];
        boolean l = connect[2];
        boolean r = connect[3];

        if(up && down && l && r) return '+';
        if(up && down && !l && !r) return '|';
        if(!up && !down && l && r) return '-';
        if(!up && down && !l && r) return '1';
        if(up && !down && !l && r) return '2';
        if(up && !down && l && !r) return '3';
        if(!up && down && l && !r) return '4';
        
        return '.';
    }
}