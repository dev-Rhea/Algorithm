package implementation.BOJ_15685;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static boolean[][] map = new boolean[101][101];
    static List<Integer> dir;
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dir = new LinkedList<>();
            rotation(d, g);
            draw(x, y);
        }

        int cnt = 0;

        for(int i=0;i<100;i++) {
            for(int j=0;j<100;j++) {
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void rotation(int d, int g) {
        dir.add(d);

        for(int i=1;i<=g;i++) {
            for(int j=dir.size()-1;j>=0;j--) {
                dir.add((dir.get(j) + 1)%4);
            }
        }
    }

    static void draw(int x, int y) {
        map[x][y] = true;

        for(int i=0;i<dir.size();i++) {
            int d = dir.get(i);

            x += dx[d];
            y += dy[d];

            map[x][y] = true;
        }
    }
} 