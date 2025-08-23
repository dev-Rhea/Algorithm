package bruteforce.BOJ_2210;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {

    static int[][] map = new int[5][5];
    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        set = new HashSet<>();

        for(int i=0;i<5;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                makeNumber(String.valueOf(map[i][j]), i, j);
            }
        }

        System.out.println(set.size());
    }

    static void makeNumber(String num, int x, int y) {
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        if(num.length() == 6) {
            set.add(num);
            return;
        }

        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < 5 && ny >= 0 && ny < 5) makeNumber(num + map[nx][ny], nx, ny);
        }
    }
}